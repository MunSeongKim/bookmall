package com.cafe24.bookmall.app;

import java.util.List;

import com.cafe24.bookmall.dao.BookDAO;
import com.cafe24.bookmall.dao.OrderBookDAO;
import com.cafe24.bookmall.vo.BookVO;
import com.cafe24.bookmall.vo.OrderBookVO;

public class OrderBook {
    public void create( Long bookId, Long orderId, int count ) {
	OrderBookDAO dao = new OrderBookDAO();
	OrderBookVO vo = new OrderBookVO();
	
	BookVO bookVo = new BookDAO().readById(bookId);

	vo.setBookId( bookId );
	vo.setOrderId( orderId );
	vo.setCount( count );
	vo.setPrice( bookVo.getPrice() * count );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }
    
    public void readAll() {
	OrderBookDAO dao = new OrderBookDAO();
	List<OrderBookVO> list = dao.readAll();
	for ( OrderBookVO vo : list ) {
	    System.out.println( vo );
	}
    }
}
