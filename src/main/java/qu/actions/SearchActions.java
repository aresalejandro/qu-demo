package qu.actions;

import qu.pages.searchPages;

public class SearchActions {
    public static String searchValueAndValidateMenssage(String value){
        searchPages.setSearchValue(value);
        searchPages.search();
        return searchPages.getSearchMenssage();
    }
}
