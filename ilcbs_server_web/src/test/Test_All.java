/*
import com.alibaba.fastjson.JSONObject;
import godday.xin.domain.Dept;
import godday.xin.domain.Role;
import godday.xin.domain.User;
import godday.xin.service.DeptService;
import godday.xin.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml"})
public class Test_All {

    @Autowired
    DeptService deptService;

    @Autowired
    private UserService userService;
    @Test
    public void test_reg(){
        Pattern pattern = Pattern.compile("\\w+");
        Matcher m = pattern.matcher("sdfdsfsdf dsfdsf     dsaf ");

        List<String> list = new ArrayList<>();
        while(m.find()){
            list.add(m.group());
        }
        for(String s : list){
            System.out.println(s);
        }
    }
    @Test
    public void test_number_format(){
        NumberFormat f = new DecimalFormat("000");
        for(int i= 111;i<999;i++){
            System.out.println(f.format(i));
        }
    }

    @Test
    public void test_json(){
        List<User> users = userService.find(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("userName"),"asd");
            }
        });
        for(User user : users){
           */
/* System.out.println(user.getId());
            System.out.println(user);
            List list =new ArrayList();
            list.add(user.getUserName());
            list.add(user.getId());*//*

            Dept dept=user.getDept();
            System.out.println(dept);
            User user_1 = new User();
            user_1.setUserName(user.getUserName());
            user_1.setId(user.getId());
            Set<Role> roles = new HashSet<>();
            for(Role role: user.getRoles()){
                Role role1= new Role();
                role1.setName(role.getName());
                role1.setRemark(role.getRemark());
                roles.add(role1);
            }
            user_1.setRoles(roles);
            JSONObject jo= (JSONObject) JSONObject.toJSON(user_1);

            System.out.println(JSONObject.toJSONString(user_1));
        }



    }

    @Test
    public  void test_mail() throws MessagingException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-mail.xml");
        JavaMailSender jms= (JavaMailSender) ac.getBean("mailSender");
        MimeMessage mm = jms.createMimeMessage();
        MimeMessageHelper mmh=new MimeMessageHelper(mm,true);
        mmh.setFrom("16602806242@163.com");

        mmh.setTo("504741809@qq.com");
        mmh.setText("<h1><a href=\"www.baidu.com\"></a><img src='cid:image' /></h1>");
        mmh.addInline("image", new FileSystemResource(new File("/Users/liuxu/Desktop/屏幕快照 2019-09-20 下午9.11.07.png")));

        mmh.setSubject("spring 多媒体带附件的邮件!!!");
        mmh.addAttachment("深入了解java虚拟机", new FileSystemResource(new File("/Users/liuxu/Desktop/深入了解java虚拟机.pdf")));

        jms.send(mm);


    }
    @Test
    public void TestGet() throws IOException {
        CloseableHttpClient cc = HttpClients.createDefault();
        HttpPost http=new HttpPost("http://localhost:8080/ilcbs_server_web_war/loginAction_login");
        ArrayList<BasicNameValuePair> al = new ArrayList<>();

        al.add(new BasicNameValuePair("username","cgx"));
        al.add(new BasicNameValuePair("password","123456"));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(al);
        http.setEntity(entity);


        CloseableHttpResponse ch = cc.execute(http);
        HttpEntity he= ch.getEntity();
        String string = EntityUtils.toString(he);
        System.out.println(string);


    }


@Test
    public void test_connection(){
        Dept dept = new Dept();
        dept.setDeptName("刘家军2");
      User  user1 = new User();
        user1.setUserName("刘琳琳");
        User  user2 = new User();
        user2.setUserName("刘煦");

        Set<User> user = new HashSet<>();
        user.add(user1);
        user.add(user2);

        user1.setDept(dept);
        user2.setDept(dept);
       */
/* dept.setUsers(user);

        deptService.saveOrUpdate(dept);*//*


       userService.saveOrUpdate(user1);
       userService.saveOrUpdate(user2);



    }

}
*/
