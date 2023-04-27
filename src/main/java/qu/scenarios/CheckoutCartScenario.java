package qu.scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import qu.actions.CheckoutActions;
import qu.actions.HomeActions;
import qu.actions.LoginActions;
import qu.core.DriverService;
import qu.core.PropertyManager;
import qu.extentReport.ExtentTestManager;

import java.io.IOException;

import static qu.context.HeadersEnums.checkout;

public class CheckoutCartScenario extends TestScenario{
    private static String USER = PropertyManager.getProperty("login.user");
    private static String PASSWORD = PropertyManager.getProperty("login.password");

    @Test
    public void checkoutCartValidations() throws IOException {
        try{
            LoginActions.doLogin(USER,PASSWORD);
            HomeActions.selectSection(checkout.name());
            Assert.assertTrue(CheckoutActions.getCartValidation());
            DriverService.takeScreenShot("Validate the sum cart ");
            ExtentTestManager.logExtentReportStatusPass("The sum is correct");
        }catch (Exception e){
            DriverService.takeScreenShot("Error while doing a sum cart");
            ExtentTestManager.logExtentReportStatusFail("Error doing the validation cart");
            throw e;
        }
    }
}
