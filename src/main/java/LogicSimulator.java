import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


public class LogicSimulator {
    enum LogicGate{
        DUMMY, AND, OR, NOT
    }
    private Vector<Device> iPins = new Vector<Device>();
    private Vector<Device> oPins = new Vector<Device>();
    private Vector<Device> circuit = new Vector<Device>();



    public boolean isFileLoaded = false;

    public String getSimulationResult(Vector<Boolean> input){
        if (!this.isFileLoaded)
            return "Please load an lcf file, before using this operation.";
        for (int i = 0; i < this.iPins.size(); i ++){
            this.iPins.get(i).setInput(input.get(i));
        }
        String result = "Simulation Result:\n" + (this.getFormText());
        int num;
        for (num = 0; num < this.iPins.size(); num++)
        {
            result += this.iPins.get(num).getOutput() + " ";
        }
        result += "| ";
        for (num = 0; num < this.oPins.size(); num++)
        {
            result += this.oPins.get(num).getOutput();
            result += num == this.oPins.size() - 1 ? "" : " ";
        }
        result += "\n";
        return result;
    }

    public String getTruthTable() {
        if(this.isFileLoaded){
            String result = "Truth table:\n" + this.getFormText();
            int n = this.iPins.size();
            int rows = (int) Math.pow(2,n);
            for (int i=0; i<rows; i++) {
                for (int j=n-1; j>=0; j--) {
                    int input = (i/(int) Math.pow(2, j))%2;
                    IPin iPin = (IPin) this.iPins.get(Math.abs(j-2));
                    iPin.setInput(input == 1 ? true : false);
                    result += input + " ";
                }
                result += "| ";
                for (int count = 0; count < this.oPins.size(); count ++){
                    result += this.oPins.get(count).getOutput();
                    result += count == this.oPins.size() - 1 ? "" : " ";
                }
                result += "\n";
            }
            return result;
        }
        return "Please load an lcf file, before using this operation.";
    }

    private String getFormText() {
        int count = 0;
        String result = new String();
        for (count = 0; count < this.iPins.size(); count++)
        {
            result += "i ";
        }
        result += "| ";
        for (count = 0; count < this.oPins.size(); count++)
        {
            result += "o";
            result += count == this.oPins.size() - 1 ? "" : " ";
        }
        result += '\n';
        for (count = 1; count <= this.iPins.size(); count++)
        {
            result += String.valueOf(count) + " ";
        }
        result += "| ";
        for (count = 1; count <= this.oPins.size(); count++)
        {
            result += String.valueOf(count);
            result += count == this.oPins.size() ? "" : " ";
        }
        result += "\n";
        for (count = 0; count < this.iPins.size(); count++)
        {
            result += "--";
        }
        result += "+";
        for (count = 0; count < this.oPins.size(); count++)
        {
            result += "--";
        }
        result += "\n";
        return result;
    }

    public boolean load(String lcfFilePath) {
        BufferedReader reader;
        int inputPinsCount = -1, gateCount = -1;
        try {
            reader = new BufferedReader(new FileReader(lcfFilePath));
            //Get the count of input pins
            String line = reader.readLine();
            if (this.isDigit(line))
                inputPinsCount = Integer.parseInt(line);
            //Get the count of gates
            line = reader.readLine();
            if (this.isDigit(line))
                gateCount = Integer.parseInt(line);

            if (inputPinsCount > 0 && inputPinsCount <= 16 && gateCount > 0 && gateCount <= 1000)
            {
                for (int i = 0; i < inputPinsCount; i++)
                    this.iPins.add(new IPin());
                //assume that the circuit are always legal
                createCircuit(reader, gateCount);
                this.isFileLoaded = true;
                reader.close();
                return true;
            }
            else
            {
                System.out.println("File format error!!");
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getCircuitInfomationText() {
        return "Circuit: " + this.iPins.size() +
                " input pins, " + this.oPins.size() +
                " output pins and " + this.circuit.size() + " gates";
    }

    private void createCircuit(BufferedReader reader, int gateCount) throws IOException {
        Vector<String> lines = new Vector<String>();
        String line;
        //createGate
        for (int i = 0; i < gateCount; i++)
        {
            line = reader.readLine();
            createGate(line.charAt(0) - '0');
            lines.add(line);
        }
        //create the connecttion between gates
        for (int i = 0; i < gateCount; i++)
        {
            this.createConnectionBetweenGates(lines.get(i), i);
        }

        //Check the device which doesn't connect to any device & create OPins
        for (int i = 0; i < this.circuit.size(); i++)
        {
            if (!this.circuit.get(i).isConnectToAnotherDevice)
            {
                //create OPins
                this.oPins.add(new OPin(this.circuit.get(i)));
            }
        }

    }

    private void createConnectionBetweenGates(String line, int gateNum) {
        String[] lineSplited = line.split("\\s+");
        //avoid the first(gateType) and last(endSign) element
        for (int i = 1; i < lineSplited.length - 1; i ++){
            int pin = (int)Double.parseDouble(lineSplited[i]);
            //if the input is a user input
            if (pin < 0)
            {
                this.circuit.get(gateNum).addInputPin(this.iPins.get(-pin - 1));
            }
            //if the input is another gate
            else
            {
                this.circuit.get(gateNum).addInputPin(this.circuit.get(pin - 1));
            }
        }
    }

    private void createGate(int gateType) {
        LogicGate gate = LogicGate.values()[gateType];
        switch (gate){
            case AND:{
                this.circuit.add(new GateAND());
                break;
            }
            case OR:{
                this.circuit.add(new GateOR());
                break;
            }
            case NOT:{
                this.circuit.add(new GateNOT());
                break;
            }
        }
    }

    public Vector<Boolean> getInputs(){
        if(this.isFileLoaded){
            String input = new String();
            int count = 0;
            Scanner scanner = new Scanner(System.in);
            Vector<Boolean> inputs = new Vector<>();
            while(count < this.iPins.size())
            {
                System.out.print("Please key in the value of input pin " + (count + 1) + ": ");
                input = scanner.next();
                if (this.isDigit(input) && (Integer.parseInt(input) == 0 || Integer.parseInt(input) == 1))
                {
                    int value = Integer.parseInt(input);
                    inputs.add(value == 1 ? true : false);
                    count++;
                }
			else
                {
                    System.out.println("The value of input pin must be 0/1");
                    return null;
                }
            }
            return inputs;
        }
        System.out.print("Please load an lcf file, before using this operation.");
        return null;
    }

    private boolean isDigit(String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
