package api;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class ReadCookieFromFile {
    //private WebDriver driver;
    //public static WebDriver driver;
    RemoteWebDriver driver;

    public void getCookies(String url,String file,String dfr){
        //driver = new ChromeDriver();
        driver.get(url);
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                StringTokenizer tokenizer = new StringTokenizer(line,";");
                String name = tokenizer.nextToken();
                String value = tokenizer.nextToken();
                String domain = tokenizer.nextToken();
                String path = tokenizer.nextToken();
                Date expiry = null;
                String dt = tokenizer.nextToken();
                if(!dt.equals("null")){
                    SimpleDateFormat sdf = new SimpleDateFormat(dfr, Locale.ENGLISH);
                    expiry = sdf.parse(dt);
                }
                boolean isSecure = Boolean.parseBoolean(tokenizer.nextToken());
                Cookie cookie = new Cookie(name,value,domain,path,expiry,isSecure);
                driver.manage().addCookie(cookie);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.get(url);
        driver.manage().window().maximize();
        try{
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}