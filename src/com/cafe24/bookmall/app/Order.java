package com.cafe24.bookmall.app;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cafe24.bookmall.dao.OrderDAO;
import com.cafe24.bookmall.vo.OrderBookVO;
import com.cafe24.bookmall.vo.OrderVO;

public class Order {
    public void create( String date, String location, Long memberId ) {
	OrderDAO dao = new OrderDAO();
	OrderVO vo = new OrderVO();

	vo.setDate( date );
	vo.setLocation( location );
	vo.setMemberId( memberId );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

    public void readAll() {
	OrderDAO dao = new OrderDAO();
	List<OrderVO> list = dao.readAll();
	for ( OrderVO vo : list ) {
	    System.out.println( vo );
	}
    }

    public void readByDate( String date ) {
	OrderDAO dao = new OrderDAO();
	OrderVO vo = dao.readByDate( date );
	System.out.println( vo );
    }

    public void readAllWithOrderBookByMember( Long memberId ) {
	OrderDAO dao = new OrderDAO();
	Map<OrderVO, OrderBookVO> map = dao.readAllWithOrderBookByMember( memberId );

	Set<OrderVO> keySet = map.keySet();
	Iterator<OrderVO> it = keySet.iterator();

	while ( it.hasNext() ) {
	    OrderVO vo = it.next();
	    System.out.println( vo + "\n" + map.get( vo ) );
	}
    }

    public void deleteById( Long id ) {
	OrderDAO dao = new OrderDAO();
	System.out.println( dao.deleteById( id ) ? "Delete complete" : "Delete Error" );
    }
}
