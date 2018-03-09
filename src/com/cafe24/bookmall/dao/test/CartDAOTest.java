package com.cafe24.bookmall.dao.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cafe24.bookmall.dao.CartDAO;
import com.cafe24.bookmall.vo.BookVO;
import com.cafe24.bookmall.vo.CartVO;

public class CartDAOTest {
    public static void main( String[] args ) {
	//createTest(3L, 1L, 1);
	readAllWithBookTest();
    }

    private static void createTest( Long bookId, Long memberId, int count ) {
	CartDAO dao = new CartDAO();
	CartVO vo = new CartVO();

	vo.setBookId( bookId );
	vo.setMemberId( memberId );
	vo.setCount( count );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

    private static void readAllTest() {
	CartDAO dao = new CartDAO();
	List<CartVO> list = dao.readAll();
	for ( CartVO vo : list ) {
	    System.out.println( vo );
	}

    }
    
    private static void readAllWithBookTest() {
	CartDAO dao = new CartDAO();
	Map<CartVO, BookVO> map = dao.readAllWithBook();
	
	Set<CartVO> keySet = map.keySet();
	Iterator<CartVO> it = keySet.iterator();
	
	while(it.hasNext()) {
	    CartVO vo = it.next();
	    System.out.println( vo + "\n" + map.get(vo) );
	}
    }
    
    private static void deleteByIdTest( Long bookId, Long memberId ) {
	CartDAO dao = new CartDAO();
	System.out.println( dao.deleteById( bookId, memberId ) ? "Delete complete" : "Delete Error" );
    }
}
