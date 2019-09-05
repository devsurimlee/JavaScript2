package javascript.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeptSalServlet
 */
@WebServlet("/DeptSalServlet")
public class DeptSalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptSalServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject obj = null;
		JSONArray ary = new JSONArray();
		
		EmpDAO dao = new EmpDAO();
		Map<String, String> map = dao.getDeptSal();
		Set<Entry<String, String>> set= map.entrySet();	
		
		for(Entry<String, String> ent : set) {
		System.out.println(ent.getKey()+"," + ent.getValue());
		obj = new JSONObject();
		obj.put("name", ent.getKey());
		obj.put("data", ent.getValue());
		ary.add(obj);
		}
		out.print(ary.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
