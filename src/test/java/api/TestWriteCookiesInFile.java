package api;

import api.WriteCookiesInFile;
import org.junit.Test;

public class TestWriteCookiesInFile {

    @Test
    public void WriteCookies(){
        WriteCookiesInFile wc = new WriteCookiesInFile();
        wc.getCookiesByCurrent("127.0.0.1:9222","qywxcookies.txt");
    }
}
