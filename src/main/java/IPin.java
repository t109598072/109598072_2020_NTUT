public class IPin extends Device {
    private boolean input;

    @Override
    public void setInput(boolean value){
        this.input = value;
    }

    @Override
    public String getOutput(){
        return input ? "1" : "0";
    }
}
