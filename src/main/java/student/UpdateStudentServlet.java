package student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.PrintWriter;


/**
 * Servlet implementation class UpdateStudentServlet
 */
@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    try {

	        int id = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String course = request.getParameter("course");

	        Class.forName("org.postgresql.Driver");

	        Connection con = DriverManager.getConnection(
	                "jdbc:postgresql://localhost:5432/studentdb",
	                "postgres",
	                "2613");

	        PreparedStatement ps = con.prepareStatement(
	                "UPDATE students SET name=?, email=?, course=? WHERE id=?");

	        ps.setString(1, name);
	        ps.setString(2, email);
	        ps.setString(3, course);
	        ps.setInt(4, id);

	        int i = ps.executeUpdate();

	        if(i > 0){
	            out.println("<h2>Student Updated Successfully!</h2>");
	            if(i > 0){
	                response.sendRedirect("ViewStudentsServlet");
	            }
	            else{
	                out.println("<h2>Update Failed!</h2>");
	            }
	        }else{
	            out.println("<h2>Update Failed!</h2>");
	        }

	        con.close();

	    } catch(Exception e){

	        out.println(e);
	    }
	}
}
