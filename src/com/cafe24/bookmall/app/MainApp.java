package com.cafe24.bookmall.app;

public class MainApp {
    static {
	Member member = new Member();
	member.create("둘리", "010-0001-0001", "a@a.com", "1234");
	member.create("마이콜", "010-0002-0002", "b@b.com", "1234");
	member.create("도우넛", "010-0003-0003", "c@c.com", "1234");
	
	Category category = new Category();
	category.create("컴퓨터/IT");
	category.create("소설");
	category.create("음악");
	
	Book book = new Book();
	book.create("토비의 스프링 3.1", 32000, 1L);
	book.create("태백산맥", 16000, 2L);
	book.create("서양 클래식 역사", 24000, 3L);
	book.create("이것이 자바다", 18000, 1L);
	
	Cart cart = new Cart();
	cart.create(1L, 1L, 1);
	cart.create(2L, 1L, 2);
	cart.create(1L, 3L, 1);
	
	Order order = new Order();
	order.create("2018-03-09", "서울시 강남구", 1L);
	
	OrderBook orderBook = new OrderBook();
	orderBook.create(1L, 1L, 3);
	orderBook.create(2L, 1L, 3);
    }
    
    
    public static void main( String[] args ) {
	Member member = new Member();
	Category category = new Category();
	Book book = new Book();
	Cart cart = new Cart();
	Order order = new Order();
	OrderBook orderBook = new OrderBook();
	
	System.out.println( "--------- 회원 리스트 ---------" );
	member.readAll();
	// member.readByName("마이콜");
	System.out.println( "---------------------------" );
	
	System.out.println( "--------- 카테고리 리스트 ---------" );
	category.readAll();
	// member.readByName("마이콜");
	System.out.println( "---------------------------" );
	
	System.out.println( "--------- 상품 리스트 ---------" );
	book.readAll();
	// member.readByName("마이콜");
	System.out.println( "---------------------------" );
	
	System.out.println( "--------- 카트 리스트 ---------" );
	cart.readAll();
	// member.readByName("마이콜");
	System.out.println( "---------------------------" );
	
	System.out.println( "--------- 주문 리스트 ---------" );
	order.readAll();
	// member.readByName("마이콜");
	System.out.println( "---------------------------" );
	
	System.out.println( "--------- 주문도서 리스트 ---------" );
	orderBook.readAll();
	// member.readByName("마이콜");
	System.out.println( "---------------------------" );
	
	
    }

}
