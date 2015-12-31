package com.alex.angularblog.model.beans;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="beanNavigation")
@SessionScoped
public class BeanNavigation implements Serializable{

    public BeanNavigation() {
        super();
    }
    public String redirectToLogin(){
        return "/login.xhtml?faces-redirect=true";
    }
    
    public String toLogin(){
        return "/login.xhtml";
    }
    
    public String redirectToInfo(){
        return "/info.xhtml";
    }
    
    public String redirectToHome(){
        //return "/index.xhtml?faces-redirect=true";
        return "/secured/mensagens.xhtml?faces-redirect=true";
    }
    
    public String toHome(){
        return "/secured/mensagens.xhtml";
    }
}
