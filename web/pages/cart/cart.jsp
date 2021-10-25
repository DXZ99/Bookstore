<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/pages/common/head.jsp"%>
<script type="text/javascript">
	$(function () {
        $("a.deleteItem").click(function () {
			return confirm("确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗？");
        });

        $("#clearCart").click(function () {
			return confirm("确定要清空购物车吗？")
        });
        
        $("input.updateCount").change(function () {
            var name = $(this).parent().parent().find("td:first").text();
            var count = $(this).val();

            var countPatt = /^[1-9]\d*$/;
            if (!countPatt.test(count)) {
                alert("输入的数量有误！");
                this.value = this.defaultValue;
                return false;
            }

			if (confirm("确定要修改【" + name  +"】的数量为" + count +"吗？")) {
				//确认修改，则发起请求给服务器保存修改
				var id = $(this).attr("bookId");
				location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count="
					+ count + "&id=" + id;
			} else {
			    //取消修改，则恢复为修改前的值
			    //defaultValue是表单项Dom对象的值，表示默认的value值
			    this.value = this.defaultValue;
			}
        });
    });
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%--购物车无商品--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，您还未挑选任何产品，快去抢购吧~</a></td>
				</tr>
			</c:if>
			<%--购物车有商品，则遍历输出--%>
			<c:if test="${not empty sessionScope.cart.items}">
			<c:forEach items="${sessionScope.cart.items}" var="entry">
			<tr>
				<td>${entry.value.name}</td>
				<td>
					<input class="updateCount" style="width: 80px;"
						   bookId="${entry.value.id}"
						   type="text" value="${entry.value.count}">
				</td>
				<td>${entry.value.price}</td>
				<td>${entry.value.totalPrice}</td>
				<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
			</tr>
			</c:forEach>
			</c:if>
			
		</table>

		<%--购物车有商品才输出以下内容--%>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
		</div>
		</c:if>

	</div>

	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>