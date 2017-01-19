import java.util.Scanner;

/**
 * So, I know that the Java check list that you provided tells us to user magic numbers. 
 * But I over heard my lab instructor saying that sometimes 0-2 are not considered magic numbers, 
 * depending on how they are used.  So I feel that my database indexes are not that magically, 
 * so don't require them to be a constant variable.
 * Also, wasn't sure if you were wanting us to put in variables for getChoice(), as that is
 * code that you wrote yourself, so I would have expected you to know when
 * and when not to use variables for that.
 * 
 * @author Rob.
 * @version 10.
 */
public class Main {
    protected String[][] database;
    private Scanner input;

    public Main() {
        database = new String[0][2];
        input = new Scanner(System.in);
    }

    /**
     * Adds name into the database.
     * 
     * @param name Name is the input from the addPerson() method
     * @param number Number is the input from the addPerson() method
     */
    public void add(final String name, final String number) {
        if (search(name) == -1) {
            String[][] temp = new String[database.length + 1][2];
            for (int i = 0; i < database.length; i++) {
                temp[i] = database[i];
            }
            temp[database.length][0] = name;
            temp[database.length][1] = number;
            database = temp;
        }
    }

    /**
     * Allows junit to be happy.
     * 
     * @param name Overload 
     */
    public void add(final String name) {
        add(name, "");
    }

    /**
     * Checks position of name in database.
     * 
     * @param searchName The inputed name is used from the add(), remove(), and find person()
     * @return the return is either i for index or returns -1 if it's not found.
     */
    public int search(final String searchName) {
        if (searchName != null) {
            for (int i = 0; i < database.length; i++) {
                if (database[i][0].equalsIgnoreCase(searchName)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /** Prints out database.
     * 
     */
    public void displayAll() {
        //This piece of code is for testing reasons
        //System.out.println("#\tName\tPhone Number");
        for (int i = 0; i < database.length; i++) {
            //This piece of code is for testing reasons
            //System.out.println(i + "\t" + database[i][0] + "\t" + database[i][1]);
            System.out.printf("%-20s %-15s\n", database[i][0], database[i][1]);
        }
    }

    /**
     * Removes name from database.
     * 
     * @param name the input that is given.
     * @return returns true or false
     */
    public boolean remove(final String name) {
        int pos = search(name);
        if (pos >= 0) {
            String[][] temp = new String[database.length - 1][2];
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos + 1, temp, pos, database.length - pos - 1);
            database = temp;
            return true;
        }
        return false;
    }

    /**
     * Displays the menu.
     */
    public void displayMenu() {
        System.out.println("1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit");
    }


    /**
     * returns choice of user.
     * 
     * @return returns the choice which the User has chosen.
     */
    public int getChoice() {
        int choice = 4;
        boolean done = false;
        while (!done) {
            System.out.print("choice? ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
            }
            if (choice > 0 && choice <= 5) {
                done = true;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        return choice;
    }

    /**
     * Checks if information is already added.
     * 
     * @param entryName The name that is passed through when calling this method
     * @param entryNumber The phone number, called entryNumber that is passed through 
     * @return returns either true of false.
     */
    public boolean checkPersonAdded(final String entryName, final String entryNumber) {
        for (int i = 0; i < database.length; i++) {
            if (database[i][0].equalsIgnoreCase(entryName)
                    || database[i][1].equalsIgnoreCase(entryNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Allows inputs from user for adding an entry.
     */
    public void addPerson() {
        String name = "";
        String phone = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.print("\nEnter the persons phone number ");
            phone = input.next();
            System.out.println("");
        } catch (Exception e) {
        }
        if (checkPersonAdded(name, phone) == false) {
            add(name, phone);
        } else {
            System.out.println("This entry is already added.");
        }
    }

    /**
     *  Deletes entry from database.
     */
    public void deletePerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
        }
        if (!remove(name)) {
            System.out.println("Could not delete " + name);
        } else {
            System.out.println(name + " was deleted successfully");
        }
    }

    /**
     * Finds a person through the given String.
     */
    public void findPerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
        } catch (Exception e) {
        }
        int pos = search(name);
        if (pos >= 0) {
            System.out.println(database[pos][0] + "'s Phone Number: " + database[pos][1]);
        } else {
            System.out.println("No such person");
        }
    }

    /**
     * Basic menu for the program.
     */
    public void run() {
        int choice = 0;
        do {
            displayMenu();
            choice = getChoice();
            switch (choice) {
            case 1:
                addPerson();
                break;
            case 2:
                deletePerson();
                break;
            case 3:
                findPerson();
                break;
            case 4:
                displayAll();
            default:
            }
        }
        while (choice != 5);
    }

    /**
     * @param args main method.
     */
    public static void main(String[] args) {
        new Main().run();
    }
}