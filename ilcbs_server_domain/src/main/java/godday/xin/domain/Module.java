package godday.xin.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="MODULE_P")
@DynamicInsert(true)
@DynamicUpdate(true)
@Getter
@Setter
public class Module extends BaseEntity{
    @Id
    @Column(name="MODULE_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String id;
    @ManyToMany(mappedBy="modules")
    private Set<Role> roles = new HashSet<Role>(0);//模块与角色 多对多
    @Column(name="PARENT_ID")
    private String parentId;//父结点编号
    @Column(name="PARENT_NAME")
    private String parentName;//父结点名称
    @Column(name="NAME")
    private String name;//模块名
    @Column(name="LAYER_NUM")
    private String layerNum;//层数
    @Column(name="IS_LEAF")
    private String isLeaf;//是否为叶子
    @Column(name="ICO")
    private String ico;//展示图标
    @Column(name="CPERMISSION")
    private String cpermission;//权限
    @Column(name="CURL")
    private String curl;//链接
    @Column(name="CTYPE")
    private String ctype;//类型
    @Column(name="STATE")
    private Integer state;//状态
    @Column(name="BELONG")
    private String belong;//从属于
    @Column(name="CWHICH")
    private String cwhich;//复用标识
    @Column(name="QUOTE_NUM")
    private String quoteNum;//引用次数
    @Column(name="REMARK")
    private String remark;//备注
    @Column(name="ORDER_NO")
    private String orderNo;//排序号
}
