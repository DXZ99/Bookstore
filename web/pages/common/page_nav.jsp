<%--
  Created by IntelliJ IDEA.
  User: zhaodongxiao
  Date: 2020/12/24
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页条--%>
<div id="page_nav">
    <%--1.大于首页才显示 首页和上一页--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
    </c:if>

    <%--2.页码输出：获得begin、end--%>
    <c:choose>
        <%--2.1总页码小于等于5--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:set var="begin" value="1" />
            <c:set var="end" value="${requestScope.page.pageTotal}" />
        </c:when>
        <%--2.2总页码大于5，再分3种小情况--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <%--2.2.1 当前页码在前3页--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1" />
                    <c:set var="end" value="5" />
                </c:when>
                <%--2.2.2 当前页码在后3页--%>
                <c:when test="${requestScope.page.pageNo >= (requestScope.page.pageTotal-2)}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}" />
                    <c:set var="end" value="${requestScope.page.pageTotal}" />
                </c:when>
                <%--2.2.3 当前页码为中间页码--%>
                <c:when test="${requestScope.page.pageNo > 3 && requestScope.page.pageNo < (requestScope.page.pageTotal-2)}">
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}" />
                    <c:set var="end" value="${requestScope.page.pageNo + 2}" />
                </c:when>
            </c:choose>
        </c:when>
    </c:choose>

    <%--2.页码输出：设置完begin，end值后再统一遍历，比每种方法都各自遍历写起来更简洁--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--3.小于末页才显示 末页和下一页--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    <%--4.共多少页和记录--%>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录

    <%--5.跳转到指定页码--%>
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" id="searchPageBtn" value="确定">

    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                if (pageNo < 1){
                    pageNo = 1;
                } else if (pageNo > ${requestScope.page.pageTotal}) {
                    pageNo = ${requestScope.page.pageTotal};
                }
                //JS语言中提供了一个location地址栏对象
                //它有个属性href，可以获取浏览器地址栏中的地址，它可读可写
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });
    </script>
</div>
