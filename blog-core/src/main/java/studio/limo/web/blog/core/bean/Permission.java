package studio.limo.web.blog.core.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "T_PERMISSION")
public class Permission extends BaseBean{

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;


}
