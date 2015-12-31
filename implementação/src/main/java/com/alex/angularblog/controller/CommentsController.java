package com.alex.angularblog.controller;

import com.alex.angularblog.model.DAO.CommentDAO;
import com.alex.angularblog.model.DAO.Conexao;
import com.alex.angularblog.model.DAO.MessageDAO;
import com.alex.angularblog.model.entitys.Comment;
import com.alex.angularblog.model.entitys.Message;
import com.alex.angularblog.model.entitys.Usuario;
import com.alex.angularblog.model.utils.Format;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CommentsController", urlPatterns = {"/comments"})
public class CommentsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String act = Format.getString(request.getParameter("act"));
        if(act.equals("ins")){
            request.getRequestDispatcher("view/protected/comment.html").forward(request, response);       
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entrou no POST");
        int mensagem = Format.getInt(request.getParameter("message"));
        Message m = new MessageDAO(Conexao.getEntityManagerFactory()).findMessage(mensagem);
        String comment = Format.getString(request.getParameter("comment"));
        HttpSession sessao = request.getSession();
        Usuario usu = (Usuario) sessao.getAttribute("usuarioLogado");
        
        Comment cmt = new Comment();
        cmt.setMessage(m);
        cmt.setComent(comment);
        cmt.setDatePost(new Date());
        cmt.setUser(usu);
        
        CommentDAO cmtDAO = new CommentDAO(Conexao.getEntityManagerFactory());
        cmtDAO.create(cmt);
    }

}
