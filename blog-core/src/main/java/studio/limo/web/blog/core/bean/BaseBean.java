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

    @Column(name = "CREATE_DATE", length = 8, nullable = false)
    protected String createDate;

    @Column(name = "UPDATE_DATE", length = 8)
    protected String updateDate;

    public BaseBean(){

    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
