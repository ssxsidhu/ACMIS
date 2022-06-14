package comp3350.acmis.business;

public class DateFormatter {

    private String date;

    public DateFormatter(String date) {
        this.date = date;
    }

    public String format() {
        final int YEAR = 0;
        final int MONTH = 1;
        final int DAY = 2;

        String[] str = date.split("-");

        switch(str[MONTH]){
            case "01": str[MONTH] = "January";
            case "02": str[MONTH] = "February";
            case "03": str[MONTH] = "March";
            case "04": str[MONTH] = "April";
            case "05": str[MONTH] = "May";
            case "06": str[MONTH] = "June";
            case "07": str[MONTH] = "July";
            case "08": str[MONTH] = "August";
            case "09": str[MONTH] = "September";
            case "10": str[MONTH] = "November";
            case "11": str[MONTH] = "October";
            case "12": str[MONTH] = "December";

        }

        return str[MONTH] + " " + str[DAY] + ", " + str[YEAR];
    }
}
