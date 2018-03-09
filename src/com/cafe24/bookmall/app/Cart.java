package com.cafe24.bookmall.app;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cafe24.bookmall.dao.CartDAO;
import com.cafe24.bookmall.vo.BookVO;
import com.cafe24.bookmall.vo.CartVO;

public class Cart {
    public void create( Long bookId, Long memberId, int count ) {
	CartDAO dao = new CartDAO();
	CartVO vo = new CartVO();

	vo.setBookId( bookId );
	vo.setMemberId( memberId );
	vo.setCount( count );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

    public void readAll() {
	CartDAO dao = new CartDAO();
	List<CartVO> list = dao.readAll();
	for ( CartVO vo : list ) {
	    System.out.println( vo );
	}

    }
    
    public void readAllWithBook() {
	CartDAO dao = new CartDAO();
	Map<CartVO, BookVO> map = dao.readAllWithBook();
	
	Set<CartVO> keySet = map.keySet();
	Iterator<CartVO> it = keySet.iterator();
	
	while(it.hasNext()) {
	    CartVO vo = it.next();
	    System.out.println( vo + "\n" + map.get(vo) );
	}
    }
    
    public void deleteById( Long bookId, Long memberId ) {
	CartDAO dao = new CartDAO();
	System.out.println( dao.deleteById( bookId, memberId ) ? "Delete complete" : "Delete Error" );
    }
}
