package Calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * view class for calculator application.
 * @author Hoja Arzanesh <ha1797>
 */
public class CalculatorGUI extends Application implements Observer< Calculator > {

    /** the model */
    Calculator model;
    /** label that will display the user inputs and answer */
    Label output;



    /** CalculatorGUI constructor */
    public CalculatorGUI() {

        // initialize model
        this.model = new Calculator();
        // initialize output
        this.output = new Label();
        this.output.setMinHeight( 75 );
        this.output.autosize();
        this.output.setFont( new Font( 50 ) );

        // add ourselves as observers.
        this.model.addObserver( this );

        // change the label size
    }

    /** utility function that makes a gridPane with all buttons */
    private GridPane makeGridPane() {

        GridPane gridPane = new GridPane();

        // All clear button
        Button clear = new Button("AC");
        clear.setMinWidth( 125 );
        clear.setMinHeight( 85 );
        clear.setFont( new Font( 25 ) );
        clear.setOnAction( event -> this.model.clear() );
        gridPane.add( clear, 0, 0 );

        // change sign button
        Button changeSign = new Button("+/-");
        changeSign.setMinWidth( 125 );
        changeSign.setMinHeight( 85 );
        changeSign.setFont( new Font( 25 ) );
        changeSign.setOnAction( event -> this.model.changeSign());
        gridPane.add( changeSign, 1, 0 );

        // change percent button
        Button changePercent = new Button("%");
        changePercent.setMinWidth( 125 );
        changePercent.setMinHeight( 85 );
        changePercent.setFont( new Font( 25 ) );
        changePercent.setOnAction( event -> this.model.changePercent() );
        gridPane.add( changePercent, 2, 0 );

        // divide button
        Button divide = new Button("/");
        divide.setMinWidth( 125 );
        divide.setMinHeight( 85 );
        changePercent.setFont( new Font( 25 ) );
        divide.setOnAction( event -> this.model.Operator("/") );
        gridPane.add( divide, 3, 0 );

        // make buttons 1-9 using for loop.
        int num = 7;
        for ( int row = 1; row < 4; ++row ) {

            // make another for loop
            for ( int col = 0; col < 3; ++col ) {

                // make variable final for lambda expression.
                int finalNum = num;

                // make button, set controller, and add to gridPane.
                Button button = new Button( String.valueOf( num ) );
                button.setMinWidth( 125 );
                button.setMinHeight( 85 );
                button.setFont( new Font( 25 ) );
                button.setOnAction( event -> this.model.Operand( String.valueOf( finalNum ) ) );
                gridPane.add( button, col, row );

                // increase num by one.
                num += 1;
            }

            // decrease num by 5.
            num -= 5;
        }

        // multiply button
        Button multiply = new Button( "*" );
        multiply.setMinWidth( 125 );
        multiply.setMinHeight( 85 );
        multiply.setFont( new Font( 25 ) );
        multiply.setOnAction( event -> this.model.Operator( "*" ) );
        gridPane.add( multiply, 3, 1 );

        // subtract button
        Button subtract = new Button( "-" );
        subtract.setMinWidth( 125 );
        subtract.setMinHeight( 85 );
        subtract.setFont( new Font( 25 ) );
        subtract.setOnAction( event -> this.model.Operator( "-" ) );
        gridPane.add( subtract, 3, 2 );

        // add button
        Button add = new Button( "+" );
        add.setMinWidth( 125 );
        add.setMinHeight( 85 );
        add.setFont( new Font( 25 ) );
        add.setOnAction( event -> this.model.Operator( "+" ) );
        gridPane.add( add, 3, 3 );

        // zero button
        Button zero = new Button( "0" );
        zero.setMinWidth( 125 );
        zero.setMinHeight( 85 );
        zero.setFont( new Font( 25 ) );
        zero.setOnAction( event -> this.model.Operand( "0" ) );
        gridPane.add( zero, 0, 4, 2, 1 );
        zero.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );

        // decimal button
        Button decimal = new Button( "." );
        decimal.setMinWidth( 125 );
        decimal.setMinHeight( 85 );
        decimal.setFont( new Font( 25 ) );
        decimal.setOnAction( event -> this.model.addDecimal() );
        gridPane.add( decimal, 2, 4 );

        // solve button
        Button solve = new Button( "=" );
        solve.setMinWidth( 125 );
        solve.setMinHeight( 85 );
        solve.setFont( new Font( 25 ) );
        solve.setOnAction( event -> this.model.equalSign() );
        gridPane.add( solve, 3, 4 );

        // return completed gridPane.
        return gridPane;
    }

    /**
     * starting of the program.
     * @param myStage the window of the program.
     */
    public void start(Stage myStage) {

        // set the title
        myStage.setTitle( "Calculator" );

        // make border pane.
        BorderPane megaPane = new BorderPane();

        // set the label on top.
        megaPane.setTop(output);

        // make and add gridPane to borderPane.
        GridPane gridPane = makeGridPane();
        megaPane.setCenter( gridPane );

        // make the scene, and set borderPane on it.
        Scene myScene = new Scene( megaPane, 500, 500 );

        // set the scene.
        myStage.setScene( myScene );

        // show stage.
        myStage.setResizable( false );
        myStage.show();
    }

    /**
     * update method that updates the label attribute (output) to what the user inputted.
     * @param calc the subject.
     */
    public void update(Calculator calc) {
        this.output.setText( calc.getText() );
    }

    /**
     * main method.
     * @param args command line.
     */
    public static void main(String[] args) {
        Application.launch();
    }
}
