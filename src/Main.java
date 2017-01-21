package ca.bcit.comp2526.a1b;

import java.util.Scanner;

/**
 * Main.
 * @author your name here
 * @version
 */
public class Main {
    
    /**
     * The main method drives the program.
     * @param args Command line args
     */
    public static void main(String[] args) {
        final UserInterface userInterface;
        final AddressBook book;

        // STEP ONE: Why is this generating a compiler error?
        userInterface = new ConsoleUserInterface();
        book = new AddressBook(userInterface);
        userInterface.run(book);
    }
}
