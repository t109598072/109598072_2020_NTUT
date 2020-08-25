import org.junit.*;
import static org.junit.Assert.*;

public class DeviceTest
{
    @Test
    public void testDevicePolymorphism()
    {
        Device device = new IPin();
        assertEquals(IPin.class.getName(), device.getClass().getName());

        /* implement OPin, GateNOT, GateAND, GateOR test */
    }

    @Test
    public void testIPinAndOPin()
    {
        // 0 = 0
        IPin iPin = new IPin();
        iPin.setInput(false);

        OPin oPin = new OPin();
        oPin.addInputPin(iPin);

        assertEquals("0", oPin.getOutput());

        /* implement 1 = 1 test */
    }

    @Test
    public void testAddInputPin()
    {
        //Arrange
        Device  uat = new Device();
        Device  deviceBeingAdded = new Device();

        //Act
        uat.addInputPin(deviceBeingAdded);

        //Assert
        assertEquals(uat.getInputCount(), 1);
    }

    @Test
    public void testGetOutput()
    {
        //Arrange
        Device  uat = new Device();

        //Assert
        try
        {
            uat.getOutput();
            fail("didn't catch the exception");
        }
        catch (RuntimeException e)
        {
            assertEquals("this is base class", e.getMessage());
        }
    }
}
