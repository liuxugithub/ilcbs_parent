package godday.xin.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USER_INFO_P")
@DynamicInsert(true)
@DynamicUpdate(true)
@Getter
@Setter

public class Userinfo  extends BaseEntity{
    @Id
    @Column(name="USER_INFO_ID")
    @GeneratedValue(generator="system-assigned")
    @GenericGenerator(name="system-assigned",strategy="assigned")
    private String id;

    @Column(name="NAME")
    private String name;//真实姓名

    @ManyToOne
    @JoinColumn(name="MANAGER_ID")
    private User manager;//直属领导   多对一

    @Column(name="JOIN_DATE")
    private Date joinDate;//入职时间

    @Column(name="SALARY")
    private Double salary;//薪水

    @Column(name="BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;//生日

    @Column(name="GENDER")
    private String gender;//性别

    @Column(name="EMAIL")
    private String email;

    @Column(name="STATION")
    private String station;//岗位

    @Column(name="TELEPHONE")
    private String telephone;//电话

    @Column(name="DEGREE")
    private Integer degree;//等级

    @Column(name="REMARK")
    private String remark;//说明

    @Column(name="ORDER_NO")
    private Integer orderNo;//排序号
}
