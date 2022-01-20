import java.util.Map;
import java.util.concurrent.*;

class SessionAndClOrdId {
    public final Integer x;
    public final Integer y;

    public SessionAndClOrdId(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SessionAndClOrdId other = (SessionAndClOrdId) obj;
        if (x == null) {
            if (other.x != null)
                return false;
        } else if (!x.equals(other.x))
            return false;

        if (y == null) {
            if (other.y != null)
                return false;
        } else if (!y.equals(other.y))
            return false;
        return true;
    }
}

class Order {
    int clOrdId;
    int origClOrdId;
    String symbol;
    int qty;
    double price;
    char side;

    Order(int clOrdId, String symbol, int qty, double price, char side) {
        this.clOrdId = clOrdId;
        this.symbol = symbol;
        this.qty = qty;
        this.price = price;
        this.side = side;
    }

    Order(int clOrdId, int origClOrdId, String symbol, int qty, double price, char side) {
        this.clOrdId = clOrdId;
        this.origClOrdId = origClOrdId;
        this.symbol = symbol;
        this.qty = qty;
        this.price = price;
        this.side = side;
    }

    @Override
    public String toString() {
        return "Order [clOrdId=" + clOrdId + ", origClOrdId=" + origClOrdId + ", symbol=" + symbol + ", qty=" + qty
                + ", price=" + price + ", side=" + side + "]";
    }
}

class Message {
    int sessionId;
    Order order;

    Message(int sessionId, Order order) {
        this.sessionId = sessionId;
        this.order = order;
    }

    @Override
    public String toString() {
        return "Message [sessionId=" + sessionId + ", order=" + order + "]";
    }
}

/*
 * Different clients can submit different messages 1. Each message has a
 * sessionId and a string msg 2. Each of the messages on a particular session
 * need to be processed sequentially
 */
public class ConcurrencyPattern {
    private Map<Integer, BlockingDeque<Runnable>> pendingTasks = new ConcurrentHashMap<>();
    private OrderProcessor orderProcessor = new OrderProcessor();
    private ExecutorService executorService = Executors.newFixedThreadPool(13);

    public static void main(String args[]) {
        ConcurrencyPattern c = new ConcurrencyPattern();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                c.submitMsg(new Message(i, new Order(10, "0001.HK", 2000 * i, 200 + i, 'B')));
                c.submitMsg(new Message(i, new Order(11, 10, "0001.HK", 1000 * i, 200 + i, 'B')));
                c.submitMsg(new Message(i, new Order(12, 11, "0001.HK", 2000 * i, 201 + i, 'B')));
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                c.submitMsg(new Message(i, new Order(10, "0001.HK", 2000 * i, 200 + i, 'S')));
                c.submitMsg(new Message(i, new Order(11, 10, "0001.HK", 1000 * i, 200 + i, 'S')));
                c.submitMsg(new Message(i, new Order(12, 11, "0001.HK", 2000 * i, 201 + i, 'S')));
            }
        }).start();
    }

    public void submitMsg(final Message msg) {
        submitTask(msg, () -> orderProcessor.processMsg(msg));
    }

    private void submitTask(final Message msg, Runnable task) {
        if (pendingTasks.containsKey(msg.sessionId)) {
            pendingTasks.get(msg.sessionId).add(task);
            return;
        }
        BlockingDeque<Runnable> pendingTasksPerSession = new LinkedBlockingDeque<Runnable>();
        pendingTasksPerSession.push(task);
        pendingTasks.put(msg.sessionId, pendingTasksPerSession);
        executorService.submit(new SynchronizedTask(msg, task));
    }

    public class SynchronizedTask implements Runnable {
        Message msg;
        Runnable task;

        public SynchronizedTask(Message msg, Runnable task) {
            this.msg = msg;
            this.task = task;
        }

        public void run() {
            task.run();
            BlockingDeque<Runnable> pendingTasksForSession = pendingTasks.get(msg.sessionId);
            if (!pendingTasksForSession.remove(task)) {
                return;
            }
            if (!pendingTasksForSession.isEmpty())
                executorService.submit(new SynchronizedTask(msg, pendingTasksForSession.getFirst()));
        }
    }

    public class OrderProcessor {
        private Map<SessionAndClOrdId, Order> orderMap = new ConcurrentHashMap<>();

        private void processMsg(Message msg) {
            if (msg.order.origClOrdId == 0) {
                print("Received new order msg " + msg);
                sleep(1000);
            } else {
                SessionAndClOrdId key = new SessionAndClOrdId(msg.sessionId, msg.order.origClOrdId);
                if (orderMap.containsKey(key)) {
                    if (isOnlyAggressivePriceMod(msg, key)) {
                        print("Aggressive price modification " + msg);
                    } else if (isOnlyQtyModUp(msg, key)) {
                        print(" Quantity modification up only for " + msg);
                    } else if (isOnlyQtyDown(msg, key)) {
                        print(" Quantity modification down only for " + msg);
                    }
                }
            }
            orderMap.put(new SessionAndClOrdId(msg.sessionId, msg.order.clOrdId), msg.order);
        }

        private boolean isOnlyQtyDown(final Message msg, SessionAndClOrdId key) {
            return msg.order.qty < orderMap.get(key).qty && Double.compare(msg.order.price, orderMap.get(key).price) == 0;
        }

        private boolean isOnlyQtyModUp(final Message msg, SessionAndClOrdId key) {
            return msg.order.qty > orderMap.get(key).qty && Double.compare(msg.order.price, orderMap.get(key).price) == 0;
        }

        private boolean isOnlyAggressivePriceMod(final Message msg, SessionAndClOrdId key) {
            return ((Double.compare(msg.order.price, orderMap.get(key).price) > 0 && msg.order.side == 'B') || (Double.compare(msg.order.price, orderMap.get(key).price) < 0 && msg.order.side == 'S'))
                    && Double.compare(msg.order.qty, orderMap.get(key).qty) == 0;
        }

        private void sleep(int time) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void print(String msg) {
            System.out.println(msg);
        }
    }
}