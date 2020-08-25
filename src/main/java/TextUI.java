import java.util.Scanner;

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
        Commands inputCommand = Commands.values()[Integer.parseInt(input) - 1];
        if (!this.isInputDigit(input) || Commands.EXIT.compareTo(inputCommand) < 0 || Commands.LOAD_LCF_FILE.compareTo(inputCommand) > 0)
        {
            System.out.println("Please input the valid command!");
            return;
        }

        switch (inputCommand){
            case LOAD_LCF_FILE:{
                System.out.print("Please key in a file path: ");
                this.logicSimulator.load(scanner.next());
                break;
            }
            case SIMULATTION:{
                String result = this.logicSimulator.setInputValue() ? this.logicSimulator.getSimulationResult() : "";
                System.out.println(result);
                break;
            }
            case DISPLAY_TRUTH_TABLE:{
                String result = this.logicSimulator.generateTruthTable();
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
