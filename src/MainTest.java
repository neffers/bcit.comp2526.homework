
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * AddressBookTest.
 * 
 * @author BCIT
 * @version 2017
 */
public class MainTest {

    private static final int LARGE_SIZE = 100;
    private static final int NOT_FOUND = -1;
    private Main addressBook;

    @Before
    public void setUp() throws Exception {
        addressBook = new Main();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testNewAddressBookIsEmpty() {
        assertTrue(addressBook.database.length == 0);
    }

    @Test
    public final void testAddLengthensDatabase() {
        addressBook.add("Alex");
        assertTrue(addressBook.database.length == 1);
    }

    @Test
    public final void testSearchPositiveCaseCorrect() {
        addressBook.add("Alex");
        assertTrue(addressBook.search("Alex") == 0);
    }

    @Test
    public final void testSearchPositiveNoCase() {
        addressBook.add("Alex");
        assertTrue(addressBook.search("alex") == 0);
    }

    @Test
    public final void testSearchNegative() {
        addressBook.add("Alex");
        assertTrue(addressBook.search("Alexander") == NOT_FOUND);
    }

    @Test
    public final void testSearchEmptyDatabaseNegative() {
        assertTrue(addressBook.search("Alex") == NOT_FOUND);
    }

    @Test
    public final void testAddDuplicateCorrectLength() {
        addressBook.add("Alex");
        addressBook.add("alex");
        assertTrue(addressBook.database.length == 1);
    }

    @Test
    public final void testAddDuplicateCorrectLocation() {
        addressBook.add("Alex");
        addressBook.add("alex");
        assertTrue(addressBook.search("Alex") == 0);
    }

    @Test
    public final void testAddManyCorrectLength() {
        for (int i = 0; i < LARGE_SIZE; ++i) {
            addressBook.add("" + i);
        }
        assertTrue(addressBook.database.length == LARGE_SIZE);
    }

    @Test
    public final void testAddManyContentsInOrder() {
        for (int i = 0; i < LARGE_SIZE; ++i) {
            addressBook.add("" + i);
        }
        for (int i = 0; i < LARGE_SIZE; ++i) {
            assertTrue(addressBook.search("" + i) == i);
        }
    }

    @Test
    public final void testRemoveManyContentsCorrect() {
        for (int i = 0; i < LARGE_SIZE; ++i) {
            addressBook.add("" + i);
        }
        for (int i = 0; i < LARGE_SIZE; ++i) {
            addressBook.remove("" + i);
            assertTrue(addressBook.search("" + i) == NOT_FOUND);
        }
    }

    @Test
    public final void testRemoveManyLengthCorrect() {
        for (int i = 0; i < LARGE_SIZE; ++i) {
            addressBook.add("" + i);
        }
        for (int i = 0; i < LARGE_SIZE; ++i) {
            addressBook.remove("" + i);
            assertTrue(addressBook.database.length == LARGE_SIZE - (1 + i));
        }
    }
}
