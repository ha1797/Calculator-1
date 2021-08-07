package Calculator;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * view class for calculator application.
 * @author Hoja Arzanesh <ha1797>
 */
public class CalculatorGUI extends Application implements Observer< Calculator > {
    /** the model */
    Calculator model;

    /** CalculatorGUI constructor */
    public CalculatorGUI() {
        // initialize model
        this.model = new Calculator();
    }

    /**
     * starting of the program.
     * @param myStage the window of the program.
     */
    public void start(Stage myStage) {

        // set the title
        myStage.setTitle("Calculator");

        //
    }

    public void update(Calculator calc) {}

    /**
     * main method.
     * @param args command line.
     */
    public static void main(String[] args) {}
}
