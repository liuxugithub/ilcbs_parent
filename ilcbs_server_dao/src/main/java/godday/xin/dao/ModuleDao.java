package godday.xin.dao;

import godday.xin.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ModuleDao extends JpaRepository<Module,String> , JpaSpecificationExecutor {
   /* //JPQL的方式
    @Query("from Module where Module =?1")
    public List<Module> findModuleByName(String name);*/



    public List<Module> findModuleByNameLike(String name);


    public List<Module> findModuleByName(String name);

    


}
