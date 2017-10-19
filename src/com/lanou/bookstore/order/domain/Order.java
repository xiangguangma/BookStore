package com.lanou.bookstore.order.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class Order implements Serializable {
    private String oid;
    private String ordertime;
    private String total;
    private int state; // 订单状态
    private String uid;
    private String address;
    private boolean del;
    private List<OrderItem> orderItemList;

    public Order() {
    }

    public Order(String oid, String ordertime, String total, int state, String uid, String address, boolean del, List<OrderItem> orderItemList) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.total = total;
        this.state = state;
        this.uid = uid;
        this.address = address;
        this.del = del;
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", total='" + total + '\'' +
                ", state='" + state + '\'' +
                ", uid='" + uid + '\'' +
                ", address='" + address + '\'' +
                ", orderItemList=" + orderItemList +
                '}';
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }


}
