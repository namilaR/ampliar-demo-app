<%@page import="com.ampliar.webanalytics.SessionCounterListener"%>
<%@page import="java.sql.ResultSet"%>
<%@ include file="./components/header.jsp" %>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
   <%

        ResultSet rs1=(ResultSet)request.getAttribute("visitor_count");
        int tot=0;
        int visitors=0;
        while(rs1.next()){
            tot=rs1.getInt("total");
            visitors=rs1.getInt("visitors");
        }
        
        
                
            
%>   
  <div class="work-section">
        <div class="container">
            <h2 class="head">WEB ANALYSIS</h2>
            <div class="work-section-grids">
                <div>
                <div class="row">
                    <div style="margin-left: 50px;width: 600px;height: 200px;float: left">
                        <table>
                            <thead>
                                <tr><h4>Current State of the Web Site</h4></tr>
                            <tr><th style="padding: 10px"><h4><a href="/ampliar/views/PageviewsChart.jsp" >Page Views </a></h4></th>
                                <th style="padding: 10px"><h4><a href="" >Visitors </a></h4></th>
                                <th style="padding: 10px"><h4><a href="" >Sessions </a></h4></th>
                                <th style="padding: 10px"><h4><a href="" >Active Sessions </a></h4></th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="padding: 10px"><h4><%=tot%></h4></td>
                                    <td style="padding: 10px"><h4><%=visitors%></h4></td>
                                    <% 
                            try{
                                     
                                    ResultSet rs2=(ResultSet)request.getAttribute("homepage_count");
                                    while(rs2.next()){
                                        int sessions = rs2.getInt("total");
                                    
                            %>
                                    <td style="padding: 10px"><h4><%=sessions%></h4></td>
                                    <td style="padding: 10px"><h4><%=SessionCounterListener.getTotalActiveSession()%></h4></td>
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
                            </tbody>
                        </table>
                    </div>
                                <div style="float: left">
                                    <div class="row" style="padding: 10px"> <a href="/ampliar/views/Postadchart.jsp"><h3> ADVERTISEMENT POSTING ANALYSIS</h3></a></div>
                                    <div class="row" style="padding: 10px"> <a href="/ampliar/views/Chart.jsp"><h3> ADVERTISEMENT CATEGORY ANALYSIS</h3></a></div>
                                    <div class="row" style="padding: 10px"> <a href="/ampliar/views/login_info_chart.jsp"><h3> AUTHENTICATION ANALYSIS</h3></a></div>
                    </div>
                </div>
                                <div class="row">
                     <div id="curve_chart" style="width: 500px; height: 300px;float:left"></div>
                     <div id="curve_chart1" style="width: 500px; height: 300px;float:left"></div>
                                </div>
                
<%
            ResultSet rs3=(ResultSet)request.getAttribute("visitorwithdate_count");
            
       
%>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Date', 'Visitors'],
          <%
            while (rs3.next()) {
                String date=rs3.getString("date");
                int noofvisitors=rs3.getInt("visitors");
            
           %>
           ['<%=date%>',     <%=noofvisitors%>],
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
        ResultSet rs4=(ResultSet)request.getAttribute("sessionswithdate_count");
%>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Date', 'Sessions'],
          <%
            while (rs4.next()) {
                String date=rs4.getString("date");
                int sessions=rs4.getInt("sessions");
            
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
<%@ include file="./components/footer.jsp" %>
        
