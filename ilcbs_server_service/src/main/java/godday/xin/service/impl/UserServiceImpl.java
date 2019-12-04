package godday.xin.service.impl;

import godday.xin.dao.UserDao;
import godday.xin.domain.User;
import godday.xin.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao UserDao;
    @Override
    public List<User> find(Specification<User> spec) {
        return UserDao.findAll(spec);
    }
    @Override
    public User get(String id) {
        return UserDao.findOne(id);
    }
    @Override
    public Page<User> findPage(Specification<User> spec, Pageable pageable) {
        return UserDao.findAll(spec, pageable);
    }
    @Override
    public void saveOrUpdate(User entity) {
        if (UtilFuns.isEmpty(entity.getId())){
            String uid= UUID.randomUUID().toString();
            entity.setId(uid);
            entity.getUserinfo().setId(uid);
            System.out.println("我回来了");
        }
        else{

        }
        UserDao.save(entity);
    }
    @Override
    public void saveOrUpdateAll(Collection<User> entitys) {
        UserDao.save(entitys);
    }
    @Override
    public void deleteById(String id) {
        UserDao.delete(id);
    }
    @Override
    public void delete(String[] ids) {
        for(String s: ids){
            UserDao.delete(s);
        }
    }
    @Override
    public List<User> findUserByUserNameLike(String name) throws Exception {
        return null;
    }
    public List<User>  findUserByUser_Name(String name) throws Exception {
        List<User> list=UserDao.findUserByUserNameLike(name);
        if( UserDao.findUserByUserNameLike(name)== null){
            throw new Exception("没有找到部门信息");
        }
        return  list;
    }
}
