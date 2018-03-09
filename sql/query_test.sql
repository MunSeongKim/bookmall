use bookmall;

INSERT INTO member
VALUES (null, '둘리', '000-001-0001', 'a@a.com', password('1234'));
INSERT INTO member
VALUES (null, '마이콜', '000-002-0002', 'b@b.com', password('1234'));
INSERT INTO member
VALUES (null, '도우넛', '000-003-0003', 'c@c.com', password('1234'));
INSERT INTO category
VALUEs (null, '컴퓨터/IT');
INSERT INTO category
VALUEs (null, '소설');
INSERT INTO category
VALUEs (null, '음악');
INSERT INTO book
VALUES (null, '토비의 스프링 3.1', 32000, 1);
INSERT INTO book
VALUES (null, '태백산맥', 16000, 2);
INSERT INTO cart
VALUES ( ( SELECT id FROM book WHERE title = '태백산맥' ), ( SELECT id FROM member WHERE name='둘리' ), 2 );




SELECT id, name, phone, email, password
  FROM member;
  
SELECt *
  FROM category;
  
SELECT *
  FROM book;
  
SELECT book.id, book.title, book.price, book.category_id, cart.book_id, cart.member_id, cart.count
  FROM book, cart
 WHERE book.id = cart.book_id;
 
 SELECT *
   FROM `order`;
   
   
SELECT `order`.id, `order`.order_no,
	   `order`.date, `order`.location,
	   `order`.member_id, order_book.count,
	   order_book.price
  FROM `order`, `order_book`
 WHERE `order`.id = order_book.order_id
   AND `order`.member_id = 1;