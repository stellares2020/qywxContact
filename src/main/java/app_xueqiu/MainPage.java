package app_xueqiu;



import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage{
    //WebDriverWait wait = new WebDriverWait(driver,20);

    public SearchPage toSearch(){
        wait = new WebDriverWait(driver,3);
        click(By.id("home_search"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_input_text")));
        return  new SearchPage(driver);
    }

    public OptionalStockPage toOptionalStock(){
        wait = new WebDriverWait(driver,3);
        //行情
        By by=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[2]");
        wait.until(ExpectedConditions.elementToBeClickable(by));
        //System.out.println("行情可被点击");
        click(by);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("action_search")));
        return  new OptionalStockPage(driver);
    }


}
