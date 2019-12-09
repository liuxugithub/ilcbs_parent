package godday.xin.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CONTRACT_PRODUCT_C")
@Getter
@Setter
public class ContractProduct implements Serializable {
    @Id
    @Column(name="CONTRACT_PRODUCT_ID")
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy = "uuid")
    private  String id;

    @Column(name="FACTORY_NAME")
    private String factoryName;//工厂名字
    @Column(name="PRODUCT_NO")
    private String productNo;//产品编号
    @Column(name="PRODUCT_IMAGE")
    private String productImage;//产品图片
    @Column(name="PRODUCT_DESC")
    private String productDesc;//产品描述
    @Column(name="LOADING_RATE")
    private String loadingRate;//装率
    @Column(name="BOX_NUM")
    private Integer boxNum;//箱数
    @Column(name="PACKING_UNIT")
    private String packingUnit;//包装单位
    @Column(name="CNUMBER")
    private Integer cnumber;//数量
    @Column(name="OUT_NUMBER")
    private Integer outNumber;//实际出货数量
    @Column(name="FINISHED")
    private Integer finished;//是否出货完毕
    @Column(name="PRODUCT_REQUEST")
    private String productRequest;//产品要求
    @Column(name="PRICE")
    private Double price;//单价
    @Column(name="AMOUNT")
    private Double amount;//总金额
    @Column(name="ORDER_NO")
    private Integer orderNo;//排序




    @ManyToOne
    @JoinColumn(name="FACTORY_ID")
    private  Factory factory = new Factory();


    @ManyToOne
    @JoinColumn(name="CONTRACT_ID")
    private  Contract contract = new Contract();


    @OneToMany(mappedBy="contractProduct",fetch = FetchType.EAGER)
    private Set<ExtCproduct> extCproducts = new HashSet<>(0);

}
