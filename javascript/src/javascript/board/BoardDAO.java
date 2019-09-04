package javascript.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jajvascript.common.DAO;

public class BoardDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int updateBoard(BoardDTO board) {
		int cnt = 0;
		conn = DAO.getConnect();
		String sql = "update boards set title =?, contents =? where board_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContents());
			pstmt.setInt(3, board.getBoardNo());
			cnt = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	
	public void deleteBoard(int boardNo) {
		conn = DAO.getConnect();
		String sql = "delete from boards where board_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();
			
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
	

	public int insertBoard(BoardDTO board) {
		conn = DAO.getConnect();
		int boardNo = 0;
		String sql1 = "select boards_seq.nextval board_no from dual";
		String sql2 = "insert into boards (board_no, title, contents, user_id, create_date)" + "values (?, ?, ?, ?, Sysdate)";

		try {
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				boardNo = rs.getInt("board_no");
			}  
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContents());
			pstmt.setString(4, "user1");
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return boardNo;

	}

	public List<BoardDTO> getBoardList() {
		conn = DAO.getConnect();
		String sql = "select * from boards where origin_no is null order by 1";
		BoardDTO brd = null;
		List<BoardDTO> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				brd = new BoardDTO();
				brd.setBoardNo(rs.getInt("board_no"));
				brd.setTitle(rs.getString("title"));
				brd.setContents(rs.getString("contents"));
				brd.setUserId(rs.getString("user_id"));
				brd.setCreateDate(rs.getString("create_date"));
				list.add(brd);
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
	
	
	public List<BoardDTO> getCntList() {
		conn = DAO.getConnect();
		String sql = "select * from boards where origin_no is not null order by 1";
		BoardDTO brd = null;
		List<BoardDTO> cntList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				brd = new BoardDTO();
				brd.setBoardNo(rs.getInt("board_no"));
				brd.setTitle(rs.getString("title"));
				brd.setContents(rs.getString("contents"));
				brd.setUserId(rs.getString("user_id"));
				brd.setCreateDate(rs.getString("create_date"));
				cntList.add(brd);
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
		return cntList;
	}

}
