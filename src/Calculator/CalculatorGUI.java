package Calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    /** font size of the label attribute (output) */
    int LABEL_FONT_SIZE = 50;
    /** height of label attribute (output) */
    int LABEL_HEIGHT = 75;
    /** font size of each button on pad */
    int BUTTON_FONT_SIZE = 45;
    /** width of each button on pad */
    int BUTTON_WIDTH = 125;
    /** height of each button on pad */
    int BUTTON_HEIGHT = 85;
    /** width of scene */
    int SCENE_WIDTH = 500;
    /** height of scene */
    int SCENE_HEIGHT = 500;

    /** CalculatorGUI constructor */
    public CalculatorGUI() {

        // initialize model
        this.model = new Calculator();

        // initialize output
        this.output = new Label();
        this.output.setMinHeight( LABEL_HEIGHT );
        this.output.autosize();
        this.output.setFont( new Font( LABEL_FONT_SIZE ) );

        // add ourselves as observers.
        this.model.addObserver( this );

        // change the label size
    }

    /** utility function that makes a gridPane with all buttons */
    private GridPane makeGridPane() {

        GridPane gridPane = new GridPane();

        // All clear button
        Button clear = new Button( "AC" );
        clear.setMinWidth( BUTTON_WIDTH );
        clear.setMinHeight( BUTTON_HEIGHT );
        clear.setFont( new Font( BUTTON_FONT_SIZE ) );
        clear.setOnAction( event -> this.model.clear() );
        gridPane.add( clear, 0, 0 );

        // change sign button
        Button changeSign = new Button( "+/-" );
        changeSign.setMinWidth( BUTTON_WIDTH );
        changeSign.setMinHeight( BUTTON_HEIGHT );
        changeSign.setFont( new Font( BUTTON_FONT_SIZE ) );
        changeSign.setOnAction( event -> this.model.changeSign());
        gridPane.add( changeSign, 1, 0 );

        // change percent button
        Button changePercent = new Button( "%" );
        changePercent.setMinWidth( BUTTON_WIDTH );
        changePercent.setMinHeight( BUTTON_HEIGHT );
        changePercent.setFont( new Font( BUTTON_FONT_SIZE ) );
        changePercent.setOnAction( event -> this.model.changePercent() );
        gridPane.add( changePercent, 2, 0 );

        // divide button
        Button divide = new Button( "/" );
        divide.setMinWidth( BUTTON_WIDTH );
        divide.setMinHeight( BUTTON_HEIGHT );
        divide.setFont( new Font( BUTTON_FONT_SIZE ) );
        divide.setOnAction( event -> this.model.Operator( "/" ) );
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
                button.setMinWidth( BUTTON_WIDTH );
                button.setMinHeight( BUTTON_HEIGHT );
                button.setFont( new Font( BUTTON_FONT_SIZE ) );
                button.setOnAction( event -> this.model.Operand( String.valueOf( finalNum ) ) );
                gridPane.add( button, col, row );

                // increase num by one.
                num += 1;
            }

            // decrease num by 5.
            num -= 6;
        }

        // multiply button
        Button multiply = new Button( "*" );
        multiply.setMinWidth( BUTTON_WIDTH );
        multiply.setMinHeight( BUTTON_HEIGHT );
        multiply.setFont( new Font( BUTTON_FONT_SIZE ) );
        multiply.setOnAction( event -> this.model.Operator( "*" ) );
        gridPane.add( multiply, 3, 1 );

        // subtract button
        Button subtract = new Button( "-" );
        subtract.setMinWidth( BUTTON_WIDTH );
        subtract.setMinHeight( BUTTON_HEIGHT );
        subtract.setFont( new Font( BUTTON_FONT_SIZE ) );
        subtract.setOnAction( event -> this.model.Operator( "-" ) );
        gridPane.add( subtract, 3, 2 );

        // add button
        Button add = new Button( "+" );
        add.setMinWidth( BUTTON_WIDTH );
        add.setMinHeight( BUTTON_HEIGHT );
        add.setFont( new Font( BUTTON_FONT_SIZE ) );
        add.setOnAction( event -> this.model.Operator( "+" ) );
        gridPane.add( add, 3, 3 );

        // zero button
        Button zero = new Button( "0" );
        zero.setMinWidth( BUTTON_WIDTH );
        zero.setMinHeight( BUTTON_HEIGHT );
        zero.setFont( new Font( BUTTON_FONT_SIZE ) );
        zero.setOnAction( event -> this.model.Operand( "0" ) );
        gridPane.add( zero, 0, 4, 2, 1 );
        zero.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );

        // decimal button
        Button decimal = new Button( "." );
        decimal.setMinWidth( BUTTON_WIDTH );
        decimal.setMinHeight( BUTTON_HEIGHT );
        decimal.setFont( new Font( BUTTON_FONT_SIZE ) );
        decimal.setOnAction( event -> this.model.addDecimal() );
        gridPane.add( decimal, 2, 4 );

        // solve button
        Button solve = new Button( "=" );
        solve.setMinWidth( BUTTON_WIDTH );
        solve.setMinHeight( BUTTON_HEIGHT );
        solve.setFont( new Font( BUTTON_FONT_SIZE) );
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
        megaPane.setTop( this.output );

        // make and add gridPane to borderPane.
        GridPane gridPane = makeGridPane();
        megaPane.setCenter( gridPane );

        // make the scene, and set borderPane on it.
        Scene myScene = new Scene( megaPane, SCENE_WIDTH, SCENE_HEIGHT );

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
