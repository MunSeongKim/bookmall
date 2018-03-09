package com.cafe24.bookmall.dao.test;

import com.cafe24.bookmall.dao.BookDAO;
import com.cafe24.bookmall.dao.OrderBookDAO;
import com.cafe24.bookmall.vo.BookVO;
import com.cafe24.bookmall.vo.OrderBookVO;

public class OrderBookDAOTest {

    public static void main( String[] args ) {
	//createTest(1L, 1L, 3);
	// createTest(2L, 1L, 3);

    }
    
    private static void createTest( Long bookId, Long orderId, int count ) {
	OrderBookDAO dao = new OrderBookDAO();
	OrderBookVO vo = new OrderBookVO();
	
	BookVO bookVo = new BookDAO().readById(bookId);

	vo.setBookId( bookId );
	vo.setOrderId( orderId );
	vo.setCount( count );
	vo.setPrice( bookVo.getPrice() * count );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

}
