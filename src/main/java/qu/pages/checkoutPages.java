package qu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class checkoutPages extends pageCommons{
    private static String FULL_NAME = "fname";
    private static String NAME_CARD = "cname";
    private static String EMAIL = "email";
    private static String CREDIT_NUMBER = "ccnum";
    private static String ADDRESS = "adr";
    private static String EXP_MONTH = "expmonth";
    private static String CITY = "city";
    private static String EXP_YEAR = "expyear";
    private static String CVV = "cvv";
    private static String STATE = "state";
    private static String ZIP = "zip";
    private static String CHECK_BOX = "sameadr";
    private static String CONTINUE_BUTTON = "//*[@class='btn']";
    private static String CONFIRM_ORDER = "order-confirmation";
    private static String CART_PRODUCTS = "//*[@class='container']/p/span";
    private static String TOTAL_CART = "//*[@class='container']/p/span/b";

    public static void setFullName(String name){
        waitForElementPresent(By.id(FULL_NAME));
        setInput(By.id(FULL_NAME),name);
    }

    public static void setEmail(String email){
        waitForElementPresent(By.id(EMAIL));
        setInput(By.id(EMAIL),email);
    }

    public static void setAddress(String address){
        waitForElementPresent(By.id(ADDRESS));
        setInput(By.id(ADDRESS),address);
    }

    public static void setCity(String city){
        waitForElementPresent(By.id(CITY));
        setInput(By.id(CITY),city);
    }

    public static void setState(String state){
        waitForElementPresent(By.id(STATE));
        setInput(By.id(STATE),state);
    }

    public static void setZip(String zip){
        waitForElementPresent(By.id(ZIP));
        setInput(By.id(ZIP),zip);
    }

    public static void setNamecard(String cardName){
        waitForElementPresent(By.id(NAME_CARD));
        setInput(By.id(NAME_CARD),cardName);
    }

    public static void setCreditNumber(String creditNumber){
        waitForElementPresent(By.id(CREDIT_NUMBER));
        setInput(By.id(CREDIT_NUMBER),creditNumber);
    }

    public static void setExpYear(String year){
        waitForElementPresent(By.id(EXP_YEAR));
        setInput(By.id(EXP_YEAR),year);
    }

    public static void setCvv(String cvv){
        waitForElementPresent(By.id(CVV));
        setInput(By.id(CVV),cvv);
    }

    public static void setExpMonth(String monthNumber){
       WebElement monthElement = getElement(By.id(EXP_MONTH));
       List<WebElement> listMonth = monthElement.findElements(By.xpath("//option"));
       String month = getMonthName(Integer.parseInt(monthNumber));
       listMonth.forEach(e->{
           if(e.getText().equalsIgnoreCase(month)){
               e.click();
           }
       });
    }

    public static String getMonthName(int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month - 1);
        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public static void checkBoxShippingAddress(){
        waitForElementPresent(By.name(CHECK_BOX));
        click(By.name(CHECK_BOX));
    }

    public static void continueToCheckout(){
        waitForElementPresent(By.xpath(CONTINUE_BUTTON));
        click(By.xpath(CONTINUE_BUTTON));
    }

    public static String getConfirmOrder(){
        WebElement element = getElement(By.id(CONFIRM_ORDER));
        return element.findElement(By.xpath("//H1")).getText();
    }

    public static boolean getCarts(){
        List<WebElement> cartElement = getElements(By.xpath(CART_PRODUCTS));
        int total = 0;
        for(WebElement cart : cartElement){
           total += Integer.parseInt(cart.getText().replace("$", ""));
        }

        return total - getTotalCart() == getTotalCart() ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Integer getTotalCart(){
        return Integer.parseInt(getText(By.xpath(TOTAL_CART)).replace("$",""));
    }
}
