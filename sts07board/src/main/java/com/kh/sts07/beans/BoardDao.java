package com.kh.sts07.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDao {
	
	private static DataSource source;
	static {
		// source에 context.xml의 Resource 정보를 설정
		// [1] 탐색 도구 생성
		// [2] 도구를 이용하여 탐색 후 source에 대입
		try {
			InitialContext ctx = new InitialContext(); //[1]
			source = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle"); // name="jdbc/oracle" 찾아
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
//	연결 메소드
	public Connection getConnection() throws Exception {
//		return common-dbcp에서 관리하는 연결을 빌려와라;
		Class.forName("oracle.jdbc.OracleDriver");
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe","home", "home");
	}
	
	public List<BoardDto> getList(int start, int finish) throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from ("
						+ "select rownum rn, A.* from ("
							+ "select * from board "
							+ "connect by prior no = superno "
							+ "start with superno is null "
							+ "order siblings by groupno desc, no asc"
						+ ")A"
					+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, start);
		ps.setInt(2, finish);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setNo(rs.getInt("no"));
			dto.setHead(rs.getString("head"));
			dto.setTitle(rs.getString("title"));
			dto.setReplyCount(rs.getInt("replycount"));
			dto.setWriter(rs.getString("writer"));
			dto.setWdate(rs.getString("wdate"));
			dto.setReadCount(rs.getInt("readcount"));
			dto.setContent(rs.getString("content"));
			
			dto.setGroupno(rs.getInt("groupno"));
			dto.setSuperno(rs.getInt("superno"));
			dto.setDepth(rs.getInt("depth"));
			
			list.add(dto);
		}
		
		con.close();
		return list;
	}
//	등록 - 새글도 등록하고 답글도 등록해야함
//	새글일 경우 no, head, title, content가 들어있다
//	답글일 경우 no, group, depth, head, title, content
	public void write(BoardDto dto) throws Exception{
		Connection con = getConnection();
		// [1]no, [2]head [3]title, [4]writer, [5]content, [6]groupno, [7]superno, [8]depth
		String sql = "insert into board values(?, ?, ?, 0, ?, sysdate, 0, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, dto.getNo());
		ps.setString(2, dto.getHead());
		ps.setString(3, dto.getTitle());
		ps.setString(4, dto.getWriter());
		ps.setString(5, dto.getContent());
		
		if(dto.getGroupno()==0) { // 새글
			ps.setInt(6, dto.getNo());			
			ps.setNull(7, Types.INTEGER); // 숫자에 들어가는 널
			ps.setInt(8, 0);
		}
		else { // 답글
			ps.setInt(6, dto.getGroupno()); // 원본글 그룹번호
			ps.setInt(7, dto.getSuperno()); // 원본글 번호
			ps.setInt(8, dto.getDepth()+1);
		}
		
		ps.execute();
		
		con.close();
	}
	
	public BoardDto getInfo(int no) throws Exception{
		Connection con = getConnection();
		String sql = "select * from board where no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		BoardDto dto = new BoardDto();
		dto.setNo(rs.getInt("no"));
		dto.setHead(rs.getString("head"));
		dto.setTitle(rs.getString("title"));
		dto.setReplyCount(rs.getInt("replycount"));
		dto.setWriter(rs.getString("writer"));
		dto.setWdate(rs.getString("wdate"));
		dto.setReadCount(rs.getInt("readcount"));
		dto.setContent(rs.getString("content"));
		dto.setGroupno(rs.getInt("groupno"));
		dto.setSuperno(rs.getInt("superno"));
		dto.setDepth(rs.getInt("depth"));
		
		
		con.close();
		return dto;
	}
	
	public void edit(BoardDto dto) throws Exception{
		Connection con = getConnection();
		String sql = "update board set head=?, title=?, content=? where no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getHead());
		ps.setString(2, dto.getTitle());
		ps.setString(3, dto.getContent());
		ps.setInt(4, dto.getNo());
		ps.execute();
		con.close();
	}
	
	public void delete(int no) throws Exception{
		Connection con = getConnection();
		String sql = "delete board where no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.executeUpdate();
		con.close();
	}
	
	public void delete(BoardDto dto) throws Exception{
		this.delete(dto.getNo());
	}
	
	public void readCountPlus(int no) throws Exception{
		Connection con = getConnection();
		String sql = "update board set readcount = readcount+1 where no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.execute();
		con.close();
	}
	
	public List<BoardDto> search(String type, String keyword, int start, int finish) throws Exception{
		Connection con = getConnection();
		
		String sql = "select * from ("
				+ "select rownum rn, A.* from ("
				+ "select * from board where "+type+" like '%'||?||'%' "
				+ "connect by prior no = superno "
				+ "start with superno is null "
				+ "order siblings by groupno desc, no asc"
				+ ")A"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyword);
		ps.setInt(2, start);
		ps.setInt(3, finish);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setNo(rs.getInt("no"));
			dto.setHead(rs.getString("head"));
			dto.setTitle(rs.getString("title"));
			dto.setReplyCount(rs.getInt("replycount"));
			dto.setWriter(rs.getString("writer"));
			dto.setWdate(rs.getString("wdate"));
			dto.setReadCount(rs.getInt("readcount"));
			dto.setContent(rs.getString("content"));
			
			dto.setGroupno(rs.getInt("groupno"));
			dto.setSuperno(rs.getInt("superno"));
			dto.setDepth(rs.getInt("depth"));
			
			list.add(dto);
		}
		
		con.close();
		return list;
	}

	public int getSequence() throws Exception{
		Connection con = getConnection();
		String sql = "select board_seq.nextval from dual";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int no = rs.getInt("nextval");
		
		con.close();
		return no;
	}
	
	public int getCount(String type, String keyword) throws Exception{
		Connection con = getConnection();
		
//		String sql = "select count(*) from board where " + type + "like '%'||?||'%' order by no desc";
		boolean isSearch = type != null && keyword != null;
		
		String sql = "select count(*) from board";
		if(isSearch) {
			sql += " where "+ type + " like '%'||?||'%' order by no desc";
		}
		PreparedStatement ps = con.prepareStatement(sql);
		if(isSearch) { 
			ps.setString(1, keyword);
		}
		ResultSet rs = ps.executeQuery();
		rs.next();
//		int count = rs.getInt("count(*)");
		int count = rs.getInt(1);
		
		con.close();
		return count;
	}
	
	public void replyCountPlus(int no) throws Exception{
		Connection con = getConnection();
		String sql = "update board set replycount = replycount+1 where no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.execute();
		con.close();
	}
	
	public void replyCountMinus(int no) throws Exception{
		Connection con = getConnection();
		String sql = "update board set replycount = replycount-1 where no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.execute();
		con.close();
	}
}