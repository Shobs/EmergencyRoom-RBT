package emergencyRoom.rbt;

/**
 * Node class to be used with RBT
 */
public class Node<E>{
    protected Node<E> left, right, p;
    protected int key;
    protected E value;
    protected boolean color; // false = black, true = red


    public Node(){
        key = -1;
        value = null;
        left = right = p = null;
        color = false;
    }

    public Node(int k, E v){
        key = k;
        value = v;
        left = right = p = null;
        color = false;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return (false);
        }

        if (!Node.class.isAssignableFrom(obj.getClass())) {
            return (false);
        }

        final Node other = (Node) obj;

        if (this.key != other.key) {
            return (false);
        }

        return (true);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + key;
        return (hash);
    }

    @Override
    public String toString() {
        String s = "left: " + left + "\n";
        s += "right: " + right + "\n";
        s += "parent: " + p + "\n";
        s += "key: " + key + "\n";
        s += "value: " + value + "\n";
        return s;
    }

    public String colorToString(){
        return (color?"\u001B[41mRED\u001B[40m":"BLACK");
    }

    /**
     * SETTERS / GETTERS
     */
    public boolean getColor(){
        return color;
    }

    public Node getLeft(){
        return left;
    }

    public Node getRight(){
        return right;
    }

    public Node getP(){
        return p;
    }

    public int getKey(){
        return key;
    }

    public E getValue(){
        return value;
    }

    public void setColor(boolean c){
        color = c;
    }

    public void setLeft(Node l){
        left = l;
    }

    public void setRight(Node r){
        right = r;
    }

    public void setP(Node p){
        this.p = p;
    }

    public void setKey(int k){
        key = k;
    }

    public void setValue(E v){
        value = v;
    }
}