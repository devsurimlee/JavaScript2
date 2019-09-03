package javascript.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jajvascript.common.DAO;

public class EmpDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public int insertEmp(Employee emp) {
		conn = DAO.getConnect();
		int r = 0;
		String sql = "insert into emp_javascript (employee_id, first_name, last_name, salary, job_id, email, hire_date)"
				+ "values((select max(employee_id)+1 from emp_javascript), ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getFirstName());
			pstmt.setString(2, emp.getLastName());
			pstmt.setInt(3, emp.getSalary());
			pstmt.setString(4, emp.getJobId());
			pstmt.setString(5, emp.getEmail());
			pstmt.setString(6, emp.getHireDate());
			
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return r;
	}
	
	
	

	public void delEmp(String id) {
		conn = DAO.getConnect();
		String sql = "delete * from emp_javascript where emplyee_id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건 삭제됨");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}	

	public List<Employee> getEmpList() {
		conn = DAO.getConnect();
		String sql = "select employee_id, first_name, last_name, job_id, salary, email, "
				+ "TO_CHAR(hire_date, 'yyyy-mm-dd') as hire_date from emp_javascript order by 1"; // hire_date yyyy-mm-dd
		Employee emp = null;
		List<Employee> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				emp = new Employee();
				emp.setEmployeeID(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getInt("salary"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));
				list.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
