package godday.xin.dao;

import godday.xin.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContractDao extends JpaRepository<Contract,String>, JpaSpecificationExecutor {

}
