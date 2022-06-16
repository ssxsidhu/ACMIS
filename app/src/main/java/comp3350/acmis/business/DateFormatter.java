package comp3350.acmis.business;

import java.util.GregorianCalendar;

public class DateFormatter {

    private static final int YEAR_IDX = 0;
    private static final int MONTH_IDX = 1;
    private static final int DAY_IDX = 2;

    private String date;

    public DateFormatter(String date) { //must be of format "yyyy-mm-dd" length 10
        final char DASH = '-';
        final int[] DASH_LOCATIONS = {4, 7};
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

        if (input[MONTH_IDX] < 1 || input[MONTH_IDX] > 12 || input[YEAR_IDX] < 1 || input[DAY_IDX] < 1) {
            throw new IllegalArgumentException();
        }
        final boolean isLeapYear =  new GregorianCalendar().isLeapYear(input[YEAR_IDX]);
        if (input[DAY_IDX] > DAYS[input[MONTH_IDX]-1]) {
            if (!(isLeapYear && input[MONTH_IDX] == 2 && input[DAY_IDX] == 29)) {
                throw new IllegalArgumentException();
            }
        }

        this.date = date;
    }

    public String format() {
        String[] str = date.split("-");

        switch(str[MONTH_IDX]){
            case "01": str[MONTH_IDX] = "January";
                        break;
            case "02": str[MONTH_IDX] = "February";
                        break;
            case "03": str[MONTH_IDX] = "March";
                        break;
            case "04": str[MONTH_IDX] = "April";
                        break;
            case "05": str[MONTH_IDX] = "May";
                        break;
            case "06": str[MONTH_IDX] = "June";
                        break;
            case "07": str[MONTH_IDX] = "July";
                        break;
            case "08": str[MONTH_IDX] = "August";
                        break;
            case "09": str[MONTH_IDX] = "September";
                        break;
            case "10": str[MONTH_IDX] = "November";
                        break;
            case "11": str[MONTH_IDX] = "October";
                        break;
            case "12": str[MONTH_IDX] = "December";
        }

        return str[MONTH_IDX] + " " + str[DAY_IDX] + ", " + str[YEAR_IDX];
    }
}
