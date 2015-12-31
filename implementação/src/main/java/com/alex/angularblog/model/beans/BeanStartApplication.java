/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alex.angularblog.model.beans;

import com.alex.angularblog.model.DAO.Conexao;
import javax.faces.bean.ApplicationScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="beanStartApplication")
@ApplicationScoped
public class BeanStartApplication {

    @ManagedProperty(value="#{beanNavigation}")
    private BeanNavigation navigationBean;
    
    public BeanStartApplication() {
        super();
        start();
    }
    
    private String start(){
        Conexao.getEntityManagerFactory();
        return navigationBean.redirectToHome();        
    }

}
