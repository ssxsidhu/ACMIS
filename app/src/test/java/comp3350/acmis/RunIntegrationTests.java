package comp3350.acmis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.acmis.integration.IntegrationTests;


@RunWith(Suite.class)

@Suite.SuiteClasses({
        IntegrationTests.class
})

public class RunIntegrationTests {

}
