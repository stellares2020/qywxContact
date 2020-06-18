package web_wework.api;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCookiesInFile {
    private WebDriver driver;

    public void getCookiesByCurrent(String adr,String filename){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress",adr);
        driver = new ChromeDriver(options);
        System.out.println(driver.manage().getCookies());
        try{
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Cookie cookie : driver.manage().getCookies()){
                bufferedWriter.write(cookie.getName()+';'+
                        cookie.getValue()+';'+
                        cookie.getDomain()+';'+
                        cookie.getPath()+';'+
                        cookie.getExpiry()+';'+
                        cookie.isSecure());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
