package app_xueqiu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestOptionalStockDetail {
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
    void getStockPChanges1(){
        optionalStockPage.getStockPriceChangeList();
    }

    @Test
    void getStockDetail(){
        optionalStockPage.getStockDetail();
    }
}
