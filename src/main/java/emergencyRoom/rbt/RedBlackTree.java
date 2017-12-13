package emergencyRoom.rbt;

import java.util.Stack;

/**
 * BST class implementation
 */
public class RedBlackTree<E>{

    private Node<E> root;
    private Node<E> nil;
    private String str;
    private String BGRED;
    private String BGRESET;

    public RedBlackTree(Node<E> root){
        nil = new Node();
        this.root = root;
        root.p = nil;
        root.left = nil;
        root.right = nil;
        str = "";
        BGRED = "\u001B[41m";
        BGRESET = "\u001B[40m";
    }

    /**
     * [leftRotate description]
     * @param  t [description]
     * @param  x [description]
     * @return   [description]
     */
    public void leftRotate(RedBlackTree<E> t, Node<E> x){
        Node<E> y = x.right;
        x.right = y.left;
        if (y.left.equals(nil)) {
            y.left.p = x;
        }
        y.p = x.p;
        if (x.p.equals(nil)) {
            t.root = y;
        }else if (x.equals(x.p.left)) {
            x.p.left = y;
        }else{
            x.p.right = y;
        }
        y.left = x;
        x.p = y;
    }

    /**
     * [leftRotate description]
     * @param  t [description]
     * @param  x [description]
     * @return   [description]
     */
    public void rightRotate(RedBlackTree<E> t, Node<E> x){
        Node<E> y = x.left;
        x.left = y.right;
        if (!y.right.equals(nil)) {
            y.right.p = x;
        }
        y.p = x.p;
        if (x.p.equals(nil)) {
            t.root = y;
        }else if (x.equals(x.p.right) ) {
            x.p.right = y;
        }else{
            x.p.left = y;
        }
        y.right = x;
        x.p = y;
    }

    /**
     * [insert description]
     * @param  t [description]
     * @param  z [description]
     * @return   [description]
     */
    public void insert(RedBlackTree<E> t, Node<E> z){
        Node<E> y = new Node();
        Node<E> x = t.root;
        while(!x.equals(nil)){
            y = x;
            if (z.key < x.key) {
                x = x.left;
            }else{
                x = x.right;
            }
        }
        z.p = y;
        if (y.equals(nil)) {
            t.root = z;
        }else if (z.key < y.key) {
            y.left = z;
        }else{
            y.right = z;
        }
        z.left = nil;
        z.right = nil;
        z.color = true;
        insertFixup(t,z);
    }

    /**
     * [insertFixup description]
     * @param  t [description]
     * @param  z [description]
     * @return   [description]
     */
    public void insertFixup(RedBlackTree<E> t, Node<E> z){

        while(!z.equals(t.root) && z.p.color){
            // if parent of z is a left child of grand parent
            if (z.p.equals(z.p.p.left)) {
                // setting uncle (y)
                Node<E> y = z.p.p.right;
                // if uncle is red
                if (y.color) {
                    z.p.color = false;
                    y.color = false;
                    z.p.p.color = true;
                    z = z.p.p;

                } else { // if uncle is black

                    if( z.equals(z.p.right)){
                        z = z.p;
                        leftRotate(t,z);
                    }

                    z.p.color = false;
                    z.p.p.color = true;
                    rightRotate(t,z.p.p);
                }
            }else{
                Node<E> y = z.p.p.left;
                if (y.color) {
                    z.p.color = false;
                    y.color = false;
                    z.p.p.color = true;
                    z = z.p.p;
                } else {
                    if( z.equals(z.p.left)){
                        z = z.p;
                        rightRotate(t,z);
                    }

                    z.p.color = false;
                    z.p.p.color = true;
                    leftRotate(t,z.p.p);
                }
            }
        }
        t.root.color = false;
    }

    /**
     * Moves subtrees around within the RBT
     * @param t [RBT]
     * @param u [root node of first sub-tree]
     * @param v [root node of second sub-tree]
     */
    public void transplant(RedBlackTree<E> t, Node<E> u, Node<E> v){
        if (u.p.equals(nil)) {
            t.root = v;
        }else if ( u.equals(u.p.left)){
            u.p.left = v;
        }else {
            u.p.right = v;
        }
        v.p = u.p;
    }

    /**
     * Deletes specific node from RBT
     * @param t [RBT]
     * @param z [Node to be deleted]
     */
    public void delete(RedBlackTree<E> t, Node<E> z){
        Node<E> y = z;
        Node<E> x = new Node();
        boolean oColor = y.color;
        if (z.left.equals(nil)) {
            x = z.right;
            transplant(t, z, z.right);
        } else if(z.right.equals(nil)){
            x = z.left;
            transplant(t, z, z.left);
        }else{


            y = minimum(z.right);
            oColor = y.color;
            x = y.right;
            if (y.p.equals(z)) {
                x.p = y;
            }else{
                transplant(t, y, y.right);
                y.right = x.right;
                y.right.p = y;
            }
            transplant(t, z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }
        if (oColor == false) {
            deleteFixup(t, x);
        }
    }

    /**
     * [deleteFixup description]
     * @param t [description]
     * @param x [description]
     */
    public void deleteFixup(RedBlackTree<E> t, Node<E> x){
        while(!x.equals(t.root) && x.color == false){            if (x.equals( x.p.left)) {
                Node<E> w = x.p.right;
                if (w.color == true) {
                    w.color = false;
                    x.p.color = true;
                    leftRotate(t, x.p);
                    w = x.p.right;
                }
                if (w.left.color == false && w.right.color == false) {
                    w.color = true;
                    x = x.p;
                } else {
                    if (w.right.color == false){
                        w.left.color = false;
                        w.color = true;
                        rightRotate(t, w);
                        w = x.p.right;
                    }
                    w.color = x.p.color;
                    x.p.color = false;
                    w.right.color = false;
                    leftRotate(t, x.p);
                    x = t.root;
                }
            } else {
                Node<E> w = x.p.left;
                if (w.color == true) {
                    w.color = false;
                    x.p.color = true;
                    rightRotate(t, x.p);
                    w = x.p.left;
                }
                if (w.right.color == false && w.left.color == false) {
                    w.color = true;
                    x = x.p;
                } else {
                    if (w.left.color == false){
                        w.right.color = false;
                        w.color = true;
                        leftRotate(t, w);
                        w = x.p.left;
                    }
                    w.color = x.p.color;
                    x.p.color = false;
                    w.left.color = false;
                    rightRotate(t, x.p);
                    x = t.root;
                }
            }
        }
        x.color = false;
    }

    /**
     * Prints out all keys and values in sorted order
     * @param x [Root node of tree]
     */
    public void inorderWalk(Node<E> x){
        // this.root.p
        if (!x.equals(nil)) {
            if (!x.equals(x.p)) {
                inorderWalk(x.left);
                str += x.value + " : " + x.colorToString() +"\n";
                inorderWalk(x.right);
            }
        }
    }

    /**
     * Returns a pointer to a node with key if one exists;
     * otherwise it returns null
     * @param  x [Root node of tree]
     * @param  k [key to be searched]
     * @return   [searched node or null value]
     */
    public Node search(Node<E> x, int k){
        if (x.equals(nil) || k == x.key) {
            return x;
        }else if (k < x.key) {
            return search(x.left, k);
        } else {
            return search(x.right, k);
        }
    }

    /**
     * Iterative version of search() more efficient on most
     * computers
     * @param  x [Root node of tree]
     * @param  k [key to be searched]
     * @return   [searched node or null value]
     */
    public Node<E> iterativeSearch ( Node<E> x, int k){
        while( !x.equals(nil) && k != x.key){
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }

    /**
     * Returns pointer to the minimum element in the subtree
     * rooted at a given node assumed to be non-null
     * @param  x [root node of tree or sub-tree]
     * @return   [node of minimum value]
     */
    public Node minimum(Node<E> x) {
        while(!x.left.equals(nil)){
            x = x.left;
        }
        return x;
    }

    /**
     * Returns pointer to the maximum element in the subtree
     * rooted at a given node assumed to be non-null
     * @param  x [root node of tree or sub-tree]
     * @return   [node of maximum value]
     */
    public Node maximum(Node<E> x) {
        while(!x.right.equals(nil)){
            x = x.right;
        }
        return x;
    }

    /**
     * Returns successor of a node.
     * @param  x [node]
     * @return   [successor of node]
     */
    public Node successor(Node<E> x) {
        if (!x.right.equals(nil)) {
            return minimum(x.right);
        }

        Node<E> y = x.p;

        while(!y.equals(nil) && x.equals(y.right)){
            x = y;
            y = y.p;
        }

        return y;
    }

    /**
     * Returns predecessor of a node.
     * @param  x [node]
     * @return   [predecessor of node]
     */
    public Node predecessor(Node<E> x) {
        if (x.left.equals(nil)) {
            return maximum(x.left);
        }

        Node<E> y = x.p;

        while(!y.equals(nil) && x.equals(y.left)){
            x = y;
            y = y.p;
        }

        return y;
    }



    /**
     * Displays tree version of BST
     */
    public void display()
    {
        Stack<Node<E>> globalStack = new Stack();
        globalStack.push(root);
        int emptyLeaf = 64;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while(isRowEmpty == false)
        {
            Stack<Node<E>> localStack = new Stack();
            isRowEmpty = true;
            for(int j = 0; j < emptyLeaf; j++)
                System.out.print(' ');
            while(globalStack.isEmpty() == false)
            {
                Node<E> temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(getBackground(temp.getColor()) + temp.key + BGRESET);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if(temp.left != null ||temp.right != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j = 0; j < (emptyLeaf * 2 - 2); j++)
                    System.out.print(' ');
            }
            System.out.println();
            emptyLeaf /= 2;
            while(localStack.isEmpty() == false)
                globalStack.push( localStack.pop() );
        }
        System.out.println("****......................................................****");
    }

    @Override
    public String toString(){
        str = "";
        inorderWalk(root);
        return (str.length()>0?str:"Empty!");
    }

    private String getBackground(boolean c){
        return (c?BGRED:"");
    }

    /**
     * SETTERS / GETTERS
     */
    public Node getRoot(){
        return root;
    }

    public void setRoot(Node<E> x){
        root = x;
    }


}