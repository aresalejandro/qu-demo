package qu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gridPages extends pageCommons{
    private static String LIST_MENU = "//*[@id='menu']/div";
    private static String PRODUCT_IMG = "//img";
    private static String PRODUCT_NAME = "//b";
    private static String PRODUCT_PRICE = "item-price";
    private static String ADD_ORDER = "//button[@data-test-id='add-to-order']";

    public static Map<String,String> getMapProduct(WebElement producElement,int position){
        Map<String,String> map = new HashMap<>();
        map.put("img",String.valueOf(producElement.findElement(By.xpath(PRODUCT_IMG)).isDisplayed()));
        map.put("prodName",producElement.findElements(By.xpath(PRODUCT_NAME)).get(position).getText());
        map.put("price",producElement.findElement(By.id(PRODUCT_PRICE)).getText());
        map.put("addOrder",String.valueOf(producElement.findElement(By.xpath(ADD_ORDER)).isDisplayed()));

        return map;
    }

    public static Map<String,String> getAProduct(int product){
        List<WebElement> menu = getListProducts();
        WebElement producElement = menu.get(product-1);
        return getMapProduct(producElement,product-1);
    }

    public static List<WebElement> getListProducts(){
        return getElements(By.xpath(LIST_MENU));
    }
}
