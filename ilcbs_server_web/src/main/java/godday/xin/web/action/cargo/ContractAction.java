package godday.xin.web.action.cargo;

import com.opensymphony.xwork2.ModelDriven;
import godday.xin.domain.Contract;
import godday.xin.service.ContractService;
import godday.xin.utils.Page;
import godday.xin.web.action.BaseAction;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Namespace("/cargo")
@Result(name="alist",location = "contractAction_list",type = "redirectAction")
@Getter
@Setter
public class ContractAction  extends BaseAction implements ModelDriven<Contract> {
    @Override
    public Contract getModel() {
        return null;
    }
    private Page page = new Page();
    @Autowired
    private ContractService contractService;
    @Action(value = "contractAction_list",results = {@Result(name="list",location = "/WEB-INF/pages/cargo/contract/jContractList.jsp")})
    public String list(){
        org.springframework.data.domain.Page page2=contractService.findPage(new Specification<Contract>() {
            @Override
            public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return null;
            }
        }, new PageRequest(page.getPageNo()-1, page.getPageSize()));
        System.out.println("总条数为:"+page2.getTotalElements());
        page.setTotalPage(page2.getTotalPages());
        page.setUrl("contractAction_list");
        page.setResults(page2.getContent());
        page.setTotalRecord(page2.getTotalElements());

        List<Contract> list  = page.getResults();

        for(Contract c : list){
            System.out.println("合同id："+c.getId());
            System.out.println("合同货物"+c.getContractProducts().size());
        }
        super.push(page);
        return "list";
    };
}
