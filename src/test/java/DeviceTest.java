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
    public void testAppInputPin()
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
