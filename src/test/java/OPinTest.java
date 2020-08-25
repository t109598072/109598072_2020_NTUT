import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OPinTest
{
    @Test
    public void testGetOutput()
    {
        //Arrange
        IPin deviceBeingAdded = new IPin();
        deviceBeingAdded.setInput(true);
        OPin uat = new OPin(deviceBeingAdded);

        //Act
        String result = uat.getOutput();

        //Assert
        assertEquals(result, "1");
    }
}
