package com.alex.angularblog.model.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="message", uniqueConstraints=@UniqueConstraint(columnNames = {"dat_post", "fk_user"}),
    indexes = {@javax.persistence.Index(columnList = "id_message", name = "idx_message")})
public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "seq_message", sequenceName = "seq_message", allocationSize = 1)
    @GeneratedValue(generator = "seq_message", strategy = GenerationType.AUTO)
    @Column(name = "id_message")
    private int id;
    
    @Column(name = "tex_message", nullable = false, columnDefinition = "text")
    private String message;
    
    @ManyToOne
    @JoinColumn(name = "fk_user", foreignKey = @javax.persistence.ForeignKey(name = "fk_user"))
    private Usuario user; // Será um usuário
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dat_post", nullable = true)
    private Date datePost;
    
    public Message(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }
    
}
