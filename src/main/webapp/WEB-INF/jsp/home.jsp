<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <script src ="<c:url value="/resources/js/home.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery/jquery-3.0.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery/jquery-ui.min.js" />"></script>
    <title>Home</title>
</head>
<body>
This is home.jsp
<h1>This is Home</h1>
<input id="ab" type="text">
<button onclick="test1();">Click</button>

<form method="post">
    <button id="button_1" value="val_1" name="but1">button 1</button>
    <button id="button_2" value="val_2" name="but2">button 2</button>
    <input id="access_token" type="hidden" name="access_token" value="<?php echo $_SESSION['access_token']; ?>" />
</form>
</body>
</html>