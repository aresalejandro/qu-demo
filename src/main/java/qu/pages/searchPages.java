package qu.pages;

import org.openqa.selenium.By;

public class searchPages extends pageCommons{
    private static String SEARCH_INPUT = "searchWord";
    private static String SEARCH_BUTTON = "//*[@class='example']/button";
    private static String MESSAGE = "result";

    public static void setSearchValue(String value){
        waitForElementPresent(By.name(SEARCH_INPUT));
        setInput(By.name(SEARCH_INPUT),value);
    }

    public static void search(){
        waitForElementPresent(By.xpath(SEARCH_BUTTON));
        click(By.xpath(SEARCH_BUTTON));
    }

    public static String getSearchMenssage(){
        waitForElementChange(By.id(MESSAGE),"searching...");
        return getText(By.id(MESSAGE));
    }
}
