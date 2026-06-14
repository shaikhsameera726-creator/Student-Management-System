package student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.PrintWriter;

/**
 * Servlet implementation class ViewStudentsServlet
 */
@WebServlet("/ViewStudentsServlet")
public class ViewStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewStudentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/studentdb",
                    "postgres",
                    "2613");

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM students");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student List</title>");
            out.println("<link rel='stylesheet' type='text/css' href='css/style.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Student Management System</h1>");
            out.println("<h2>Student List</h2>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Course</th>");
            out.println("<th>Edit</th>");
            out.println("<th>Delete</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("course") + "</td>");
                out.println("<td><a href='EditStudentServlet?id="+ rs.getInt("id") + "'>Edit</a></td>");
                out.println("<td><a href='DeleteStudentServlet?id="+ rs.getInt("id")+ "'>Delete</a></td>");
                out.println("<td>");
                out.println("<a href='EditStudentServlet?id="
                        + rs.getInt("id")
                        + "'>Edit</a>");
                out.println("</td>");              
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><br>");
            out.println("<a href='index.jsp'><button>Back to Home</button></a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            con.close();

        } catch (Exception e) {

            out.println(e);

        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
