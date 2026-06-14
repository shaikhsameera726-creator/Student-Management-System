package student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet({ "/addStudent" })
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String course = request.getParameter("course");

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    try {

	        Class.forName("org.postgresql.Driver");

	        Connection con = DriverManager.getConnection(
	                "jdbc:postgresql://localhost:5432/studentdb",
	                "postgres",
	                "2613");

	        String sql =
	                "INSERT INTO students(name,email,course) VALUES(?,?,?)";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, name);
	        ps.setString(2, email);
	        ps.setString(3, course);

	        int i = ps.executeUpdate();

	        if(i>0){
	            out.println("<h2>Student Added Successfully!</h2>");
	        }
	        else{
	            out.println("<h2>Failed to Add Student</h2>");
	        }

	        con.close();

	    }
	    catch(Exception e){

	        out.println(e);
	    }
	}}
	
	    