package com.cafe24.bookmall.vo;

public class OrderBookVO {
    private Long bookId;
    private Long orderId;
    private Integer count;
    private Integer price;

    public Long getBookId() {
	return bookId;
    }

    public void setBookId( Long bookId ) {
	this.bookId = bookId;
    }

    public Long getOrderId() {
	return orderId;
    }

    public void setOrderId( Long orderId ) {
	this.orderId = orderId;
    }

    public Integer getCount() {
	return count;
    }

    public void setCount( Integer count ) {
	this.count = count;
    }

    public Integer getPrice() {
	return price;
    }

    public void setPrice( Integer price ) {
	this.price = price;
    }

    @Override
    public String toString() {
	return "bookId:" + bookId + ", orderId:" + orderId + ", count:" + count + ", price:" + price;
    }

}