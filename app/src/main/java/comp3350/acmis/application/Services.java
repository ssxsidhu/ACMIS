//This class is used for data services. For getting data from the database, The database creation, access to database and also closing the data access.

package comp3350.acmis.application;

import comp3350.acmis.persistence.DataAccessStub;

public class Services {
    private static DataAccessStub dataAccessService = null;

    public static DataAccessStub createDataAccess(String dbName) {
        if (dataAccessService == null) {
            dataAccessService = new DataAccessStub(dbName);
            dataAccessService.open(Main.dbName);
        }
        return dataAccessService;
    }

    public static DataAccessStub getDataAccess(String dbName) {
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
