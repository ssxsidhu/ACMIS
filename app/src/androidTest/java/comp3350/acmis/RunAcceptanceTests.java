package comp3350.acmis;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.acmis.acceptance.AcceptanceTests;


@RunWith(Suite.class)
@Suite.SuiteClasses({AcceptanceTests.class})

public class RunAcceptanceTests {
    public RunAcceptanceTests() {
        System.out.println("Acceptance tests");
    }
}
