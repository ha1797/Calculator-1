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
    private List< String > tokens;
    /** string builder that adds strings to it so it can be passed to view so user can see */
    private StringBuilder text;

    /** create a new Calculator object. */
    public Calculator() {
        this.tokens = new ArrayList<>();
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

        // append to expressions list and notify observers.
        this.tokens.add(TorAnd);
        notifyObservers();
    }

    /** calculates the total value of expressions in expressions attribute.*/
    public void equalSign() {
        // make queue for postfix and stack for operators.
        Queue<String> postfix = new LinkedList<>();
        Stack<String> opStack = new Stack<>();

        // transform tokens into postfix notation.






    }

    /** helper function that sorts the "tokens" into postfix form
     *
     * @param postfix the postfix queue passed in from "equalSign()" function.
     * @param opStack the stack of operators passed in form "equalSign()" function.
     */
    private void intoPostfix(Queue<String> postfix, Stack<String> opStack) {
        // loop through the tokens list.
        for (String token : this.tokens) {

            // token is not an operator (it is numeric), so we put it in postfix.
            if (!isOperator(token)) {
                postfix.add(token);
                continue;
            }

            // token is an operator, and opStack is empty, so we put it in opStack.
            if (isOperator(token) && opStack.isEmpty()) {
                opStack.add(token);
                continue;
            }

            // token is an operator, and opStack is not empty, so check precedence.
            if (isOperator(token) && !opStack.isEmpty()) {

                // precedence of token is higher than or equal to precedence of top of opStack.
                if ()
            }
        }
    }

    /** utility function that determines whether a string is numeric or not.
     *
     * @param token the string that is going to checked if it is a number or not.
     * @return true if string is a number, false if not.
     */
    private boolean isNumeric(String token) {
        int length = token.length();
        int i = 0;

        // check if first char of token is a negative sign and length is greater than one.
        // return true if
        if (token.charAt(0) == '-' && length > 1) {
            return true;
        }

        // increase index by one.
        ++i;


    }

    /**
     * utility function that checks if token is an operator.
     *
     * @param token token of from token list.
     * @return true if token is an operator, false if not.
     */
    private boolean isOperator(String token) { //
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    /**
     * utility function that checks whether top has greater or equal precedence to token.
     * @param token a token from tokenList.
     * @param top top of opStack.
     * @return true if token has greater or equal precedence, false if token has less precedence than top.
     */
    private boolean greaterEqualPrecedence(String top, String token) {
        return this.precedence.get(top) >= this.precedence.get(token);
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
