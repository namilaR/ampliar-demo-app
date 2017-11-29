<%@page import="com.ampliar.webanalytics.views"%>
<%@ include file="./components/header.jsp" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<div class="main-banner banner text-center">
	<div class="container">
		<h1>
			Sell or Advertise <span class="segment-heading"> anything
				online </span> with Resale
		</h1>
            <br>
		<a href="/ampliar-demo-app/post-ad">Post Free Ad</a>
	</div>
</div>
<%
    //Get Client IP Address.
    String ipaddress = request.getRemoteAddr();
    
   // Get session date and time.
   DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd");
   DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
   Date date = new Date();
   String pageloaddate = dateFormat.format(date);
   String pageloadtime=timeFormat.format(date);
   views vm = new views();
   
   //int count_index=SessionCounterListener.getTotalActiveSession();
   
   
   
   vm.pageviews(ipaddress, pageloaddate,pageloadtime, "Home");
   
      
%>
<!-- content-starts-here -->
<div class="content">
	<div class="categories">
		<div class="container">
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=mobile">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-mobile"></i>
							</div>
							<h4 class="clrchg">Mobiles</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=electronics">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-laptop"></i>
							</div>
							<h4 class="clrchg">Electronics & Appliances</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=cars">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-car"></i>
							</div>
							<h4 class="clrchg">Cars</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=bikes">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-motorcycle"></i>
							</div>
							<h4 class="clrchg">Bikes</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=furnitures">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-wheelchair"></i>
							</div>
							<h4 class="clrchg">Furnitures</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=pets">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-paw"></i>
							</div>
							<h4 class="clrchg">Pets</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=books">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-book"></i>
							</div>
							<h4 class="clrchg">Books, Sports & Hobbies</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=fashion">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-asterisk"></i>
							</div>
							<h4 class="clrchg">Fashion</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=kids">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-gamepad"></i>
							</div>
							<h4 class="clrchg">Kids</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=services">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-shield"></i>
							</div>
							<h4 class="clrchg">Services</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=jobs">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-at"></i>
							</div>
							<h4 class="clrchg">Jobs</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-2 focus-grid">
				<a href="/ampliar-demo-app/classified-all?type=real_estate">
					<div class="focus-border">
						<div class="focus-layout">
							<div class="focus-image">
								<i class="fa fa-home"></i>
							</div>
							<h4 class="clrchg">Real Estate</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>

<%@ include file="./components/footer.jsp" %>
