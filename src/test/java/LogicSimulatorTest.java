import org.junit.*;

import static org.junit.Assert.*;

public class LogicSimulatorTest
{
    @Test
    public void testLoadSucess()
    {
        //Arrange
        LogicSimulator uat = new LogicSimulator();
        String lcfFilePath = "src/File1.lcf";

        //Act
        boolean result = uat.load(lcfFilePath);

        //Assert
        assertTrue(result);
    }
}
