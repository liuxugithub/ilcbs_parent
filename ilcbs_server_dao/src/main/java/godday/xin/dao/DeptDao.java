package godday.xin.dao;

import godday.xin.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptDao extends JpaRepository<Dept,String> , JpaSpecificationExecutor {
   /* //JPQL的方式
    @Query("from Dept where Dept =?1")
    public List<Dept> findDeptByName(String name);*/

    //符合命名规范


    public List<Dept> findDeptByState(Integer state);


    public List<Dept> findDeptByDeptNameLike(String name);


    public List<Dept> findDeptByDeptName(String name);






}
