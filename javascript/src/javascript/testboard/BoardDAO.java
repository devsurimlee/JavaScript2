package javascript.testboard;

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
		String sql = "update new_board set title =?, content =? where board_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
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
		String sql = "delete from new_board where board_no = ?";
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
		String sql1 = "select newbrd_seq.nextval board_no from dual"; //시퀀스
		String sql2 = "insert into new_board (board_no, title, content, creation_date)"
				+ "values (?, ?, ?, Sysdate)";

		try {
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				boardNo = rs.getInt("board_no");
			}
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
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
	
	
	public int insertCnt(BoardDTO board) {
		conn = DAO.getConnect();
		int boardNo = 0;
		String sql1 = "select boards_seq.nextval board_no from dual"; //시퀀스생성
		String sql2 = "insert into new_board (board_no, parent_no, title, content, creation_date)"
				+ "values (?, ?, ?, ?, Sysdate)";

		try {
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				boardNo = rs.getInt("board_no");
			}
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, board.getBoardNo());
			pstmt.setInt(2, board.getParentNo());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
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
		String sql = "select * from new_board order by board_no, parent_no desc";
		BoardDTO brd = null;
		List<BoardDTO> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				brd = new BoardDTO();
				brd.setBoardNo(rs.getInt("board_no"));
				brd.setParentNo(rs.getInt("parent_no"));
				brd.setTitle(rs.getString("title"));
				brd.setContent(rs.getString("content"));
				brd.setCreateDate(rs.getString("creation_date"));
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

}
