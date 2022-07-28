package comp3350.acmis.integration;


import org.junit.Test;

import java.util.ServiceConfigurationError;

import comp3350.acmis.application.Main;
import comp3350.acmis.application.Services;
import comp3350.acmis.business.AccessBookings;

public class BusinessPersistenceSeamTest  {
    private static String dnName;

    public BusinessPersistenceSeamTest(String typeOfDb){
        dnName = typeOfDb;
    }


    @Test
    public void testAccessBookings(){
        AccessBookings testAccess;


        //resetting persistence
        Services.closeDataAccess();

        System.out.println("Starting Integration test of AccessBooking to persistence");

        Services.createDataAccess(Main.dbName);

        testAccess = new AccessBookings("braico");
    }
}
