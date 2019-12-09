
package godday.xin.service.impl;

import godday.xin.dao.ContractDao;
import godday.xin.dao.ContractProductDao;
import godday.xin.domain.Contract;
import godday.xin.domain.ContractProduct;
import godday.xin.service.ContractProductService;
import godday.xin.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ContractProductServiceImpl implements ContractProductService {
    @Autowired
    private ContractProductDao contractProductDao;

    @Autowired
    private ContractDao contractDao;

    @Override
    public List<ContractProduct> find(Specification<ContractProduct> spec) {
        return contractProductDao.findAll(spec);
    }

    @Override
    public ContractProduct get(String id) {
       return (ContractProduct) contractProductDao.findOne(id);
    }

    @Override
    public Page<ContractProduct> findPage(Specification<ContractProduct> spec, Pageable pageable) {
        return contractProductDao.findAll(spec, pageable);
    }

    @Override
    public void saveOrUpdate(ContractProduct entity) {
        if(UtilFuns.isEmpty(entity.getId())){
            Double amount=0.0;
            if(UtilFuns.isNotEmpty(entity.getCnumber())&&UtilFuns.isNotEmpty(entity.getPrice())){
                amount=entity.getCnumber()*entity.getPrice();
            }
            Contract contract = contractDao.findOne(entity.getContract().getId());
            contract.setTotalAmount(contract.getTotalAmount()+amount);
            contractDao.save(contract);
        }
        else{
            Double oldAmount=entity.getAmount();
            Double amount=0.0;
            if(UtilFuns.isNotEmpty(entity.getCnumber())&&UtilFuns.isNotEmpty(entity.getPrice())){
                amount=entity.getCnumber()*entity.getPrice();
            }
            if(oldAmount!=amount){
                entity.setAmount(amount);

                Contract contract = contractDao.findOne(entity.getContract().getId());
                contract.setTotalAmount(contract.getTotalAmount()+amount);
                contractDao.save(contract);
            }
        }
        contractProductDao.save(entity);
    }

    @Override
    public void saveOrUpdateAll(Collection<ContractProduct> entitys) {
        contractProductDao.save(entitys);
    }

    @Override
    public void deleteById(String id) {

        contractProductDao.delete(id);
    }

    @Override
    public void delete(String[] ids) {

        for(String id : ids){
            contractProductDao.delete(id);
        }
    }
}
