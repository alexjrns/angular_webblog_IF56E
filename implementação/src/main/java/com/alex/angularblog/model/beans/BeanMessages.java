package com.alex.angularblog.model.beans;

import com.alex.angularblog.model.DAO.Conexao;
import com.alex.angularblog.model.DAO.MessageDAO;
import com.alex.angularblog.model.entitys.Message;
import com.alex.angularblog.model.entitys.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "beanMessages")
@SessionScoped
public class BeanMessages implements Serializable {
    private Message message;
    private List<Message> messages;

    public BeanMessages() {
        message = new Message();
        messages = new ArrayList<>();
        messages = new MessageDAO(Conexao.getEntityManagerFactory()).findMessageEntitiesInvert();
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    
    public void newMessage(Usuario usuario){
        Message msg = new Message();
        
        msg.setDatePost(new Date());
        msg.setMessage(message.getMessage());
        
        msg.setUser(usuario);

        MessageDAO dao = new MessageDAO(Conexao.getEntityManagerFactory());
        dao.create(msg);

        messages = dao.findMessageEntitiesInvert();
        message = new Message();
    }

}
