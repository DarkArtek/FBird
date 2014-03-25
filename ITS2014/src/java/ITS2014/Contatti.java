/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ITS2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marco.parenzan
 */
@WebServlet(name = "Contatti", urlPatterns = {"/Contatti"})
public class Contatti extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Contatti</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Contatti at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String format = request.getParameter("format");
        if (format == null) {
            format = "html";
        } else if (format.equals("")) {
            format = "html";
        }

        String mode = request.getParameter("mode");
        if (mode == null) {
            mode = "static";
        } else if (mode.equals("")) {
            mode = "static";
        }

        PrintWriter writer = response.getWriter();

        if (format.equals("html")) {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<h1>Contatti</h1>");
            writer.println("<table id='contatti'>");
            writer.println("<thead>");

            writer.println("<tr>");
            writer.println("<th>Cognome</th>");
            writer.println("<th>Nome</th>");
            writer.println("<th>Cellulare</th>");
            writer.println("<th>E-Mail</th>");
            writer.println("</tr>");

            writer.println("</thead>");
            writer.println("<tbody>");

            if (mode.equals("static")) {
                FileReader fileContatti
                        = new FileReader("C:\\temp\\contatti.csv");
                BufferedReader readerContatti
                        = new BufferedReader(fileContatti);
                // salta la riga di intestazione
                readerContatti.readLine();

                while (true) {
                    String line = readerContatti.readLine();
                    if (line == null) {
                        break;
                    }
                    if (line.equals("")) {
                        break;
                    }

                    String[] values = line.split(";");

                    writer.println("<tr>");
                    writer.printf("\r\n<td>%s</td>", values[0]);
                    writer.printf("\r\n<td>%s</td>", values[1]);
                    writer.printf("\r\n<td>%s</td>", values[2]);
                    writer.printf("\r\n<td>%s</td>", values[3]);
                    writer.println("</tr>");
                }

                readerContatti.close();
            }

            if (mode.equals("dynamic")) {
                writer.println("<script src='Scripts/jquery-1.11.0.js'></script>");
                writer.println("<script src='Scripts/contatti.js'></script>");
            }

            writer.println("</tbody>");
            writer.println("</table>");

            writer.println("</body>");
            writer.println("</html>");
        } else if (format.equals("json")) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            writer.print("[");

            FileReader fileContatti
                    = new FileReader("C:\\temp\\contatti.csv");
            BufferedReader readerContatti
                    = new BufferedReader(fileContatti);
            // salta la riga di intestazione
            readerContatti.readLine();

            boolean first = true;
            while (true) {
                String line = readerContatti.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("")) {
                    break;
                }

                String[] values = line.split(";");

                if (first) {
                    first = false;
                } else {
                    writer.println(",");
                }

                writer.print("{");
                writer.printf(" \"cognome\": \"%s\"", values[0]);
                writer.printf(", \"nome\": \"%s\"", values[1]);
                writer.printf(", \"cellulare\": \"%s\"", values[2]);
                writer.printf(", \"email\": \"%s\"", values[3]);
                writer.print(" }");
            }

            readerContatti.close();

            writer.println("]");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

//Versione precedente
/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "contatti", urlPatterns = {"/Contatti"})
public class contattiServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet contattiServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet contattiServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String format = request.getParameter("format");
        if(format == null) format = "html";
        else if (format.equals("")) format = "html";
        
        String mode = request.getParameter("mode");
        if (mode == null) mode = "static";
        else if (mode.equals("")) mode = "static";
        
        PrintWriter writer =response.getWriter();
        
        if(format.equals("html"))
        {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<h1>Contatti</h1>");
            writer.println("<table id='contatti>");
            writer.println("<thead>");
            writer.println("<tr>");
            writer.println("<th>Cognome</th>");
            writer.println("<th>Nome</th>");
            writer.println("<th>Cellulare</th>");
            writer.println("<th>Email</th>");
            writer.println("</tr>");
            writer.println("</thead>");
            writer.println("<tbody>");
            
            //inizio parte statica
            if(mode.equals("static"))
            {
                FileReader fileContatti
                    = new FileReader("C:\\temp\\contatti.csv");
            BufferedReader readerContatti
                    = new BufferedReader(fileContatti);
            //salta la riga di intestazione

            readerContatti.readLine();

            while (true) {
                String line = readerContatti.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("")) {
                    break;
                }

                String[] values = line.split(";"); //value 0 Nome, 1 Cognome, 2 cellulare, 3 email
                writer.println("<tr>");
                writer.printf("\r\n<td>%s</td>", values[0]);
                writer.printf("\r\n<td>%s</td>", values[1]);
                writer.printf("\r\n<td>%s</td>", values[2]);
                writer.printf("\r\n<td>%s</td>", values[3]);
                writer.println("</tr>");
            }

            readerContatti.close(); //chiusura del file
            } // fine parte statica
            
            if (mode.equals("dynamic"))
            {
                writer.println("<script src='Scripts/jquery-1.11.0'></script>");
                writer.println("<script src=''>Scripts/contatti.js</script>");
            }
            
            //inizio parte dinamica
            FileReader fileContatti
                    = new FileReader("C:\\temp\\contatti.csv");
            BufferedReader readerContatti
                    = new BufferedReader(fileContatti);
            //salta la riga di intestazione

            readerContatti.readLine();

            while (true) {
                String line = readerContatti.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("")) {
                    break;
                }

                String[] values = line.split(";"); //value 0 Nome, 1 Cognome, 2 cellulare, 3 email
                writer.println("<tr>");
                writer.printf("\r\n<td>%s</td>", values[0]);
                writer.printf("\r\n<td>%s</td>", values[1]);
                writer.printf("\r\n<td>%s</td>", values[2]);
                writer.printf("\r\n<td>%s</td>", values[3]);
                writer.println("</tr>");
            }

            readerContatti.close(); //chiusura del file
            //fine parte dinamica
            writer.println("</tbody>");
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");

            writer.close();
        }
        else if (format.equals("json"))
        {
            response.setContentType("text/json");
            writer.println("["); //in formato json arriva un vettore, un array di oggetti tutti uguali (che sarà il contatto)
                FileReader fileContatti = new FileReader("C:\\temp\\contatti.csv");
                BufferedReader readerContatti = new BufferedReader(fileContatti);
                // salta la riga di intestazione
        
                readerContatti.readLine(); //legge la prima riga a vuoto
                while(true)
                {
                String line = readerContatti.readLine();
                if (line == null) break;
                if (line.equals("")) break;
            
                String[] values = line.split(";"); //divide le celle in stringe, suddivise in colonne!
            
                writer.println("{");
                writer.printf("\r\ncognome:'%s'</td>", values[0]); //colonna!
                writer.printf("\r\n, nome: '%s'td>", values[1]);
                writer.printf("\r\n, cellulare: '%s'</td>", values[2]);
                writer.printf("\r\n, email: '%s'</td>", values[3]);
                writer.println("}");
            
                }
        
                readerContatti.close();
            writer.println("]");
        }
        
        
        FileReader fileContatti = new FileReader("C:\\temp\\contatti.csv");
        BufferedReader readerContatti = new BufferedReader(fileContatti);
        // salta la riga di intestazione
        
        readerContatti.readLine(); //legge la prima riga a vuoto
        
        boolean first = true;
        while(true)
        {
            String line = readerContatti.readLine();
            if (line == null) break;
            if (line.equals("")) break;
            
            String[] values = line.split(";"); //divide le celle in stringe, suddivise in colonne!
            
            if (first)
            {
                first = false;
            }
            else
            {
                writer.println(",");
            }
            writer.println("<tr>");
            writer.printf("\r\n<td>%s</td>", values[0]); //colonna!
            writer.printf("\r\n<td>%s</td>", values[1]);
            writer.printf("\r\n<td>%s</td>", values[2]);
            writer.printf("\r\n<td>%s</td>", values[3]);
            writer.println("</tr>");
            
        }
        
        readerContatti.close();
        writer.println("</tbody>");
        writer.println("</table>");
                
        writer.println("</body>");
        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
*/
