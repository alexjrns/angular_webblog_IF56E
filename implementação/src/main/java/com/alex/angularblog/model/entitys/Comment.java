package com.alex.angularblog.model.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Alex
 */
@Entity
@Table(name="comment", uniqueConstraints=@UniqueConstraint(columnNames = {"dat_post", "fk_user"}),
        indexes = {@Index(columnList = "id_comment", name = "idx_comment")})
public class Comment implements Serializable {
    @Id
    @Basic(optional = false)
    //@Index(name = "idx_comment")
    @SequenceGenerator(name = "seq_comment", sequenceName = "seq_comment", allocationSize = 1)
    @GeneratedValue(generator = "seq_comment", strategy = GenerationType.AUTO)
    @Column(name = "id_comment")    
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "fk_message", foreignKey = @ForeignKey(name = "fk_message"))
    private Message message;
    
    @Column(name = "tex_comment", nullable = false, columnDefinition = "text")
    private String coment;
    
    @ManyToOne
    @JoinColumn(name = "fk_user", foreignKey = @ForeignKey(name = "fk_user"))
    private Usuario user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dat_post", nullable = false)
    private Date datePost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
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
