package Scores;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tabris
 */
@WebServlet(urlPatterns = {"/Scores"})
public class ScoresServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Scores</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Scores at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    
    private Connection getConnection()  throws SQLException 
    {
        try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ScoresServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ScoresServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ScoresServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //apertura DB
            Connection con
                    = DriverManager
                    .getConnection(
                            "jdbc:"
                            + "sqlserver://127.0.0.1;"
                            + "instanceName=SQLSERVER;"
                            + "databaseName=FlappyBird;"
                            + "user=flappybird;password=flappybird;");
            
            return con;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter writer = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            writer.print("[");
            
            Connection con = getConnection(); //getConnection è la funzione dichiarata in precedenza
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Username, Score, Timestamp FROM dbo.Top_5");

            boolean first = true;
            while (true) {
                if (!rs.next()) {
                    break;
                }
                String username = rs.getString(1);
                String score = rs.getString(2);
                String timestamp = rs.getString(3);

                if (first) {
                    first = false;
                } else {
                    writer.println(",");
                }
                writer.print("{");
                writer.printf(" \"username\": \"%s\" ", username);
                writer.printf(", \"score\": \"%s\" ", score);
                writer.printf(", \"timestamp\": \"%s\"", timestamp);
                writer.print(" }");
            }

            con.close();

            //chiudi connessione
            writer.println("]");
        } catch (SQLException ex) {
            Logger.getLogger(ScoresServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        ScoresViewModel scores = (ScoresViewModel) gson.fromJson(reader, ScoresViewModel.class);
        try {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(ScoresServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ScoresServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScoresServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        
        
        String sql
                = "INSERT INTO dbo.Scores (Username, Score, Timestamp)"
                + "VALUES ("
                + "'" + scores.username + "'"
                + ", " + scores.score
                +", GetDate()"
                + ")";

         stmt.execute(sql); //esegue la query
         
         JsonWriter writer = new JsonWriter(response.getWriter());
         gson.toJson(Risposta.OK, Risposta.class, writer);
        }catch (SQLException ex) {
            Logger.getLogger(ScoresServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
