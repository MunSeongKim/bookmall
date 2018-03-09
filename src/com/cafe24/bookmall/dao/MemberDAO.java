package com.cafe24.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.bookmall.vo.MemberVO;

public class MemberDAO {

    public boolean create( MemberVO vo ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = "INSERT INTO member VALUES(null, ?, ?, ?, password(?))";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setString( 1, vo.getName() );
	    pstmt.setString( 2, vo.getPhone() );
	    pstmt.setString( 3, vo.getEmail() );
	    pstmt.setString( 4, vo.getPassword() );
	    int count = pstmt.executeUpdate();

	    result = (count == 1);
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return result;
    }

    public List<MemberVO> readAll() {
	List<MemberVO> list = new ArrayList<MemberVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT id, name, phone, email FROM member";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		MemberVO vo = new MemberVO();
		vo.setId( rs.getLong( 1 ) );
		vo.setName( rs.getString( 2 ) );
		vo.setPhone( rs.getString( 3 ) );
		vo.setEmail( rs.getString( 4 ) );

		list.add( vo );
	    }
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( rs != null ) rs.close();
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return list;
    }

    public MemberVO readByName( String name ) {
	MemberVO vo = new MemberVO();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT id, name, phone, email FROM member WHERE name = ?";
	    pstmt = conn.prepareStatement( sql );
	    pstmt.setString( 1, name );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		vo.setId( rs.getLong( 1 ) );
		vo.setName( rs.getString( 2 ) );
		vo.setPhone( rs.getString( 3 ) );
		vo.setEmail( rs.getString( 4 ) );
	    }
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( rs != null ) rs.close();
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return vo;
    }

    public boolean deleteById( Long id ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = "DELETE FROM member WHERE id = ?";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setLong( 1, id );
	    int count = pstmt.executeUpdate();

	    result = (count == 1);
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return result;
    }

    private Connection getConnection() throws SQLException {
	Connection conn = null;

	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	    String url = "jdbc:mysql://localhost:3306/bookmall";
	    conn = DriverManager.getConnection( url, "bookmall", "bookmall" );
	} catch ( ClassNotFoundException e ) {
	    System.out.println( "Failed to load driver : " + e );
	}

	return conn;
    }
}
