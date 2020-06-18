package app_xueqiu;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class OptionalStockPage extends SearchPage {
    static SearchPage searchPage;
    private By stockLocator = By.id("portfolio_stockName");
    private By nameLocator = By.id("name");

    public OptionalStockPage(AppiumDriver<MobileElement> driver){
        super(driver);
    }

    //在自选股页面点击查询
    public OptionalStockPage actionSearch(){
        //System.out.println("actionsearch");
        click(By.id("action_search"));
       //wait = new WebDriverWait(driver,5);
        return  new OptionalStockPage(driver);
    }

    public SearchPage searchStock(String keyword){
        searchPage = new SearchPage(driver).search1(keyword);
        return this ;
    }

    public String getStockName(){
        String text = getTxt(By.id("stockName"));
        return text;
    }

    public String getStockPrice(){
        String text = getTxt(By.id("current_price"));
        return text;
    }

    public String getStockCode(){
        String text = getTxt(By.id("stockCode"));
        return text;
    }

    public String getStockChangePercentage(){
        String text = getTxt(By.id("change_percentage"));
        return text;
    }

    public OptionalStockPage StockDetail(){
        System.out.println("股票名称："+ getStockName());
        System.out.println("股票代码为："+ getStockCode());
        System.out.println("股票价格为："+ getStockPrice());
        System.out.println("股价当天涨跌幅度为："+ getStockChangePercentage());
        return this;
    }

    //添加自选股
    public OptionalStockPage addStock(String stockName){
        //System.out.println("nameLocator"+nameLocator);
        click(nameLocator);

        wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ll_stock_result_view")));
        String stockName2 = getTxt(By.id("search_input_text"));
        assertThat(stockName2,equalTo(stockName));
        //System.out.println("follow_btn的text:"+find(By.id("follow_btn")).getText());
        //System.out.println(getPageSources());
        String xml = getPageSources();
        //System.out.println(xml);
        if(xml.contains("follow_btn")){
            System.out.println("##将加入自选列表的股票信息如下：");
            StockDetail();
            click(By.id("follow_btn"));
            //click(By.id("snb_tip_text"));
            //click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[5]"));
            timeout(5);
            String xml1 = getPageSources();
            //System.out.println(xml);
            if(xml1.contains("action_close")){
                click(By.id("action_close"));
            }else {
                click(By.id("tv_left"));
                click(By.id("action_close"));
            }
            System.out.println("添加成功。");
        }else{
            System.out.println(getStockName()+" 已在自选列表,该股详细信息如下：");
            StockDetail();
            click(By.id("action_close"));
        }

        return this;
    }

    //清空自选股列表
    public OptionalStockPage DelAllStock(){
        String xml = getPageSources();
        System.out.println(xml);
        if (xml.contains("portfolio_stockName")){
            System.out.println("当前自选股列表已添加股票："+ getStockNameList());
            click(By.id("edit_group"));
            click(By.id("check_all"));
            click(By.id("cancel_follow"));
            click(By.id("tv_right"));
            click(By.id("action_close"));
            find(By.id("ll_main"));
            assertThat(getTxt(By.id("tv_two")),equalTo("去看看雪球热股榜"));
            System.out.println("自选股列表已清空。");
            return this;
        }else{
            find(By.id("edit_group"));
            find(By.id("tv_two"));
            assertThat(getTxt(By.id("tv_two")),equalTo("去看看雪球热股榜"));
            System.out.println("自选股列表已是清空状态。");
            return this;
        }

    }

    //获取自选股列表所有股票名
    public List<String> getStockNameList(){
        List<String> nameList = new ArrayList();
        find(stockLocator);
        driver.findElements(stockLocator).forEach(element -> nameList.add(element.getText()));
        System.out.println(nameList);
        return nameList;
    }

    //获取自选股列表所有股票代码
    public List<String> getStockCodeList(){
        List<String> nameList = new ArrayList();
        By by = By.id("portfolio_stockCode");
        find(by);
        driver.findElements(by).forEach(element -> nameList.add(element.getText()));
        System.out.println(nameList);
        return nameList;
    }

    //获取自选股列表所有股票价格
    public List<String> getStockPriceList(){
        List<String> nameList = new ArrayList();
        By by = By.id("portfolio_currentPrice");
        By by1 = By.id("tv_two");
        try{
            find(by1);
            System.out.println("无自选股。");
            return null;
        }catch (Exception e){
            find(by);
            driver.findElements(by).forEach(element -> nameList.add(element.getText()));
            System.out.println(nameList);
            return nameList;
        }

    }

    public List<String> getStockDetail(){
        //定位到自选股列表
        By by=By.xpath("//*[@resource-id=\"com.xueqiu.android:id/content_recycler\"]//*[@resource-id=\"com.xueqiu.android:id/row_layout\"]");
        List stockDetail = new ArrayList();
        List<MobileElement> elementList = findEles(by);
        if (elementList.size()>0){
            for(int i=0;i<elementList.size();i++){
                List<String> stock = new ArrayList();
                stock.add(elementList.get(i).findElement(By.id("portfolio_stockName")).getText());
                stock.add(elementList.get(i).findElement(By.id("portfolio_stockCode")).getText());
                try{
                    stock.add(elementList.get(i).findElement(By.id("portfolio_currentPrice")).getText());
                    stock.add(elementList.get(i).findElement(By.id("portfolio_change")).getText());
                }catch (Exception e){
                    stock.add(elementList.get(i).findElements(By.id("item_layout")).get(0).getText());
                    stock.add(elementList.get(i).findElements(By.id("item_layout")).get(1).getText());

                }
                stockDetail.add(stock);
            }
        }



        System.out.println(stockDetail);
        System.out.println(stockDetail.size());

        return stockDetail;
    }

    //获取自选股列表所有股票涨跌幅度
    public List<String> getStockPortfoliochangeList(){
        List<String> nameList = new ArrayList();
        By by = By.id("portfolio_change");
        find(by);
        driver.findElements(by).forEach(element -> nameList.add(element.getText()));
        System.out.println(nameList);
        return nameList;
    }
    //获取自选股列表所有股票涨跌幅度
    public List<String> getStockPriceChangeList(){
        List<String> nameList = new ArrayList();
        By by = By.id("item_layout");
        By byPrice = By.id("portfolio_currentPrice");
        By byChange = By.id("portfolio_change");

        find(by);
        driver.findElements(by).forEach(element -> nameList.add(element.getText()));
        //System.out.println(nameList);
        //int i =1;
        List<String> price = new ArrayList();
        List<String> pChange = new ArrayList();
        //int j = length(nameList);
        //System.out.println(nameList.size());
        if(nameList.size()>2){
            for (int i=2;i<nameList.size();i++){
                if(i%2 == 0 ){
                    price.add(nameList.get(i));
                }else{
                    pChange.add(nameList.get(i));
                }
            }
        }
//        System.out.println("当前价格："+price);
//        System.out.println("涨跌幅度："+pChange);
//        System.out.println("##########");

        String xml = driver.getPageSource();
        if(xml.contains("portfolio_currentPrice")){
            driver.findElements(byPrice).forEach(element -> price.add(element.getText()));
        }

        if(xml.contains("portfolio_change")){
            driver.findElements(byChange).forEach(element -> pChange.add(element.getText()));
        }

        System.out.println("当前价格："+price);
        System.out.println("涨跌幅度："+pChange);
        return nameList;
    }




}
