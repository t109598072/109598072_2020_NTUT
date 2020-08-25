public class GateOR extends Device {
    @Override
    public void addInputPin(Device input) {
        this.iPins.add(input);
        input.isConnectToAnotherDevice = true;
    }

    @Override
    public String getOutput(){
        for (Device input: this.iPins) {
            if (input.getOutput() == "1") {
                return "1";
            }
        }
        return "0";
    }
}
