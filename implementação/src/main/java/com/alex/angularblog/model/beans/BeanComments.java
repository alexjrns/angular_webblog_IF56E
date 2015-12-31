package com.alex.angularblog.model.beans;

import com.alex.angularblog.model.DAO.CommentDAO;
import com.alex.angularblog.model.DAO.Conexao;
import com.alex.angularblog.model.DAO.MessageDAO;
import com.alex.angularblog.model.entitys.Comment;
import com.alex.angularblog.model.entitys.Message;
import com.alex.angularblog.model.entitys.Usuario;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "beanComments")
@SessionScoped
public class BeanComments implements Serializable{
    private int codMess;
    private Comment comment;
    
    @ManagedProperty(value="#{beanNavigation}")
    private BeanNavigation navigationBean;
    
    public BeanComments() {
        super();
        comment = new Comment();
    }

    public List<Comment> getComentarios(Message mess){
        CommentDAO comeDAO = new CommentDAO(Conexao.getEntityManagerFactory());
        return comeDAO.findCommentEntities(mess);
    }

    public int getCodMess() {
        return codMess;
    }

    public void setCodMess(int codMess) {
        this.codMess = codMess;
    }
    
    public String newComment(Usuario usuario){
        Comment com = new Comment();
        
        com.setDatePost(new Date());
        com.setComent(comment.getComent());
        com.setMessage(new MessageDAO(Conexao.getEntityManagerFactory()).findMessage(codMess));
        com.setUser(usuario);

        CommentDAO dao = new CommentDAO(Conexao.getEntityManagerFactory());
        dao.create(com);

        //messages = dao.findCommentEntities();
        comment = new Comment();
        return navigationBean.redirectToHome();
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public BeanNavigation getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(BeanNavigation navigationBean) {
        this.navigationBean = navigationBean;
    }
    
    
}
