package com.alex.angularblog.model.entitys;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Table(name="usuario", 
        indexes = {@Index(name = "idx_usuario", columnList = "id_usuario")}, 
        uniqueConstraints = @UniqueConstraint(columnNames = {"val_login", "val_senha"})
)
@NamedQueries({
    @NamedQuery(name="Usuario.findByLogin", query = "SELECT u FROM Usuario AS u " + 
        "WHERE u.login = :lgn AND u.senha = :sen")
})
@Entity
public class Usuario implements Serializable{
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private int id;

    /*@Column(name = "cod_usuario", nullable = false, unique = true)
    private int codigo;
*/
    @Column(name = "des_nome", nullable = true)
    private String nome;

    @Column(name = "val_login", nullable = false)
    private String login;

    @Column(name = "val_senha", nullable = false, length = 32)
    private String senha;
    
    @Column(name = "des_email", nullable = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dat_ultimologin", nullable = true)
    private Calendar ultimologin;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dat_cadastro", nullable = false)
    private Calendar dataCadastro;

    public Usuario() {
    }

    public Usuario(int id, /*int codigo, */String nome, String login, String senha, String email, Calendar ultimologin, Calendar dataCadastro) {
        this.id = id;
        //this.codigo = codigo;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.ultimologin = ultimologin;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
*/
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getUltimologin() {
        return ultimologin;
    }

    public void setUltimologin(Calendar ultimologin) {
        this.ultimologin = ultimologin;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
}
