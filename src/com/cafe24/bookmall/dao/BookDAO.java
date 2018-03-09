package com.cafe24.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cafe24.bookmall.vo.BookVO;
import com.cafe24.bookmall.vo.CategoryVO;

public class BookDAO {
    public boolean create( BookVO vo ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = "INSERT INTO book VALUES(null, ?, ?, ?)";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setString( 1, vo.getTitle() );
	    pstmt.setInt( 2, vo.getPrice() );
	    pstmt.setLong( 3, vo.getCategoryId() );
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

    public List<BookVO> readAll() {
	List<BookVO> list = new ArrayList<BookVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT id, title, price, category_id FROM book";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		BookVO vo = new BookVO();
		vo.setId( rs.getLong( 1 ) );
		vo.setTitle( rs.getString( 2 ) );
		vo.setPrice( rs.getInt( 3 ) );
		vo.setCategoryId( rs.getLong( 4 ) );

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

    public Map<BookVO, CategoryVO> readAllWithCategory() {
	Map<BookVO, CategoryVO> map = new HashMap<BookVO, CategoryVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT book.id, book.title, book.price, book.category_id, category.id, category.name " +
		    	 "FROM book, category " +
		    	 "WHERE book.category_id = category.id";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		BookVO bvo = new BookVO();
		bvo.setId( rs.getLong( 1 ) );
		bvo.setTitle( rs.getString( 2 ) );
		bvo.setPrice( rs.getInt( 3 ) );
		bvo.setCategoryId( rs.getLong( 4 ) );

		CategoryVO cvo = new CategoryVO();
		cvo.setId(rs.getLong(5));
		cvo.setName(rs.getString(6));
		
		map.put( bvo, cvo );
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

	return map;
    }

    public BookVO readByTitle( String title ) {
	BookVO vo = new BookVO();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT id, title, price, category_id FROM book WHERE title = ?";
	    pstmt = conn.prepareStatement( sql );
	    pstmt.setString( 1, title );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		vo.setId( rs.getLong( 1 ) );
		vo.setTitle( rs.getString( 2 ) );
		vo.setPrice( rs.getInt( 3 ) );
		vo.setCategoryId( rs.getLong( 4 ) );
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
    
    public BookVO readById( Long id ) {
	BookVO vo = new BookVO();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT id, title, price, category_id FROM book WHERE id = ?";
	    pstmt = conn.prepareStatement( sql );
	    pstmt.setLong( 1, id );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		vo.setId( rs.getLong( 1 ) );
		vo.setTitle( rs.getString( 2 ) );
		vo.setPrice( rs.getInt( 3 ) );
		vo.setCategoryId( rs.getLong( 4 ) );
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
	    String sql = "DELETE FROM book WHERE id = ?";
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
