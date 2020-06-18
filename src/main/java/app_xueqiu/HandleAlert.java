package app_xueqiu;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;



import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class  HandleAlert extends BasePage{
    public  HandleAlert(AppiumDriver<MobileElement> driver){
        super(driver);
    }


    static int i = 1;
    public  MobileElement findElement(By by){
        try{
            return driver.findElement(by);
        }catch (Exception e){
            if(i>2){   //设置最多递归两次
                i=1;
                return driver.findElement(by);
            }
            //System.out.println("进入弹窗处理第"+i+"次。");//最后调用自身完成递归，防止多弹框同时出现造成定位失败
            handleAlertByPageSource();
            i++;
            //System.out.println("return");
            return driver.findElement(by);
        }

    }

    public  void handleAlertByPageSource(){
        //很多弹框的话，最好的是直接定位到到底哪个弹框在界面上，元素的判断使用xpath
        String pageSource = driver.getPageSource();
        //黑名单
        String adBox = "com.xueqiu.android:id/ib_close";
        String gestruePromptBox = "com.xueqiu.android:id/snb_tip_text";
        String evaluateBox = "com.xueqiu.android:id/md_buttonDefaultNegative";
        String upBox = "com.xueqiu.android:id/image_cancel";
        String contentBox = "com.xueqiu.android:id/tv_left";

        //将标记和定位符存入map
        HashMap<String,By> map = new HashMap<>();
        map.put(adBox,By.id("ib_close"));
        map.put(gestruePromptBox,By.id("snb_tip_text"));
        map.put(evaluateBox,By.id("md_buttonDefaultNegative"));
        map.put(upBox,By.id("image_cancel"));
        map.put(contentBox,By.id("tv_left"));

        //临时修改隐式等待时间，防止查找黑名单中元素过久
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //遍历map，判断黑名单弹框元素是否存在于当前pageSource，存在即点击处理
        map.entrySet().forEach(entry ->{
            if(pageSource.contains(entry.getKey())){
                //System.out.println(entry.getKey());
                if(entry.getKey().equals("com.xueqiu.android:id/snb_tip_text")){
                    System.out.println("gesturePromptBox found");
                    Dimension size = driver.manage().window().getSize();
                    new TouchAction<>(driver).tap(PointOption.point(size.width/2,size.height/2)).perform();
                }else{
                    //System.out.println("entry.getValue()值为: "+entry.getValue());
                    //System.out.println(By.id(entry.getKey()));
                    driver.findElement(entry.getValue()).click();
//                    System.out.println("111");
//                    try {
//                        driver.wait(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    //click(By.id(entry.getKey()));
                }
            }

        });
        //判断完成后将隐式等待时间恢复
        driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
    }
}
