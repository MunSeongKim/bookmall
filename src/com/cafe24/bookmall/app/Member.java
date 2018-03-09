package com.cafe24.bookmall.app;

import java.util.List;

import com.cafe24.bookmall.dao.MemberDAO;
import com.cafe24.bookmall.vo.MemberVO;

public class Member {
    public void create(String name, String phone, String email, String password) {
	MemberDAO dao = new MemberDAO();
	MemberVO vo = new MemberVO();

	vo.setName( name );
	vo.setPhone( phone );
	vo.setEmail( email );
	vo.setPassword( password );
	System.out.println( dao.create( vo ) ? "Create complete" : "Create Error" );
    }

    public void readAll() {
	MemberDAO dao = new MemberDAO();
	List<MemberVO> list = dao.readAll();
	for ( MemberVO vo : list ) {
	    System.out.println( vo );
	}
    }
    
    public void readByName( String name ) {
	MemberDAO dao = new MemberDAO();
	System.out.println( dao.readByName(name) );
    }
    
    public void deleteById( Long id ) {
	MemberDAO dao = new MemberDAO();
	System.out.println( dao.deleteById( id ) ? "Delete complete" : "Delete Error" );
	
    }
}
