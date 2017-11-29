<%@page import="com.ampliar.webanalytics.views"%>
<%@ include file="./components/header.jsp" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<div class="banner text-center">
    <%@ include file="./components/banner.jsp" %>
</div>
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
<!-- Submit Ad -->
<div class="submit-ad main-grid-border">
    <div class="container">
        <h2 class="head">Post an Ad</h2>

        <div class="post-ad-form">
            <form action="/ampliar-demo-app/api-insert-mobile" method="POST" enctype="multipart/form-data">

                <label>Category <span>*</span></label>
                <select class="" name="Category">
                    <%@ include file="./components/categories.jsp" %>
                </select>

                <div class="clearfix"></div>

                <label>Sub Category <span>*</span></label>
                <select class="" name="SubCategory">
                    <%@ include file="./components/sub-categories.jsp" %>
                </select>

                <div class="clearfix"></div>

                <label>District <span>*</span></label>
                <select class="" name="District">
                    <%@ include file="./components/locations.jsp" %>
                </select>

                <div class="clearfix"></div>

                <label>Local Area <span>*</span></label>
                <select class="" name="DistrictLocalArea">
                    <%@ include file="./components/localareas.jsp" %>
                </select>

                <div class="clearfix"></div>

                <label>Ad Title <span>*</span></label>
                <input type="text" name="title" placeholder="">

                <div class="clearfix"></div>

                <label>Price <span>*</span></label>
                <input type="text" name="price" placeholder="">

                <div class="clearfix"></div>


                <label>Condition<span>*</span></label>
                <select class="" name="condition">
                    <%@ include file="./components/conditions.jsp" %>
                </select>

                <div class="clearfix"></div>


                <label>Brand <span>*</span></label>
                <input type="text" name="brand" placeholder="">

                <div class="clearfix"></div>

                <label>Model <span>*</span></label>
                <input type="text" name="model" placeholder="">

                <div class="clearfix"></div>


                <label>Authenticity <span>*</span></label>
                <input type="text" name="authenticity" placeholder="">

                <div class="clearfix"></div>

                <div class="clearfix"></div>

                <div>
                    <input type="file" id="files" name="files" multiple="multiple"/>
                </div>

                <input type="submit" value="Publish">

            </form>

        </div>

    </div>
</div>
<!-- // Submit Ad -->
<!--footer section start-->
<%@ include file="./components/footer.jsp" %>