package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public BasePage() {
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait=new WebDriverWait(driver, 10);
    }

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait=new WebDriverWait(driver, 10);

    }


    public void quit() {
        driver.quit();
    }


    public void click(By by){
        //todo: 异常处理
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(content);
    }

    public void upload(By by, String path){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }

    public void mouseMove(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Actions action =new Actions(driver);
        action.moveToElement(driver.findElement(by)).click().perform();
    }

    public String getText(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    public List getTexts(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        List<WebElement> eles = driver.findElements(by);
        List<String> list = new ArrayList<String>();
        for (WebElement ele :eles){
            list.add(ele.getText());
        }

        return list;

    }
}
