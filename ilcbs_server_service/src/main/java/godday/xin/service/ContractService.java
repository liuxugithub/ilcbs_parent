package godday.xin.service;

import godday.xin.domain.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;

public interface ContractService {
     List<Contract> find(Specification<Contract> specification);
      Contract get(String id);

     Page<Contract> findPage(Specification<Contract> specification, Pageable pageable);


      void saveOrupdate(Contract contract);

      void saveOrupdateAll(Collection<Contract> contracts);

      void deleteByid(String id);

     void  delete(String []id);
}
