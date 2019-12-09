package godday.xin.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="EXT_CPRODUCT_C")
@Getter
@Setter
public class ExtCproduct implements Serializable {
    @Id
    @Column(name="EXT_CPRODUCT_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String id;
    @Column(name="FACTORY_NAME")
    private String factoryName;//厂家
    @Column(name="PRODUCT_NO")
    private String productNo;//货号
    @Column(name="PRODUCT_IMAGE")
    private String productImage;//产品图片
    @Column(name="PRODUCT_DESC")
    private String productDesc;//货物描述
    @Column(name="PACKING_UNIT")
    private String packingUnit;//包装单位
    @Column(name="CNUMBER")
    private Integer cnumber;//数量
    @Column(name="PRICE")
    private Double price;//单价
    @Column(name="AMOUNT")
    private Double amount;//总金额
    @Column(name="PRODUCT_REQUEST")
    private String productRequest;//要求
    @Column(name="ORDER_NO")
    private Integer orderNo;//排序号
    @ManyToOne
    @JoinColumn(name="CONTRACT_PRODUCT_ID")
    private  ContractProduct contractProduct = new ContractProduct() ;
    @ManyToOne
    @JoinColumn(name="FACTORY_ID")
    private  Factory factory = new Factory();

}
