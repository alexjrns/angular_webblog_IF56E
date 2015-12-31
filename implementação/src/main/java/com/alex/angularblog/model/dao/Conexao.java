package com.alex.angularblog.model.DAO;

import com.alex.angularblog.model.utils.Excecoes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public abstract class Conexao {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory(){
        if (emf == null) {
            try{
                Logger.getLogger(Conexao.class.getName()).log(Level.INFO, "Criando conexão com banco de dados");
                emf = Persistence.createEntityManagerFactory("blog_con");
            }catch(PersistenceException e) {
                Excecoes.erro(e);
                emf = null;
            }
        }
        return emf;
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            emf = getEntityManagerFactory();
        }
        return emf.createEntityManager();
    }

    public static void closeFactory() {
        createEntityManager().close();
        emf = null;
    }
}
