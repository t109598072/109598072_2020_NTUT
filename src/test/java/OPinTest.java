import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OPinTest
{
    @Test
    public void testGetOutput()
    {
        //Arrange
        IPin deviceBeingAdded = new IPin();
        deviceBeingAdded.setInputValue(1);
        OPin uat = new OPin(deviceBeingAdded);

        //Act
        String result = uat.getOutput();

        //Assert
        assertEquals(result, "1");
    }
}
