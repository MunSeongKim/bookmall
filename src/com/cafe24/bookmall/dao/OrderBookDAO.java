package com.cafe24.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.bookmall.vo.BookVO;
import com.cafe24.bookmall.vo.OrderBookVO;

public class OrderBookDAO {
    public boolean create( OrderBookVO vo ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();

	    BookVO bookVo = new BookDAO().readById( vo.getBookId() );
	    vo.setPrice( vo.getCount() * bookVo.getPrice() );
	    String sql = "INSERT INTO `order_book` VALUES(?, ?, ?, ?)";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setLong( 1, vo.getBookId() );
	    pstmt.setLong( 2, vo.getOrderId() );
	    pstmt.setInt( 3, vo.getCount() );
	    pstmt.setInt( 4, vo.getPrice() );
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
    
    public List<OrderBookVO> readAll() {
	List<OrderBookVO> list = new ArrayList<OrderBookVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT book_id, order_id, count, price FROM order_book";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		OrderBookVO vo = new OrderBookVO();
		vo.setBookId( rs.getLong( 1 ) );
		vo.setOrderId( rs.getLong( 2 ) );
		vo.setCount( rs.getInt( 3 ) );
		vo.setPrice( rs.getInt( 4 ) );

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
