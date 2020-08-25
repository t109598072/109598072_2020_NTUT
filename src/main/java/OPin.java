public class OPin extends Device {
    public OPin(){

    }

    public OPin(Device device){
        this.addInputPin((device));
        device.isConnectToAnotherDevice = true;
    }

    @Override
    public String getOutput(){
        return this.iPins.firstElement().getOutput();
    }
}
