package com.cafe24.bookmall.dao.test;

import java.util.List;

import com.cafe24.bookmall.dao.CategoryDAO;
import com.cafe24.bookmall.vo.CategoryVO;

public class CategoryDAOTest {

    public static void main( String[] args ) {
	createTest( "컴퓨터/IT" );
	createTest( "소설" );
	// readAllTest();
	createTest( "음악" );
	//readByNameTest( "음악" );
	//deleteByIdTest( 3L );

    }

    private static void createTest( String name ) {
	CategoryDAO dao = new CategoryDAO();
	CategoryVO vo = new CategoryVO();

	vo.setName( name );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

    private static void readAllTest() {
	CategoryDAO dao = new CategoryDAO();
	List<CategoryVO> list = dao.readAll();
	for ( CategoryVO vo : list ) {
	    System.out.println( vo );
	}
    }

    private static void readByNameTest( String name ) {
	CategoryDAO dao = new CategoryDAO();
	CategoryVO vo = dao.readByName( name );
	System.out.println( vo );
    }

    private static void deleteByIdTest( Long id ) {
	CategoryDAO dao = new CategoryDAO();
	System.out.println( dao.deleteById( id ) ? "Delete complete" : "Delete Error" );
    }
}