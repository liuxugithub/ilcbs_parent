package godday.xin.dao;

import godday.xin.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,String> , JpaSpecificationExecutor {
   /* //JPQL的方式
    @Query("from Role where Role =?1")
    public List<Role> findRoleByName(String name);*/



    public List<Role> findRoleByNameLike(String name);


    public List<Role> findRoleByName(String name);

    


}
