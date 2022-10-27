package com.example.a3laboratorinis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvMain, tvInputList;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnRoot, btnDivide, btnMultiply, btnMinus, btnPlus, btnPlusMinus, btnComma, btnEqual;
    Button btnBackspace, btnClear;
    boolean numberWasClicked = false;
    boolean operatorWasClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMain = findViewById(R.id.tvMain);
        tvInputList = findViewById(R.id.tvInputList);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnRoot = findViewById(R.id.btnRoot);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnPlusMinus = findViewById(R.id.btnPlusMinus);
        btnComma = findViewById(R.id.btnComma);
        btnEqual = findViewById(R.id.btnEqual);

        btnBackspace = findViewById(R.id.btnBackspace);
        btnClear = findViewById(R.id.btnClear);

    }

    public void updateText(String strToAdd) {
        String bottomStr = tvMain.getText().toString();
        String newBottomStr;

        if (operatorWasClicked) {
            bottomStr = "0";
            operatorWasClicked = false;
        }

        if (bottomStr.equals("0")) {
            if (strToAdd.equals(".")) { // If was pressed , (.) button
                newBottomStr = bottomStr + strToAdd;
            }
            else if (strToAdd.equals("+-")) { // 0  -->  +-  -->  ==0
                newBottomStr = bottomStr;
            }
            else {  // If strToAdd is not comma (dot) or +-
                newBottomStr = strToAdd;
            }
        }
        else {
            if (bottomStr.contains(".") && strToAdd.equals(".")) { // If there was comma (dot) and you press it again
                newBottomStr = bottomStr;
            }
            else if (strToAdd.equals("+-")) {   // If was pressed +- button
                if (bottomStr.contains("-")) {  // If number contains minus in front: -0,  -0,1  -1  -10
                    newBottomStr = bottomStr.substring(1);
                }
                else {  // If number doesn't contain minus in front: 0,  0,1  1  10
                    newBottomStr = "-" + bottomStr;
                }
            }
            else {  // If strToAdd is not comma (dot) or +-
                newBottomStr = bottomStr + strToAdd;
            }
        }
        numberWasClicked = true;
        tvMain.setText(newBottomStr);
    }

    public void arithmeticOperations(String operator) {
        String bottomStr = tvMain.getText().toString();
        String topStr = tvInputList.getText().toString();
        String newTopStr;
        String[] parts;
        String lastPart;
        int lastPartLength;
        String stringWithoutLastRoot;

        if (numberWasClicked) { // If "0," --> "0",   if "0,0" --> "0,0"
            if (bottomStr.substring(bottomStr.length() - 1).equals(".")) {
                newTopStr = bottomStr.substring(0, bottomStr.length() - 1);
                tvMain.setText(newTopStr);
                if (operator.equals("√")) {
                    if (newTopStr.charAt(0) == '-') {
                        tvMain.setText("Invalid input");
                        tvInputList.setText("");
                    }
                    else {
                        newTopStr = topStr + operator + "(" + newTopStr + ")";
                        tvInputList.setText(newTopStr);
                        numberWasClicked = false;
                    }
                }
                else {
                    newTopStr = topStr + newTopStr + operator;
                    tvInputList.setText(newTopStr);
                    numberWasClicked = false;
                }
            }
            else {
                if (operator.equals("√")) {
                    if (!topStr.equals("") && !topStr.substring(topStr.length() - 1).matches("[-+*/]")){
                        parts = topStr.split("[-+*/]");
                        lastPart = parts[parts.length - 1];
                        lastPartLength = lastPart.length();
                        stringWithoutLastRoot = topStr.substring(0, topStr.length() - lastPartLength);

                        if (bottomStr.charAt(0) == '-') {
                            tvMain.setText("Invalid input");
                            tvInputList.setText("");
                        }
                        else {
                            newTopStr = stringWithoutLastRoot + operator + "(" + bottomStr + ")";
                            tvInputList.setText(newTopStr);
                            numberWasClicked = false;
                        }
                    }
                    else {
                        if (bottomStr.charAt(0) == '-') {
                            tvMain.setText("Invalid input");
                            tvInputList.setText("");
                        }
                        else {
                            newTopStr = topStr + operator + "(" + bottomStr + ")";
                            tvInputList.setText(newTopStr);
                            numberWasClicked = false;
                        }
                    }
                }
                else if (topStr.equals("")) {
                    newTopStr = bottomStr + operator;
                    tvInputList.setText(newTopStr);
                    numberWasClicked = false;
                }
                else {
                    if (topStr.substring(topStr.length() - 1).equals(")")) {
                        parts = topStr.split("[-+*/]");
                        lastPart = parts[parts.length-1];
                        lastPartLength = lastPart.length();
                        stringWithoutLastRoot = topStr.substring(0, topStr.length() - lastPartLength);
                        newTopStr = stringWithoutLastRoot + bottomStr + operator;
                        tvInputList.setText(newTopStr);
                        numberWasClicked = false;
                    }
                    else {
                        newTopStr = topStr + bottomStr + operator;
                        numberWasClicked = false;
                        tvInputList.setText(newTopStr);
                    }
                }
            }
        }
        else {
            if (topStr.equals("")) {
                if (operator.equals("√")) {
                    if (bottomStr.charAt(0) == '-') {
                        tvMain.setText("Invalid input");
                        tvInputList.setText("");
                    }
                    else {
                        newTopStr = operator + "(" + bottomStr + ")";
                        tvInputList.setText(newTopStr);
                    }
                }
                else {
                    newTopStr = bottomStr + operator;
                    tvInputList.setText(newTopStr);
                }
            }
            else if (topStr.substring(topStr.length() - 1).equals(operator)) {
                newTopStr = topStr;
                tvInputList.setText(newTopStr);
            }
            else {
                if (topStr.substring(topStr.length() - 1).equals(")")) {
                    if (operator.equals("√")) {
                        parts = topStr.split("[-+*/]");
                        lastPart = parts[parts.length-1];
                        lastPartLength = lastPart.length();
                        stringWithoutLastRoot = topStr.substring(0, topStr.length() - lastPartLength);

                        if (bottomStr.charAt(0) == '-') {
                            tvMain.setText("Invalid input");
                            tvInputList.setText("");
                        }
                        else {
                            newTopStr = stringWithoutLastRoot + operator + "(" + lastPart + ")";
                            tvInputList.setText(newTopStr);
                        }
                    }
                    else {
                        newTopStr = topStr + operator;
                        tvInputList.setText(newTopStr);
                    }
                }
                else {
                    if (operator.equals("√")) {
                        if (bottomStr.charAt(0) == '-') {
                            tvMain.setText("Invalid input");
                            tvInputList.setText("");
                        }
                        else {
                            newTopStr = topStr + operator + "(" + bottomStr + ")";
                            tvInputList.setText(newTopStr);
                        }
                    }
                    else {
                        newTopStr = topStr.substring(0, topStr.length() - 1);
                        newTopStr = newTopStr + operator;
                        tvInputList.setText(newTopStr);
                    }
                }
            }
        }
        operatorWasClicked = true;
    }

    public void deleteOneChar() {
        String bottomStr = tvMain.getText().toString();
        String newBottomStr;
        if (bottomStr.length() == 1) {  // If bottom text is:  0, 1 ... 9.
            tvMain.setText("0");
        }
        else if (bottomStr.length() == 2 && bottomStr.charAt(0) == '-') {   // If bottom text is:  -1, -2 ... -9.
            tvMain.setText("0");
        }
        else {  // If bottom text is:  10, 11, -10, -11 ... infinity.
            newBottomStr = bottomStr.substring(0, bottomStr.length() - 1);
            tvMain.setText(newBottomStr);
        }
    }

    public void clearAllText() {
        tvMain.setText("0");
        tvInputList.setText("");
    }

    public void calculateResult(String operator) {
        String topStr = tvInputList.getText().toString();
        String bottomStr = tvMain.getText().toString();
        String newTopStr;
        String newBottomStr;

        if (topStr.equals("")) {
            if (bottomStr.substring(bottomStr.length() - 1 ).equals(".")) {
                newTopStr = bottomStr.substring(0, bottomStr.length() - 1) + operator;
                newBottomStr = bottomStr.substring(0, bottomStr.length() - 1);
                tvInputList.setText(newTopStr);
                tvMain.setText(newBottomStr);
            }
            else {
                newTopStr = bottomStr + operator;
                newBottomStr = bottomStr;
                tvInputList.setText(newTopStr);
                tvMain.setText(newBottomStr);
            }
        }
        else {
            if (topStr.substring(topStr.length() - 1).equals("=")) {
                return;
            }

            int index = 0;
            double result = 0;
            Log.e("result", String.valueOf(result));
            String partWithoutOperator;
            String partOperator;
            String firstMinus = "";
            newTopStr = topStr + bottomStr;
            String[] parts = newTopStr.split("(?<=[-+*/])");

            while (index < parts.length) {
                if (index == 0) {
                    if (parts[index].equals("-")) {
                        firstMinus = parts[index];
                    }
                    else {
                        if (parts[index].charAt(0) == '√') {
                            String[] rootAndNumber = parts[index].split("(?<=[)])");    // √(10)10 split to:  √(10)   and   10
                            partWithoutOperator = rootAndNumber[0].substring(2, rootAndNumber[0].length() - 1);
                            result = result + Math.sqrt(Double.parseDouble(partWithoutOperator));
                        }
                        else {
                            partWithoutOperator = parts[index].substring(0, parts[index].length() - 1);
                            result = result + Double.parseDouble(partWithoutOperator);
                        }
                    }
                }
                else {
                    if (firstMinus.equals("-")) {
                        partWithoutOperator = parts[index].substring(0, parts[index].length() - 1);
                        result = result - Double.parseDouble(partWithoutOperator);
                        firstMinus = "";
                    }
                    else {
                        if (parts[index].equals("-")) {
                            //Don't do anything
                        }
                        else if (parts[index].charAt(0) == '√') {
                            if (parts[index].substring(parts[index].length() - 1).matches("[-+*/]")) {
                                partWithoutOperator = parts[index].substring(2, parts[index].length() - 2);
                                partOperator = parts[index - 1].substring(parts[index - 1].length() - 1);

                                switch (partOperator) {
                                    case "+":
                                        result = result + Math.sqrt(Double.parseDouble(partWithoutOperator));
                                        break;
                                    case "-":
                                        result = result - Math.sqrt(Double.parseDouble(partWithoutOperator));
                                        break;
                                    case "*":
                                        result = result * Math.sqrt(Double.parseDouble(partWithoutOperator));
                                        break;
                                    case "/":
                                        result = result / Math.sqrt(Double.parseDouble(partWithoutOperator));
                                        break;
                                }
                            }
                            else {  // If last and has √(10)10
                                String[] rootAndNumber = parts[index].split("(?<=[)])");    // √(10)10 split to:  √(10)   and   10
                                partWithoutOperator = rootAndNumber[0].substring(2, rootAndNumber[0].length() - 1);
                                partOperator = parts[index - 1].substring(parts[index - 1].length() - 1);

                                switch (partOperator) {
                                    case "+":
                                        result = result + Math.sqrt(Double.parseDouble(partWithoutOperator));
                                        break;
                                    case "-":
                                        result = result - Math.sqrt(Double.parseDouble(partWithoutOperator));
                                        break;
                                    case "*":
                                        result = result * Math.sqrt(Double.parseDouble(partWithoutOperator));
                                        break;
                                    case "/":
                                        result = result / Math.sqrt(Double.parseDouble(partWithoutOperator));
                                        break;
                                }
                            }
                        }
                        else {
                            if (parts[index - 1].equals("-")) {
                                if (parts[index].substring(parts[index].length() - 1).matches("[-+*/]")) {
                                    partWithoutOperator = parts[index].substring(0, parts[index].length() - 1);
                                }
                                else {
                                    partWithoutOperator = parts[index];
                                }

                                partOperator = parts[index - 2].substring(parts[index - 2].length() - 1);

                                switch (partOperator) {
                                    case "+":
                                        result = result - Double.parseDouble(partWithoutOperator);
                                        break;
                                    case "-":
                                        result = result + Double.parseDouble(partWithoutOperator);
                                        break;
                                    case "*":
                                        result = result * Double.parseDouble("-" + partWithoutOperator);
                                        break;
                                    case "/":
                                        result = result / Double.parseDouble("-" + partWithoutOperator);
                                        break;
                                }
                            }
                            else {
                                if (parts[index].substring(parts[index].length() - 1).matches("[-+*/]")) {
                                    partWithoutOperator = parts[index].substring(0, parts[index].length() - 1);
                                }
                                else {
                                    partWithoutOperator = parts[index];
                                }

                                partOperator = parts[index - 1].substring(parts[index - 1].length() - 1);

                                switch (partOperator) {
                                    case "+":
                                        result = result + Double.parseDouble(partWithoutOperator);
                                        break;
                                    case "-":
                                        result = result - Double.parseDouble(partWithoutOperator);
                                        break;
                                    case "*":
                                        result = result * Double.parseDouble(partWithoutOperator);
                                        break;
                                    case "/":
                                        if (partWithoutOperator.equals("0")) {
                                            tvMain.setText("Cannot divide by zero");
                                            tvInputList.setText("");
                                            return;
                                        }
                                        else {
                                            result = result / Double.parseDouble(partWithoutOperator);
                                        }
                                        break;
                                }
                            }
                        }
                    }
                }
                index++;
            }
            tvInputList.setText(newTopStr);
            tvMain.setText(String.valueOf(Math.round(result * 100000000) / 100000000.0));
        }
    }

    public void invalidInputChecker() {
        String bottomStr = tvMain.getText().toString();
        if (bottomStr.equals("Invalid input") || bottomStr.equals("Cannot divide by zero")) {
            tvMain.setText("0");
            tvInputList.setText("");
        }
    }

    public void zeroBtn (View view) {
        invalidInputChecker();
        updateText("0");
    }

    public void oneBtn (View view) {
        invalidInputChecker();
        updateText("1");
    }

    public void twoBtn (View view) {
        invalidInputChecker();
        updateText("2");
    }

    public void threeBtn (View view) {
        invalidInputChecker();
        updateText("3");
    }

    public void fourBtn (View view) {
        invalidInputChecker();
        updateText("4");
    }

    public void fiveBtn (View view) {
        invalidInputChecker();
        updateText("5");
    }

    public void sixBtn (View view) {
        invalidInputChecker();
        updateText("6");
    }

    public void sevenBtn (View view) {
        invalidInputChecker();
        updateText("7");
    }

    public void eightBtn (View view) {
        invalidInputChecker();
        updateText("8");
    }

    public void nineBtn (View view) {
        invalidInputChecker();
        updateText("9");
    }

    public void rootBtn (View view) {
        invalidInputChecker();
        arithmeticOperations("√");
    }

    public void divideBtn (View view) {
        invalidInputChecker();
        arithmeticOperations("/");
    }

    public void multiplyBtn (View view) {
        invalidInputChecker();
        arithmeticOperations("*");
    }

    public void minusBtn (View view) {
        invalidInputChecker();
        arithmeticOperations("-");
    }

    public void plusBtn (View view) {
        invalidInputChecker();
        arithmeticOperations("+");
    }

    public void plusMinusBtn (View view) {
        invalidInputChecker();
        updateText("+-");
    }

    public void commaBtn (View view) {
        invalidInputChecker();
        updateText(".");
    }

    public void equalBtn (View view) {
        invalidInputChecker();
        calculateResult("=");
    }

    public void backspaceBtn (View view) {
        invalidInputChecker();
        deleteOneChar();
    }

    public void clearBtn (View view) {
        invalidInputChecker();
        clearAllText();
    }
}