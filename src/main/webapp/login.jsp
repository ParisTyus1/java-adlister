<%--
  Created by IntelliJ IDEA.
  User: ptyus
  Date: 2/26/20
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.PrintWriter" %>

<html>
<head>
    <title>Login</title>
    <jsp:include page="partials/css/style.css"/>
    <jsp:include page="partials/head.jsp"/>

</head>
<body>

<jsp:include page="partials/headerNav.jsp"/>


<h3 style="text-align: center;">Login Form</h3>
<div style="text-align: center;">
    <form action="login.jsp" method="post">
        Username:<input type="text" name="username"/><br/><br/>
        Password:<input type="password" name="password"/><br/><br/>
        <input type="submit" value="Submit"/>
    </form>
</div>
<%
    PrintWriter printer = response.getWriter();
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if(username != null && password != null ){
        if (username.equals("admin")&& password.equals("password123")){
            response.sendRedirect("/profile.jsp");
        }else {
            response.sendRedirect("/login.jsp");
        }
    }


%>
<br/>


<jsp:include page="partials/footer.jsp" />
<jsp:include page="partials/script.jsp"/>
</body>
</html>
