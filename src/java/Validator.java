/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Parth Shingala
 */
@WebServlet(urlPatterns = {"/Validator"})
public class Validator extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
  {
    res.setContentType("text/html");  
        try (PrintWriter out = res.getWriter()) {
            String str1 = req.getParameter("atmnumber");
            if(str1.length()<13){
                res.sendRedirect("index.html");
                System.exit(0);
            }
            StringBuilder input1 = new StringBuilder();
            input1 = input1.append(str1);
            input1.reverse();
            int[] revatmnumber = new int[input1.length()];
            
            for (int i = 0; i < input1.length(); i++) {
                revatmnumber[i] = Character.digit(input1.charAt(i), 10);
            }
            int sum1=0;
            for(int i=0; i<input1.length();i+=2){
                sum1 += revatmnumber[i];
            }
            int sum2=0;
            for(int i=1;i<input1.length();i+=2){
                revatmnumber[i] *= 2;
                if(revatmnumber[i]>9){
                    int n;
                    int m= revatmnumber[i];
                    int add=0;
                    while(m > 0)
                    {
                        n = m % 10;
                        add = add + n;
                        m = m / 10;
                    }
                    revatmnumber[i] = add;
                }
                sum2 += revatmnumber[i];
            }
            int result = sum1+sum2;
            if((result%10==0))
            {
                out.println("VALID");
                out.println("If you want to revalidate card number please click <a href=\"index.html\"> ");
                out.println("here</a>");
            }
            else
            {
                res.sendRedirect("index.html");               // observe, usage of sendRedirect()
            }   }
  }
}
