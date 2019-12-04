package godday.xin.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/*
  dept_id   VARCHAR2(40) not null,
       dept_name VARCHAR2(50),
       parent_id VARCHAR2(40),
       state     NUMBER(6)

 */
@Table(name="DEPT_P")
@Entity
@Getter
@Setter
public class Dept implements Serializable {
    @Id
    @Column(name="DEPT_ID")
    @GeneratedValue(generator = "sys-uuid")
    @GenericGenerator(name="sys-uuid",strategy = "uuid")
    private String id;
    @Column(name="dept_name")
    private  String deptName;
    @ManyToOne
    @JoinColumn(name="parent_id",referencedColumnName ="dept_id")
    private  Dept parent;
    @Column(name="state")
    private  Integer state;
    @OneToMany(mappedBy = "dept")
    private Set<User> users=new HashSet<>();


}
