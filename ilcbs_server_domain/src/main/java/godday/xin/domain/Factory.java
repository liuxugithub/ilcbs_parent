package godday.xin.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="FACTORY_C")
@Getter
@Setter
public class Factory extends  BaseEntity{


    @Id
    @Column(name="FACTORY_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid")
    private  String id;


    @Column(name="CTYPE")
    private String ctype;//类型
    @Column(name="FULL_NAME")
    private String fullName;//厂家全称
    @Column(name="FACTORY_NAME")
    private String factoryName;//工厂简称
    @Column(name="CONTACTS")
    private String contacts;//联系人
    @Column(name="PHONE")
    private String phone;//电话
    @Column(name="MOBILE")
    private String mobile;//手机
    @Column(name="FAX")
    private String fax;//传真
    @Column(name="ADDRESS")
    private String address;//地址
    @Column(name="INSPECTOR")
    private String inspector;//验货员
    @Column(name="REMARK")
    private String remark;//说明
    @Column(name="ORDER_NO")
    private String orderNo;//排序号
    @Column(name="STATE")
    private String state;//状态
}
