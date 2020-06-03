package page;


import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class ContactPage extends BasePage {
    By addMember=By.linkText("添加成员");
    By username=By.name("username");
    By delete=By.linkText("删除");

    public ContactPage(RemoteWebDriver driver) {
        super(driver);
    }

    public ContactPage addMember(String username, String acctid, String mobile) {
        //todo:
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(addMember));
//        //todo: 就算可点击，仍然有一定的概率是点击不成功的
//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(addMember));


        while(driver.findElements(this.username).size()==0){
            click(addMember);
        }

//        driver.findElement(this.username).sendKeys(username);
//        driver.findElement(By.name("acctid")).sendKeys(acctid);
//        driver.findElement(By.name("mobile")).sendKeys(mobile);
//        driver.findElement(By.cssSelector(".js_btn_save")).click();
        sendKeys(this.username, username);
        sendKeys(By.name("acctid"), acctid);
        sendKeys(By.name("mobile"), mobile);
        click(By.cssSelector(".js_btn_save"));
        return this;
    }

    public ContactPage search(String keyword){
        sendKeys(By.id("memberSearchInput"), keyword);
//        driver.findElement(By.id("memberSearchInput")).sendKeys(keyword);
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(delete));
        return this;
    }

    public String getUserName(){
        return driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
    }

    public ContactPage delete(){
        click(delete);
        click(By.linkText("确认"));
        click(By.id("clearMemberSearchInput"));
//        driver.findElement(delete).click();
//        driver.findElement(By.linkText("确认")).click();
//        driver.findElement(By.id("clearMemberSearchInput")).click();
        return this;

    }

    public ContactPage importFromFile(URL path){
        //todo:
        System.out.println(path.getPath());

        String path_utf="";
        try {
            path_utf=URLDecoder.decode(path.getFile(), "UTF-8");
            System.out.println(path_utf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
//        click(By.name("file"));
//        sendKeys(By.name("file"), "C:\\fakepath\\通讯录批量导入模板.xlsx");
        upload(By.name("file"), path_utf);
//        driver.findElement(By.name("file")).sendKeys("/Users/seveniruby/projects/Java3/src/main/resources/通讯录批量导入模板.xlsx");
//        sendKeys(By.name("file"), "C:\\fakepath\\通讯录批量导入模板.xlsx");
        click(By.linkText("导入"));
        click(By.linkText("完成"));

        return this;
    }

    //添加部门
    public ContactPage deptAdd(String deptname){
        click(By.xpath("//li/a[contains(text(),\"组织架构\")]"));
        click(By.cssSelector(".member_colLeft_top_addBtn"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),deptname);
        click(By.linkText("选择所属部门"));
        click(By.xpath("//label[contains(text(),'所属部门')]/../div/div/ul/li/a/i"));
        click(By.linkText("确定"));

        return this;
    }

    //删除部门
    public ContactPage deptDel(String deptname){
        click(By.xpath("//li/a[contains(text(),\"组织架构\")]"));
        click(By.xpath("//*[@id=\"search_result_wrap\"]/../div[2]//*[contains(text(),'"+deptname+"')]"));
        click(By.xpath("//*[@id=\"search_result_wrap\"]/../div[2]//*[contains(text(),'"+deptname+"')]/span"));
        click(By.xpath("/html/body/ul/li/*[contains(text(),\"删除\")]"));
        click(By.linkText("确定"));

        return this;
    }



    //添加标签
    public ContactPage tagAdd(String tagname){
        List list =getTexts(By.xpath("//ul[@class=\"ww_btnGroup\"]/li"));
        System.out.println(list);
        if (list.contains("标签")){
            click(By.xpath("//ul[@class=\"ww_btnGroup\"]/li/a[contains(text(),\"标签\")]"));
            click(By.cssSelector(".member_colLeft_top_addBtn"));
        }else {
            click(By.cssSelector(".member_colLeft_top_addBtn"));
            click(By.linkText("添加标签"));
        }
        sendKeys(By.name("name"),tagname);
        click(By.xpath("//label[contains(text(),'可使用人')]/../div/a/span[2]"));
        //选择最后一个
        click(By.xpath("//label[contains(text(),'可使用人')]/../div/div/ul/li[last()]/a"));
        click(By.linkText("确定"));
        return this;
    }

    //修改标签名
    public ContactPage tagEdit(String tagname,String tagname1){
        click(By.xpath("//li/a[contains(text(),\"标签\")]"));
        click(By.xpath("//*[contains(text(),\"我的标签\")]/../ul/li[contains(text(),'"+tagname+"')]"));
        click(By.xpath("//*[contains(text(),\"我的标签\")]/../ul/li[contains(text(),'"+tagname+"')]/a"));
        click(By.xpath("//main[@id=\"main\"]//*[contains(text(),\"修改名称\")]"));
        sendKeys(By.xpath("//input[@value='"+tagname+"']"),tagname1);
        click(By.linkText("确定"));

        return this;
    }

    //删除标签
    public ContactPage tagDel(String tagname){
        click(By.xpath("//li/a[contains(text(),\"标签\")]"));
        click(By.xpath("//*[contains(text(),\"我的标签\")]/../ul/li[contains(text(),'"+tagname+"')]"));
        click(By.xpath("//*[contains(text(),\"我的标签\")]/../ul/li[contains(text(),'"+tagname+"')]/a"));
        click(By.xpath("//main[@id=\"main\"]//*[contains(text(),\"删除\")]"));
        click(By.linkText("确定"));

        return this;
    }



    //添加互联企业,跳转到验证页面
    public ContactPage corAdd(){
        click(By.xpath("//li/a[contains(text(),\"组织架构\")]"));
        click(By.cssSelector(".member_colLeft_top_addBtn"));
        click(By.linkText("添加互联企业"));
        String text =getText(By.xpath("//div[@class=\"msgDlg_right_text\"]"));
        assertThat(text,equalTo("企业未完成验证，暂时无法添加互联企业"));
        click(By.linkText("去验证"));
        String text1 = getText(By.xpath("//span[@class=\"ww_normalCntHead_title_content js_title\"]"));
        assertThat(text1,equalTo("验证主体信息"));
        click(By.xpath("//*[@id=\"menu_contacts\"]/span"));

        return this;
    }


}

