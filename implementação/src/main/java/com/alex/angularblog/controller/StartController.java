//@author Alex

package com.alex.angularblog.controller;

import com.alex.angularblog.model.DAO.Conexao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="StartController", urlPatterns={"/init"})
public class StartController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Iniciando aplicação");
        Conexao.getEntityManagerFactory();
        request.getRequestDispatcher("view/index.html").forward(request, response);
        //response.sendRedirect("view/index.html");
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
