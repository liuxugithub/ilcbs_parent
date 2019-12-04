package godday.xin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Column(name="CREATE_BY")
    protected String creatBy;
    @Column(name="CREATE_DEPT")
    protected  String createDept;
    @Column(name="CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createTime;
    @Column(name="UPDATE_BY")
    protected String updateBy;
    @Column(name="UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    protected  Date updateTime;
}
