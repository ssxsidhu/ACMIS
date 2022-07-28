package comp3350.acmis.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BusinessPersistenceSeamTest.class,
        BusinessAccessHsqldb.class
})

public class IntegrationTests {
}
