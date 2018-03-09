package com.cafe24.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.bookmall.vo.CategoryVO;

public class CategoryDAO {
    public boolean create( CategoryVO vo ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = "INSERT INTO category VALUES(null, ?)";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setString( 1, vo.getName() );
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

    public List<CategoryVO> readAll() {
	List<CategoryVO> list = new ArrayList<CategoryVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT id, name FROM category";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		CategoryVO vo = new CategoryVO();
		vo.setId( rs.getLong( 1 ) );
		vo.setName( rs.getString( 2 ) );

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

    public CategoryVO readByName( String name ) {
	CategoryVO vo = new CategoryVO();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT id, name FROM category WHERE name = ?";
	    pstmt = conn.prepareStatement( sql );
	    pstmt.setString( 1, name );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		vo.setId( rs.getLong( 1 ) );
		vo.setName( rs.getString( 2 ) );
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
	    String sql = "DELETE FROM category WHERE id = ?";
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
