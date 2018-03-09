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
import com.cafe24.bookmall.vo.CartVO;

public class CartDAO {
    public boolean create( CartVO vo ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = "INSERT INTO cart VALUES(?, ?, ?)";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setLong( 1, vo.getBookId() );
	    pstmt.setLong( 2, vo.getMemberId() );
	    pstmt.setInt( 3, vo.getCount() );
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

    public List<CartVO> readAll() {
	List<CartVO> list = new ArrayList<CartVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT book_id, member_id, count FROM cart";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		CartVO vo = new CartVO();
		vo.setBookId( rs.getLong( 1 ) );
		vo.setMemberId( rs.getLong( 2 ) );
		vo.setCount( rs.getInt( 3 ) );

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

    public Map<CartVO, BookVO> readAllWithBook() {
	Map<CartVO, BookVO> map = new HashMap<CartVO, BookVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT book.id, book.title, book.price, book.category_id, cart.book_id, cart.member_id, cart.count " +
		    	 "FROM book, cart " +
		    	 "WHERE book.id = cart.book_id";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		BookVO bvo = new BookVO();
		bvo.setId( rs.getLong( 1 ) );
		bvo.setTitle( rs.getString( 2 ) );
		bvo.setPrice( rs.getInt( 3 ) );
		bvo.setCategoryId( rs.getLong( 4 ) );

		CartVO cvo = new CartVO();
		cvo.setBookId( rs.getLong( 5 ) );
		cvo.setMemberId( rs.getLong( 6 ) );
		cvo.setCount( rs.getInt( 7 ) );

		map.put( cvo, bvo );
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

    public boolean deleteById( Long bookId, Long memberId ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = "DELETE FROM cart WHERE bookId = ? AND memberId = ?";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setLong( 1, bookId );
	    pstmt.setLong( 2, memberId );
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
