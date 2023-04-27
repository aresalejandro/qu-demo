package qu.scenarios;

import static qu.context.HeadersEnums.checkout;

import org.testng.Assert;
import org.testng.annotations.Test;
import qu.actions.CheckoutActions;
import qu.actions.HomeActions;
import qu.actions.LoginActions;
import qu.core.DriverService;
import qu.core.PropertyManager;
import qu.extentReport.ExtentTestManager;

import java.io.IOException;

public class CheckoutFormScenario extends TestScenario{
    private static String USER = PropertyManager.getProperty("login.user");
    private static String PASSWORD = PropertyManager.getProperty("login.password");
    private static String COMPLETED_ORDER = "Order Confirmed!";

    @Test
    public void checkoutFormValidations() throws IOException {
        try{
            LoginActions.doLogin(USER,PASSWORD);
            HomeActions.selectSection(checkout.name());
            Assert.assertEquals(COMPLETED_ORDER,CheckoutActions.completeForm());
            DriverService.takeScreenShot("Validate the order was completed");
            ExtentTestManager.logExtentReportStatusPass("The message is the expected message");
        }catch (Exception e){
            DriverService.takeScreenShot("Error while doing a form");
            ExtentTestManager.logExtentReportStatusFail("Error doing the validation form");
            throw e;
        }
    }
}
