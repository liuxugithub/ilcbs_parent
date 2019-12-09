package godday.xin.dao;

import godday.xin.domain.ContractProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

public interface ContractProductDao extends JpaSpecificationExecutor, JpaRepository<ContractProduct,String> {

}
