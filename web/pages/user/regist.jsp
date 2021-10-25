<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function () {
            //给用户名输入框绑定失去焦点事件
            $("#username").blur(function () {
                //1.获取用户名
                var username = this.value;
                var usernamePatt = /^\w{5,12}$/;
                //2.发AJAX请求
                $.getJSON("http://localhost:8080/book/userServlet", "action=ajaxExistsUsername&username=" + username,
                    function (data) {
                        if (data.existsUsername) {
                            $("span.errorMsg").text("用户名已存在！");
                        } else {
                            if (!usernamePatt.test(username)) {
                                $("span.errorMsg").text("用户名不合法！");
                            } else {
                                $("span.errorMsg").text("用户名可用");
                            }
                        }
                    });
            });

            //1.给验证码图片绑定单击事件
            $("#code_img").click(function () {
               this.src="${basePath}/kaptcha.jpg?d=" + new Date();
            });

            //2.给注册按钮绑定单击事件
            $("#sub_btn").click(function () {
                //2.1验证用户名
                //2.1.1 获取用户输入框里的内容
                var usernameText = $("#username").val();
                //2.1.2 创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;
                //2.1.3 使用test方法验证
                if (!usernamePatt.test(usernameText)) {
                    //2.1.4 提示用户结果
                    $("span.errorMsg").text("用户名不合法！");
                    return false;
                }
                //2.1.5 合法则去掉不合法的提示结果，这是为了防止跳转太慢，不合法的内容还显示着
                $("span.errorMsg").text("");


                //2.2 验证密码	步骤和验证用户名相同
                var passwordText = $("#password").val();
                var passwordPatt = /^\w{5,12}$/;
                if (!passwordPatt.test(passwordText)) {
                    $("span.errorMsg").text("密码不合法！");
                    return false;
                }
                $("span.errorMsg").text("");


                //2.3 确认密码
                //2.3.1 获取用户输入框内容
                var repwdText = $("#repwd").val();
                //2.3.2 和密码比较
                if (repwdText != passwordText) {
                    //2.3.3 提示用户
                    $("span.errorMsg").text("确认密码和输入的密码不一致！");
                    return false;
                }
                $("span.errorMsg").text("");


                //2.4 邮箱验证	步骤和验证用户名相同
                var emailText = $("#email").val();
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailPatt.test(emailText)) {
                    $("span.errorMsg").text("邮箱不合法！");
                    return false;
                }
                $("span.errorMsg").text("");


                //2.5 验证码
                var codeText = $("#code").val();
                var codeTrimText = $.trim(codeText);	//$.trim(JQ)去除前后空格
                if (codeTrimText == null || codeTrimText == "") {
                    $("span.errorMsg").text("验证码不合法！");
                    return false;
                }
                $("span.errorMsg").text("");
            });
        });
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        <%--<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                        ${ requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                               <%--value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
                               value="${ requestScope.username }"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                               <%--value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"--%>
                               value="${ requestScope.email }"
                        />
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 30px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>