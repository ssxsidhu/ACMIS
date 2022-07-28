//This class is used for data services. For getting data from the database, The database creation, access to database and also closing the data access.

package comp3350.acmis.application;

import comp3350.acmis.persistence.DataAccess;
import comp3350.acmis.persistence.DataAccessObject;
import comp3350.acmis.persistence.DataAccessStub;

public class Services {
    private static DataAccess dataAccessService = null;

    public static void createDataAccess(){
        if(dataAccessService == null)
        dataAccessService =  new DataAccessObject();
    }

    public static DataAccess createDataAccess(DataAccess alternateDataAccessService) {
        dataAccessService = alternateDataAccessService;
        return dataAccessService;
    }

    public static void dataAccessOpen(){
        dataAccessService.open();
    }
    public static String getDbName(){
        if(dataAccessService != null)
            return dataAccessService.getDBName();
        return  null;
    }

    public static DataAccess getDataAccess() {
        if (dataAccessService == null) {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void setDbPath(String dbPath){
        System.out.println("Setting DB path to: " +dbPath);
        dataAccessService.setDbPath(dbPath);
    }

    public static void closeDataAccess() {
        if (dataAccessService != null) {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
