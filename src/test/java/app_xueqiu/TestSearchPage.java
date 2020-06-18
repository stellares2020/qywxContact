package app_xueqiu;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


//import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSearchPage {
    static web_wework.page.MainPage mainPage;
    static SearchPage searchPage;

    @BeforeAll
    static void beforeClass(){
        searchPage = new MainPage().toSearch();
    }

    @AfterAll
    static void afterAll(){
        searchPage.quit();
    }

    @ParameterizedTest
    @CsvSource({
            "alibaba,阿里巴巴",
            "jd,京东"
    })
    void search(String keyword,String name){
        System.out.println(keyword +"," + name);
        //searchPageXq.search(keyword);
        //System.out.println(searchPageXq.getSearchList());
        searchPage.search(keyword).getSearchList().get(0);
        assertEquals(searchPage.search(keyword).getSearchList().get(0),name);

    }

    @Test
    void search1(){
        assertEquals(searchPage.search("alibaba").getSearchList().get(0),"阿里巴巴");

    }

    @Test
    void getPrice(){
        assertTrue(searchPage.search("alibaba").getPrice()>200);
        System.out.println(searchPage.search("alibaba").getPrice());
    }
}
