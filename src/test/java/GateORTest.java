import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GateORTest
{
    @Test
    public void testAddInputPin()
    {
        //Arrange
        Device uat = new GateOR();
        Device deviceBeingAdded = new Device();

        //Act
        uat.addInputPin(deviceBeingAdded);

        //Assert
        assertEquals(uat.getInputCount(), 1);
        assertTrue(deviceBeingAdded.isConnectToAnotherDevice);
    }

    @Test
    public void testGetOutputTrue()
    {
        //Arrange
        Device uat = new GateOR();
        IPin inputPin1 = new IPin();
        inputPin1.setInput(true);
        IPin inputPin2 = new IPin();
        inputPin2.setInput(false);
        uat.addInputPin(inputPin1);
        uat.addInputPin(inputPin2);

        //Act
        String result = uat.getOutput();

        //Assert
        assertEquals("1", result);
    }

    @Test
    public void testGetOutputFalse()
    {
        //Arrange
        Device uat = new GateOR();
        IPin inputPin1 = new IPin();
        inputPin1.setInput(false);
        IPin inputPin2 = new IPin();
        inputPin2.setInput(false);
        uat.addInputPin(inputPin1);
        uat.addInputPin(inputPin2);

        //Act
        String result = uat.getOutput();

        //Assert
        assertEquals("0", result);
    }

}
