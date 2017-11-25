<%@ include file="./components/header.jsp" %>
<%
                    String question = (String) request.getAttribute("question");
                    
%>
    <div id="page-wrapper" class="sign-in-wrapper">
        <div class="graphs">
            <div class="sign-in-form">
                <form method="POST" action="OTPVerification">
                    <div class="sign-in-form-top">
                        <h1 style="text-align: center">Verify It's you!</h1>
                    </div>
                    <div class="signin">
                        <div class="log-input">
                            <div class="log-input-left" style="padding: 10px">
                                        <h2 style="text-align: center">One-time Password - OTP</h2>
                                </div>
                                
                                <div class="clearfix"> </div>
                        </div>
                        <div class="log-input">
                            <div class="log-input-left">
                                    <p style="padding: 10px">We've sent a verification code to your email. Enter the code from the email in the field below</p>
                                    <input placeholder="Enter 6 digit code" type="password" name="code" required=""/>

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
