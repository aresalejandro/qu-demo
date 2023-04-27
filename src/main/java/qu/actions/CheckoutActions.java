package qu.actions;

import org.json.JSONObject;
import qu.pages.checkoutPages;

public class CheckoutActions {
    private static String jsonPath = System.getProperty("user.dir") + "/src/main/java/qu/data/data.json";

    public static String completeForm(){
        JSONObject json = CommonActions.getJsonData(jsonPath);
        checkoutPages.setFullName(json.getString("fullName"));
        checkoutPages.setEmail(json.getString("email"));
        checkoutPages.setAddress(json.getString("address"));
        checkoutPages.setCity(json.getString("city"));
        checkoutPages.setState(json.getString("state"));
        checkoutPages.setZip(json.getString("zip"));
        checkoutPages.setNamecard(json.getString("nameOnCard"));
        checkoutPages.setCreditNumber(json.getString("creditCard"));
        checkoutPages.setExpMonth(json.getString("expMonth"));
        checkoutPages.setExpYear(json.getString("expYear"));
        checkoutPages.setCvv(json.getString("cvv"));

        checkoutPages.continueToCheckout();

        if(checkoutPages.alertIsPresent()){
            checkoutPages.checkBoxShippingAddress();
            checkoutPages.continueToCheckout();
        }

        return checkoutPages.getConfirmOrder();
    }

    public static boolean getCartValidation(){
        return checkoutPages.getCarts();
    }
}
