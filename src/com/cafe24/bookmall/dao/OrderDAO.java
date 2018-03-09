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

import com.cafe24.bookmall.vo.OrderBookVO;
import com.cafe24.bookmall.vo.OrderVO;

public class OrderDAO {
    public boolean create( OrderVO vo ){
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    // 마지막, 주문번호 가져오기
	    OrderVO tmpOrderVO = readAtLast();
	    if ( tmpOrderVO.getOrderNo() == null ) {
		vo.setOrderNo("BM00001");
	    } else {
		String lastOrderNo = tmpOrderVO.getOrderNo().substring( 6 );
		vo.setOrderNo( "BM" + String.format( "%05", Integer.parseInt( lastOrderNo ) + 1 ) );
	    }
	    String sql = "INSERT INTO `order` VALUES(null, ?, DATE_FORMAT(?, '%Y-%m-%d'), ?, ?)";
	    pstmt = conn.prepareStatement( sql );
	    
	    pstmt.setString( 1, vo.getOrderNo() );
	    pstmt.setString( 2, vo.getDate() );
	    pstmt.setString( 3, vo.getLocation() );
	    pstmt.setLong( 4, vo.getMemberId() );
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
    
    public OrderVO readAtLast() {
	OrderVO vo = new OrderVO();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT MAX(order_no) FROM `order`";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		vo.setOrderNo(rs.getString(1));
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
    
    public List<OrderVO> readAll() {
	List<OrderVO> list = new ArrayList<OrderVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT id, order_no, date, location, member_id FROM `order`";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		OrderVO vo = new OrderVO();
		vo.setId( rs.getLong( 1 ) );
		vo.setOrderNo( rs.getString( 2 ) );
		vo.setDate( rs.getString( 3 ) );
		vo.setLocation( rs.getString( 4 ) );
		vo.setMemberId( rs.getLong( 5 ) );

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
    
    public Map<OrderVO, OrderBookVO> readAllWithOrderBookByMember( Long memberId ) {
	Map<OrderVO, OrderBookVO> map = new HashMap<OrderVO, OrderBookVO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT `order`.id, `order`.order_no, "
	               + "`order`.date, `order`.location, "
		       + "`order`.member_id, `order_book`.count, "
		       + "`order_book`.price, `order_book`.book_id " 
	               + "FROM `order`, `order_book` "
		       + "WHERE `order`.id = `order_book`.order_id "
	               + "AND `order`.member_id = ?";
	    pstmt = conn.prepareStatement( sql );
	    pstmt.setLong(1, memberId);
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		OrderVO orderVo = new OrderVO();
		orderVo.setId( rs.getLong( 1 ) );
		orderVo.setOrderNo( rs.getString( 2 ) );
		orderVo.setDate( rs.getString( 3 ) );
		orderVo.setLocation( rs.getString( 4 ) );
		orderVo.setMemberId( rs.getLong( 5 ) );
		OrderBookVO orderBookVo = new OrderBookVO();
		orderBookVo.setCount( rs.getInt( 6 ) );
		orderBookVo.setPrice( rs.getInt( 7 ) );
		orderBookVo.setBookId( rs.getLong( 8 ) );

		map.put(orderVo, orderBookVo);
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
    
    public OrderVO readByDate( String date ) {
	OrderVO vo = new OrderVO();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "SELECT id, order_no, date, location, member_id FROM `order` WHERE date = ?";
	    pstmt = conn.prepareStatement( sql );
	    pstmt.setString( 1, date );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		vo.setId( rs.getLong( 1 ) );
		vo.setOrderNo( rs.getString( 2 ) );
		vo.setDate( rs.getString( 3 ) );
		vo.setLocation( rs.getString( 4 ) );
		vo.setMemberId( rs.getLong( 5 ) );
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
	    String sql = "DELETE FROM order WHERE id = ?";
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
