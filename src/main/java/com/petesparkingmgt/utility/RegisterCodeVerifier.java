package com.petesparkingmgt.utility;

import com.petesparkingmgt.dto.UserDTO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class RegisterCodeVerifier extends HttpServlet{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            UserDTO user= (UserDTO) session.getAttribute("authcode");

            String code = request.getParameter("authcode");

            if(code.equals(user.getCode())){
                out.println("Verification Done");
            }else{
                out.println("Incorrect verification code");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
