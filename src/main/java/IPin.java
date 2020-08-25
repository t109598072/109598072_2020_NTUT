public class IPin extends Device {
    private int input;

    public void setInputValue(int value) {
        this.input = value;
    }

    @Override
    public String getOutput(){
        return input == 1 ? "1" : "0";
    }
}
