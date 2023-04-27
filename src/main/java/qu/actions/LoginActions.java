package qu.actions;

import qu.core.PropertyManager;
import qu.pages.loginPages;

public class LoginActions {
    public static void doLogin(String user,String password){
        loginPages.goToBaseUrl(PropertyManager.getProperty("base.url"));
        loginPages.setUser(user);
        loginPages.setPassword(password);
        loginPages.signIn();
    }

    public static String getLoginMenssage(){
        return loginPages.loginMenssage();
    }
}
