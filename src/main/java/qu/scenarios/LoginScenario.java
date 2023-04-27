package qu.scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import qu.actions.HomeActions;
import qu.actions.LoginActions;
import qu.core.DriverService;
import qu.core.PropertyManager;
import qu.extentReport.ExtentTestManager;

import java.io.IOException;

public class LoginScenario extends TestScenario{
    private static String USER = PropertyManager.getProperty("login.user");
    private static String PASSWORD = PropertyManager.getProperty("login.password");

    @Test
    public void loginValidations() throws IOException {
        try{
            LoginActions.doLogin(USER,PASSWORD);
            boolean menssage = HomeActions.validateHomeView().contains(USER) ? Boolean.TRUE : Boolean.FALSE;
            Assert.assertTrue(menssage);
            DriverService.takeScreenShot("Validate that the message is the expected menssage");
            ExtentTestManager.logExtentReportStatusPass("The message is the expected message");
        }catch (Exception e){
            DriverService.takeScreenShot("The message is not the same");
            ExtentTestManager.logExtentReportStatusFail("Error doing the validation login");
            throw e;
        }
    }
}
