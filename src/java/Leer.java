/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(urlPatterns = {"/Leer"})
public class Leer extends HttpServlet {

 

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            ServletContext context = getServletContext();
            String fullPath = context.getRealPath("/jsn.json");

//Create File object
            File f = new File(fullPath);
            String jsonString = null;

//Create RandomAccessFile Object to read
            RandomAccessFile frar = new RandomAccessFile(f, "r");
            jsonString = frar.readLine();
            /* use readUTF() only with writeUTF(); */
            frar.close();

            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(jsonString);
        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Ha sido imposible recuperar los datos\"}");

        }
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
