<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>

    <script src="<c:url value="/resources/js/jquery/jquery-3.0.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery/jquery-ui.min.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/util/dist.commons.js" />"></script>

    <script src="<c:url value="/resources/js/home.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">

    <title>Home</title>
</head>
<body>
<div class="container">
    <h1>Home</h1>
</div>
<div class="well">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
               Register as a new node
            </div>
            <div class="col-lg-2">
                <button class="btn btn-primary" id="regBtn">Click here</button>
            </div>
        </div>
    </div>
</div>
<div class="well">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                Leave from bootstrap server
            </div>
            <div class="col-lg-2">
                <button class="btn btn-primary" id="leaveBtn">Click here</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>