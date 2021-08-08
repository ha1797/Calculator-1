package Calculator;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * view class for calculator application.
 * @author Hoja Arzanesh <ha1797>
 */
public class CalculatorGUI extends Application implements Observer< Calculator > {
    /** the model */
    Calculator model;
    TextField output;


    /** CalculatorGUI constructor */
    public CalculatorGUI() {
        // initialize model
        this.model = new Calculator();
        // initialize output
        this.output = new TextField();
    }

    /** utility function that makes a gridPane with all buttons */
    private GridPane makeGridPane() {

        // All clear button
        Button clear = new Button("AC");
        clear.setOnAction( event -> model.clear() );

        // change sign button
        Button changeSign = new Button("+/-");
        changeSign.setOnAction( event -> model.changeSign());



    }

    /**
     * starting of the program.
     * @param myStage the window of the program.
     */
    public void start(Stage myStage) {

        // set the title
        myStage.setTitle("Calculator");

        // make border pane.
        BorderPane megaPane = new BorderPane();

        //
    }

    public void update(Calculator calc) {}

    /**
     * main method.
     * @param args command line.
     */
    public static void main(String[] args) {}
}
