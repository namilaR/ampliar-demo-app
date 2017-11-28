<%@page import="java.sql.ResultSet"%>
<%@ include file="./components/header.jsp" %>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<%

        ResultSet rs1=(ResultSet)request.getAttribute("browser_count");
        ResultSet rs2 = (ResultSet)request.getAttribute("device_count");
        ResultSet rs3 = (ResultSet)request.getAttribute("authenticator_count");
        ResultSet rs4 = (ResultSet)request.getAttribute("login_count");
                        
            
%> 
<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['browser', 'No of usage'],
        <%
            while (rs1.next()) {
                String browser=rs1.getString("browser");
                int usage=rs1.getInt("views");
        %>
          
            ['<%=browser%>',     <%=usage%>],
        <%  }
        %>
        ]);

        var options = {
          title: 'Browser Usage',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
</script>
<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['device', 'No of views'],
        <%
            while (rs2.next()) {
                String device=rs2.getString("device");
                int views=rs2.getInt("views");
        %>
          
            ['<%=device%>',     <%=views%>],
        <%  }
        %>
        ]);

        var options = {
          title: 'Device Usage',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['authenticator', 'No of views'],
        <%
            while (rs3.next()) {
                String device=rs3.getString("authenticator");
                int views=rs3.getInt("views");
        %>
          
            ['<%=device%>',     <%=views%>],
        <%  }
        %>
        ]);

        var options = {
          title: 'Authentication Type',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_1'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Date', 'No of Logins'],
          <%
            while (rs4.next()) {
                String date=rs4.getString("time");
                int logins=rs4.getInt("logins");
            
           %>
           ['<%=date%>',     <%=logins%>],
        <%  
            }
        
        %>
        ]);

        var options = {
          title: 'Authentication Progress',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
<div class="work-section">
        <div class="container">
            <h2 class="head">LOGIN INFORMATION ANALYSIS</h2>
            <div class="work-section-grids">
               
                    <div>
                        <div id="piechart_3d" style="width: 500px; height: 300px;float:left"></div>
                        <div id="piechart" style="width: 600px; height: 300px;float:left"></div>
                    </div>
            
                <div class="row">
                    <div id="piechart_1" style="width: 550px; height: 300px;float:left"></div>
                     <div id="curve_chart" style="width: 550px; height: 300px;float:left"></div>
                </div>
            </div>
        </div>
  </div>
  <%@ include file="./components/footer.jsp" %>
