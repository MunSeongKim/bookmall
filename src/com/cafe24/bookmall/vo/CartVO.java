package com.cafe24.bookmall.vo;

public class CartVO {
    private Long bookId;
    private Long memberId;
    private int count;

    public Long getBookId() {
	return bookId;
    }

    public void setBookId( Long bookId ) {
	this.bookId = bookId;
    }

    public Long getMemberId() {
	return memberId;
    }

    public void setMemberId( Long memberId ) {
	this.memberId = memberId;
    }

    public int getCount() {
	return count;
    }

    public void setCount( int count ) {
	this.count = count;
    }

    @Override
    public String toString() {
	return "bookId:" + bookId + ", memberId:" + memberId + ", count:" + count;
    }

}
