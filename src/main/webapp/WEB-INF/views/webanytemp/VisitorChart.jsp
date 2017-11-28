<%-- 
    Document   : analytics_index
    Created on : Aug 19, 2017, 8:58:09 AM
    Author     : Kasuni
--%>
<%@page import="analytics.SessionCounterListener"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='layout/header.jsp'%>

<!-- Start Site Header --><!-- Start Site Header -->
<%@include file='layout/site_header_wrapper.jsp'%>
<!-- END Site Header -->
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<div class="spacer-100"></div>
 
<div class="spacer-100"></div>
<!DOCTYPE html>
<h1>WEB ANALYZATION</h1>
    <%
    response.setContentType("text/html; charset=utf-8");

    if (request.getMethod() == "GET") {

        try {
            ResultSet rs = null;
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            
            Statement st = con.createStatement();
            rs = st.executeQuery("select COUNT(*) AS total,COUNT(DISTINCT ip) AS visitors from page_views");
             while (rs.next()) {
            int tot=rs.getInt("total");
            int visitors=rs.getInt("visitors");
                
            
%>          
 <!-- Start Body Content -->
<div class="main" role="main">
    <div id="content" class="content full padding-b0">
        <div class="container">
            <!-- Welcome Content and Services overview -->
            <div class="row">
                 <div class="col-md-6">
                     
                     <table class="table table-bordered table-responsive dashboard-tables saved-cars-table">
                    
                    <thread>
                        <tr><h4><center>Current State of the Web Site</center></h4></tr>
                        <tr></tr>
                        <tr>
                            <th width="50"><h4><center><a href="/ampliar/views/PageviewsChart.jsp" >Page Views </a> </center></h4></th>
                            <th width="50"><h4><center> <a href="" >Visitors </a> </center></h4></th>
                            <th width="50"><h4><center> <a href="" >Sessions </a> </center></h4></th>
                            <th width="50"><h4><center> <a href="" >Active Sessions </a> </center></h4></th>
                        </tr>
                    </thread>
                    
                    <tbody>

                        <tr>
                            <td width="50" class="red"><h4><center><%=tot%></center></h4></td>
                            <td width="50" class="blue"><h4><center><%=visitors%></center></h4></td>
                            
                            <% 
                            try{
                                     ResultSet rs1=null;
                                     Class.forName("com.mysql.jdbc.Driver");
                                     Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            
                                    Statement st1 = con.createStatement();
                                    rs1 = st.executeQuery("select COUNT(*) AS total from page_views where page='Home'");
                            
                                    while(rs1.next()){
                                        int sessions = rs1.getInt("total");
                                    
                            %>
                            <td width="50" class="blue"><h4><center><%=sessions%></center></h4></td>
                            <td width="50" class="blue"><h4><center><%=SessionCounterListener.getTotalActiveSession()%></center></h4></td>
                            
                            <%
                            }
                            }
                            catch(Exception e)
                            {
                                System.out.print(e);
                                e.printStackTrace();
                            }
                            %>
                        </tr>
                    <tr>
            
                    </tr>
        
</tbody>

<%
             }
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }

    }

%>

                </table>   
                <div class="row"> <a href="/ampliar/views/Postadchart.jsp"><h3> ADVERTISEMENT POSTING ANALYSIS</h3></a></div>
                <div class="row"> <a href="/ampliar/views/Chart.jsp"><h3> ADVERTISEMENT CATEGORY ANALYSIS</h3></a></div>
                <div class="row"> <a href="/ampliar/views/login_info_chart.jsp"><h3> AUTHENTICATION ANALYSIS</h3></a></div>
                
                </div>

<script>
  $(document).ready(function() {
    $("#datepicker").datepicker();
  });
  </script>
  
  <script>
  $(document).ready(function() {
    $("#datepicker1").datepicker();
  });
  </script>

                <div class="col-md-6">
                    <div>
                        <form action="">
                        <label>Start Date</label>
                        <input type="date" name="start_date" id="datepicker" >
                        
                        <label>Start Date</label>
                        <input type="date" name="start_date" id="datepicker1" >
                        <div>
                        
                        <button id="save_ad_button" class="btn btn-success" type="submit" value="Save">Find</<button> 
                        </div>
                        </form>
                    </div>

                     <div id="curve_chart" style="width: 600px; height: 300px"></div>
                     <div id="curve_chart1" style="width: 600px; height: 300px"></div>
                </div>
<%
        ResultSet resultSet = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            Statement st = con.createStatement();
            resultSet = st.executeQuery("SELECT time,COUNT(DISTINCT ip) AS visitors FROM `page_views` where date BETWEEN datepicker AND datepicker1 group by time");
            
            
        }
        catch(Exception ex)
        {
            
        }
%>

<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['time', 'Visitors'],
          <%
            while (resultSet.next()) {
                String time=resultSet.getString("time");
                int visitors=resultSet.getInt("visitors");
            
           %>
           ['<%=time%>',     <%=visitors%>],
        <%  
            }
        
        %>
        ]);

        var options = {
          title: 'Visitors',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>


<%
        ResultSet resultSet2 = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            Statement st = con.createStatement();
            resultSet2 = st.executeQuery("select date,COUNT(*) AS sessions from page_views group by date");
            
            
        }
        catch(Exception ex)
        {
            
        }
%>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Date', 'Sessions'],
          <%
            while (resultSet2.next()) {
                String date=resultSet2.getString("date");
                int sessions=resultSet2.getInt("sessions");
            
           %>
           ['<%=date%>',     <%=sessions%>],
        <%  
            }
        
        %>
        ]);

        var options = {
          title: 'Sessions',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart1'));

        chart.draw(data, options);
      }
    </script>




                
                
                
            </div>
                
            
               
                    
           
        </div>
    </div>
</div>      
        <form>
            <div class="table-responsive">
                <style>
                    
                </style>
                
                
                
            </div>
            
        </form>
        
    
<%@include file='layout/footer.jsp'%>
