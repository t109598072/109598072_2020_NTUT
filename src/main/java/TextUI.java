import java.util.Scanner;
import java.util.Vector;

public class TextUI {
    enum Commands
    {
        LOAD_LCF_FILE, SIMULATTION, DISPLAY_TRUTH_TABLE,EXIT
    };

    private LogicSimulator logicSimulator = new LogicSimulator();

    private boolean isExit = false;

    public void displayMenu(){
        while(!this.isExit){
            System.out.print("1. Load logic circuit file\n" +
                                 "2. Simulation\n"+
                                 "3. Display truth table\n"+
                                 "4. Exit\n"+
                                "Command:");
            this.processCommand();
        }
    }

    private void processCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        // check if the input is valid
        // TODO: check if the input is out of the range of int
        int MAX_COUNT_OF_COMMAND = 4;
        if (!this.isInputDigit(input) || Integer.parseInt(input) > MAX_COUNT_OF_COMMAND || Integer.parseInt(input) <= 0)
        {
            System.out.println("Please input the valid command!");
            return;
        }
        Commands inputCommand = Commands.values()[Integer.parseInt(input) - 1];
        switch (inputCommand){
            case LOAD_LCF_FILE:{
                System.out.print("Please key in a file path: ");
                boolean isLoadSuccesful = this.logicSimulator.load(scanner.next());
                if (isLoadSuccesful)
                    System.out.println(this.logicSimulator.getCircuitInfomationText());
                break;
            }
            case SIMULATTION:{
                Vector<Boolean> inputs = this.logicSimulator.getInputs();
                String result = inputs != null ? this.logicSimulator.getSimulationResult(inputs) : "";
                System.out.println(result);
                break;
            }
            case DISPLAY_TRUTH_TABLE:{
                String result = this.logicSimulator.getTruthTable();
                System.out.println(result);
                break;
            }
            case EXIT:{
                System.exit(0);
            }

        }
    }

    private boolean isInputDigit(String str) {
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
