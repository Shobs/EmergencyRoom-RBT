package emergencyRoom.cht;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.LinkedList;
import emergencyRoom.cht.ChainingHashTable;
import emergencyRoom.Person;

/*
 * BinarySearchTreeTest -- unit tests for the BinarySearchTree
 * class.
*/
public class ChainingHashTableTest {

    private String[] names = new String[]{"Lillian Patton","Jimmie Hunter","Cecelia Henderson","Steven Parsons","James Barton","Archie Sanchez","Heather Blair","Shawna Mitchell","Kimberly Wong","Billie Leonard","Cathy Nash","Thomas Chavez","Phil Alvarez","Shaun Haynes","Angelina Figueroa","Cody Douglas","Willard Fernandez","Marilyn McCarthy","Garrett Willis","Suzanne Hayes"};

    private int[] priorities = new int[]{2505,4011,8011,5988,3469,4142,6780,3984,5039,7301,4750,4979,9416,8117,2094,783,7074,8800,5962,5904};


    /**
     * Generates a predefined CHT for testing purpose.
     * @return [CHT]
     */
    public ChainingHashTable generateCHT(){
        ChainingHashTable<Person> cht = new ChainingHashTable();
        for (int i = 0; i < names.length; i++ ) {
            Person p = new Person(priorities[i], names[i]);
            cht.insert(p.getPriority(), p);
        }

        return cht;
    }

    @Test
    public void testInsert() {
        ChainingHashTable cht = new ChainingHashTable();
        Person p = new Person(783, "Cody Douglas");
        cht.insert(p.getPriority(), p);

        String str = "0: null\n1: null\n2: 783 | Cody Douglas\n3: null\n4: null\n5: null\n6: null\n7: null\n8: null\n9: null\n10: null\n";

        assertEquals(str, cht.toString());
    }

    @Test
    public void testSearch() {
        ChainingHashTable<Person> cht = generateCHT();
        Person p = cht.search(4750);
        assertEquals(4750, p.getPriority());
    }

    @Test
    public void testDelete() {
        ChainingHashTable<Person> cht = generateCHT();

        cht.delete(4750);

        String str = "0: 9416 | Phil Alvarez ---> 8800 | Marilyn McCarthy ---> 5962 | Garrett Willis\n1: 5039 | Kimberly Wong ---> 7074 | Willard Fernandez\n2: 3984 | Shawna Mitchell ---> 783 | Cody Douglas\n3: 8011 | Cecelia Henderson\n4: 5988 | Steven Parsons ---> 3469 | James Barton ---> 6780 | Heather Blair ---> 2094 | Angelina Figueroa\n5: null\n6: 4142 | Archie Sanchez\n7: 4011 | Jimmie Hunter ---> 4979 | Thomas Chavez\n8: 2505 | Lillian Patton ---> 7301 | Billie Leonard ---> 5904 | Suzanne Hayes\n9: \n10: 8117 | Shaun Haynes\n";

        assertEquals(str, cht.toString());
    }
}
