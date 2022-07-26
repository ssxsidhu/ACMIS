package comp3350.acmis;

import comp3350.acmis.business.BusinessTests;
import comp3350.acmis.objects.ObjectTests;
import comp3350.acmis.persistence.PersistenceTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;




@RunWith(Suite.class)

@Suite.SuiteClasses({
    ObjectTests.class,
    BusinessTests.class,
    PersistenceTests.class
})

public class RunUnitTests {

}
