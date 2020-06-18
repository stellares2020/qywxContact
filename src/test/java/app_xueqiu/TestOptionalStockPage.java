package app_xueqiu;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.MatcherAssert.*;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;

public class TestOptionalStockPage {
    static web_wework.page.MainPage mainPage;
    static OptionalStockPage optionalStockPage;

    @BeforeAll
    static  void beforeAll(){
        optionalStockPage = new MainPage().toOptionalStock();
    }

    @AfterAll
    static void afterAll(){
        optionalStockPage.quit();
    }


    @ParameterizedTest
    @CsvSource({
            //"jd","gzmt","zycx","lxjm","zgzx"
            "zxgj,中芯国际","alibaba,阿里巴巴","jd,京东","zycx,兆易创新","hygy,华友钴业","hygy,华友钴业"
    })
    void addStocks(String keyword,String stockName){
        optionalStockPage.actionSearch().searchStock(keyword);

        //optionalStockPage.searchStock(keyword);
//        String stockName2 = optionalStockPage.getTxt(By.id("search_input_text"));
//        assertThat(stockName2,equalTo(stockName));
        optionalStockPage.addStock(stockName);
    }


    @Test
    void addStock(){
        optionalStockPage.actionSearch().searchStock("alibaba");
        //optionalStockPage.searchStock("alibaba");
        optionalStockPage.addStock("阿里巴巴");
    }

    @Test
    void getStockNames(){
        optionalStockPage.getStockNameList();
    }

    @Test
    void getStockCodes(){
        optionalStockPage.getStockCodeList();
    }

    @Test
    void getStockPrices(){
        optionalStockPage.getStockPriceList();
    }

    @Test
    void getStockPChanges(){
        optionalStockPage.getStockPortfoliochangeList();
    }


    @Test
    void delAllStock(){
        optionalStockPage.DelAllStock();
    }
}
