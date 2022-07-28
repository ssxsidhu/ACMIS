package comp3350.acmis.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses({
        StubDBTest.class,
        RealDBTest.class
})

public class PersistenceTests {


}
