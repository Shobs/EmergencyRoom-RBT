
import java.io.Console;
import java.lang.Integer;
import emergencyRoom.PatientPriority;
// import emergencyRoom.cht.ChainingHashTable;
import emergencyRoom.Person;

/**
 * Program that can be use to keep track of patients waiting for
 * to receive care based on a priority system
 */
public class Main{
    public static void main(String[] args) {


        String intro = "\n Welcome to the ER - ";
        String menu = "Main Menu\n--------------------------------------\n1. Generate patient list\n2. View patient list\n3. Add patient\n4. Search patient\n5. Remove patient\n6. Display RBT\n7. Reset patient list\n\nEnter your choice (1-7) or q to quit: ";

        Console con = System.console();

        if (con == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String input = con.readLine(intro + menu);

        PatientPriority pp = new PatientPriority();

        while(!input.equals("q")){
            switch(input){
                case "1":
                pp.generatePatients();
                System.out.println("\nRandomized Patient list has been created!\n");
                break;
                case "2":
                System.out.println("\nPatients currently in list:\n");
                System.out.println(pp.toString());
                break;
                case "3":
                System.out.println("\nAdding new patient to the list:\n");
                String priority = con.readLine("Enter the Patient's priority:");
                String name = con.readLine("Enter the Patient's name:");
                if (priority.length()>0 && priority != null) {
                    pp.add(Integer.parseInt(priority), name);
                } else {
                    pp.add(name);
                }

                break;
                case "4":

                System.out.println("\nSearching Patient:\n");
                priority = con.readLine("Enter the Patient's priority:");
                System.out.println(pp.getPatient(Integer.parseInt(priority)));

                break;
                case "5":

                System.out.println("\nRemoving Patient:\n");
                priority = con.readLine("Enter the Patient's priority:");
                pp.remove(Integer.parseInt(priority));
                System.out.println("\nPatient removed\n");

                break;
                case "6":

                System.out.println("\nRBT tree form:\n");
                pp.printRBT();

                break;
                case "7":

                System.out.println("\nPatient List reset!\n");
                pp.resetPriority();

                break;
                default:

                break;
            }
            input = con.readLine("\n"+ menu);
        }

    }
}