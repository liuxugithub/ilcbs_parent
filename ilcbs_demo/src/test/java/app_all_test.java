import godday.xin.domain.Dept;
import godday.xin.domain.User;
import godday.xin.service.DeptDao;
import godday.xin.service.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml"})
public class app_all_test {
    @Autowired
    DeptDao deptDao;
    @Autowired
    UserDao userDao;
    @Test
    public void test_dao(){
        Dept dept = new Dept();
        User user1= new User();
        User user2 = new User();
        user1.setName("liuxu2");
        user2.setName("liulinlin2");
       /*List<Dept> deptList=  deptDao.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.equal(root.get("dept_name").as(String.class),"市场部");
            }
        });*/
       Set<User> user = new HashSet<>();
       user.add(user1);
       user.add(user2);
       dept.setUsers(user);
       dept.setDept_name("市场部4");
       deptDao.save(dept);
       /* System.out.println(deptList.get(0).getDept_name());
        Dept dept1=deptList.get(0);
        user1.setDept(dept1);
        user2.setDept(dept1);
        userDao.save(user1);
        userDao.save(user2);*/
       /* Set<User> users = dept1.getUsers();
        for(User user : users){
            userDao.delete(user.getId());
        }
*/
    }
}
