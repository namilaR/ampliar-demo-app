<%@page import="com.ampliar.webanalytics.SessionCounterListener"%>
<%@page import="java.sql.ResultSet"%>
<%@ include file="./components/header.jsp" %>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
 <%

        ResultSet rs1=(ResultSet)request.getAttribute("pageview_count");
        ResultSet rs2 = (ResultSet)request.getAttribute("homepage_views");
        ResultSet rs3 = (ResultSet)request.getAttribute("listpage_views");
        ResultSet rs4 = (ResultSet)request.getAttribute("sellpage_views");
                        
            
%> 

<script type="text/javascript">
    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ['Page', 'No of views'],
      <%
          while (rs1.next()) {
              String pageView =rs1.getString("page");
              int views=rs1.getInt("views");
      %>

          ['<%=pageView%>',     <%=views%>],
      <%  }
      %>
      ]);

      var options = {
        //title: 'Page viewers',
        is3D: true,

      };

      var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
      chart.draw(data, options);
    }
  </script>
  <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Date', 'Home'],
          <%
            while (rs2.next()) {
                String date=rs2.getString("date");
               
                int views=rs2.getInt("views");
            
           %>
           ['<%=date%>',     <%=views%>],
        <%  
            }
        
        %>
        ]);

        var options = {
          title: 'Home Page',
          curveType: 'function',
         // legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart1'));

        chart.draw(data, options);
      }
    </script>       
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Date', 'List'],
          <%
            while (rs3.next()) {
                String date=rs3.getString("date");
               
                int views=rs3.getInt("views");
            
           %>
           ['<%=date%>',     <%=views%>],
        <%  
            }
        
        %>
        ]);

        var options = {
          title: 'Advertisements List Page',
          curveType: 'function',
          //legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart2'));

        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Date', 'Sell'],
          <%
            while (rs4.next()) {
                String date=rs4.getString("date");
               
                int views=rs4.getInt("views");
            
           %>
           ['<%=date%>',     <%=views%>],
        <%  
            }
        
        %>
        ]);

        var options = {
          title: 'Post Advertisements Page',
          curveType: 'function',
          //legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart3'));

        chart.draw(data, options);
      }
    </script>
  <div class="work-section">
        <div class="container">
            <h2 class="head">PAGE VISUALIZATION OF THE VISITORS</h2>
            <div class="work-section-grids">
               
                    <div>
                        <div id="piechart_3d" style="width: 500px; height: 300px;float:left"></div>
                        <div id="curve_chart3" style="width: 600px; height: 300px;float:left"></div>
                    </div>
            
                <div class="row">
                    <div id="curve_chart1" style="width: 550px; height: 300px;float:left"></div>
                     <div id="curve_chart2" style="width: 550px; height: 300px;float:left"></div>
                </div>
            </div>
        </div>
  </div>
  <%@ include file="./components/footer.jsp" %>
