package qu.scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import qu.actions.LoginActions;
import qu.core.DriverService;
import qu.extentReport.ExtentTestManager;

import java.io.IOException;

public class EmptyLoginScenario extends TestScenario{
    private static final String EMPTY_CREDENTIAL = "FIELDS CAN NOT BE EMPTY";

    @Test
    public void emptyCredentialValidations() throws IOException {
        try{
            LoginActions.doLogin("","");
            Assert.assertEquals(EMPTY_CREDENTIAL,LoginActions.getLoginMenssage());
            DriverService.takeScreenShot("Validate that the message is the expected menssage");
            ExtentTestManager.logExtentReportStatusPass("The message is the expected message");
        }catch (Exception e){
            DriverService.takeScreenShot("The message is not the same");
            ExtentTestManager.logExtentReportStatusFail("Error doing the validation login");
            throw e;
        }
    }
}
