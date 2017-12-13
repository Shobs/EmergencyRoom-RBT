package emergencyRoom.bst;

import static org.junit.Assert.*;
import org.junit.Test;
import emergencyRoom.bst.Node;
import emergencyRoom.Person;

/*
 * NodeTest -- unit tests for the Node class.
*/
public class NodeTest {

    Person p1 = new Person(3, "Apple");
    Person p2 = new Person(5, "Bobby");
    Person p3 = new Person(8, "William");

    Node<Person> nP = new Node(p1.getPriority(), p1);
    Node<Person> nL = new Node(p2.getPriority(), p2);
    Node<Person> nR = new Node(p3.getPriority(), p3);

    // Matrix Create
    @Test
    public void testParent() {
        nL.setP(nP);
        assertEquals(nP,nL.getP());
    }

    @Test
    public void testLeft() {
        nP.setLeft(nL);
        assertEquals(nL,nP.getLeft());
    }

    @Test
    public void testRight() {
        nP.setRight(nR);
        assertEquals(nR,nP.getRight());
    }

        @Test
    public void testKey() {
        nP.setKey(p1.getPriority());
        assertEquals(3,nP.getKey());
    }

        @Test
    public void testValue() {
        nP.setValue(p1);
        assertEquals(p1,nP.getValue());
    }

}
