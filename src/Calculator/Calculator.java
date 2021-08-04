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
        intoPostfix(postfix, opStack);

        // loop through the postfix queue, and put each token into an ArrayList.
        List<String> elements = new ArrayList<>();
        while (!postfix.isEmpty()) {
            elements.add(postfix.remove());
        }

        // loop through elements list until encounter an operator,
        // then do that operation with previous two numbers.
        String calculated = ""; // saved result of operation of previous two nums.
        double calc = 0; // final result of "calculated" parsed into double type.
        for (int i = 0; i < elements.size(); ++i) {

            // token at index is an operator
            if (isOperator(elements.get(i))) {

                // no result saved in "calculated" string.
                if (calculated.equals("")) {

                    // solve expression and save result
                    switch (elements.get(i)) {

                        // token at index is a "+"
                        case "+" -> calculated = String.valueOf(Double.parseDouble(elements.get(i - 2)) +
                                Double.parseDouble(elements.get(i - 2)));

                        // token at index is a "-"
                        case "-" -> calculated = String.valueOf(Double.parseDouble(elements.get(i - 2)) -
                                Double.parseDouble(elements.get(i - 2)));

                        // token at index is a "*"
                        case "*" -> calculated = String.valueOf(Double.parseDouble(elements.get(i - 2)) *
                                Double.parseDouble(elements.get(i - 2)));

                        // token at index is a "/"
                        case "/" -> calculated = String.valueOf(Double.parseDouble(elements.get(i - 2)) /
                                Double.parseDouble(elements.get(i - 2)));
                    }
                }

                else {

                }
            }
        }





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
                if (greaterEqualPrecedence(token, opStack.peek())) {
                    opStack.push(token);
                }

                // precedence of token is less than precedence of top of stack.
                else {

                    // loop until token has greater or equal precedence to top.
                    while (!greaterEqualPrecedence(token, opStack.peek())) {
                        postfix.add(opStack.pop());
                    }
                    // push token to opStack.
                    opStack.push(token);
                }
            }
        }

        // add remaining tokens in opStack to postfix.
        while (!opStack.isEmpty()) {
            postfix.add(opStack.pop());
        }
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
     * utility function that checks whether the token has greater or equal precedence to top of opStack.
     * @param token a token from tokenList.
     * @param top top of opStack.
     * @return true if token has greater or equal precedence, false if token has less precedence than top.
     */
    private boolean greaterEqualPrecedence(String token, String top) {
        return this.precedence.get(token) >= this.precedence.get(top);
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
