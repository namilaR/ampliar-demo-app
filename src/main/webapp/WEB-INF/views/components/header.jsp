<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Resale a Business Category Flat Bootstrap Responsive
	Website Template | Home :: w3layouts</title>


<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/bootstrap-select.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/flexslider.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/font-awesome.min.css" />"
	rel="stylesheet" />

<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Resale Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- //for-mobile-apps -->
<!--fonts-->
<link href='//fonts.googleapis.com/css?family=Ubuntu+Condensed'
	rel='stylesheet' type='text/css'>
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<!--//fonts-->
<!-- js -->
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.min.js" />"></script>
<!-- js -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap-select.js" />"></script>
<script>
	$(document).ready(function() {
		var mySelect = $('#first-disabled2');

		$('#special').on('click', function() {
			mySelect.find('option:selected').prop('disabled', true);
			mySelect.selectpicker('refresh');
		});

		$('#special2').on('click', function() {
			mySelect.find('option:disabled').prop('disabled', false);
			mySelect.selectpicker('refresh');
		});

		$('#basic2').selectpicker({
			liveSearch : true,
			maxOptions : 1
		});
	});
</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.leanModal.min.js" />"></script>
<link href="<c:url value="/resources/css/jquery.uls.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/jquery.uls.grid.css" />"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/jquery.uls.lcd.css" />"
	rel="stylesheet" />


<link href="<c:url value="/resources/" />" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/resources/" />"></script>

<!-- Source -->

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.uls.data.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.uls.data.utils.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.uls.lcd.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.uls.languagefilter.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.uls.regionfilter.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.uls.core.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/loader.js" />"></script>
<script>
	$(document).ready(function() {
		$('.uls-trigger').uls({
			onSelect : function(language) {
				var languageName = $.uls.data.getAutonym(language);
				$('.uls-trigger').text(languageName);
			},
			quickList : [ 'en', 'hi', 'he', 'ml', 'ta', 'fr' ]
		//FIXME
		});
	});
</script>
</head>
<body>
	<div class="header">
            <div class="container">
                <div class="logo">
                        <a href="/ampliar-demo-app"><span>Re</span>sale</a>
                </div>
                <div class="header-right">
                    <%
                    String email = (String) request.getSession().getAttribute("email");
                    if(email==null)
                    {
                    %>
                    <button class="btn btn-primary" style="margin-top: 0px"><a class="account" href="/ampliar-demo-app/login">Login</a> </button>
                    <button class="btn btn-primary" style="margin-top: 0px"><a class="account" href="/ampliar-demo-app/register">Register</a></button>
                    <%  
                    }
                    else
                    {
                    %>
                        <button class="btn btn-primary" style="margin-top: 0px"><a class="account" href="myaccount">Welcome <%=email%></a></button>
                        <button class="btn btn-primary" style="margin-top: 0px"><a class="account" href="logout">Logout</a> </button>
                     <%
                    }
                    %>
                     
                        <div class="selectregion">
                            <button class="btn btn-primary" data-toggle="modal"
                                    data-target="#myModal">Select Your Region</button>
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                                    aria-labelledby="myLargeModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                    <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-hidden="true">&times;</button>
                                                            <h4 class="modal-title" id="myModalLabel">Please Choose Your
                                                                    Location</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                            <form class="form-horizontal" role="form">
                                                                    <div class="form-group">
                                                                            <select id="basic2" class="show-tick form-control" multiple>
                                                                                      <%@ include file="locations.jsp" %>
                                                                            </select>
                                                                    </div>
                                                            </form>
                                                    </div>
                                            </div>
                                    </div>
                            </div>
                            <script>
                                    $('#myModal').modal('');
                                    </script>
                    </div>
                </div>
            </div>
        </div>