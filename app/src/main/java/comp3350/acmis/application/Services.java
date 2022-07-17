//This class is used for data services. For getting data from the database, The database creation, access to database and also closing the data access.

package comp3350.acmis.application;

import comp3350.acmis.persistence.DataAccess;
import comp3350.acmis.persistence.DataAccessObject;
import comp3350.acmis.persistence.DataAccessStub;

public class Services {
    private static DataAccess dataAccessService = null;

    public static DataAccess createDataAccess(String dbName) {
        if (dataAccessService == null) {

            if (Main.getDBPathName().equals("UF")) {
                dataAccessService = new DataAccessStub(dbName);
            } else {
                dataAccessService = new DataAccessObject(dbName);
            }
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccess createDataAccess(DataAccess alternateDataAccessService) {
        if (dataAccessService == null) {
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccess getDataAccess(String dbName) {
        if (dataAccessService == null) {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess() {
        if (dataAccessService != null) {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
