<%@ include file="./components/header.jsp" %>
<%
                    String question = (String) request.getAttribute("question");
                    
%>
    <div id="page-wrapper" class="sign-in-wrapper">
        <div class="graphs">
            <div class="sign-in-form">
                <form method="POST" action="verification">
                    <div class="sign-in-form-top">
                        <h1 style="text-align: center">Verify It's you!</h1>
                    </div>
                    <div class="signin">
                        <div class="log-input">
                            <div class="log-input-left" style="padding: 10px">
                                        <h4><%=question%></h4>
                                </div>
                                
                                <div class="clearfix"> </div>
                        </div>
                        <div class="log-input">
                            <div class="log-input-left">
                                    <input type="text" name="answer" placeholder="" required=""/>

                            </div>
                            <div class="clearfix"> </div>
                        </div>
                        <input type="submit" value="Verify">
                    </div>
                     
                </form>
            </div>
        </div>
</div>
<%@ include file="./components/footer.jsp" %>
