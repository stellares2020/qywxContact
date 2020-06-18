package app_xueqiu;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage{

    private By nameLocator = By.id("name");
    private By stockLocator = By.id("stockName");

    public SearchPage(AppiumDriver<MobileElement> driver){
        super(driver);
    }

    public SearchPage search1(String keyword){
        //System.out.println("search_input_text");
        sendKeys(By.id("search_input_text"),keyword);

        if (driver.findElements(nameLocator).size() >0){
            //System.out.println(driver.findElements(nameLocator).size());
            return this;
        }else{
            //System.out.println(driver.findElements(stockLocator).size());
            nameLocator = stockLocator;
            return this;
        }
    }

    public SearchPage search(String keyword){
        do{
            //click(By.id("search_input_text"));
            sendKeys(By.id("search_input_text"),keyword);
            //System.out.println("sendkeys:"+keyword);
            //System.out.println(driver.findElements(nameLocator).size());
        }while(driver.findElements(nameLocator).size() <=0);

        return this;
    }

    public List<String> getSearchList(){
        List<String> nameList = new ArrayList();
        driver.findElements(nameLocator).forEach(element -> nameList.add(element.getText()));
        return nameList;
    }

    public double getPrice(){
        click(nameLocator);
        return Double.valueOf(find(By.id("current_price")).getText());
    }



}
