package qu.pages;

import org.openqa.selenium.By;

public class loginPages extends pageCommons{
    private static String INPUT_USER = "username";
    private static String INPUT_PASSWORD = "password";
    private static String SING_IN_BUTTON = "signin-button";
    private static String LOGIN_MENSSAGE = "message";

    public static void setUser(String user){
        waitForElementPresent(By.id(INPUT_USER));
        setInput(By.id(INPUT_USER),user);
    }

    public static void setPassword(String password){
        waitForElementPresent(By.id(INPUT_PASSWORD));
        setInput(By.id(INPUT_PASSWORD),password);
    }

    public static void signIn(){
        waitForElementPresent(By.id(SING_IN_BUTTON));
        click(By.id(SING_IN_BUTTON));
    }

    public static String loginMenssage(){
        waitForElementPresent(By.id(LOGIN_MENSSAGE));
        return getText(By.id(LOGIN_MENSSAGE));
    }
}
