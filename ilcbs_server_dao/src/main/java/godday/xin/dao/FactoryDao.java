package godday.xin.dao;

import godday.xin.domain.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FactoryDao extends JpaSpecificationExecutor, JpaRepository<Factory,String> {
}
