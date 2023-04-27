package qu.scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import qu.actions.HomeActions;
import qu.actions.LoginActions;
import qu.actions.SearchActions;
import qu.core.DriverService;
import qu.core.PropertyManager;
import qu.extentReport.ExtentTestManager;

import java.io.IOException;

import static qu.context.HeadersEnums.search;

public class SearchScenario extends TestScenario{
    private static String USER = PropertyManager.getProperty("login.user");
    private static String PASSWORD = PropertyManager.getProperty("login.password");
    private static String SEARCH_VALUE = "AUTOMATION";
    private static String SEARCH_MESSAGE = "Found one result for " + SEARCH_VALUE;

    @Test
    public void searchValueValidations() throws IOException {
        try{
            LoginActions.doLogin(USER,PASSWORD);
            HomeActions.selectSection(search.name());
            Assert.assertEquals(SEARCH_MESSAGE,SearchActions.searchValueAndValidateMenssage(SEARCH_VALUE));
            DriverService.takeScreenShot("Validate the search message");
            ExtentTestManager.logExtentReportStatusPass("The search message is correct");
        }catch (Exception e){
            DriverService.takeScreenShot("Error while doing a search value");
            ExtentTestManager.logExtentReportStatusFail("Error doing the validation search");
            throw e;
        }
    }
}
