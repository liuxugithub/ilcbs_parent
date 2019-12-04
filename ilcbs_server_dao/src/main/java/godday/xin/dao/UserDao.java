package godday.xin.dao;

import godday.xin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,String> , JpaSpecificationExecutor {
   /* //JPQL的方式
    @Query("from User where User =?1")
    public List<User> findUserByName(String name);*/

    //符合命名规范
    public List<User> findUserByState(Integer state);
    public List<User> findUserByUserNameLike(String name);

}
