//Main class. Program starts here. The database is activated and the system start up.

package comp3350.acmis.application;

public class Main {

    //Comment either pair to choose which database you'd like to use

    //These are for the stub database
//    public static final String dbName = "Stub";
//    private static String dbPathName = null;

    //These are for the HSQLDB database
//    public static final String dbName = "acmisHSQLDB";
//    private static String dbPathName = "database/acmisHSQLDB";

    public static void main(String[] args) {
        startUp();
        shutDown();
        System.out.println("All done");
    }

    public static void startUp() {
        Services.createDataAccess();
    }

    public static void shutDown() {
        Services.closeDataAccess();
    }

}