/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alex.angularblog.model.beans;

import com.alex.angularblog.model.DAO.Conexao;
import com.alex.angularblog.model.DAO.UsuarioDAO;
import com.alex.angularblog.model.entitys.Usuario;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="beanUsuario")
public class BeanUsuario {
    private Usuario usuario;

    @ManagedProperty(value="#{beanNavigation}")
    private BeanNavigation navigationBean;
    
    public BeanUsuario() {
        super();
        usuario = new Usuario();
    }
    
    public String saveUser(){
        usuario.setDataCadastro(Calendar.getInstance());
        UsuarioDAO usuDAO = new UsuarioDAO(Conexao.getEntityManagerFactory());
        usuDAO.create(usuario);
        return navigationBean.redirectToLogin();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BeanNavigation getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(BeanNavigation navigationBean) {
        this.navigationBean = navigationBean;
    }
    
    

}
