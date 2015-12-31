package com.alex.angularblog.view;

import com.alex.angularblog.model.entitys.Comment;
import com.alex.angularblog.model.utils.Format;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

public class CommentsJSON {
    public CommentsJSON(){
        super();
    }
    
    public String convert (List <Comment> lista) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Comment c : lista) {
            builder.add(Json.createObjectBuilder()
                            .add("id", c.getId())
                            .add("coment", c.getComent())
                            .add("date", Format.getData(c.getDatePost()))
                            .add("user", c.getUser().getNome())
                    );
        }
        return builder.build().toString();
    }
}
