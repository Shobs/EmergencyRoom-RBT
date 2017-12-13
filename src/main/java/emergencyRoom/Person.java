package emergencyRoom;

/**
 * Person class implementation
 */
public class Person{

    private int priority;
    private String name;

    public Person(int p, String n){
        this.priority = p;
        this.name = n;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return (false);
        }

        if (!Person.class.isAssignableFrom(obj.getClass())) {
            return (false);
        }

        final Person other = (Person) obj;

        if (this.priority != other.priority) {
            return (false);
        }

        return (true);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.priority;
        return (hash);
    }

    @Override
    public String toString() {
        String s = "" + this.priority + " | " + this.name;

        return s;
    }

    /**
     * SETTERS / GETTERS
     */
    public void setPriority(int p){
        this.priority = p;
    }

    public void setName(String n){
        this.name = n;
    }

    public int getPriority(){
        return (this.priority);
    }

    public String getName(){
        return (this.name);
    }



}