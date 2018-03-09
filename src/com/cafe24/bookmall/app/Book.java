package com.cafe24.bookmall.app;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cafe24.bookmall.dao.BookDAO;
import com.cafe24.bookmall.vo.BookVO;
import com.cafe24.bookmall.vo.CategoryVO;

public class Book {
    public void create( String name, int price, Long categoryId ) {
	BookDAO dao = new BookDAO();
	BookVO vo = new BookVO();

	vo.setTitle( name );
	vo.setPrice( price );
	vo.setCategoryId( categoryId );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

    public void readAll() {
	BookDAO dao = new BookDAO();
	List<BookVO> list = dao.readAll();
	for ( BookVO vo : list ) {
	    System.out.println( vo );
	}
    }

    public void readByTitle( String name ) {
	BookDAO dao = new BookDAO();
	BookVO vo = dao.readByTitle( name );
	System.out.println( vo );
    }

    public void readAllWithCategory() {
	BookDAO dao = new BookDAO();
	Map<BookVO, CategoryVO> map = dao.readAllWithCategory();

	Set<BookVO> keySet = map.keySet();
	Iterator<BookVO> it = keySet.iterator();

	while ( it.hasNext() ) {
	    BookVO vo = it.next();
	    System.out.println( vo + " \n " + map.get( vo ) );
	}
    }

    public void deleteById( Long id ) {
	BookDAO dao = new BookDAO();
	System.out.println( dao.deleteById( id ) ? "Delete complete" : "Delete Error" );
    }
}
