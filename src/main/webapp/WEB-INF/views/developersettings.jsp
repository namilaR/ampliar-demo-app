<%@ include file="./components/header.jsp" %>
<%@page import="java.util.Properties"%>
<div id="page-wrapper" class="sign-in-wrapper">
    <%
                    Properties prop = (Properties) request.getAttribute("prop");
                    
    %>
        <div class="graphs">
            <div class="sign-up" style="width: 80%">
                    <form method="POST" action="adaptiveSettings">
                        <div class="modal-header">
                          <h4 class="modal-title text-center">Adaptive authentication Settings- developer</h4>
                        </div>
                          <div class="modal-body">
                          <div class="row gap-20">
                              <div class="row" style="padding-bottom: 10px">
                                <div class="col-md-offset-3" style="width: 415px;float: left">
                                    <label>IP address weight</label>
                                </div>
                                <div style="width: 70px;float: left">
                                    <input  type="number" id="ip_weight" name="ip_weight" class="form-control" style="height: 30px" value="<%=Double.parseDouble(prop.getProperty("ip_weight"))*100%>">
                                </div>
                                <div style="width: 60px;float: left">
                                    <b><p>%</p></b>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row" style="padding-bottom: 10px">
                              <div class="col-md-offset-3" style="width: 415px;float: left">
                                <label>Browser weight</label>
                              </div>
                              <div style="width: 70px;float: left">
                                <input type="number" id="browser_weight" name="browser_weight" class="form-control" style="height: 30px" value="<%=Double.parseDouble(prop.getProperty("browser_weight"))*100%>">
                              </div>
                              <div style="width: 60px;float: left">
                                    <b><p>%</p></b>
                              </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row" style="padding-bottom: 10px">
                              <div class="col-md-offset-3" style="width: 415px;float: left">
                                <label>Device weight</label>
                              </div>
                              <div style="width: 70px;float: left">
                                <input  type="number" id="device_weight" name="device_weight" class="form-control" style="height: 30px" value="<%=Double.parseDouble(prop.getProperty("device_weight"))*100%>">
                              </div>
                              <div style="width: 60px;float: left">
                                    <b><p>%</p></b>
                              </div>
                            </div>
                            <div class="spacer-10"></div>
                              
                            <div class="row" style="padding-bottom: 10px">
                              <div class="col-md-offset-3" style="width: 415px;float: left">
                                <label>Date/Time weight</label>
                              </div>
                              <div style="width: 70px;float: left">
                                  <input  type="number" id="time_weight" name="time_weight" class="form-control" style="height: 30px" value="<%=Double.parseDouble(prop.getProperty("time_weight"))*100%>">
                              </div>
                              <div style="width: 60px;float: left">
                                    <b><p>%</p></b>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row" style="padding-bottom: 10px">
                               <div class="col-md-offset-4" style="width: 322px;float: left">
                                <label>Time range</label>
                                <p>To consider about the events that are established X hours before/after</p>
                              </div>
                              <div style="width: 70px;float: left">
                                <input  type="number" name="time_range" class="form-control" style="height: 30px" value="<%=prop.getProperty("time_range")%>">
                              </div>
                              <div style="width: 60px;float: left">
                                    <b><p>hours</p></b>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row" style="padding-bottom: 10px">
                                <div class="col-md-offset-3" style="width: 415px;float: left">
                                    <label>Location weight</label>
                                </div>
                                <div style="width: 70px;float: left">
                                    <input type="number" id="location_weight" name="location_weight" class="form-control" style="height: 30px" value="<%=Double.parseDouble(prop.getProperty("location_weight"))*100%>">
                                </div>
                                <div style="width: 60px;float: left">
                                    <b><p>%</p></b>
                                </div>
                            </div>  
                            <div class="spacer-10"></div>
                            <div class="row" style="padding-bottom: 10px">
                               <div class="col-md-offset-4" style="width: 322px;float: left">
                                <label>Latitude range</label>
                                <p>To consider about the degrees of latitude +/-</p>
                              </div>
                              <div style="width: 70px;float: left">
                                <input  type="number" name="latitude_range" class="form-control" style="height: 30px" value="<%=prop.getProperty("latitude_range")%>">
                              </div>
                              
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row" style="padding-bottom: 10px">
                               <div class="col-md-offset-4" style="width: 322px;float: left">
                                <label>Longitude range</label>
                                <p>To consider about the degrees of longitude +/-</p>
                              </div>
                              <div style="width: 70px;float:left">
                                <input  type="number" name="longitude_range" class="form-control" style="height: 30px" value="<%=prop.getProperty("longitude_range")%>">
                              </div>
                              
                            </div>
                          </div>
                        </div>
                        <div class="col-md-offset-3">
                            <div class="modal-footer text-center">
                                <div class="sub_home_left">
                                    <input type="submit" class="sub_home_left" onclick="return checkweightedSum();" value="Save">

                                </div>
                            </div>
                        </div>
                      </form>
                </div>
        </div>
                              
</div>
    <script type="text/javascript">
        function checkweightedSum()
        {
          var ip_weight = document.getElementById("ip_weight").value;
          var browser_weight=document.getElementById("browser_weight").value;
          var device_weight=document.getElementById("device_weight").value;
          var time_weight=document.getElementById("time_weight").value;
          var location_weight=document.getElementById("location_weight").value;
          var total=+ip_weight+ +browser_weight+ +device_weight+ +time_weight+ +location_weight;
          
          
          if(total===100)
          {
             alert("Saved");
             return true;
             
          }
          else
          {
             alert("Sum of the weighted values should be 100");
             return false;
          }
          
        }
      </script>
<%@ include file="./components/footer.jsp" %>
