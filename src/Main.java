//imports
import java.util.Scanner;

public class Main {
    protected String[][] database;
    private Scanner input;

    public Main() {
        database = new String[0][2];
        input = new Scanner(System.in);
    }

    //add function, should check for duplicate entry, adding the new entry to the array if it is not a duplicate
    public void add(final String name, final String number) {
	System.out.println("starting add function");
        if (search(name) == -1) {
	    System.out.println("creating new array");
            String[][] temp = new String[database.length + 1][2];
            for (int i = 0; i < database.length; i++) {
	        System.out.println("copied entry at position "+i+" to new array");
                temp[i] = database[i];
            }
	    temp[database.length][0] = name;
	    temp[database.length][1] = number;
            database = temp;
        }
    }
    
    //overload for add function, when it only calls a name
    public void add(final String name) {
         add(name,"");
    }
    //search function, should search for the entered string as a name
    public int search(final String name) {
	System.out.println("starting search function");
        if (name != null) {
            for (int i = 0; i < database.length; i++) {
                if (database[i][0].toLowerCase() == name.toLowerCase()) {
		    System.out.println("search returned true at position "+i);
                    return i;
                }
            }
        }
	System.out.println("search returned null!");
        return -1;
    }

    //displayall, should display all entries
    public void displayAll() {
	System.out.println("#\tName\tPhone Number");
        for (int i = 0; i < database.length; i++) {
            System.out.println(i+"\t"+database[i][0]+"\t"+database[i][1]);
        }
    }

    //remove, should function without any editing
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

    //UI function, should not need editing
    public void displayMenu() {
        System.out.println("1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit");
    }
    
    //get user choice and return it
    public int getChoice() {
        int choice = 4;// default
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

    //used in addperson to check if the entry already exists
    public boolean checkPersonAdded(String entryName, String entryNumber) {
        for (int i = 0; i < database.length; i++) {
            if(database[i][0].toLowerCase().contains(entryName.toLowerCase()) || database[i][1] == entryNumber) {
		return true;
            }
        }
        return false;
    } 

    //UI for add() function
    public void addPerson() {
        String name = "";
        String phone = "";
        boolean done = false;
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

    //ui for remove() function
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

    //ui for search() function
    public void findPerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
        }
        int pos = search(name);
        if (pos >= 0) {
                    System.out.println(database[pos][0]+"'s Phone Number: "+database[pos][1]);
        } else {
            System.out.println("No such person");
        }
    }

    //main execution of ui
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
                // should not get here
            }
        } while (choice != 5);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
