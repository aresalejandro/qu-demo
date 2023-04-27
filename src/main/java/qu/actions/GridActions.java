package qu.actions;

import org.apache.log4j.Logger;
import org.junit.Assert;
import qu.pages.gridPages;
import java.util.Map;

public class GridActions {
    private static final Logger logger = Logger.getLogger(GridActions.class);

    public static String getNameOfOneProduct(int product){
        Map<String,String> mapProduct = gridPages.getAProduct(product);
        return mapProduct.get("prodName");
    }

    public static String getPriceOfOneProduct(int product){
        Map<String,String> mapProduct = gridPages.getAProduct(product);
        return mapProduct.get("price");
    }

    public static boolean validateAllProducts(){
        int products = gridPages.getListProducts().size();
        boolean validation = false;
        try{
            for(int i = 1; i < products+1; i++){
                Map<String,String> map = gridPages.getAProduct(i);
                Assert.assertTrue(Boolean.parseBoolean(map.get("img")));
                Assert.assertFalse(map.get("prodName").isEmpty());
                Assert.assertFalse(map.get("price").isEmpty());
                Assert.assertTrue(Boolean.parseBoolean(map.get("addOrder")));
            }
            validation = true;
        }catch (Exception e){
            logger.info("Error while doing validation in all products");
        }
        return validation;
    }
}
