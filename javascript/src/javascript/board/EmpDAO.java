package javascript.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jajvascript.common.DAO;

public class EmpDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public Map<String, String> getDeptSal() {
	      conn = DAO.getConnect();
	      
	      String sql = "select first_name, salary from (select * from employees order by salary desc) where rownum <7";      
	      Map<String, String> map = new HashMap<>();   //util 로 import
	      String key, value;
	      try {
	         pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {
	            key = rs.getString("first_name");
	            value = rs.getString("salary");
	            map.put(key, value);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally{
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	         
	      }
	      return map;
	      
	   }
	
	
	
	
	
	public Map<String, String> getDeptPct() {
	      conn = DAO.getConnect();
	      
	      String sql = "SELECT d.department_name, round(count(*)/21*100,2) AS pcnt " + 
	            "FROM employees e, departments d " + 
	            "WHERE e.department_id = d.department_id " + 
	            "and e.department_id IS NOT NULL " + 
	            "GROUP BY d.department_name";
	      
	      Map<String, String> map = new HashMap<>();   //util 로 import
	      String key, value;
	      try {
	         pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {
	            key = rs.getString("department_name");
	            value = rs.getString("pcnt");
	            map.put(key, value);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally{
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	         
	      }
	      return map;
	      
	   }
	
	public int insertEmp(Employee emp) {
		conn = DAO.getConnect();
		int r = 0;
		int employeeId = 0;
		String sql1 = "select max(employee_id)+1 emp_id from emp_javascript";
		
		String sql = "insert into emp_javascript (first_name, last_name, salary, "
				+ "email, job_id, hire_date, employee_id) "
				+ "values(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				employeeId = rs.getInt("emp_id");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getFirstName());
			pstmt.setString(2, emp.getLastName());
			pstmt.setInt(3, emp.getSalary());
			pstmt.setString(4, emp.getJobId());
			pstmt.setString(5, emp.getEmail());
			pstmt.setString(6, emp.getHireDate());
			pstmt.setInt(7, employeeId);
			
			r = pstmt.executeUpdate();
			System.out.println(r + "inserted ==> id:" + employeeId);
			
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
		return employeeId;
	}
	
	
	public void delEmp(String id) {
		conn = DAO.getConnect();
		String sql = "delete from emp_javascript where employee_id = ?";
		
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
