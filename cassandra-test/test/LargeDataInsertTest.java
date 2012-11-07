import models.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Test the Plugin's Ability to manipulate large data sets, including
 * selection and deletion
 */
public class LargeDataInsertTest extends UnitTest {
    @Before
    public void setup() {
        // Refresh database prior to each test
        Fixtures.deleteAllModels();
    }

    @After
    public void tearDown() {
        Fixtures.deleteAllModels();
    }

    @Test
    public void testLargeInsert() {
        long ordersToCreate = 100000;
        long expected = 0;
        long result = Order.count();
        assertEquals(expected, result);

        String seed = String.valueOf(System.currentTimeMillis());
        for ( long i = 0; i < ordersToCreate; i++ ) {
            Order model = new Order();
            model.id = seed + String.valueOf(i);
            model.save();
        }

        result = Order.count();
        assertEquals(ordersToCreate, result);
    }
}
