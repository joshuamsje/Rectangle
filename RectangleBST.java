package group1;

import java.util.Stack;

// import binarySearchTree.BinaryNode;

/**
 * This class is an implementation of a binary search tree that stores
 * rectangles and contains functions that would do a specfic task with given
 * parameters like removing a rectangle by its name or dimensions.
 * 
 * @author Kurt Karpin (kkarp9)
 * @author Joshua Mathew (josh827)
 * @param <T>
 * 
 * @version 2020.2.17
 */
public class RectangleBST<T> {
    private BinaryNode<Rectangle> root;
    private int size;


    /**
     * BST Iterator class to add the nodes to a stack
     * data structure and iterate through the tree
     * in in order traversal.
     * 
     * @author Kurt Karpin (kkarp9)
     * @author Joshua Mathew (josh827)
     *
     */
    public class BSTIterator {

        // creates a stack that handles BinaryNode<rectangle>
        // type
        private Stack<BinaryNode<Rectangle>> holder;


        /**
         * BST Iterator constructor where the root is
         * pushed to the very left node of the tree for in order traversal
         * and the stack is initialized with the nodes getting
         * pushed to the stack.
         * 
         * @param root
         *            the head node of the tree
         */
        public BSTIterator(BinaryNode<Rectangle> root) {
            // initializes the stack
            holder = new Stack<BinaryNode<Rectangle>>();

            // moves the iterator to the leftmost node of the
            // tree
            while (root != null) {
                holder.push(root);
                root = root.getLeft();
            }
        }


        /**
         * hasNext method to see if the tree has been traversed through
         * 
         * @return boolean true or false
         */
        public boolean hasNext() {
            // sees if if the stack has nodes in nit
            return !holder.isEmpty();
        }


        /**
         * getNext method to get the next node's rectangle in the traversal
         * 
         * @return Rectangle element in the stack
         */
        public Rectangle getNext() {
            // gets the top of the stack which is the leftmost node
            // pushed to the stack
            BinaryNode<Rectangle> parent = holder.pop();

            // gets the first right node on the left side on the tree
            BinaryNode<Rectangle> rightNode = parent.getRight();

            // makes sure rightNode isn't null and goes through
            // in order traversal
            while (rightNode != null) {

                // pushes the rightNode to the stack
                // and then moves the rightNode to the left
                holder.push(rightNode);
                rightNode = rightNode.getLeft();
            }
            // returns the rectangle that was at the top of the stack
            return parent.getElement();
        }

    }


    /**
     * BST Constructor that sets the root node to null and size to 0.
     */
    public RectangleBST() {
        root = null;
        size = 0;
    }


    /**
     * Attempts to insert a rectangle into the tree based on different
     * conditions. More detail on it is in the
     * helper function.
     * 
     * @param rectangle
     *            to insert
     * @return boolean true or false if insertion succeeds or fails
     */
    public boolean insert(Rectangle rectangle) {
        // gets the size of the BST
        int currSize = getSize();

        // calls the helper method to try and insert a rectangle at the root
        // node
        root = insert(rectangle, root);

        return (currSize == getSize());
    }


    /**
     * Attempts to remove a rectangle from the tree if they share the same
     * name with the given parameter.
     * 
     * @param name
     *            of the rectangle to remove
     * @return boolean true or false if removed properly or not
     */
    public boolean remove(String name) {

        // gets the size of the bst and tries to remove a rectangle with it's
        // given name at the root node
        int currSize = getSize();
        root = removeName(name, root);

        return (currSize != getSize());
    }


    /**
     * Attempts to remove a rectangle from the tree if they share
     * the same x and y coordinates as well as the same
     * width and height with the given parameter.
     * 
     * @param x
     *            coordinate
     * @param y
     *            coordinate
     * @param width
     *            length
     * @param height
     *            length
     * @return boolean true or false if removing was successful or not
     */
    public boolean remove(int x, int y, int width, int height) {

        // gets the size of the bst and makes a new rectangle for comparsion
        // with another rectangle to see if it should be removed based on its
        // parameters
        int currSize = getSize();
        Rectangle compare = new Rectangle("Compare", x, y, width, height);

        // new root after the remove helper is called
        root = removeDimension(compare, root);

        // this means no nodes were removed
        return (currSize != getSize());
    }


    /**
     * Searches for all rectangles that are within the boundaries of the given
     * region on the 1024 by 1024 grid.
     * 
     * @param x
     *            coordinate
     * @param y
     *            coordinate
     * @param width
     *            of x
     * @param height
     *            of y
     * @return String a report of rectangles
     *         that intersects the given region on the grid
     */
    public String regionsearch(int x, int y, int width, int height) {

        // creates a stringbuilder to append results in
        StringBuilder builder = new StringBuilder();

        // sees if regionsearch didn't return any rectangles for that region
        if (regionsearch(new Rectangle("search", x, y, width, height), root)
            .equals("")) {
            return "";

        }

        // adds the rectangles that were found into the builder
        builder.append("Rectangles intersecting region (" + x + ", " + y + ", "
            + width + ", " + height + "):\n");

        builder.append(regionsearch(new Rectangle("search", x, y, width,
            height), root));

        return builder.toString();
    }


    /**
     * Prints an In-Order string variant of all
     * rectangles in this tree with showing each node and its content and depth
     * and ends with telling us the size aka number of nodes.
     * 
     * @return string representation of binary search tree
     */
    public String dump() {
        StringBuilder build = new StringBuilder();

        // checks to see if the root is null which means nothing was added to
        // the BST
        if (root == null) {
            build.append("Node has depth 0, Value (null) \n");
            build.append("BST size is: 0");

            return ("BST dump:\n" + build.toString());
        }

        // otherwise call the inOrder string method to print out the dump
        // correctly with the size
        return ("BST dump:\n" + inOrderString(root, 0) + "BST size is: " + this
            .getSize());
    }


    /**
     * Searches for all rectangles to
     * return their information that share the name
     * given in the parameter.
     * 
     * @param name
     *            of rectangle
     * @return String if rectangle was found
     */
    public String search(String name) {
        // if nothing prints out, this means no rectangles
        String result = search(name, root);
        // were found that shares the given name
        if (result.isEmpty()) {
            return ("Rectangle not found: " + name + "\n");
        }
        return result;
    }


    /**
     * Checks if the tree is empty --> checking if root null helps determine it.
     * 
     * @return root == null
     */
    public boolean isEmpty() {
        return (root == null);
    }


    /**
     * Reports all pairs of rectangles within
     * the tree that intersect each other.
     * 
     * @return String pairs of rectangles that intersect with each other in
     *         the tree
     */
    public String intersections() {

        StringBuilder builder = new StringBuilder();
        //checks to see if the root was null meaning nothing was added to the tree
        if (root == null) {
            return builder.toString();
        }

        //calls intersections helper method at the root node
        builder.append(intersections1(root));
        return builder.toString();
    }


    /**
     * Inserts a rectangle into the binary tree.
     * There are restrictions to consider if the rectangle
     * can be inserted or not: the name must begin with a letter,
     * its height and width must be greater than 0, and they must fit within the
     * 1024 x 1024 units world box where the upper left corner is (0, 0)
     * 
     * @param rectangle
     * @param node
     * @return new root of the subtree
     */
    private BinaryNode<Rectangle> insert(
        Rectangle rectangle,
        BinaryNode<Rectangle> node) {

        boolean dontInsert = (rectangle.getWidth() <= 0 || rectangle
            .getHeight() <= 0 || rectangle.getXCoordinate() < 0 || rectangle
                .getYCoordinate() < 0 || rectangle.getXMax() > 1024 || rectangle
                    .getYMax() > 1024);
        if (!dontInsert) {
            // check if they are out of the box
            if (node == null) {
                size++;
                return new BinaryNode<Rectangle>(rectangle);
            }
            else if (rectangle.compareTo(node.getElement(), true) <= 0) {
                node.setLeft(insert(rectangle, node.getLeft()));
                // same names goes to the left node
            }
            else if (rectangle.compareTo(node.getElement(), true) > 0) {
                node.setRight(insert(rectangle, node.getRight()));
            }
        }
        return node;
    }


    /**
     * Attempts to remove the node from the tree with the given name
     * 
     * @param name
     *            the key to remove nodes that share this
     * @param node
     *            node to check through
     * @return BinaryNode
     */
    private BinaryNode<Rectangle> removeName(
        String name,
        BinaryNode<Rectangle> node) {
        // If there's no more subtree to examine
        if (node == null) {
            return node;
        }

        // if value should be to the left of the root
        if (name.compareTo(node.getElement().getName()) < 0) {
            node.setLeft(removeName(name, node.getLeft()));
        }
        // if value should be to the right of the root
        else if (name.compareTo(node.getElement().getName()) > 0) {
            node.setRight(removeName(name, node.getRight()));
        }
        // If value is on the current node
        else {
            size--;
            // If there are two children
            if (node.getLeft() != null && node.getRight() != null) {
                node.setElement(minValue(node.getRight()));
                size++;

                node.setRight(removeName(node.getElement().getName(), node
                    .getRight()));
            }
            // If there is only one child on the left
            else if (node.getLeft() != null) {
                return node.getLeft();
            }
            // If there is only one child on the right
            else {
                return node.getRight();
            }
        }
        return node;
    }


    /**
     * Helper method that returns the minimum value
     * of the given root of a binary tree
     * 
     * @param node
     * @return minimum value of tree
     */
    private Rectangle minValue(BinaryNode<Rectangle> node) {
        Rectangle minName = node.getElement();
        while (node.getLeft() != null) {
            minName = node.getLeft().getElement();
            node = node.getLeft();
        }
        return minName;
    }


    /**
     * Attempts to remove a rectangle by dimensions. Here we create a rectangle
     * because the name is independent of determining if a rectangle should be
     * removed and only check the 4 dimensions/lengths
     * 
     * @param name
     * @param node
     * @return BinaryNode
     */
    private BinaryNode<Rectangle> removeDimension(
        Rectangle compare,
        BinaryNode<Rectangle> node) {

        if (node == null) {
            return null;
        }
        // finds the dimensions
        else if (compare.compareTo(node.getElement(), false) == 0) {
            size--;
            // If there are two children
            if (node.getLeft() != null && node.getRight() != null) {
                node.setElement(minValue(node.getRight()));

                // increment because we first set
                // the element to be replaced than
                size++;

                // proceed to delete the replacing
                // node from its original position
                node.setRight(removeDimension(node.getElement(), node
                    .getRight()));
            }
            // If there is only one child on the left
            else if (node.getLeft() != null) {
                return node.getLeft();
            }
            // If there is only one child on the right
            else {
                return node.getRight();
            }
        }
        else {
            if (node.getLeft() != null) {
                node.setLeft(removeDimension(compare, node.getLeft()));
            }
            if (node.getRight() != null) {
                node.setRight(removeDimension(compare, node.getRight()));
            }
        }
        return node;
    }


    /**
     * Regionsearch checks all rectangles
     * if they are within the boundaries of the given rectangle with
     * dimensions. It will return a string representation of all rectangles
     * that are within boundaries of the given rectangle dimensions.
     * 
     * @param rectangle
     * @param node
     * @return String of the search resutlt
     */
    private String regionsearch(
        Rectangle rectangle,
        BinaryNode<Rectangle> node) {

        StringBuilder builder = new StringBuilder();
        if (node == null) {
            return "";
        }
        Rectangle compare = node.getElement();
        if (node.getLeft() != null) {
            builder.append(regionsearch(rectangle, node.getLeft()));
        }

        // same dimensions count too
        if (rectangle.inRange(compare) || rectangle.compareTo(compare,
            false) == 0) {
            builder.append(compare.toString() + "\n");
        }
        if (node.getRight() != null) {
            builder.append(regionsearch(rectangle, node.getRight()));
        }

        return builder.toString();
    }


    /**
     * Helper method that searches for all
     * rectangles that share the given name and
     * prints out the rectangle.
     * 
     * @param name
     *            String
     * @param node
     *            binaryNode
     * @return all rectangles that share the name
     */
    private String search(String name, BinaryNode<Rectangle> node) {
        StringBuilder builder = new StringBuilder();
        if (node == null) {
            return "";
        }

        if (node.getElement().getName().compareTo(name) == 0) {
            builder.append("Rectangle found: " + node.getElement().toString()
                + "\n");

        }
        if (node.getLeft() != null) {
            builder.append(search(name, node.getLeft()));
        }
        if (node.getRight() != null) {
            builder.append(search(name, node.getRight()));
        }
        return builder.toString();

    }


    /**
     * (Need to handle case of saying rectangle not found or being rejected)
     * Helper method where it returns a
     * string variant of all rectangles from their
     * respective nodes along with all the depths of each one.
     * 
     * @param node
     *            the node to return the string form of the rectangles
     * @param level
     *            depth of the given node
     * @return In-Order String representation of bst
     */
    private String inOrderString(BinaryNode<Rectangle> node, int level) {

        StringBuilder builder = new StringBuilder();
        if (node.getLeft() != null) {
            builder.append(inOrderString(node.getLeft(), level + 1));
        }
        builder.append("Node has depth " + level + ", Value ");
        builder.append(node.getElement().toString());
        builder.append("\n");
        if (node.getRight() != null) {
            builder.append(inOrderString(node.getRight(), level + 1));
        }
        return builder.toString();

    }


    /**
     * Returns the size of the search tree
     * returning the number of total nodes in
     * it.
     * 
     * @return total number of nodes in the tree
     */
    public int getSize() {
        return size;
    }


    /**
     * checks all the rectangles to see if they have intersections
     * 
     * @param node
     * @return String of intersection paired results
     */
    private String intersections1(BinaryNode<Rectangle> node) {

        BSTIterator iterator = new BSTIterator(node);

        StringBuilder builder = new StringBuilder();
        int count = 1;
        while (iterator.hasNext()) {
            Rectangle rec1 = iterator.getNext();
            BSTIterator iterator1 = new BSTIterator(node);
            int index = 1;
            while (iterator1.hasNext()) {
                Rectangle rec2 = iterator1.getNext();
                boolean exist = rec1.inRange(rec2);
                if ((exist && (rec2.compareTo(rec1, true) >= 0))
                    && count != index) {
                    builder.append(rec1.toString() + " : " + rec2.toString()
                        + "\n");
                }
                index++;
            }
            count++;
        }

        return builder.toString();

    }

}
