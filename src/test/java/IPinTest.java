import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IPinTest
{
    @Test
    public void testSetInputValue()
    {
        //Arrange
        IPin uat = new IPin();

        //Act
        uat.setInputValue(1);

        //Assert
        assertEquals(uat.getOutput(), "1");
    }
}
