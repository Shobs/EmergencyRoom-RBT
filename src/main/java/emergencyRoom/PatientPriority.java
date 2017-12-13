package emergencyRoom;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Collections;
import emergencyRoom.rbt.Node;
import emergencyRoom.rbt.RedBlackTree;
import emergencyRoom.Person;


/**
 * Patient Priority Class
 */
public class PatientPriority {

    private final int MIN = 1;
    private final int MAX = 10000;
    // private int a[];
    // private ArrayList<Person> al;
    private RedBlackTree<Person> rbt;
    private String[] names = new String[]{"Lillian Patton","Jimmie Hunter","Cecelia Henderson","Steven Parsons","James Barton","Archie Sanchez","Heather Blair","Shawna Mitchell","Kimberly Wong","Billie Leonard","Cathy Nash","Thomas Chavez","Phil Alvarez","Shaun Haynes","Angelina Figueroa","Cody Douglas","Willard Fernandez","Marilyn McCarthy","Garrett Willis","Suzanne Hayes"};

    /**
     * Constructor
     */
    public PatientPriority(){
        rbt = new RedBlackTree<Person>(new Node<Person>());
    }

    /**
     * generates random priority numbers and names from a list
     */
    public void generatePatients(){
        for (int i = 0; i < names.length; i++ ) {
            Person p = new Person(randInt(MIN, MAX), names[i]);
            System.out.println("insert:" + p.getPriority() + " : " + p);
            rbt.insert(rbt, new Node<Person>(p.getPriority(), p));
        }
    }

    /**
     * adds a patient to the priority list and generate random
     * priority
     * @param n [name of patient]
     */
    public void add(String name){
        Person p = new Person(randInt(MIN, MAX), name);
        rbt.insert(rbt, new Node<Person>(p.getPriority(), p));
    }

    /**
     * adds a patient to the priority list and generate random
     * priority
     * @param i [priority number]
     * @param n [name of patient]
     */
    public void add(int i, String name){
        Person p = new Person(i, name);
        rbt.insert(rbt, new Node<Person>(p.getPriority(), p));
    }

    /**
     * Returns the patient with the highest priority
     * @return [name of patient]
     */
    public Person getPatient(int i){
        return (Person)(rbt.iterativeSearch(rbt.getRoot(),i)).getValue();
    }


    /**
     * removes a patient to the priority list and generate random
     * priority
     * @param i [priority number]
     * @param n [name of patient]
     */
    public void remove(int i){
        rbt.delete(rbt, rbt.iterativeSearch(rbt.getRoot(), i));
    }

    // /**
    //  * Returns the patient with the highest priority
    //  * @return [name of patient]
    //  */
    public String getFirst(){
        // Node<Person> n = rbt.minimum(rbt.getRoot());
        return rbt.minimum(rbt.getRoot()).toString();
    }

    /**
     * Resets the patient priority list
     */
    public void resetPriority(){
        rbt = new RedBlackTree<Person>(new Node<Person>());
    }

    /**
     * Prints out a rbt in tree form
     * @return [rbt]
     */
    public void printRBT() {
        rbt.display();
    }

    /**
     * Prints out the priority arrray number in a tree like
     * fashion
     * @return [Formated string]
     */
    @Override
    public String toString() {
        return rbt.toString();
    }

    public static int randInt(int min, int max){
        return (ThreadLocalRandom.current().nextInt(min, max + 1));
    }
}