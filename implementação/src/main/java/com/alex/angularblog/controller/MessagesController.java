package com.alex.angularblog.controller;

import com.alex.angularblog.model.DAO.Conexao;
import com.alex.angularblog.model.DAO.MessageDAO;
import com.alex.angularblog.model.entitys.Message;
import com.alex.angularblog.model.entitys.Usuario;
import com.alex.angularblog.model.utils.Format;
import com.alex.angularblog.view.MessagesJSON;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MessagesController", urlPatterns = {"/messages"})
public class MessagesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MessageDAO msgDAO = new MessageDAO(Conexao.getEntityManagerFactory());
        List <Message> lista = msgDAO.findMessageEntitiesInvert();
        response.setContentType("application/json");
        response.getWriter().print((new MessagesJSON()).convert(lista));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        Usuario usu = (Usuario) sessao.getAttribute("usuarioLogado");
        
        String message = Format.getString(request.getParameter("message"));
        Message msg = new Message();
        msg.setMessage(message);
        msg.setDatePost(new Date());
        msg.setUser(usu);

        MessageDAO msgDAO = new MessageDAO(Conexao.getEntityManagerFactory());
        msgDAO.create(msg);
    }
}
