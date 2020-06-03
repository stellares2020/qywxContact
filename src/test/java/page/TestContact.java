package page;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestContact {
    static MainPage main;
    static ContactPage contact;
    @BeforeClass
    public static void beforeAll(){
        main=new MainPage();
        contact=main.toContact();
    }

    @Ignore
    @Test
    public void testAddMember(){
        String username=contact.addMember("3", "3", "15600534763").search("3").getUserName();
        assertEquals(username, "3");
    }

    @Ignore
    @Test
    public void testSearch(){
        contact.search("3").delete();
    }

    @Ignore
    @Test
    public void testImportFromFile(){
        //todo: 中文名
        contact.importFromFile(this.getClass().getResource("/通讯录批量导入模板.xlsx"));

    }

    @Test
    public void testDeptAdd(){
        contact.deptAdd("部门一号");
    }

    @Test
    public void testDeptDel(){
        contact.deptDel("部门一号");
    }


    @Test
    public void testTagAdd(){
        contact.tagAdd("标签1号");
    }

    @Test
    public void testTagEdit(){
        contact.tagEdit("标签1号","标签园");
    }

    @Test
    public void testTagDel(){ contact.tagDel("标签园"); }

    @Test
    public void testCorAdd(){
        contact.corAdd();
    }

    @AfterClass
    public static  void afterAll(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contact.quit();
    }

}

