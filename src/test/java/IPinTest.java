import org.junit.Test;

import static org.junit.Assert.*;

public class GateANDTest
{
    @Test
    public void testAddInputPin()
    {
        //Arrange
        Device uat = new GateAND();
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
        Device uat = new GateAND();
        IPin inputPin1 = new IPin();
        inputPin1.setInputValue(1);
        IPin inputPin2 = new IPin();
        inputPin2.setInputValue(1);
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
        GateAND uat = new GateAND();
        IPin inputPin1 = new IPin();
        inputPin1.setInputValue(0);
        IPin inputPin2 = new IPin();
        inputPin2.setInputValue(1);
        uat.addInputPin(inputPin1);
        uat.addInputPin(inputPin2);

        //Act
        String result = uat.getOutput();

        //Assert
        assertEquals("0", result);
    }

}
