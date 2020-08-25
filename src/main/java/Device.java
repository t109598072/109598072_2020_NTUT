import java.util.Vector;

public class Device {
    protected Vector<Device> iPins = new Vector<Device>();

    public boolean isConnectToAnotherDevice = false;

    public String getOutput(){
        throw new RuntimeException("this is base class");
    }

    public void addInputPin(Device deviceBeingAdded) {
        this.iPins.add(deviceBeingAdded);
    }

    public int getInputCount() {
        return iPins.size();
    }
}
