package qu.scenarios;

import org.testng.annotations.AfterClass;
import qu.core.TestListener;

public abstract class TestScenario{
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        TestListener.onTestFinish();
    }
}
