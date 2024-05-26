import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class SILab2Test
{
    @Test
    public void testEveryBranchCriterion()
    {
        // Test case: site items se null
        Exception exception = assertThrows(RuntimeException.class, () ->
        {
            SILab2.checkCart(null, 100);
        });
        assertEquals("allItems list can't be null!", exception.getMessage());


        // Test case: Item so ime null
        List<Item> itemListWithNullName = new ArrayList<>();
        itemListWithNullName.add(new Item(null, "21543", 100, 0));
        assertTrue(SILab2.checkCart(itemListWithNullName, 100));

        // Test case: prazna lista od items
        assertTrue(SILab2.checkCart(new ArrayList<>(), 200));

    }

    @Test
    public void testMultipleConditionCriteria()
    {
        // Test case: site se true
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Item1", "012233", 350, 0.1f));
        assertTrue(SILab2.checkCart(itemList, 32)); // payment value as int

        // Test case: site se false
        itemList.clear();
        itemList.add(new Item("Item1", "112244", 100, 0));
        assertTrue(SILab2.checkCart(itemList, 100));


    }

}