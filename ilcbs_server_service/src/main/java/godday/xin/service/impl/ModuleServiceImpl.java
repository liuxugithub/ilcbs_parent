package godday.xin.service.impl;

import godday.xin.dao.ModuleDao;
import godday.xin.domain.Module;
import godday.xin.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleDao moduleDao;

    @Override
    public List<Module> find(Specification<Module> spec) {
        return moduleDao.findAll(spec);
    }

    @Override
    public Module get(String id) {
        return moduleDao.findOne(id);
    }

    @Override
    public Page<Module> findPage(Specification<Module> spec, Pageable pageable) {

        return moduleDao.findAll(spec, pageable);
    }

    @Override
    public void saveOrUpdate(Module entity) {
        moduleDao.save(entity);
    }

    @Override
    public void saveOrUpdateAll(Collection<Module> entitys) {
        moduleDao.save(entitys);
    }

    @Override
    public void deleteById(String id) {
        moduleDao.delete(id);
    }

    @Override
    public void delete(String[] ids) {
        for (String s : ids) {
            moduleDao.delete(s);
        }
    }

    @Override
    public List<Module> findModuleByModuleNameLike(String name) throws Exception {
        return moduleDao.findModuleByNameLike(name);
    }

    public List<Module> findModuleByModule_Name(String name) throws Exception {
        List<Module> list = moduleDao.findModuleByNameLike(name);
        if (moduleDao.findModuleByNameLike(name) == null) {
            throw new Exception("没有找到部门信息");
        }
        return list;
    }
}
