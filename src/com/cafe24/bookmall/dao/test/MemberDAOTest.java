package com.cafe24.bookmall.dao.test;

import java.util.List;

import com.cafe24.bookmall.dao.MemberDAO;
import com.cafe24.bookmall.vo.MemberVO;

public class MemberDAOTest {

    public static void main( String[] args ) {
	createTest("둘리", "010-0001-0001", "a@a.com", "1234");
	createTest("마이콜", "010-0001-0001", "b@b.com", "1234");
	readAllTest();
	readByNameTest("마이콜");
	//deleteByIdTest(5L);
    }

    private static void createTest(String name, String phone, String email, String password) {
	MemberDAO dao = new MemberDAO();
	MemberVO vo = new MemberVO();

	vo.setName( name );
	vo.setPhone( phone );
	vo.setEmail( email );
	vo.setPassword( password );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

    private static void readAllTest() {
	MemberDAO dao = new MemberDAO();
	List<MemberVO> list = dao.readAll();
	for ( MemberVO vo : list ) {
	    System.out.println( vo );
	}
    }
    
    private static void readByNameTest( String name ) {
	MemberDAO dao = new MemberDAO();
	System.out.println( dao.readByName(name) );
    }
    
    private static void deleteByIdTest( Long id ) {
	MemberDAO dao = new MemberDAO();
	System.out.println( dao.deleteById( id ) ? "Delete complete" : "Delete Error" );
	
    }
}
