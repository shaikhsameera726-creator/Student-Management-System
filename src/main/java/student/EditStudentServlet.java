package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditStudentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/studentdb",
                    "postgres",
                    "2613");

            PreparedStatement ps =
                    con.prepareStatement(
                    "SELECT * FROM students WHERE id=?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<h2>Edit Student</h2>");

                out.println("<form action='UpdateStudentServlet' method='post'>");

                out.println("<input type='hidden' name='id' value='"
                        + rs.getInt("id") + "'>");

                out.println("Name:<br>");
                out.println("<input type='text' name='name' value='"
                        + rs.getString("name") + "'><br><br>");

                out.println("Email:<br>");
                out.println("<input type='text' name='email' value='"
                        + rs.getString("email") + "'><br><br>");

                out.println("Course:<br>");
                out.println("<input type='text' name='course' value='"
                        + rs.getString("course") + "'><br><br>");

                out.println("<input type='submit' value='Update Student'>");

                out.println("</form>");
            }

            con.close();

        } catch (Exception e) {
            out.println(e);
        }
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
