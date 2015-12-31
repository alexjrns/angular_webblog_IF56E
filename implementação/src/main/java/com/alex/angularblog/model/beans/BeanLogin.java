package com.alex.angularblog.model.beans;

import com.alex.angularblog.model.DAO.Conexao;
import com.alex.angularblog.model.DAO.UsuarioDAO;
import com.alex.angularblog.model.entitys.Usuario;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean(name="beanLogin")
@SessionScoped
public class BeanLogin implements Serializable{
    private String login;
    private String password;
    private boolean loggedIn;
    private Usuario usuarioLogado;
    
    @ManagedProperty(value="#{beanNavigation}")
    private BeanNavigation navigationBean;
    
    public BeanLogin() {
        super();
    }
    
    public String doLogin(){
        UsuarioDAO usuDAO = new UsuarioDAO(Conexao.getEntityManagerFactory());
        Usuario usuario =  usuDAO.logar(login, password);
        if(usuario != null){
            loggedIn = true;
            usuarioLogado = usuario;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("beanLogin", this);
            try {
                usuDAO.salvarLogin(usuario);
            } catch (Exception ex) {
                Logger.getLogger(BeanLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            return navigationBean.redirectToHome();
        }else{
            //FacesMessage msg = new FacesMessage(Format.getMsg("LOGINERROR"), "ERROR MSG");
            loggedIn = false;
            usuarioLogado = null;   
            FacesMessage msg = new FacesMessage("Erro de login", "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return navigationBean.toLogin();
        }    
    }
    
    public String doLogout(){
        loggedIn = false;
        usuarioLogado = null;        
        FacesMessage msg = new FacesMessage("Logout Success", "INFO MSF");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return navigationBean.toLogin();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public BeanNavigation getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(BeanNavigation navigationBean) {
        this.navigationBean = navigationBean;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
}
