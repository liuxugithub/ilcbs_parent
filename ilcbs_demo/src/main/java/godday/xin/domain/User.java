package godday.xin.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Setter
@Getter
@Table(name="user_test")
public class User {
    @Id
    @Column(name="user_ID")
    @GeneratedValue(generator = "sys-uuid")
    @GenericGenerator(name="sys-uuid",strategy = "uuid")
    private String id;
    @Column(name="user_name")
    private String name;
    @Column(name="user_pwd")
    private String pwd;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private  Dept dept;


}
