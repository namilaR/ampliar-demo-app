<%@ include file="./components/header.jsp" %>
    <%
                    String otp = (String) request.getAttribute("otp");
                    
    %>
    <div id="page-wrapper" class="sign-in-wrapper">
        <div class="graphs">
            <div class="sign-in-form">
                <form method="POST" name="otpform" action="OTPVerification">
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
                                    <div style="font-weight: bold;padding: 10px;float: right" id="time-left"></div>
                                    <input placeholder="Enter 6 digit code" type="password" name="code" id="code" required=""/>

                            </div>
                            <div class="clearfix"> </div>
                        </div>
                        <input type="submit" value="Verify" onclick="return CheckOTP();">
                    </div>
                     
                </form>
            </div>
        </div>
</div>
<script type="text/javascript">
    var max_time = 5;
    var c_seconds  = 0;
    var total_seconds =60*max_time;
    max_time = parseInt(total_seconds/60);
    c_seconds = parseInt(total_seconds%60);
    document.getElementById("time-left").innerHTML=max_time + ' : ' + c_seconds ;
    
    function init(){
        document.getElementById("time-left").innerHTML=max_time + ' : ' + c_seconds;
        setTimeout("CheckTime()",999);
    }
    function CheckTime(){
        document.getElementById("time-left").innerHTML=max_time + ' : ' + c_seconds;
        
        if(total_seconds <=0){
            setTimeout('document.otpform.submit()',1);
        } 
        else
        {
            total_seconds = total_seconds -1;
            max_time = parseInt(total_seconds/60);
            c_seconds = parseInt(total_seconds%60);
            setTimeout("CheckTime()",999);
        }

    }
    init();
    
</script>
<script type="text/javascript">
    function CheckOTP()
    {
        var otp=<%=otp%>;
        var input_otp = document.getElementById("code").value;
        
        if(otp==input_otp)
        {
            return true;
        }
        else
        {
            alert("Invalid OTP");
            return false;
        }
    }
</script>
<%@ include file="./components/footer.jsp" %>
