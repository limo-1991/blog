package studio.limo.web.blog.core.bean;

import javax.persistence.*;
import java.io.Serializable;
@MappedSuperclass
public class BaseBean implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            name = "OID"
    )
    protected Long oid;

    public BaseBean(){

    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }
}
