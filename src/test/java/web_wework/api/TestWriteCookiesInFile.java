package web_wework.api;


import org.junit.jupiter.api.Test;

public class TestWriteCookiesInFile {

    @Test
    void WriteCookies(){
        WriteCookiesInFile wc = new WriteCookiesInFile();
        wc.getCookiesByCurrent("127.0.0.1:9222","qywxcookies.txt");
    }

    @Test
    void WriteCookies1(){
        WriteCookiesInFile wc = new WriteCookiesInFile();
        wc.getCookiesByCurrent("127.0.0.1:9222","bdwpcookies.txt");
    }
}
