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

/**
 * Servlet implementation class Empservlet
 */
@WebServlet("/Empservlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();

		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(list).toString()); //JSONArray 라이브러리필요
		
///////////////////////////////////////////////
//		int cnt = list.size();
//		int ind = 0;
//		out.print("[");
//		
//		//for문 주의
//		for (Employee emp : list) {
//			out.print("{\"employeeId\":" + emp.getEmployeeID() 
//			+ ",\"firstName\":\"" + emp.getFirstName() 
//			+ "\",\"lastName\":\"" + emp.getLastName() 
//			+ "\",\"salary\":\"" + emp.getSalary()
//			+ "\",\"email\":\"" + emp.getEmail()
//			+ "\",\"hire_date\":\"" + emp.getHireDate()
//			+"\"}");
//			if(cnt != ++ind)
//				out.print(","); //인덱스 끝나면 출력안함
//		}
//		out.print("]");	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
