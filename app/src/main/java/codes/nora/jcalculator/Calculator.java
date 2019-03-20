package codes.nora.jcalculator;

import java.util.Locale;
import java.util.Stack;

public class Calculator {
    public String state;

    private static final String operators = "-+%/x^";
    private static final String operands = "0123456789";

    public Calculator() {
        this.state = "";
    }

    public Calculator(String state) {
        this.state = state;
    }

    public void push(String item) {
        this.state += item;
    }

    public String display() {
        return this.state;
    }

    public void clear() {
        this.state = "";
    }

    public void evaluate() {
        this.state = String.format(Locale.ENGLISH, "%d", evaluatePostfix(toPostfix(state)));
    }

    public String toPostfix(String expression) {
        // Set up the datastructures in use.
        char[] expression_chars = expression.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder out = new StringBuilder(expression.length());
        boolean placingOperand = false;

        for (char c: expression_chars) {
            // For operators, look at the stack and make sure it's in a state where we can add on
            // an operator.
            if (isOperator(c)) {
                placingOperand = false;
                while (!stack.isEmpty()) {
                    if (operatorGreaterOrEqual(stack.peek(), c)) {
                        out.append(stack.pop());
                    } else {
                        break;
                    }
                }
                stack.push(c);
            }  else if (isOperand(c)) {
                if (!placingOperand) {
                    placingOperand = true;
                    out.append(' ');
                    out.append(c);
                } else {
                    out.append(c);
                }
            } else {
                throw new Error("Bad value in parsing expression.");
            }
        }
        while (!stack.empty()) {
            out.append(stack.pop());
        }
        return out.toString();
    }

    public int evaluatePostfix(String expression) {
        char[] expression_chars = expression.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();

        boolean currentlyInOperand = false;

        for (char c: expression_chars) {
            if (isOperand(c)) {
                if (!currentlyInOperand) {
                    currentlyInOperand = true;
                    stack.push(Integer.parseInt(String.format(Locale.ENGLISH, "%c", c)));
                } else {
                    int op = stack.pop();
                    stack.push(op * 10 + Integer.parseInt(String.format(Locale.ENGLISH, "%c", c)));
                }
            } else if (isOperator(c)) {
                currentlyInOperand = false;
                int a = stack.pop();
                int b = stack.pop();
                int result;
                switch (c) {
                    case 'x':
                        result = a * b;
                        stack.push(result);
                        break;
                    case '/':
                        result = b / a;
                        stack.push(result);
                        break;
                    case '+':
                        result = a + b;
                        stack.push(result);
                        break;
                    case '-':
                        result = b - a;
                        stack.push(result);
                        break;
                    case '%':
                        result = b % a;
                        stack.push(result);
                        break;
                    case '^':
                        result = (int) Math.pow((double) b, (double) a);
                        stack.push(result);
                        break;
                }
            } else if (c == ' ') {
                currentlyInOperand = false;
            }
        }
        return stack.pop();
    }
    private int getPrecedence(char operator) {
        int ret = Integer.MAX_VALUE;
        if (operator == '-' || operator == '+') {
            ret = 1;
        } else if (operator == 'x' || operator == '/') {
            ret = 2;
        } else if (operator == '%') {
            ret = 3;
        }
        return ret;
    }
    private boolean operatorGreaterOrEqual(char op1, char op2) {
        return getPrecedence(op1) >= getPrecedence(op2);
    }

    private boolean isOperator(char val) {
        return operators.indexOf(val) >= 0;
    }

    private boolean isOperand(char val) {
        return operands.indexOf(val) >= 0;
    }

    private static String fmt(double d)
    {
        if(d == (long) d)
            return String.format(Locale.ENGLISH, "%d",(long)d);
        else
            return String.format(Locale.ENGLISH, "%s",d);
    }
}
