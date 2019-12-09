package godday.xin.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name="dept_test")
@Entity
@Getter
@Setter

public class Dept {
    @Id
    @Column(name="Id")
    @GeneratedValue(generator = "sys-uuid")
    @GenericGenerator(name="sys-uuid",strategy = "uuid")
    private String dpet_id;
    @Column(name="name")
    private  String dept_name;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="dept_id")
    private Set<User> users=new HashSet<>();
}
