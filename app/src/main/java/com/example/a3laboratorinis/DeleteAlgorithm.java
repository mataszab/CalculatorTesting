package com.example.a3laboratorinis;

public class DeleteAlgorithm {

    public static String delAlgorithm(String bottomStr, String newBottomStr) {

        if (bottomStr == null) {
            return "0";
        }

        if (bottomStr.length() == 1 || bottomStr.length() == 0) {  // If bottom text is:  0, 1 ... 9.
            return "0";
        }
        else if (bottomStr.length() == 2 && bottomStr.charAt(0) == '-') {   // If bottom text is:  -1, -2 ... -9.
            return "0";
        }
        else {  // If bottom text is:  10, 11, -10, -11 ... infinity.
            newBottomStr = bottomStr.substring(0, bottomStr.length() - 1);
            return newBottomStr;
        }
    }
}
