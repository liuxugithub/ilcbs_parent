package godday.xin.domain;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="ROLE_P")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Role extends  BaseEntity {
    @Id
    @Column(name="ROLE_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String id;//角色id
    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<User>(0);//角色与用户   多对多


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ROLE_MODULE_P",
            joinColumns={@JoinColumn(name="ROLE_ID",referencedColumnName="ROLE_ID")},
            inverseJoinColumns={@JoinColumn(name="MODULE_ID",referencedColumnName="MODULE_ID")})
    @OrderBy("ORDER_NO")
    private Set<Module> modules = new HashSet<Module>(0);//角色与模块  多对多
    @Column(name="NAME")
    @JSONField(name="role_name")
    private String name;//角色名称
    @Column(name="REMARK")
    private String remark;//备注
    @Column(name="ORDER_NO")
    private Integer orderNo;//排序号
}
