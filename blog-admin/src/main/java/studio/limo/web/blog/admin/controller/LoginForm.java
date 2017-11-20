package studio.limo.web.blog.admin.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {

    @NotEmpty
    private String account;

    @NotEmpty
    private String password;

    private String checkcode;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
}
