package godday.xin.domain;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="USER_P")
@DynamicInsert(true)  //oralce可以字段用默认值
@DynamicUpdate(true)
@Getter
@Setter
@ToString
public class User  extends  BaseEntity implements Serializable {
    @Id
    @Column(name="USER_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    @JSONField(name="user_id")
    private String id;//编号
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="DEPT_ID")
    private Dept dept;//用户与部门   多对一

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name="ROLE_USER_P",joinColumns={@JoinColumn(name="USER_ID",referencedColumnName="USER_ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID",referencedColumnName="ROLE_ID")}
    )
    //referencedColumnName 非主键类
    @OrderBy("ORDER_NO")
    private Set<Role> roles = new HashSet<Role>();//用户与角色   多对多


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="USER_ID")
    private Userinfo userinfo;//一对一   用户与扩展信息

    @Column(name="USER_NAME")
    @JSONField(name="username")
    private String userName;//用户名

    @Column(name="PASSWORD")
    private String password;//密码


    @Column(name="STATE")
    private Integer state;//状态   0停用




}
