package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Reg_model;
import controller.Reg_control;



public class Reg_serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Reg_serv() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	Reg_model rg=new Reg_model();
	rg.setUname(request.getParameter("username"));
	rg.setpassword(request.getParameter("password"));
	rg.setEmail(request.getParameter("Email"));
	rg.setPhoneNo(request.getParameter("PhoneNo"));
	Reg_control rc=new Reg_control(rg);
	if(rc.toStore())
		response.sendRedirect("index.html");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
