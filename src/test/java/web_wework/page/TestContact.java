package web_wework.page;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestContact {
    static MainPage main;
    static ContactPage contact;
    @BeforeAll
    static void beforeAll(){
        main=new MainPage();
        contact=main.toContact();
    }


    @Test
    void testAddMember(){
        String username=contact.addMember("14", "14", "14000000000").search("14").getUserName();
        assertEquals(username, "14");
    }


    @Test
    void testMemberSearch(){
        contact.search("15");
    }

    @Test
    void testMemberDel(){
        contact.search("15").delete();
    }


    @Test
    void testImportFromFile(){
        //todo: 中文名
        contact.importFromFile(this.getClass().getResource("/通讯录批量导入模板.xlsx"));

    }

    @Test
    void testDeptAdd(){
        contact.deptAdd("部门一号");
    }

    @Test
    void testDeptDel(){
        contact.deptDel("部门一号");
    }


    @Test
    void testTagAdd(){
        contact.tagAdd("标签1号");
    }

    @Test
    void testTagEdit(){
        contact.tagEdit("标签1号","标签园");
    }

    @Test
    void testTagDel(){ contact.tagDel("标签园"); }

    @Test
    void testCorAdd(){
        contact.corAdd();
    }

    @AfterAll
    static  void afterAll(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contact.quit();
    }

}

