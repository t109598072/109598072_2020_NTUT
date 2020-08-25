public class GateNOT extends Device {
    @Override
    public void addInputPin(Device input) {
        this.iPins.add(input);
        if (this.iPins.size() > 1){
            throw new RuntimeException("GateNOT Would not more than one input");
        }
        input.isConnectToAnotherDevice = true;
    }

    @Override
    public String getOutput(){
        return this.iPins.firstElement().getOutput() == "1" ? "0" : "1";
    }
}
