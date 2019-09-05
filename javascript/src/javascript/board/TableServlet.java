package javascript.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		JSONArray aryIn = new JSONArray();
		JSONArray aryOut = new JSONArray();
		JSONObject obj = new JSONObject();
		
		
		for(Employee emp: list) {
			aryIn = new JSONArray();
			aryIn.add(emp.getEmployeeID());
			aryIn.add(emp.getFirstName());
			aryIn.add(emp.getLastName());
			aryIn.add(emp.getEmail());
			aryIn.add(emp.getJobId());
			aryIn.add(emp.getSalary());
			aryIn.add(emp.getSalary());
			aryIn.add(emp.getHireDate());
			aryOut.add(aryIn);

		}
		obj.put("data", aryOut);
		PrintWriter out = response.getWriter();
		out.print(obj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
