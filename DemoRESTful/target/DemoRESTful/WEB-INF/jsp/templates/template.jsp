<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/user/css/style.css" />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
    </head>
    <body>
            <div class="header">
                 This is Header
            </div> 
            <div class="center">
					<div>
                    	<tiles:insertAttribute name="content"></tiles:insertAttribute>
                    </div>
                    <!--end of left content-->
                <div class="clear"></div>
            </div><!--end of center content-->
            <div class="footer" >
				this is footer copyright--------- vomtung@gmail.com
            </div>
    </body>
</html>
