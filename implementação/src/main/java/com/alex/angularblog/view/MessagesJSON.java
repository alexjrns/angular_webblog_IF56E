package com.alex.angularblog.view;

import com.alex.angularblog.model.DAO.CommentDAO;
import com.alex.angularblog.model.DAO.Conexao;
import com.alex.angularblog.model.entitys.Comment;
import com.alex.angularblog.model.entitys.Message;
import com.alex.angularblog.model.utils.Format;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

public class MessagesJSON {
    public MessagesJSON(){
        super();
    }
    
    public String convert (List <Message> lista) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Message m : lista) {
           JsonArrayBuilder builderComm = Json.createArrayBuilder(); 
           CommentDAO cmtDAO = new CommentDAO(Conexao.getEntityManagerFactory());
           List <Comment> lstComm = cmtDAO.findCommentEntities(m);
           for (Comment c: lstComm){
            builderComm.add(Json.createObjectBuilder()
                             .add("id", c.getId())
                             .add("coment", c.getComent())
                             .add("date", Format.getData(c.getDatePost()))
                             .add("user", c.getUser().getNome())
            );
           }
            builder.add(Json.createObjectBuilder()
                            .add("id", m.getId())
                            .add("message", m.getMessage())
                            .add("date", Format.getData(m.getDatePost()))
                            .add("user", m.getUser().getNome())
                            .add("comments", builderComm)
                    );
        }
        return builder.build().toString();
    }
}
