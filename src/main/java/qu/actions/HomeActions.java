package qu.actions;

import qu.pages.homePages;

public class HomeActions {

    public static String validateHomeView(){
        return homePages.getMenssage();
    }

    public static void selectSection(String section){
        homePages.goToSection(section);
    }
}
