package app_xueqiu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSearchPage2 {
    static web_wework.page.MainPage mainPage;
    static SearchPage searchPage;
    static OptionalStockPage optionalStockPage;

    @BeforeEach
    public static void beforeClass(){
        searchPage = new MainPage().toSearch();
    }

    @AfterEach
    public static void afterAll(){
        searchPage.quit();
    }

    @Test
    public void search1(){
        System.out.println(searchPage.search1("a").getSearchList());
    }

    @Test
    public void search(){
        System.out.println(searchPage.search("alibaba").getSearchList().get(0));
    }


    @Test
    public void getText(){
        searchPage.search1("alibaba");
        System.out.println(searchPage.getSearchList());
    }




    @Test
    public void getPrice(){
        assertTrue(searchPage.search("alibaba").getPrice()>200);
        System.out.println(searchPage.search("alibaba").getPrice());
    }




    @Test
    public void searchNamePrice(){
        System.out.println(searchPage.search("alibaba").getSearchList().get(0));
        assertEquals(searchPage.search("alibaba").getSearchList().get(0),"阿里巴巴");

    }
}
