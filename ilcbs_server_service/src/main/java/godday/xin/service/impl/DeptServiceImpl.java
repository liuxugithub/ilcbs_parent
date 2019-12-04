package godday.xin.service.impl;

import godday.xin.dao.DeptDao;
import godday.xin.domain.Dept;
import godday.xin.service.DeptService;
import godday.xin.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public List<Dept> find(Specification<Dept> spec) {
        return deptDao.findAll(spec);
    }

    @Override
    public Dept get(String id) {
        return deptDao.findOne(id);
    }

    @Override
    public Page<Dept> findPage(Specification<Dept> spec, Pageable pageable) {
        return deptDao.findAll(spec, pageable);
    }

    @Override
    public void saveOrUpdate(Dept entity) {
        if (UtilFuns.isEmpty(entity.getId()))  entity.setState(1);
        deptDao.save(entity);
    }

    @Override
    public void saveOrUpdateAll(Collection<Dept> entitys) {
        deptDao.save(entitys);
    }

    @Override
    public void deleteById(String id) {
        deptDao.delete(id);
    }

    @Override
    public void delete(String[] ids) {
        for(String s: ids){
            deptDao.delete(s);
        }
    }

    @Override
    public List<Dept> findDeptByDeptNameLike(String name) throws Exception {
        return null;
    }

    public List<Dept>  findDeptByDept_Name(String name) throws Exception {
        List<Dept> list=deptDao.findDeptByDeptNameLike(name);

        if( deptDao.findDeptByDeptNameLike(name)== null){
            throw new Exception("没有找到部门信息");
        }
        return  list;
    }
}
