package com.kientran.week04;


public class CalculatorEngine {

    final static String ZERO = "0";
    final static String PLUS_SIGN = "+";
    final static String MINUS_SIGN = "\u2212";
    final static String MULTIPLY_SIGN = "\u00D7";
    final static String DEVIDE_SIGN = "\u00F7";
    final static String PERCENT_SIGN = "%";
    final static String PLUS_OR_MINUS_SIGN = "+/-";
    final static String CLEAR_SIGN = "AC";
    final static String EQUAL_SIGN = "=";
    final static String DOT_SIGN = ".";
    final static String SQUARE_ROOT_SIGN = "\u221a";
    final static String MEMORY_ADD = "M+";
    final static String MEMORY_SUBTRACT = "M-";
    final static String MEMORY_RECALL = "MR";
    final static String MEMORY_CLEAR = "MC";


    private String calcResult = "";
    private String calcHistory = "";
    private String storedOperator = "";
    private String lastInputtedKey = "";

    private Double result = new Double(0);
    private Double memory = new Double(0);


    private Boolean isCalculated = false;
    private int historyLineLength;
    private int maxInputLength;

    public CalculatorEngine() {
        historyLineLength = 80;
        maxInputLength = 28;
    }

    public CalculatorEngine(int historyLineLength, int maxInputLength) {
        this.historyLineLength = historyLineLength;
        this.maxInputLength = maxInputLength;
    }

    public void calc(String value) {

        switch (value) {
            case CLEAR_SIGN:
                calcHistory = "";
                calcResult = "";
                result = new Double(0);
                storedOperator = "";
                isCalculated = false;
                break;
            case PERCENT_SIGN:
                calculatePercentage();
                break;
            case SQUARE_ROOT_SIGN:
                calculateSquareRoot();
                break;
            case MEMORY_CLEAR:
                memory = new Double(0);
                break;
            case MEMORY_RECALL:
                if (lastInputtedKey.equals(EQUAL_SIGN)) {
                    calcHistory = " | MR: " + getDisplayText(memory) + " | ";
                    calcResult = getDisplayText(memory);
                    storedOperator = "";
                    result = memory;
                    isCalculated = false;
                } else {
                    if (isCalculated == true) {
                        isCalculated = false;

                    }
                    calcResult = getDisplayText(memory);
                    calcHistory += " | MR: " + getDisplayText(memory) + " | ";
                }


                break;
            case MEMORY_ADD:
                memoryOperation(true);
                break;
            case MEMORY_SUBTRACT:
                memoryOperation(false);
                break;
            case DEVIDE_SIGN:
            case MULTIPLY_SIGN:
            case PLUS_SIGN:
            case MINUS_SIGN:
                calculateOnOperator(value);
                break;
            case EQUAL_SIGN:
                calculateOnEqual(false);
                break;
            case PLUS_OR_MINUS_SIGN:
                if (!calcResult.isEmpty()) {

                    if (calcResult.indexOf("-") == -1) {
                        calcResult = "-" + calcResult;
                    } else {
                        calcResult = calcResult.substring(1);
                    }
                }
                break;
            case DOT_SIGN:
                if (!calcResult.isEmpty() && isCalculated == false && calcResult.indexOf(".") == -1 && calcResult.length() < maxInputLength) {
                    calcHistory += value;
                    calcResult += value;
                }
                break;
            default:
                if (lastInputtedKey.equals(EQUAL_SIGN) || lastInputtedKey.equals(PERCENT_SIGN)) {
                    calcHistory = value;
                    calcResult = value;
                    storedOperator = "";
                    result = new Double(0);
                    isCalculated = false;
                } else if (isCalculated == true) {
                    isCalculated = false;
                    calcResult = value;
                    calcHistory += value;
                } else {
                    if (calcResult.length() < maxInputLength && (!value.equals(ZERO) || (value.equals(ZERO) && !calcResult.equals(ZERO)))) {
                        calcResult += value;
                        calcHistory += value;
                    }
                }

                break;
        }

        lastInputtedKey = value;

    }

    private void memoryOperation(boolean isAdd) {
        if (!calcResult.isEmpty()) {
            String value = calcResult;
            if (!isAdd) {
                value = "-" + value;
                calcHistory += " | M-: " + calcResult + " | ";
            } else {
                calcHistory += " | M+: " + calcResult + " | ";
            }

            memory = memory + Double.valueOf(value);
        }
    }

    private void calculatePercentage() {
        if (!calcResult.isEmpty()) {
            if (!storedOperator.isEmpty()) {
                Double temp_result;
                switch (storedOperator) {
                    case PLUS_SIGN:
                    case MINUS_SIGN:
                        temp_result = result * (new Double(calcResult)) / 100;
                        calcResult = getDisplayText(temp_result);
                        calculateOnEqual(true);
                        break;
                    case MULTIPLY_SIGN:
                    case DEVIDE_SIGN:
                        temp_result = (new Double(calcResult)) * 0.01;
                        calcResult = getDisplayText(temp_result);
                        calculateOnEqual(true);
                        break;
                    default:
                        break;
                }

            } else {
                result = (new Double(calcResult)) * 0.01;
                calcResult = getDisplayText(result);
                calcHistory = removeLastOperator(calcHistory) + PERCENT_SIGN + EQUAL_SIGN + getDisplayText(result);
            }
            isCalculated = true;
            storedOperator = "";
        }
    }

    private void calculateSquareRoot() {
        if (!calcResult.isEmpty()) {
            Double num = new Double((calcResult));
            if (num > 0) {
                result = Math.sqrt(num);

            }
            calcResult = getDisplayText(result);
            calcHistory = removeLastOperator(calcHistory) + SQUARE_ROOT_SIGN + EQUAL_SIGN + getDisplayText(result);
            isCalculated = true;
            storedOperator = "";
        }
    }

    private void calculateOnEqual(Boolean isOnPercent) {
        if (!calcResult.isEmpty() && !storedOperator.isEmpty()) {

            if (isCalculated) {

                Double second = new Double(result);

                result = calculate(result, second, storedOperator);

                calcHistory = removeLastOperator(calcHistory) + storedOperator + getDisplayText(second) + (isOnPercent?PERCENT_SIGN:"") + EQUAL_SIGN + getDisplayText(result);
            } else {

                result = calculate(result, new Double(calcResult), storedOperator);


                calcHistory = calcHistory + (isOnPercent?PERCENT_SIGN:"") + EQUAL_SIGN + getDisplayText(result);

            }
            calcResult = getDisplayText(result);
            isCalculated = true;

        }
    }

    private void calculateOnOperator(String operator) {
        if (!calcResult.isEmpty()) {
            if (storedOperator.isEmpty()) {
                result = new Double(calcResult);
                calcHistory = calcHistory + operator;
                isCalculated = true;
            } else {
                if (isCalculated) {
                    calcHistory = removeLastOperator(calcHistory) + operator;
                } else {
                    result = calculate(result, new Double(calcResult), storedOperator);
                    isCalculated = true;
                    calcHistory = calcHistory + "=" + getDisplayText(result) + operator;
                    calcResult = getDisplayText(result);
                }
            }

            storedOperator = operator;
        }

    }

    private String removeLastOperator(String value) {
        if (value.length() > 0) {
            String lastChar = value.substring(value.length() - 1);
            try {
                Integer.valueOf(lastChar);
            } catch (NumberFormatException e) {
                return value.substring(0, value.length() - 1);
            }
        }
        return value;

    }

    private Double calculate(Double first, Double second, String operator) {
        Double result;

        result = new Double(0);

        switch (operator) {
            case PLUS_SIGN:
                result = first + second;
                break;
            case MINUS_SIGN:
                result = first - second;
                break;
            case MULTIPLY_SIGN:
                result = first * second;
                break;
            case DEVIDE_SIGN:
                result = first / second;
                break;
            default:
                break;
        }

        return result;
    }

    public String getDisplayText(Double num) {

        long iPart;
        double fPart;

        iPart = (long) num.doubleValue();
        fPart = num - iPart;

        if (fPart == 0.0) {
            return String.valueOf(num.longValue());
        } else {

            return num.toString();
        }

    }

    public String getCalcResult() {
        return calcResult;
    }

    public String getCalcHistory() {
        if (calcHistory.length() <= historyLineLength) {
            return calcHistory;
        } else {

            return calcHistory.substring(calcHistory.length() - historyLineLength);
        }
    }


}
