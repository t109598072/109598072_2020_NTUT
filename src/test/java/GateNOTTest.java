import org.junit.Test;

import static org.junit.Assert.*;

public class GateNOTTest
{
    @Test
    public void testAddInputPin()
    {
        //Arrange
        Device uat = new GateNOT();
        Device  deviceBeingAdded = new Device();

        //Act
        uat.addInputPin(deviceBeingAdded);

        //Assert
        assertEquals(uat.getInputCount(), 1);
        assertTrue(deviceBeingAdded.isConnectToAnotherDevice);
    }

    @Test
    public void testExceptionWhileAddMoreThanOneInput(){
        //Arrange
        Device uat = new GateNOT();
        Device  deviceBeingAdded1 = new Device();
        Device  deviceBeingAdded2 = new Device();

        //Act
        uat.addInputPin((deviceBeingAdded1));

        //Assert
        try{
            uat.addInputPin((deviceBeingAdded2));
            fail("didn't catch the exception");
        }
        catch (RuntimeException e){
            assertEquals("GateNOT Would not more than one input", e.getMessage());
        }
    }

    @Test
    public void testGetOutputTrue()
    {
        //Arrange
        Device uat = new GateNOT();
        IPin inputPin = new IPin();
        inputPin.setInputValue(1);
        uat.addInputPin(inputPin);

        //Act
        String result = uat.getOutput();

        //Assert
        assertEquals("0", result);
    }

    @Test
    public void testGetOutputFalse()
    {
        //Arrange
        Device uat = new GateNOT();
        IPin inputPin = new IPin();
        inputPin.setInputValue(0);
        uat.addInputPin(inputPin);

        //Act
        String result = uat.getOutput();

        //Assert
        assertEquals("1", result);
    }

}
