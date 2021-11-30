import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/HelloWorld")
public class HelloWorld extends HttpServlet {
           static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
           static final String DB_URL = "jdbc:mysql://101.34.182.59/linux_final?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
           static final String USER = "root";
           static final String PASS = "1234";
         public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
                 response.setContentType ("text/html") ;
                 PrintWriter out = response.getWriter();

                 Student stu = getStudent();
                 

                 out.println("<h1>hello world, " + stu.name + "</h1>");
       }
         public Student getStudent(){
                 Student stu = new Student();
                 Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM t_student WHERE id=4";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                stu.id = rs.getInt("id");
                stu.name = rs.getString("name");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }   
 

                 return stu;
 }

}
class Student{
    public int id;
    public String name;
    
}
