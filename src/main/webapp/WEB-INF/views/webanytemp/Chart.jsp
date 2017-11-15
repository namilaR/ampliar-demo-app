
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
            resultSet = st.executeQuery("SELECT category,COUNT(*) as views FROM `getitem_listener` group by category");
            
            
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
          ['Category', 'No of views'],
        <%
            while (resultSet.next()) {
                String category=resultSet.getString("category");
                int views=resultSet.getInt("views");
        %>
          
            ['<%=category%>',     <%=views%>],
        <%  }
        %>
        ]);

        var options = {
          title: 'Category viewers',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
    
    <%
        ResultSet resultSet2 = null;
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            Statement st = con.createStatement();
            resultSet2 = st.executeQuery("SELECT date,COUNT(*) as views FROM `getitem_listener` where category='Cars and Vehicles' group by date");
            
            
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
          ['Date', 'Cars and vehicles'],
          <%
            while (resultSet2.next()) {
                String date=resultSet2.getString("date");
                int views=resultSet2.getInt("views");
            
           %>
           ['<%=date%>',     <%=views%>],
        <%  
            }
        
        %>
        ]);

        var options = {
          title: 'Cars and vehicles',
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
                </div>
                <div class="col-md-6">
                    <div id="curve_chart" style="width: 600px; height: 300px"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file='layout/footer.jsp'%>