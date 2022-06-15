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
