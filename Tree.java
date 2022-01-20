import java.util.Stack;

class TreeNode {
    public int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

public class Tree {
    int minimumElement(TreeNode root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    TreeNode deleteNode(TreeNode root, int value) {
        if (root == null)
            return null;
        if (root.data > value) {
            root.left = deleteNode(root.left, value);
        } else if (root.data < value) {
            root.right = deleteNode(root.right, value);
        } else {
            if (root.left != null && root.right != null) {
                TreeNode temp = root;
                root.data = minimumElement(temp.right);
                deleteNode(root.right, root.data);
            } else if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else
                root = null;
        }
        return root;
    }

    void spiralOrZigzagLevelOrder(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        boolean directionflag = false;
        while (!stack.isEmpty()) {
            Stack<TreeNode> tempStack = new Stack<TreeNode>();

            while (!stack.isEmpty()) {
                TreeNode tempNode = stack.pop();
                System.out.printf("%d ", tempNode.data);
                if (!directionflag) {
                    if (tempNode.left != null)
                        tempStack.push(tempNode.left);
                    if (tempNode.right != null)
                        tempStack.push(tempNode.right);
                } else {
                    if (tempNode.right != null)
                        tempStack.push(tempNode.right);
                    if (tempNode.left != null)
                        tempStack.push(tempNode.left);
                }
            }
            directionflag = !directionflag;
            stack = tempStack;
        }
    }
}
