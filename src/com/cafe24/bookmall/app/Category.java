package com.cafe24.bookmall.app;

import java.util.List;

import com.cafe24.bookmall.dao.CategoryDAO;
import com.cafe24.bookmall.vo.CategoryVO;

public class Category {
    public void create( String name ) {
	CategoryDAO dao = new CategoryDAO();
	CategoryVO vo = new CategoryVO();

	vo.setName( name );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

    public void readAll() {
	CategoryDAO dao = new CategoryDAO();
	List<CategoryVO> list = dao.readAll();
	for ( CategoryVO vo : list ) {
	    System.out.println( vo );
	}
    }

    public void readByName( String name ) {
	CategoryDAO dao = new CategoryDAO();
	CategoryVO vo = dao.readByName( name );
	System.out.println( vo );
    }

    public void deleteById( Long id ) {
	CategoryDAO dao = new CategoryDAO();
	System.out.println( dao.deleteById( id ) ? "Delete complete" : "Delete Error" );
    }
}
