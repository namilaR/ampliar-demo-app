
<%@page import="java.sql.*"%>
<%@include file='layout/header.jsp'%>

<!-- Start Site Header --><!-- Start Site Header -->
<%@include file='layout/site_header_wrapper.jsp'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        ResultSet resultSet = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            Statement st = con.createStatement();
            resultSet = st.executeQuery("SELECT browser,COUNT(*) as views FROM `login_info` group by browser");
            
            
        }
        catch(Exception ex)
        {
            
        }
%>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['browser', 'No of usage'],
        <%
            while (resultSet.next()) {
                String browser=resultSet.getString("browser");
                int usage=resultSet.getInt("views");
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
    
    <%
        ResultSet resultSet1 = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            Statement st = con.createStatement();
            resultSet1 = st.executeQuery("SELECT device,COUNT(*) as views FROM `login_info` group by device");
            
            
        }
        catch(Exception ex)
        {
            
        }
    %>
    
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['device', 'No of views'],
        <%
            while (resultSet1.next()) {
                String device=resultSet1.getString("device");
                int views=resultSet1.getInt("views");
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
    
    <%
        ResultSet resultSet2 = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            Statement st = con.createStatement();
            resultSet2 = st.executeQuery("SELECT authenticator,COUNT(*) as views FROM `users` group by authenticator");
            
            
        }
        catch(Exception ex)
        {
            
        }
    %>
    
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['authenticator', 'No of views'],
        <%
            while (resultSet2.next()) {
                String device=resultSet2.getString("authenticator");
                int views=resultSet2.getInt("views");
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
    
    <%
        ResultSet resultSet4 = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            Statement st = con.createStatement();
            resultSet4 = st.executeQuery("SELECT date,COUNT(*) as logins FROM `login_info` group by date");
            
            
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
          ['Date', 'No of Logins'],
          <%
            while (resultSet4.next()) {
                String date=resultSet4.getString("date");
                int logins=resultSet4.getInt("logins");
            
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
<div class="spacer-100"></div>
<div class="spacer-100"></div>
<!-- Start Body Content -->
<div class="main" role="main">
    <div id="content" class="content full padding-b0">
        <div class="container">
            <!-- Welcome Content and Services overview -->
            <div class="row">
                <div class="col-md-6">
                    <div id="piechart_3d" style="width: 600px; height: 300px"></div>
                    <div id="piechart" style="width: 600px; height: 300px"></div>
                </div>
                <div class="col-md-6">
                    <div id="piechart_1" style="width: 600px; height: 300px"></div>
                    <div id="curve_chart" style="width: 600px; height: 300px"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file='layout/footer.jsp'%>