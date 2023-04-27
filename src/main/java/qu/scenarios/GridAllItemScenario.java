package qu.scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import qu.actions.GridActions;
import qu.actions.HomeActions;
import qu.actions.LoginActions;
import qu.core.DriverService;
import qu.core.PropertyManager;
import qu.extentReport.ExtentTestManager;

import java.io.IOException;

import static qu.context.HeadersEnums.grid;

public class GridAllItemScenario extends TestScenario{
    private static String USER = PropertyManager.getProperty("login.user");
    private static String PASSWORD = PropertyManager.getProperty("login.password");

    @Test
    public void gridAllItemValidations() throws IOException {
        try{
            LoginActions.doLogin(USER,PASSWORD);
            HomeActions.selectSection(grid.name());
            Assert.assertTrue(GridActions.validateAllProducts());
            DriverService.takeScreenShot("The validation of one product was success");
            ExtentTestManager.logExtentReportStatusPass("The validation was finished success");
        }catch (Exception e){
            DriverService.takeScreenShot("Error while doing a grid validation");
            ExtentTestManager.logExtentReportStatusFail("Error doing the validation grid");
            throw e;
        }
    }
}
