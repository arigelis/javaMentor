package com.javamentor;

import com.javamentor.utils.Processor;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            worker();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private static void worker() throws IOException {
        System.out.print("Input exp->");
        Scanner in = new Scanner(System.in);
        int x,y;
        boolean romanDigits = false;


        String exp = in.nextLine().replaceAll(" ", "");
        if (Processor.containsRomanDigits(exp) && Processor.containsArabicDigits(exp)) {
            throw new IOException("Incorrect input. Dual digits.");
        }

        String separator = exp.contains("/") ? "/" :
                exp.contains("*") ? "*" :
                        exp.contains("+") ? "+" : exp.contains("-") ? "-" : "";

        if (separator.isEmpty()) {
            throw new IOException("Incorrect input. Unknown action.");
        }
        if (Processor.containsRomanDigits(exp)) {
            romanDigits = true;
            x = Processor.convertToRoman(exp.substring(0, exp.indexOf(separator)));
            y = Processor.convertToRoman(exp.substring(exp.indexOf(separator) + 1));
        } else if (Processor.isNumeric(exp.substring(0, exp.indexOf(separator))) && Processor.isNumeric(exp.substring(exp.indexOf(separator) + 1))) {
            x = Integer.parseInt(exp.substring(0, exp.indexOf(separator)));
            y = Integer.parseInt(exp.substring(exp.indexOf(separator) + 1));
        } else {
            throw new IOException("Incorrect input");
        }
        if (x > 10 || y > 10) {
            throw new IOException("Incorrect input. Too big values.");
        }

        try {
            Integer res = separator.equalsIgnoreCase("/") ? Execution.DIVISION.action(x, y) :
                    separator.equalsIgnoreCase("*") ? Execution.MULTIPLY.action(x, y) :
                            separator.equalsIgnoreCase("+") ? Execution.SUM.action(x, y) :
                                    Execution.SUBTRACT.action(x, y);
            System.out.println(romanDigits ? Processor.reverseConvert(res).get() : res);
        } catch (ArithmeticException ae) {
            System.out.println("Arithmetic exception, incorrect data.");
            System.exit(0);
        }

        requestEnd();
        worker();
    }

    private static void requestEnd() {
        System.out.println("Stop execution? Y/N");
        Scanner in = new Scanner(System.in);
        if (in.nextLine().trim().startsWith("y")) {
            System.exit(0);
        }
    }
}
