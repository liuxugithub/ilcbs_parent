package godday.xin.service;

import godday.xin.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeptDao extends JpaRepository<Dept,String>, JpaSpecificationExecutor {


}
