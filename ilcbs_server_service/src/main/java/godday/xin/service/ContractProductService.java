package godday.xin.service;

import godday.xin.domain.ContractProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;

public interface ContractProductService {
    //查询所有，带条件查询
    public List<ContractProduct> find(Specification<ContractProduct> spec);
    //获取一条记录
    public ContractProduct get(String id);
    //分页查询，将数据封装到一个page分页工具类对象
    public Page<ContractProduct> findPage(Specification<ContractProduct> spec, Pageable pageable);
    //新增和修改保存
    public  void saveOrUpdate(ContractProduct entity);
    //批量新增和修改保存
    public  void saveOrUpdateAll(Collection<ContractProduct> entitys);
    //单条删除，按id
    public  void deleteById( String id);
    //批量删除
    public  void delete(String[] ids);
}
