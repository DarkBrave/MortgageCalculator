package com.darkbrave;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double loanAmount = readNumber("Principal: ", 1_000, 1_000_000);
        double ratePercent = readNumber("Annual Interest Rate: ", 1, 30);
        double periodYears = readNumber("Period (Years): ", 1,30);

        double calcComplete = calculateMortgage(loanAmount, ratePercent, periodYears);

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String answer = currency.format(calcComplete);

        System.out.println("Your Mortgage is: " + answer);
    }

    public static double readNumber (String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Enter a value between" + min + "and" + max);
        }
        return value;
    }

    public static double calculateMortgage(double loanAmount, double ratePercent, double periodYears) {
        final double rateDecimal = ratePercent / 100 / 12;
        final double periodMonths = periodYears * 12;

        double calcTopA = 1 + rateDecimal;
        double calcBottomA = 1 + rateDecimal;
        double calcTopB = Math.pow(calcTopA, periodMonths);
        double calcBottomB = Math.pow(calcBottomA, periodMonths);
        double calcTopC = rateDecimal * calcTopB;
        double calcBottomC = calcBottomB - 1;

        double calcDivision = calcTopC / calcBottomC;
        double calcComplete = loanAmount * calcDivision;

        return calcComplete;
    }
}
