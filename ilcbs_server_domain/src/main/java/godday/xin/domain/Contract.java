package godday.xin.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CONTRACT_C")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Contract extends  BaseEntity{
    @Id
    @Column(name="CONTRACT_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid")
    private String id;//购销合同id
    @Column(name="OFFEROR")
    private String offeror;//收购方
    @Column(name="CONTRACT_NO")
    private String contractNo;//编号
    @Column(name="SIGNING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date signingDate;//签单日期
    @Column(name="INPUT_BY")
    private String inputBy;//制单人
    @Column(name="CHECK_BY")
    private String checkBy;//审单人
    @Column(name="INSPECTOR")
    private String inspector;//验货员
    @Column(name="TOTAL_AMOUNT")
    private Double totalAmount;//总金额
    @Column(name="CREQUEST")
    private String crequest;//要求
    @Column(name="CUSTOM_NAME")
    private String customName;//客户名称
    @Column(name="SHIP_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipTime;//船期
    @Column(name="IMPORT_NUM")
    private Integer importNum;//重要程度
    @Column(name="DELIVERY_PERIOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryPeriod;//交货期限
    @Column(name="OLD_STATE")
    private Integer oldState;//旧的状态
    @Column(name="OUT_STATE")
    private Integer outState;//走货状态
    @Column(name="TRADE_TERMS")
    private String tradeTerms;//贸易条款
    @Column(name="PRINT_STYLE")
    private Integer printStyle;//打印版式
    @Column(name="REMARK")
    private String remark;//说明
    @Column(name="STATE")
    private Integer state;//状态
    @OneToMany(mappedBy="contract",fetch = FetchType.EAGER)
    private  Set<ContractProduct> contractProducts = new HashSet<>(0);

}
