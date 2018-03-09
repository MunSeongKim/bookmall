package com.cafe24.bookmall.dao.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cafe24.bookmall.dao.OrderDAO;
import com.cafe24.bookmall.vo.OrderVO;
import com.cafe24.bookmall.vo.CategoryVO;
import com.cafe24.bookmall.vo.OrderBookVO;

public class OrderDAOTest {

    public static void main( String[] args ) {
	// createTest("2018-03-09", "서울시 강남구", 1L);
	readAllTest();
	readByDateTest( "2018-03-09" );
	readAllWithOrderBookByMemberTest( 1L );
    }

    private static void createTest( String date, String location, Long memberId ) {
	OrderDAO dao = new OrderDAO();
	OrderVO vo = new OrderVO();

	vo.setDate( date );
	vo.setLocation( location );
	vo.setMemberId( memberId );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

    private static void readAllTest() {
	OrderDAO dao = new OrderDAO();
	List<OrderVO> list = dao.readAll();
	for ( OrderVO vo : list ) {
	    System.out.println( vo );
	}
    }

    private static void readByDateTest( String date ) {
	OrderDAO dao = new OrderDAO();
	OrderVO vo = dao.readByDate( date );
	System.out.println( vo );
    }

    private static void readAllWithOrderBookByMemberTest( Long memberId ) {
	OrderDAO dao = new OrderDAO();
	Map<OrderVO, OrderBookVO> map = dao.readAllWithOrderBookByMember( memberId );

	Set<OrderVO> keySet = map.keySet();
	Iterator<OrderVO> it = keySet.iterator();

	while ( it.hasNext() ) {
	    OrderVO vo = it.next();
	    System.out.println( vo + "\n" + map.get( vo ) );
	}
    }

    private static void deleteByIdTest( Long id ) {
	OrderDAO dao = new OrderDAO();
	System.out.println( dao.deleteById( id ) ? "Delete complete" : "Delete Error" );
    }

}
