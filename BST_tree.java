package Trees;

public class BST_tree {
    public static void main(String[] args) {
        BST tree = new BST();

        // Insert nodes
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Inorder traversal
        System.out.println("Inorder traversal:");
        tree.inorder();

        // Preorder traversal
        System.out.println("\nPreorder traversal:");
        tree.preorder();

        // Postorder traversal
        System.out.println("\nPostorder traversal:");
        tree.postorder();

        // Search for a node
        System.out.println("\nSearch for 40:");
        System.out.println(tree.search(40) ? "Found" : "Not Found");

        // Delete a node
        System.out.println("\nDelete 20:");
        tree.delete(20);
        System.out.println("Inorder traversal after deletion:");
        tree.inorder();
    }

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    static class BST {
        Node root;

        public BST() {
            root = null;
        }

        // Insert a node
        public void insert(int data) 
        {
            root = insertRec(root, data);
        }

        private Node insertRec(Node root, int data) 
        {
            if (root == null) 
            {
                root = new Node(data);
                return root;
            }
            if (data < root.data) 
            {
                root.left = insertRec(root.left, data);
            } 
            else if (data > root.data) 
            {
                root.right = insertRec(root.right, data);
            }
            return root;
        }
//********************************************************************************************************* */
        // Delete a node
        public void delete(int data) {
            root = deleteRec(root, data);
        }

        private Node deleteRec(Node root, int data) {
            //Case1 : if the deletion Node is a leaf node
            if (root == null) return root;
            
            if (data < root.data) 
            {
                root.left = deleteRec(root.left, data);
            }
             else if (data > root.data) 
            {
                root.right = deleteRec(root.right, data);
            } 
            //Case2 : if the deletion Node conatins one child
            else 
            {
                if (root.left == null) return root.right;// makes the direct connection from grand parent to right child
                else if (root.right == null) return root.left;// makes the direct connection from grand parent to left child
            //Case3 : if the deletion Node contains two child
                root.data = minValue(root.right);//finds the most left Node from the right subtree(i.e. left value from the right half) and replace the root Node with minvalue
                root.right = deleteRec(root.right, root.data);//after replacing minvalue in place of deltion parent node delete the min value. 
            }
            return root;
        }

        private int minValue(Node root) {
            int minv = root.data;
            while (root.left != null) {
                minv = root.left.data;//stores the left node
                root = root.left;//iterate through the left node
            }
            return minv;
        }
/***************************************************************************************************************************** */
        // Search for a node
        public boolean search(int data) 
        {
            return searchRec(root, data);
        }

        private boolean searchRec(Node root, int data) 
        {
            if (root == null) return false;
            if (root.data == data) return true;
            return data < root.data ? searchRec(root.left, data) : searchRec(root.right, data);
        }
/*************************************************************************************************** */
        // Inorder traversal
        public void inorder() {
            inorderRec(root);
        }

        private void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.left);
                System.out.print(root.data + " ");
                inorderRec(root.right);
            }
        }

        // Preorder traversal
        public void preorder() {
            preorderRec(root);
        }

        private void preorderRec(Node root) {
            if (root != null) {
                System.out.print(root.data + " ");
                preorderRec(root.left);
                preorderRec(root.right);
            }
        }

        // Postorder traversal
        public void postorder() {
            postorderRec(root);
        }

        private void postorderRec(Node root) {
            if (root != null) {
                postorderRec(root.left);
                postorderRec(root.right);
                System.out.print(root.data + " ");
            }
        }
    }
}
