<%@ include file="./components/header.jsp" %>
	<div class="banner text-center">
	  <div class="container">    
			<h1>Sell or Advertise   <span class="segment-heading">    anything online </span> with Resale</h1>
                        <br>
			<a href="/ampliar-demo-app/post-ad">Post Free Ad</a>
	  </div>
	</div>
	<!--single-page-->
	<div class="single-page main-grid-border">
		<div class="container">
			<ol class="breadcrumb" style="margin-bottom: 5px;">
				<li><a href="/ampliar-demo-app">Home</a></li>
				<li><a href="/ampliar-demo-app/classified-all">All Ads</a></li>
				<li class="active"><a href="/ampliar-demo-app/classified-all?type=mobile">Mobiles</a></li>
				<li class="active">Mobile Phone</li>
			</ol>
			<div class="product-desc">
				<div class="col-md-7 product-view">
					<h2><%= request.getParameter("title") %></h2>
					<p> <i class="glyphicon glyphicon-map-marker"></i><a href="#"><%= request.getParameter("localAreaName") %></a>, <a href="#"><%= request.getParameter("advertismentDistrict") %></a></p>
					<div class="flexslider">
						<ul class="slides">
							<li data-thumb="http://localhost:8080/ampliar-demo-app/resources/images/ss<%= request.getParameter("imageID") %>.jpg">
								<img src="http://localhost:8080/ampliar-demo-app/resources/images/ss<%= request.getParameter("imageID") %>.jpg" />
							</li>
							<li data-thumb="http://localhost:8080/ampliar-demo-app/resources/images/ss<%= request.getParameter("imageID") %>.jpg">
								<img src="http://localhost:8080/ampliar-demo-app/resources/images/ss<%= request.getParameter("imageID") %>.jpg" />
							</li>
							<li data-thumb="http://localhost:8080/ampliar-demo-app/resources/images/ss<%= request.getParameter("imageID") %>.jpg">
								<img src="http://localhost:8080/ampliar-demo-app/resources/images/ss<%= request.getParameter("imageID") %>.jpg" />
							</li>
						</ul>
					</div>
					<!-- FlexSlider -->
					  <script defer src="http://localhost:8080/ampliar-demo-app/resources/js/jquery.flexslider.js"></script>
					<link rel="stylesheet" href="http://localhost:8080/ampliar-demo-app/resources/css/flexslider.css" type="text/css" media="screen" />

						<script>
					// Can also be used with $(document).ready()
					$(window).load(function() {
					  $('.flexslider').flexslider({
						animation: "slide",
						controlNav: "thumbnails"
					  });
					});
					</script>
					<!-- //FlexSlider -->
					<div class="product-details">
						<h4>Brand : <a href="#"><%= request.getParameter("brand") %></a></h4>
					
					</div>
				</div>
				<div class="col-md-5 product-details-grid">
					<div class="item-price">
						<div class="product-price">
							<p class="p-price">Price</p>
							<h3 class="rate">LKR <%= request.getParameter("price") %></h3>
							<div class="clearfix"></div>
						</div>
						<div class="condition">
							<p class="p-price">Condition</p>
							<h4><%= request.getParameter("condition") %></h4>
							<div class="clearfix"></div>
						</div>
						<div class="itemtype">
							<p class="p-price">Item Type</p>
							<h4><%= request.getParameter("subCategoryName") %></h4>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--//single-page-->
<%@ include file="./components/footer.jsp" %>