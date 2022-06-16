package comp3350.acmis.business;

import java.util.GregorianCalendar;

public class DateFormatter {

    private static final int YEAR = 0;
    private static final int MONTH = 1;
    private static final int DAY = 2;

    private String date;

    public DateFormatter(String date) { //must be of format "yyyy-mm-dd" length 10
        final char DASH = '-';
        final int[] DASH_LOCATIONS = {4, 7};
        final int[] MONTHS = {1 , 2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12};
        final int[] DAYS   = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


        if (date == null) {
            throw new NullPointerException();
        }
        date = date.trim();

        if (date.length() != 10) {
            throw new IllegalArgumentException();
        }

        //check for proper dashes
        for (int i = 0; i < DASH_LOCATIONS.length; i++) {
            if (date.charAt(DASH_LOCATIONS[i]) != DASH){
                throw new IllegalArgumentException("Expected a " + DASH + " at index " + i);
            }
        }

        //check numbers are actually numbers
        for (int i = 0; i < date.length(); i++) {
            if (i != DASH_LOCATIONS[0] && i != DASH_LOCATIONS[1] && !Character.isDigit(date.charAt(i))) {
                throw new IllegalArgumentException();
            }
        }

        //check for valid date
        String[] arr = date.split(DASH + "");
        int[] input = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            input[i] = Integer.parseInt(arr[i]);
        }

        if (input[MONTH] < 1 || input[MONTH] > 12 || input[YEAR] < 1 || input[DAY] < 1) {
            throw new IllegalArgumentException();
        }
        final boolean isLeapYear =  new GregorianCalendar().isLeapYear(input[YEAR]);
        if (input[DAY] > DAYS[input[MONTH]-1]) {
            if (!(isLeapYear && input[MONTH] == 2 && input[DAY] == 29)) {
                throw new IllegalArgumentException();
            }
        }

        this.date = date;
    }

    public String format() {
        String[] str = date.split("-");

        switch(str[MONTH]){
            case "01": str[MONTH] = "January";
                        break;
            case "02": str[MONTH] = "February";
                        break;
            case "03": str[MONTH] = "March";
                        break;
            case "04": str[MONTH] = "April";
                        break;
            case "05": str[MONTH] = "May";
                        break;
            case "06": str[MONTH] = "June";
                        break;
            case "07": str[MONTH] = "July";
                        break;
            case "08": str[MONTH] = "August";
                        break;
            case "09": str[MONTH] = "September";
                        break;
            case "10": str[MONTH] = "November";
                        break;
            case "11": str[MONTH] = "October";
                        break;
            case "12": str[MONTH] = "December";

        }

        return str[MONTH] + " " + str[DAY] + ", " + str[YEAR];
    }
}
