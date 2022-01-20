import org.javatuples.Unit;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

class Pair<L, R> {

    private final L left;
    private final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pairo = (Pair<?, ?>) o;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }

}

public class OrderBookEngine {

    private ConcurrentMap<Double, Integer> bidOffers = new ConcurrentSkipListMap<>(Comparator.reverseOrder());//Buy orders sorted in descending order.
    private Map<Double, Integer> askOffers = new ConcurrentSkipListMap<>(Comparator.naturalOrder());
    private java.util.Queue<Pair<Double, Integer>> executedOrders = new LinkedBlockingQueue<>();

    public static void main(String args[]) {
        OrderBookEngine engine = new OrderBookEngine();

        engine.receiveBuyOrder(97, 1000);
        engine.receiveBuyOrder(97, 1000);
        engine.receiveBuyOrder(97, 1000);
        engine.receiveSellOrder(99, 2500);
        engine.receiveSellOrder(100, 2500);
        engine.receiveBuyOrder(100, 1000);
        engine.receiveSellOrder(101, 1000);
        engine.receiveBuyOrder(102, 5000);
        engine.receiveSellOrder(97, 3000);

        engine.printExecutedBook();
        //Executors.newScheduledThreadPool(10).scheduleAtFixedRate(()->engine.receiveOrder(97+ ThreadLocalRandom.current().nextInt(5),1000,true),0,3, TimeUnit.SECONDS);
        // Executors.newScheduledThreadPool(10).scheduleAtFixedRate(()->engine.receiveOrder(100+ThreadLocalRandom.current().nextInt(5),1000,false),0,3, TimeUnit.SECONDS);


        // Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(()->engine.printOrderBook(),0,5,TimeUnit.SECONDS);
        // Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(()->engine.printExecutedBook(),0,10,TimeUnit.SECONDS);


    }

    public void printExecutedBook() {
        if (!executedOrders.isEmpty()) {
            System.out.println("");
            System.out.println("                                ____ EXECUTED ORDERS ____ ");
            for (Pair<Double, Integer> pair : executedOrders) {
                System.out.println("                             " + pair.getRight() + " @ " + pair.getLeft());
            }
            System.out.println("                                ______________ ");
        }
    }

    public void printOrderBook() {
        System.out.println("___________ORDER BOOK______________");
        Map<Double, Integer> sortedMap = new TreeMap<>(Collections.reverseOrder());
        sortedMap.putAll(askOffers);
        Set<Double> ask_prices = sortedMap.keySet();
        for (double ask_price : ask_prices) {
            System.out.println("              " + ask_price + " " + askOffers.get(ask_price));
        }
        Set<Double> bid_prices = bidOffers.keySet();
        List<Double> bid_prices_list = new ArrayList<>(bid_prices);
        for (Double bid_price : bid_prices_list) {
            System.out.println(bidOffers.get(bid_price) + " " + bid_price);
        }
    }

    public void receiveBuyOrder(double price, int quantity) {
        System.out.println("Recvd buy " + quantity + "@" + price);
        for (double ask_price : new ArrayList<>(askOffers.keySet())) {
            if (quantity > 0 && price >= ask_price) {
                int ask_quantity = askOffers.get(ask_price);
                if (quantity >= ask_quantity) {
                    quantity = quantity - ask_quantity;
                    removeAskOrder(ask_price, ask_quantity);
                    addExecutedOrder(ask_price, ask_quantity, "Adding execution for buy order --Sell order executed ");
                    addExecutedOrder(ask_price, ask_quantity, "Adding execution for buy order --Buy order executed ");
                } else {
                    removeAskOrder(ask_price, quantity);
                    addExecutedOrder(ask_price, quantity, "Adding execution for buy order --Sell order executed ");
                    addExecutedOrder(ask_price, quantity, "Adding execution for buy order --Buy order executed ");
                    quantity = 0;
                }
            }
        }
        if (quantity > 0) {
            addBidOrder(price, quantity);
        }
        printOrderBook();
    }

    public void receiveSellOrder(double price, int quantity) {
        System.out.println("Recvd sell " + quantity + "@" + price);
        for (double bid_price : new ArrayList<>(bidOffers.keySet())) {
            if (quantity > 0 && price <= bid_price) {
                int bid_quantity = bidOffers.get(bid_price);
                if (quantity >= bid_quantity) {
                    quantity = quantity - bid_quantity;
                    removeBidOrder(bid_price, bid_quantity);
                    addExecutedOrder(bid_price, bid_quantity, "Adding execution for sell order-- Buy order executed ");
                    addExecutedOrder(bid_price, bid_quantity, "Adding execution for sell order --Sell order executed ");

                } else {
                    removeBidOrder(bid_price, quantity);
                    quantity = 0;
                    addExecutedOrder(bid_price, quantity, "Adding execution for sell order-- Buy order executed ");
                    addExecutedOrder(bid_price, quantity, "Adding execution for sell order --Sell order executed ");
                }
            }
        }
        if (quantity > 0) {
            addAskOffer(price, quantity);
        }
        printOrderBook();
    }

    private void addExecutedOrder(double bid_price, int bid_quantity, String s) {
        System.out.println(s + bid_quantity + "@" + bid_price);
        executedOrders.add(new Pair<>(bid_price, bid_quantity));
    }

    public void addBidOrder(double price, int quantity) {
        System.out.println("Adding to bid side book " + quantity + "@" + price);
/*        if (bidOffers.containsKey(price))
            bidOffers.put(price, bidOffers.get(price) + quantity);
        else
            bidOffers.put(price, quantity);*/
        bidOffers.computeIfPresent(price, (k, v) -> v + quantity);
        bidOffers.putIfAbsent(price, quantity);
    }

    public void removeBidOrder(double price, int quantity) {
        System.out.println("Removing bid order " + quantity + "@" + price);
/*     *//*   int lastQuantity = bidOffers.get(price);
        if (lastQuantity == quantity) {
            bidOffers.remove(price);
        } else {
            bidOffers.put(price, lastQuantity - quantity);
        }*//*
        synchronized (this) {
            if (bidOffers.get(price) == quantity) {
                bidOffers.remove(price);
            }
        }*/
        bidOffers.keySet().removeIf(e-> bidOffers.get(e)==quantity);
        bidOffers.computeIfPresent(price, (k, v) -> v - quantity);
    }

        public synchronized void addAskOffer ( double price, int quantity){
            System.out.println("Adding to ask side book " + quantity + "@" + price);
            if (askOffers.containsKey(price))
                askOffers.put(price, askOffers.get(price) + quantity);
            else
                askOffers.put(price, quantity);
        }

        public int getBidQuantity ( double bestPrice){
            int bidQuantity = 0;
            for (double price : bidOffers.keySet()) {
                if (price > bestPrice) {
                    bidQuantity += bidOffers.get(price);
                }
            }
            return bidQuantity;
        }

        public int getAskQuantity ( double bestPrice){
            int askQuantity = 0;
            for (double price : askOffers.keySet()) {
                if (price < bestPrice) {
                    askQuantity += askOffers.get(price);
                }
            }
            return askQuantity;
        }


        public synchronized void removeAskOrder ( double price, int quantity){
            System.out.println("Removing ask order " + quantity + "@" + price);
/*            int lastQuantity = askOffers.get(price);
            if (lastQuantity == quantity) {
                askOffers.remove(price);
            } else {
                askOffers.put(price, lastQuantity - quantity);
            }*/
            askOffers.keySet().removeIf(e-> askOffers.get(e)==quantity);
           // askOffers.computeIfPresent(price, (k, v) -> v - quantity);
            askOffers.merge(price,askOffers.get(price),(k,v)->askOffers.get(price)-quantity);
        }

        public void reset () {
            System.out.println("size ask = " + askOffers.size());
            System.out.println("size bid = " + bidOffers.size());
            System.out.println("executed orders = " + executedOrders.size());
            askOffers.clear();
            bidOffers.clear();
            executedOrders.clear();
        }
    }
