package com.lanou.bookstore.order.web.servlet;

import com.lanou.bookstore.admin.service.AdminService;
import com.lanou.bookstore.admin.service.impl.AdminServiceImpl;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderException;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.order.service.impl.OrderServiceImpl;
import com.lanou.bookstore.user.domian.User;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    private BookService bookService = new BookServiceImpl();
    private AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建 order 对象
        Order order = new Order();
        // 获得 cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 校验 车是否存在
        if (cart == null){
            cart = new Cart(new HashMap<>());
        }
        if (cart.getCartItems().size() == 0){
            request.setAttribute("msg", "您没有要买的东西!");
            return "f:/jsps/order/msg.jsp";
        }

        // 设置 oid
        order.setOid(CommonUtils.uuid());
        // 时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sdf.format(date);
        order.setOrdertime(time);
        // 设置总价 count
        String count = request.getParameter("count");
        order.setTotal(count);
        // 设置 订单状态
        order.setState(0);
        // 设置 uid
        User user = (User) request.getSession().getAttribute("formUser");
        order.setUid(user.getUid());

        // 将 cart 中的数据赋给 List<OrderItemDao>
        Map<String, CartItem> cartItems = cart.getCartItems();
        List<OrderItem> orderItemList = new ArrayList<>();
        // 遍历 key
        Set<String> bid = cartItems.keySet();
        for (String s : bid) {
            OrderItem orderItem = new OrderItem();
            // 将 cart 中的数据添加到 orderItemList 中
            orderItem.setIid(CommonUtils.uuid());
            orderItem.setCount(cartItems.get(s).getCount());
            orderItem.setSubtotal(cartItems.get(s).getCount() * cartItems.get(s).getBook().getPrice());
            orderItem.setOid(order.getOid());
            orderItem.setBid(cartItems.get(s).getBook().getBid());
            orderItem.setBook(cartItems.get(s).getBook());
            // 添加 orderItem
            orderItemList.add(orderItem);
        }

        // 将 orderItemList 赋给 order 对象
        order.setOrderItemList(orderItemList);
        // 存储 order
        orderService.addOrder(order);
        // 保存 orderItemList
        orderService.addOrderItemList(orderItemList);
        request.setAttribute("order", order);
        // 删除 cart
        request.getSession().removeAttribute("cart");

        return "f:/jsps/order/desc.jsp";
    }


    public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 user
        User user = (User) request.getSession().getAttribute("formUser");
        List<Order> orders = orderService.findOrderByUid(user.getUid());
        request.setAttribute("orders", orders);
        return "f:/jsps/order/list.jsp";
    }

    /**
     *   付款加载
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 oid
        String oid = request.getParameter("oid");
        // 调用service 方法 获取 order
        Order order = orderService.findOrderByOid(oid);
        request.setAttribute("order", order);
        return "f:/jsps/order/desc.jsp";
    }

    /**
     *  确认收货
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 oid
        String oid = request.getParameter("oid");
        // 调用service 方法 获取 order
        try {
            orderService.confirm(oid);
        } catch (OrderException e) {
            // 有异常保存错误信息转发
            request.setAttribute("msg", e.getMessage());
            return "f:/jsps/order/msg.jsp";
        }
        // 没异常保存转发
        request.setAttribute("msg", "确实成功, 交易完成");
        return "f:/jsps/order/msg.jsp";
    }

    /**
     *  付款
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String submit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 oid
        String oid = request.getParameter("oid");
        // 调用 service 方法 修改 order 的 address
        String address1 = request.getParameter("paddress");
        String address2 = request.getParameter("caddress");
        String address3 = request.getParameter("daddress");
        String address = address1 + address2 + address3;
        if (address.equals("请选择请选择相应市")){
            request.setAttribute("msg", "请先填写收货地址");
            return "f:/jsps/order/desc.jsp";
        }
        orderService.updateAddressByOid(oid, address);
        // 获取 order
        Order order = orderService.findOrderByOid(oid);
        // 修改状态, 保存成功信息转发
        orderService.updateState(order);
        request.setAttribute("msg", "付款成功");
        return "f:/jsps/order/msg.jsp";

    }
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 oid
        String oid = request.getParameter("oid");
        // 调用service 方法 获取 order
        orderService.delete(oid);
        // 没异常保存转发
        request.setAttribute("msg", "删除成功");
        return "f:/jsps/order/msg.jsp";
    }

    public String findCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用service 方法 获取 所有完成的订单
        List<Order> orders = orderService.findAllByState(4);
        Map<String, Integer> map = new HashMap<>();
        int[] sum = new int[15];


        for (Order order : orders) {
            List<OrderItem> orderItems = orderService.findOrderItemsByOid(order.getOid());
            for (OrderItem orderItem : orderItems) {
                String bid = orderItem.getBid();
                if (bid.equals("1")){
                    sum[0] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[0]);
                }
                if (bid.equals("2")){
                    sum[1] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[1]);
                }
                if (bid.equals("3")){
                    sum[2] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[2]);
                }
                if (bid.equals("4")){
                    sum[3] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[3]);
                }
                if (bid.equals("5")){
                    sum[4] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[4]);
                }
                if (bid.equals("6")){
                    sum[5] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[5]);
                }
                if (bid.equals("7")){
                    sum[6] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[6]);
                }
                if (bid.equals("8")){
                    sum[7] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[7]);
                }
                if (bid.equals("9")){
                    sum[8] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[8]);
                }
                if (bid.equals("2CC39FFD8E404824974F3F764785C106")){
                    sum[9] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[9]);
                }
                if (bid.equals("673BCB5AA82B4A64A557EA7568793A03")){
                    sum[10] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[10]);
                }
                if (bid.equals("76DCC833128F4A88980E9947805867B5")){
                    sum[11] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[11]);
                }
                if (bid.equals("9C2E012D2A584400B5D2DA7B556E79C9")){
                    sum[12] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[12]);
                }
                if (bid.equals("CEB2453F5CDD496D888A7C63D2DDEFE0")){
                    sum[13] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[13]);
                }
                if (bid.equals("D95536126B0B47468F197D0EA2D20FE1")){
                    sum[14] += orderItem.getCount();
                    map.put(orderItem.getBid(), sum[14]);
                }

            }

        }
        // 保存转发
        request.setAttribute("map", map);
        return "f:/jsps/order/send.jsp";
    }
}