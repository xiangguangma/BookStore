package com.lanou.bookstore.cart.web.servlet;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.user.domian.User;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 校验登录
        User formUser = (User) request.getSession().getAttribute("formUser");
        if (formUser == null) {
            request.setAttribute("msg", "请先登录");
            return "r:/jsps/user/login.jsp";
        }
        // 校验购物车为空
        if (request.getParameter("size") == null) {
            return "r:/jsps/main.jsp";
        }

        // 获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 校验购物车是否存在, 如果不存在, 创建一辆车
        if (cart == null) {
            // 添加一辆购物车
            cart = new Cart(new HashMap<>());
        }


        // 获取表单参数
        String bid = request.getParameter("bid");
        int count = Integer.parseInt(request.getParameter("count"));


        // 通过 bid 得到 book
        Book book = bookService.findByBid(bid);


        // 创建 CartItem
        CartItem cartItem = new CartItem();
        // 添加到购物车
        Map<String, CartItem> map = cart.getCartItems();

        // 重复添加的覆盖问题
        if (map.containsKey(bid)) {
            int count1 = map.get(bid).getCount();
            count += count1;
        }
        cartItem.setBook(book);
        cartItem.setCount(count);

        // 将 big,  cartItem 赋给 map,  将 map 赋给 cart
        map.put(bid, cartItem);
        cart.setCartItems(map);
        request.getSession().setAttribute("cart", cart);

        return "f:/jsps/cart/list.jsp";
    }


    public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 校验是否有内容
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart.getCartItems().size() == 0){
            // 为空, 保存错误信息并转发
            request.setAttribute("msg", "您没有什么可以清空的!");
            return "f:/jsps/msg.jsp";
        }
        // 不为空, 清空
        cart.getCartItems().clear();

        return "f:/jsps/cart/list.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bid = request.getParameter("bid");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.delete(bid);

        return "f:/jsps/cart/list.jsp";
    }

}