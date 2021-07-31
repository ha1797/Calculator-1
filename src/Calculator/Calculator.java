package Calculator;

import java.util.*;

/**
 * model class for calculator application
 * @author Hoja Arzanesh <ha1797>
 */
public class Calculator {
    /** The add operator */
    public final static String ADD = "+";
    /** The subtract operator */
    public final static String SUBTRACT = "-";
    /** The multiply operator */
    public final static String MULTIPLY = "*";
    /** The divide operator */
    public final static String DIVIDE = "/";

    /** observers attribute. */
    private final List< Observer< Calculator > > observers = new LinkedList<>();
    /** hash map for user-inputted operators and operands. */
    private HashMap<String, Integer> precedence = new HashMap<>();
    /** The list of converted postfix expressions */
    private List< String > expressions;
    /** string builder that adds strings to it so it can be passed to view so user can see */
    private StringBuilder text;

    /** create a new Calculator object. */
    public Calculator() {
        this.expressions = new ArrayList<>();
        this.text = new StringBuilder();

        /* populate the precedence map */
        this.precedence = new HashMap<>();
        this.precedence.put(MULTIPLY, 3);
        this.precedence.put(DIVIDE, 3);
        this.precedence.put(ADD, 2);
        this.precedence.put(SUBTRACT, 2);
    }

    /**
     * add operator or operand to string
     *
     * @param TorAnd the string passed in from view.
     */
    public void operatorAndOperand(String TorAnd) {
        // append the passed down string.
        this.text.append(TorAnd);

        // append to expressions list and sort.
        this.expressions.add(TorAnd);

        notifyObservers();
    }

    /** calculates the total value of expressions in expressions attribute.*/
    public void equalSign() {

    }

    /** helper function that sorts the "expressions" based on precedence */
    private void sortList() {
        // check to see if list has greater than one element.
        if (this.expressions.size() > 1) {

        }
    }

    /**
     * The view calls this to add itself as an observer.
     *
     * @param observer the view
     */
    public void addObserver(Observer< Calculator > observer) {
        this.observers.add(observer);
    }

    /**
     * The model's state has changed (the counter), so inform the view via
     * the update method
     */
    private void notifyObservers() {
        for (Observer< Calculator > obs : this.observers) {
            obs.update(this);
        }
    }

}
