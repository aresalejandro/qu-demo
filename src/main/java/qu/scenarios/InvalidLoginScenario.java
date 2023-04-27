package qu.scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import qu.actions.CommonActions;
import qu.actions.LoginActions;
import qu.core.DriverService;
import qu.extentReport.ExtentTestManager;

import java.io.IOException;

public class InvalidLoginScenario extends TestScenario{
    private static String WRONG_CREDENTIAL = "WRONG CREDENTIALS";

    @Test
    public void invalidCredentialValidations() throws IOException {
        try{
            String user = CommonActions.getStringRandom(8);
            String password = CommonActions.getStringRandom(6);
            LoginActions.doLogin(user,password);
            Assert.assertEquals(WRONG_CREDENTIAL,LoginActions.getLoginMenssage());
            DriverService.takeScreenShot("Validate that the message is the expected menssage");
            ExtentTestManager.logExtentReportStatusPass("The message is the expected message");
        }catch (Exception e){
            DriverService.takeScreenShot("The message is not the same");
            ExtentTestManager.logExtentReportStatusFail("Error doing the validation login");
            throw e;
        }
    }
}
