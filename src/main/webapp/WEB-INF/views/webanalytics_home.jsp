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
                    <div>
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
                                    while(rs1.next()){
                                        int sessions = rs1.getInt("total");
                                    
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
                        <div class="row"> <a href="/ampliar/views/Postadchart.jsp"><h3> ADVERTISEMENT POSTING ANALYSIS</h3></a></div>
                        <div class="row"> <a href="/ampliar/views/Chart.jsp"><h3> ADVERTISEMENT CATEGORY ANALYSIS</h3></a></div>
                        <div class="row"> <a href="/ampliar/views/login_info_chart.jsp"><h3> AUTHENTICATION ANALYSIS</h3></a></div>
                    </div>
                </div>
            </div>
        </div>
</div>
<%@ include file="./components/footer.jsp" %>
        
