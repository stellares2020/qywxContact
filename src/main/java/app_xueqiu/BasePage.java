package app_xueqiu;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class BasePage {

    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;

    public BasePage(AppiumDriver<MobileElement> driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,20);
    }




    public BasePage(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("deviceName","ALP-AL00");
        //desiredCapabilities.setCapability("deviceName","VOG-AL00");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity",".view.WelcomeActivityAlias");
//        desiredCapabilities.setCapability("noReset","true");
//        desiredCapabilities.setCapability("udid","");
//        desiredCapabilities.setCapability("dontStopAppOnReset","true");

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }

//        driver = new AppiumDriver(remoteUrl,desiredCapabilities);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver,10);
        //click(By.id("tv_agree"));
        //click(By.id("tv_left"));

        //click(By.id("image_cancel"));

        driver = new AppiumDriver(remoteUrl,desiredCapabilities);
        Boolean check = new WebDriverWait(driver,30)
                .until(x ->{
                    String xml = driver.getPageSource();
                    //System.out.println("xml:"+xml);
                    //System.out.println("###");
                    Boolean checkResult = xml.contains("user_profile_container") || xml.contains("com.xueqiu.android:id/ib_close") || xml.contains("com.xueqiu.android:id/image_cancel");
                    //System.out.println("主页元素查找的结果是:" + checkResult);
                    return checkResult;
       });
        HandleAlert handleAlert = new HandleAlert(driver);
        handleAlert.findElement(By.id("home_search"));


        return ;
    }

    public void quit(){
        driver.quit();
    }

    public MobileElement find(By by){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Exception e){
            HandleAlert handleAlert = new HandleAlert(driver);
            handleAlert.findElement(by);
        }

        return driver.findElement(by);
    }

    public void click(By by){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Exception e){
            HandleAlert handleAlert = new HandleAlert(driver);
            handleAlert.findElement(by);
        }
        driver.findElement(by).click();
    }

    public void sendKeys(By by,String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        //driver.findElement(by).clear();
        driver.findElement(by).sendKeys(content);
    }

    public String getPageSources(){
        String xml = driver.getPageSource();
//        System.out.println("###getPageSource");
//        System.out.println(xml);
//        System.out.println("###");
        return xml;
    }

    public String  getTxt(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    public void timeout(int times){
        driver.manage().timeouts().implicitlyWait(times, TimeUnit.SECONDS);
    }

    public List<MobileElement> findEles(By by){
        return driver.findElements(by);
    }

}
