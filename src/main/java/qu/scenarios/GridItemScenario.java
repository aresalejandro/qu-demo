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

public class GridItemScenario extends TestScenario{
    private static String USER = PropertyManager.getProperty("login.user");
    private static String PASSWORD = PropertyManager.getProperty("login.password");
    private static Integer PRODUCT_POSITION = 7;
    private static String PRODUCT_NAME = "Super Pepperoni";
    private static String PRODUCT_PRICE = "$10";

    @Test
    public void gridItemValidations() throws IOException {
        try{
            LoginActions.doLogin(USER,PASSWORD);
            HomeActions.selectSection(grid.name());
            Assert.assertEquals(PRODUCT_NAME,GridActions.getNameOfOneProduct(PRODUCT_POSITION),"The product name is not the same");
            Assert.assertEquals(PRODUCT_PRICE,GridActions.getPriceOfOneProduct(PRODUCT_POSITION),"The price is not the same");
            DriverService.takeScreenShot("The validation of one product was success");
            ExtentTestManager.logExtentReportStatusPass("The validation was finished success");
        }catch (Exception e){
            DriverService.takeScreenShot("Error while doing a grid validation");
            ExtentTestManager.logExtentReportStatusFail("Error doing the validation grid");
            throw e;
        }
    }
}
