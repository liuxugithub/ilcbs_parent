import godday.xin.dao.DeptDao;
import godday.xin.domain.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml"})
public class app_test {

    @Autowired
    DeptDao deptDao;


    @Test
    public void test_dao(){
        Dept dept = new Dept();


    }
}
