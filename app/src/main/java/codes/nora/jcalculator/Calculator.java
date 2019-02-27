package codes.nora.jcalculator;

import java.util.Locale;

public class Calculator {
    // The last computed value.
    public double storedValue = 0;
    // The operation in progress.
    public int storedOperation = 0;
    // The value currently being entered
    public Double workingValue = null;

    public static int OP_REPLACE = 0;
    public static int OP_ADD = 1;
    public static int OP_SUBTRACT = 2;
    public static int OP_MUTLIPLY = 3;
    public static int OP_DIVIDE = 4;
    public static int OP_EXPONENT = 5;
    public static int OP_MODULO = 6;

    public Calculator() {}

    public Calculator(double storedValue, int storedOperation, Double workingValue) {
        this.storedValue = storedValue;
        this.storedOperation = storedOperation;
        this.workingValue = workingValue;
    }

    public String display() {
        if (storedOperation == OP_REPLACE) {
            if (workingValue == null) {
                return String.format(Locale.ENGLISH, "%s", fmt(storedValue));
            } else {
                return String.format(Locale.ENGLISH, "%s", fmt(workingValue));
            }
        } else {
            if (workingValue == null) {
                return String.format(Locale.ENGLISH, "%s%s", fmt(storedValue), operationDisplay());
            } else {
                return String.format(Locale.ENGLISH, "%s%s%s", fmt(storedValue), operationDisplay(), fmt(workingValue));
            }
        }
    }

    public void pushDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new NumberFormatException("Cannot push a digit outside of range 0 to 9.");
        }

        if (workingValue == null) {
            workingValue = (double) digit;
        } else {
            workingValue = workingValue * 10 + digit;
        }
    }

    public void pushOperation(int operation) {
        evaluate();
        storedOperation = operation;
    }

    public void evaluate() {
        // Pressing equals with no working value is meaningless.
        if (workingValue == null) {
            return;
        }

        if (storedOperation == OP_REPLACE) {
            storedValue = workingValue;
        } else if (storedOperation == OP_ADD) {
            storedValue += workingValue;
        } else if (storedOperation == OP_SUBTRACT) {
            storedValue -= workingValue;
        } else if (storedOperation == OP_MUTLIPLY) {
            storedValue *= workingValue;
        } else if (storedOperation == OP_DIVIDE) {
            storedValue /= workingValue;
        } else if (storedOperation == OP_EXPONENT) {
            storedValue = Math.pow(storedValue, workingValue);
        } else if (storedOperation == OP_MODULO) {
            storedValue = (double) (Math.floor(storedValue) % Math.floor(workingValue));
        }

        workingValue = null;
        storedOperation = OP_REPLACE;
    }

    public void clear() {
        workingValue = null;
        storedValue = 0;
        storedOperation = OP_REPLACE;
    }

    private String operationDisplay() {
        if (storedOperation == OP_REPLACE) {
            return "";
        } else if (storedOperation == OP_ADD) {
            return "+";
        } else if (storedOperation == OP_SUBTRACT) {
            return "-";
        } else if (storedOperation == OP_MUTLIPLY) {
            return "×";
        } else if (storedOperation == OP_DIVIDE) {
            return "÷";
        }  else if (storedOperation == OP_EXPONENT) {
            return "^";
        }  else if (storedOperation == OP_MODULO) {
            return "%";
        } else {
            return "?";
        }
    }

    private static String fmt(double d)
    {
        if(d == (long) d)
            return String.format(Locale.ENGLISH, "%d",(long)d);
        else
            return String.format(Locale.ENGLISH, "%s",d);
    }
}
