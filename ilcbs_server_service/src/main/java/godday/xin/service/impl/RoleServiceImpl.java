package godday.xin.service.impl;

import godday.xin.dao.RoleDao;
import godday.xin.domain.Role;
import godday.xin.service.RoleService;
import godday.xin.utils.UtilFuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> find(Specification<Role> spec) {
        return roleDao.findAll(spec);
    }

    @Override
    public Role get(String id) {
        return roleDao.findOne(id);
    }

    @Override
    public Page<Role> findPage(Specification<Role> spec, Pageable pageable) {

        return roleDao.findAll(spec, pageable);
    }

    @Override
    public void saveOrUpdate(Role entity) {
        roleDao.save(entity);
    }

    @Override
    public void saveOrUpdateAll(Collection<Role> entitys) {
        roleDao.save(entitys);
    }

    @Override
    public void deleteById(String id) {
        roleDao.delete(id);
    }

    @Override
    public void delete(String[] ids) {
        for (String s : ids) {
            roleDao.delete(s);
        }
    }

    @Override
    public List<Role> findRoleByRoleNameLike(String name) throws Exception {
        return roleDao.findRoleByNameLike(name);
    }

    public List<Role> findRoleByRole_Name(String name) throws Exception {
        List<Role> list = roleDao.findRoleByNameLike(name);
        if (roleDao.findRoleByNameLike(name) == null) {
            throw new Exception("没有找到部门信息");
        }
        return list;
    }
}
