<%@ page import="com.dist.controller.HomeController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="someModel" scope="request" class="com.dist.controller.HomeController"/>
<html>

<head>

    <script src="<c:url value="/resources/js/jquery/jquery-3.0.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery/jquery-ui.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery/jquery.bootgrid.js" />"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.bootgrid.min.css" />">
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/util/dist.commons.js" />"></script>
    <script src="<c:url value="/resources/js/util/underscore.js" />"></script>

    <script src="<c:url value="/resources/js/home.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <%@ page isELIgnored="false" %>
    <title>Home</title>
</head>
<body>

<div class="container">
    <h1>Home</h1>
    <div class="well">
        <div class="container center">
            <div class="row">
                <div class="col-lg-3">
                    Node is running on IP :
                    <g id="ip">${ip}</g>
                    PORT :
                    <div id="port">${port}</div>
                </div>
            </div>
        </div>
        <div class="well">
            <div class="container center">
                <div class="row">
                    <div class="col-lg-3">
                        Register as a new node
                    </div>
                    <div class="col-lg-2">
                        <button class="btn btn-primary" id="regBtn">Click here</button>
                    </div>
                    <div class="col-lg-2" id="reg_response">
                    </div>
                </div>
            </div>
        </div>
        <div class="well">
            <div class="container row-lg-6">
                <div class="row">
                    <div class="col-lg-3 center">
                        Leave from bootstrap server
                    </div>
                    <div class="col-lg-2">
                        <button class="btn btn-primary" id="leaveBtn">Click here</button>
                    </div>
                    <div class="col-lg-2" id="leave_response">
                    </div>
                </div>
            </div>
        </div>
        <div id="showFiles">
            <a>Show files</a>
        </div>
        <div class="well" id="fileWell">
            <div class="container">
                <div class="row dist-file-row">
                    <div class="col-lg-10">
                        Files in the local drive :
                    </div>
                    <div class="col-lg-2">
                        <button class="btn btn-primary dist-btn" id="refreshBtn">@</button>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row dist-file-row">
                    <div class="col-lg-1 result-header">*</div>
                    <div class="col-lg-3 result-header">File Name</div>
                    <div class="col-lg-2 result-header">Change</div>
                </div>
            </div>
            <div class="container" id="fileContainer">

            </div>
        </div>
    </div>
    <div class="well">
        <div class="well">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        Search for file
                    </div>
                    <div class="col-lg-4">
                        File name: <input id="searchName" type="text">
                    </div>
                    <div class="col-lg-4">
                        <button class="btn btn-primary" id="searchBtn">Click here</button>
                    </div>
                    <div class="col-lg-2" id="search_response">
                    </div>
                </div>
            </div>
        </div>
        <div class="well">
            <div class="container">
                <div class="row dist-file-row">Search results :</div>
            </div>
            <div class="container">
                <div class="row dist-file-row">
                    <div class="col-lg-1 result-header">*</div>
                    <div class="col-lg-3 result-header">File Name</div>
                    <div class="col-lg-2 result-header">IP</div>
                    <div class="col-lg-2 result-header">Port</div>
                    <div class="col-lg-2 result-header">Hops</div>
                    <div class="col-lg-2 result-header">Download</div>
                </div>
            </div>
            <div class="container" id="resultsContainer">

            </div>
        </div>
    </div>
</div>
<div id="notificationPopupContainer">

</div>
</body>
</html>