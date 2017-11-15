
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="analytics.views"%>
<%@page import="java.util.Date"%>
<%@page import="analytics.SessionCounterListener"%>
<%@include file='layout/header.jsp'%>

<!-- Start Site Header --><!-- Start Site Header -->
<%@include file='layout/site_header_wrapper.jsp'%>
<!-- END Site Header -->

<% 
//Get Client IP Address.
    String ipaddress = request.getRemoteAddr();
    
   // Get session creation date & time.
   
   DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd");
   DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
   Date date = new Date();
   String pageloaddate = dateFormat.format(date);
   String pageloadtime=timeFormat.format(date);
   views vm = new views();
   
   vm.pageviews(ipaddress, pageloaddate,pageloadtime, "Sell");
 

%>

<div class="spacer-100"></div>
<div class="spacer-100"></div>

<h1>Active Session:<% int count_sell=SessionCounterListener.getTotalActiveSession();%> </h1>

<!-- Start Body Content -->
<div class="main" role="main">
    <div id="content" class="content full padding-b0">
        <div class="container">
            <!-- Welcome Content and Services overview -->
            <div class="row">
                <div class="col-md-6">
                    <h1 class="uppercase strong">Sell Your Item<br></h1>
                    <p class="lead">By creating an Advertisement<br>easy and quick </p>
                </div>
                <div class="col-md-6 col-sm-6 text-center">
                    <h3>Advertisement Details</h3>
                    <form action="/ampliar/PostItemServlet" method="post">
                        <div class="col-md-12 col-sm-12 text-center">
                            <label for="ad_name">Advertisement Name</label>
                            <input type="text" name="ad_name" id="ad_name" class="form-control">
                        </div>
                        <div class="col-md-12 col-sm-12 text-center">
                            <label for="ad_price">Advertisement Price</label>
                            <input type="text" name="ad_price" id="ad_price" class="form-control">
                        </div>
                        <div class="col-md-12 col-sm-12 text-center">
                            <label for="description">Description</label>
                            <textarea type="text" name="description" id="description" class="form-control"></textarea>
                        </div>
                        <div class="col-md-12 col-sm-12 text-center">
                            <label for="category">Category</label>
                            <div id="category-div" ></div>
                        </div>
                        <div class="col-md-12 col-sm-12 text-center">
                            <label for="sub_category">Sub Category</label>
                            <div id="sub-category-div" ></div>
                        </div>
                        <div class="col-md-12 col-sm-12 text-center">
                            <label for="district">District</label>
                            <div id="district-div" ></div>
                        </div>
                        <div class="col-md-12 col-sm-12 text-center">
                            <label for="local_area">Town/City</label>
                            <div id="local-area-div" ></div>
                        </div>
                        <div class="col-md-12 col-sm-12 text-center">
                            <button id="save_ad_button" class="btn btn-success" onclick="saveAdvertisement();" type="submit" value="Save">Save</<button> 
                            
                                    </div>
                                    </form>
                                    </div>
                                    </div>
                                    </div>
                                    </div>

                                    <script>

                                        function loadDistrictSelect() {
                                            
                                            $.ajax({
                                                url: '/ampliar/services/district_select.jsp?'

                                            }).done(function (data) {
                                                $('#district-div').html(data);
                                                
                                                loadLocalAreasSelect();
                                            });
                                            
                                        }
                                        
                                        function loadLocalAreasSelect() {
                                            
                                            var district_select = $('#district-select').val();
                                            $.ajax({
                                                url: '/ampliar/services/local_areas_select.jsp?district_select='+district_select

                                            }).done(function (data) {
                                                $('#local-area-div').html(data);
                                            });
                                            
                                        }
                                        
                                        function loadCategorySelect() {
                                            
                                            $.ajax({
                                                url: '/ampliar/services/categories_select.jsp?'

                                            }).done(function (data) {
                                                $('#category-div').html(data);
                                                loadSubCategorySelect();
                                            });
                                            
                                        }
                                        
                                        function loadSubCategorySelect() {
                                            
                                            var category_select = $('#category-select').val();
                                            $.ajax({
                                                url: '/ampliar/services/sub_categories_select.jsp?category_select='+category_select

                                            }).done(function (data) {
                                                $('#sub-category-div').html(data);
                                            });
                                            
                                        }
                                        
                                        window.addEventListener("load", function () {
                                            loadDistrictSelect();
                                            loadCategorySelect();
                                        });


                                    </script>
                                    <!-- End Body Content -->

                                    <%@include file='layout/footer.jsp'%>