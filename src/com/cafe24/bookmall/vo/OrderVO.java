package com.cafe24.bookmall.vo;

public class OrderVO {
    private Long id;
    private String orderNo;
    private String date;
    private String location;
    private Long memberId;

    public Long getId() {
	return id;
    }

    public void setId( Long id ) {
	this.id = id;
    }

    public String getOrderNo() {
	return orderNo;
    }

    public void setOrderNo( String orderNo ) {
	this.orderNo = orderNo;
    }

    public String getDate() {
	return date;
    }

    public void setDate( String date ) {
	this.date = date;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation( String location ) {
	this.location = location;
    }

    public Long getMemberId() {
	return memberId;
    }

    public void setMemberId( Long memberId ) {
	this.memberId = memberId;
    }

    @Override
    public String toString() {
	return "id:" + id + ", orderNo:" + orderNo + ", date:" + date + ", location:" + location
		+ ", member_id:" + memberId;
    }

}
