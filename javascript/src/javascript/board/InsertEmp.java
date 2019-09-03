package javascript.board;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertEmp
 */
@WebServlet("/InsertEmp")
public class InsertEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertEmp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //넘겨주는 파라미터들~~
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("FName");
		String lastName = request.getParameter("LName");
		String email = request.getParameter("email");
		String jobId = request.getParameter("jobId");
//		String salary = request.getParameter("salary");  
		int salary = Integer.parseInt(request.getParameter("salary")); // string > 인트 형변환
		String hireDate = request.getParameter("hireDate");
		
		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setEmail(email);
		emp.setJobId(jobId);
		emp.setSalary(salary);  //원래 인트라서 에러뜸!
		emp.setHireDate(hireDate);
		
		EmpDAO dao = new EmpDAO();
		int cnt = dao.insertEmp(emp);
		PrintWriter out = response.getWriter();
		out.print(cnt);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
