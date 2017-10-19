package com.lanou.bookstore.cart.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by dllo on 17/9/22.
 */
public class Cart implements Serializable {
    private Map<String, CartItem> cartItems;

    public Cart() {
    }

    public Cart(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    // delete 方法
    public void delete(String bid){
        Set<String> key = cartItems.keySet();
        Iterator<String> iterator = key.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            // remove 这条 iterator, 不是 key
            if (next.equals(bid)){
                iterator.remove();
            }
        }

    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                '}';
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
