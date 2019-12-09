package godday.xin.service.impl;

import godday.xin.dao.ContractDao;
import godday.xin.domain.Contract;
import godday.xin.service.ContractService;
import godday.xin.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;
    @Override
    public List<Contract> find(Specification<Contract> specification) {
        return contractDao.findAll(specification);
    }
    @Override
    public Contract get(String id) {
        return contractDao.getOne(id);
    }
    @Override
    public Page<Contract> findPage(Specification<Contract> specification, Pageable pageable) {
        return contractDao.findAll(specification, pageable);
    }
    @Override
    public void saveOrupdate(Contract contract) {

        if(UtilFuns.isEmpty(contract.getId())) {
            contract.setState(0);  //0:草稿  1：已上报  2:已报运
            contract.setTotalAmount(0.0); //务必设置默认值否则新增货物，分散计算的时候会出先null + xxxx
        }
        contractDao.save(contract);
    }
    @Override
    public void saveOrupdateAll(Collection<Contract> contracts) {

        contractDao.save(contracts);
    }
    @Override
    public void deleteByid(String id) {

        contractDao.delete(id);
    }
    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            contractDao.delete(id);
        }
    }
}
