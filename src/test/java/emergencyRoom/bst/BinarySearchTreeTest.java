package emergencyRoom.bst;

import static org.junit.Assert.*;
import org.junit.Test;
import emergencyRoom.bst.BinarySearchTree;
import emergencyRoom.bst.Node;
import emergencyRoom.Person;

/*
 * BinarySearchTreeTest -- unit tests for the BinarySearchTree
 * class.
*/
public class BinarySearchTreeTest {

    private String[] names = new String[]{"Lillian Patton","Jimmie Hunter","Cecelia Henderson","Steven Parsons","James Barton","Archie Sanchez","Heather Blair","Shawna Mitchell","Kimberly Wong","Billie Leonard","Cathy Nash","Thomas Chavez","Phil Alvarez","Shaun Haynes","Angelina Figueroa","Cody Douglas","Willard Fernandez","Marilyn McCarthy","Garrett Willis","Suzanne Hayes"};

    private int[] priorities = new int[]{2505,4011,8011,5988,3469,4142,6780,3984,5039,7301,4750,4979,9416,8117,2094,783,7074,8800,5962,5904};


    /**
     * Generates a predefined BST for testing purpose.
     * @return [BST]
     */
    public BinarySearchTree generateBST(){
        BinarySearchTree bst = new BinarySearchTree();
        for (int i = 0; i < names.length; i++ ) {
            Person p = new Person(priorities[i], names[i]);
            bst.treeInsert(bst, new Node<Person>(p.getPriority(), p));
        }

        return bst;
    }

    @Test
    public void testTreeInsert() {
        BinarySearchTree bst = new BinarySearchTree();
        Person p = new Person(783, "Cody Douglas");
        Node<Person> n = new Node(p.getPriority(), p);
        bst.treeInsert(bst, n);
        assertEquals(n, bst.getRoot());
    }

    @Test
    public void testInorderTreeWalk() {
        BinarySearchTree bst = generateBST();
        String str = "783 | Cody Douglas\n2094 | Angelina Figueroa\n2505 | Lillian Patton\n3469 | James Barton\n3984 | Shawna Mitchell\n4011 | Jimmie Hunter\n4142 | Archie Sanchez\n4750 | Cathy Nash\n4979 | Thomas Chavez\n5039 | Kimberly Wong\n5904 | Suzanne Hayes\n5962 | Garrett Willis\n5988 | Steven Parsons\n6780 | Heather Blair\n7074 | Willard Fernandez\n7301 | Billie Leonard\n8011 | Cecelia Henderson\n8117 | Shaun Haynes\n8800 | Marilyn McCarthy\n9416 | Phil Alvarez\n";

        assertEquals(str,bst.toString());
    }

    @Test
    public void testTreeSearch() {
        BinarySearchTree bst = generateBST();
        Node<Person> n = bst.treeSearch(bst.getRoot(), 7074);
        assertEquals(7074,n.getValue().getPriority());
    }

    @Test
    public void testIterativeTreeSearch() {
        BinarySearchTree bst = generateBST();
        Node<Person> n = bst.iterativeTreeSearch(bst.getRoot(), 7074);
        assertEquals(7074,n.getValue().getPriority());
    }

    @Test
    public void testTreeMinimum() {
        BinarySearchTree bst = generateBST();
        Node<Person> min = bst.treeMinimum(bst.getRoot());
        assertEquals(783,min.getKey());
    }

    @Test
    public void testTreeMaximum() {
        BinarySearchTree bst = generateBST();
        Node<Person> min = bst.treeMaximum(bst.getRoot());
        assertEquals(9416,min.getKey());
    }


    @Test
    public void testTreeSuccessor() {
        BinarySearchTree bst = generateBST();
        Node<Person> n = bst.treeSuccessor(bst.treeSearch(bst.getRoot(), 4011));
        assertEquals(4142,n.getValue().getPriority());
    }

    @Test
    public void testTreePredecessor() {
        BinarySearchTree bst = generateBST();
        Node<Person> n = bst.treePredecessor(bst.treeSearch(bst.getRoot(), 4011));
        assertEquals(3984,n.getValue().getPriority());
    }

    @Test
    public void testTransplant() {
        BinarySearchTree bst = generateBST();

        String str = "4142 | Archie Sanchez\n4750 | Cathy Nash\n4979 | Thomas Chavez\n5039 | Kimberly Wong\n5904 | Suzanne Hayes\n5962 | Garrett Willis\n5988 | Steven Parsons\n6780 | Heather Blair\n7074 | Willard Fernandez\n7301 | Billie Leonard\n2505 | Lillian Patton\n3469 | James Barton\n3984 | Shawna Mitchell\n4011 | Jimmie Hunter\n4142 | Archie Sanchez\n4750 | Cathy Nash\n4979 | Thomas Chavez\n5039 | Kimberly Wong\n5904 | Suzanne Hayes\n5962 | Garrett Willis\n5988 | Steven Parsons\n6780 | Heather Blair\n7074 | Willard Fernandez\n7301 | Billie Leonard\n8011 | Cecelia Henderson\n8117 | Shaun Haynes\n8800 | Marilyn McCarthy\n9416 | Phil Alvarez\n";

        bst.transplant(bst, bst.treeSearch(bst.getRoot(), 2094), bst.treeSearch(bst.getRoot(), 5988));

        assertEquals(str,bst.toString());
    }

    @Test
    public void testTreeDelete() {
        BinarySearchTree bst = generateBST();

        String str = "783 | Cody Douglas\n2094 | Angelina Figueroa\n2505 | Lillian Patton\n3469 | James Barton\n3984 | Shawna Mitchell\n4011 | Jimmie Hunter\n4142 | Archie Sanchez\n4750 | Cathy Nash\n5039 | Kimberly Wong\n5904 | Suzanne Hayes\n5962 | Garrett Willis\n5988 | Steven Parsons\n6780 | Heather Blair\n7074 | Willard Fernandez\n7301 | Billie Leonard\n8011 | Cecelia Henderson\n8117 | Shaun Haynes\n8800 | Marilyn McCarthy\n9416 | Phil Alvarez\n";

        bst.treeDelete(bst, bst.treeSearch(bst.getRoot(), 4979));

        assertEquals(str,bst.toString());
    }
}
