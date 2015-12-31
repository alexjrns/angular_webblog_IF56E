package com.alex.angularblog.controller;

import com.alex.angularblog.model.DAO.Conexao;
import com.alex.angularblog.model.DAO.UsuarioDAO;
import com.alex.angularblog.model.entitys.Usuario;
import com.alex.angularblog.model.utils.Format;
import com.alex.angularblog.model.utils.Utilitario;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hadoop
 */
@WebServlet(name="LoginController", urlPatterns={"/home"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = Format.getString(request.getParameter("act"));
        if(acao.equals("logout")){
            HttpSession sessao = request.getSession(false);

            if (sessao != null) {
                sessao.invalidate();
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("usuarioLogado") != null) {
                request.getRequestDispatcher("view/pages/index.jsp").forward(request, response);            
            } else{
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = Format.getString(request.getParameter("login"));
        String senha = Utilitario.MD5(Format.getString(request.getParameter("pass")));
        //Boolean lembrarUsuario = Format.getBoolean(request.getParameter("lembrar"));

        HttpSession sessao = request.getSession();
        UsuarioDAO usuDAO = new UsuarioDAO(Conexao.getEntityManagerFactory());
        Usuario usr = usuDAO.logar(login, senha);
        if (usr != null) {
            /*if(!lembrarUsuario)
                sessao.setMaxInactiveInterval(30 * 60);*/
            usr.setUltimologin(Calendar.getInstance());
            
            System.out.println("Logado com: " + login + "\tsenha: " + senha);
            
            try {
                usuDAO.salvarLogin(usr);
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            sessao.setAttribute("usuarioLogado", usr);
            request.getRequestDispatcher("view/protected/home.html").forward(request, response);
        } else {
           response.setContentType("text/html;charset=UTF-8");
            String info = ("<p><div class=\"msg_erro alert alert-danger fade in\" role=\"alert\">"
                    + "<button class=\"close\" data-dismiss=\"alert\" type=\"button\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Fechar</span></button>"
                    + "<h4>Usu&aacute;rio e/ou senha incorretos!</h4>"
                    + "<p>Verifique o usu&aacute;rio, a senha e digite os dados novamente.</p>"
                    + "</div></p>");
            request.setAttribute("inf", info);
            request.setAttribute("login", login);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            //request.getRequestDispatcher("view/pages/index.jsp").forward(request, response);   
        }
    }
}
