package qu.pages;

import org.openqa.selenium.By;

public class homePages extends pageCommons{
    private static String WELCOME_MENSSAGE = "welcome-message";
    private static String HEADERS = "//*[@href='/%s']";

    public static String getMenssage(){
        return getText(By.id(WELCOME_MENSSAGE));
    }

    public static void goToSection(String section){
        String header = HEADERS.replace("%s",section);
        waitForElementPresent(By.xpath(header));
        click(By.xpath(header));
    }
}
