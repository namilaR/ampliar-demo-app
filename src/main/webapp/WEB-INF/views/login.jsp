<%@page import="com.ampliar.authenticationmodule.impl.TwitterClass"%>
<%@page import="com.ampliar.authenticationmodule.impl.Google"%>
<%@page import="com.ampliar.authenticationmodule.impl.Facebook"%>
<%@page import="com.ampliar.authenticationmodule.impl.Linkedin"%>
<%@ include file="./components/header.jsp" %>
<script>
        window.onload = function() {
        
            loadbuttons();
        };
        
        function loadbuttons(){
            <%
            Linkedin.assignvalues();
            Facebook.assignvalues();
            TwitterClass.assignvalues();
            Google.assignvalues();
            //Facebook fb=new Facebook();
            //Google go=new Google();
            //TwitterClass tw=new TwitterClass();
        
        %>
            var LINKEDIN_ENABLE="<%=Linkedin.ENABLE%>";
            var FB_ENABLE="<%=Facebook.ENABLE%>";
            var GOOGLE_ENABLE="<%=Google.ENABLE%>";
            var TWITTER_ENABLE="<%=TwitterClass.ENABLE%>";
            
            if(LINKEDIN_ENABLE==="false")
            {
                document.getElementById("linkedInButton").style.display = "none";
            }
            
            if(FB_ENABLE==="false")
            {
                document.getElementById("facebookButton").style.display = "none";
            }

            if(GOOGLE_ENABLE==="false")
            {
                document.getElementById("googleButton").style.display = "none";
            }

            if(TWITTER_ENABLE==="false")
            {
                document.getElementById("twitterButton").style.display = "none";
            }

        }
        
        $(document).ready(function () {
            $("#linkedInButton").click(makeLinkedinRequest);
        });

        function makeLinkedinRequest() {
            // Define properties
            var AUTH_ENDPOINT = "<%=Linkedin.AUTH_ENDPOINT%>";
            var RESPONSE_TYPE = "<%=Linkedin.RESPONSE_TYPE%>";
            var CLIENT_ID = "<%=Linkedin.CLIENT_ID%>";
            var REDIRECT_URI = "<%=Linkedin.REDIRECT_URI%>";
            var SCOPE = "<%=Linkedin.SCOPE%>";
            var STATE = "<%=Linkedin.STATE%>";

            // Build authorization request endpoint
            // According OAuth 2 specification, all the request parameters should be URL encoded
            var requestEndpoint = AUTH_ENDPOINT + "?" +
                    "response_type=" + encodeURIComponent(RESPONSE_TYPE) + "&" +
                    "client_id=" + encodeURIComponent(CLIENT_ID) + "&" +
                    "redirect_uri=" + encodeURIComponent(REDIRECT_URI) + "&" +
                    "scope=" + encodeURIComponent(SCOPE) + "&" +
                    "state=" + encodeURIComponent(STATE);

            // Send to authorization request endpoint
            window.location.href = requestEndpoint;
        }
        $(document).ready(function () {
            $("#facebookButton").click(makeFbRequest);
        });

        function makeFbRequest() {
            // Define properties
            var AUTH_ENDPOINT = "<%=Facebook.AUTH_ENDPOINT%>";
            var RESPONSE_TYPE = "<%=Facebook.RESPONSE_TYPE%>";
            var CLIENT_ID = "<%=Facebook.CLIENT_ID%>";
            var REDIRECT_URI = "<%=Facebook.REDIRECT_URI%>";
            var SCOPE = "<%=Facebook.SCOPE%>";
            
            // Build authorization request endpoint
            var requestEndpoint = AUTH_ENDPOINT + "?" +
                    "response_type=" + encodeURIComponent(RESPONSE_TYPE) + "&" +
                    "client_id=" + encodeURIComponent(CLIENT_ID) + "&" +
                    "redirect_uri=" + encodeURIComponent(REDIRECT_URI) + "&" +
                    "scope=" + encodeURIComponent(SCOPE);
            
            // Send to authorization request endpoint
            window.location.href = requestEndpoint;
        }
        $(document).ready(function () {
            $("#twitterButton").click(makeTwitterRequest);
        });

        function makeTwitterRequest() {
            
            window.location.href = "twittersignin";
        }
        $(document).ready(function () {
            $("#googleButton").click(makeGoogleRequest);
        });

        function makeGoogleRequest() {
            // Define properties
            var AUTH_ENDPOINT = "<%=Google.AUTH_ENDPOINT%>";
            var RESPONSE_TYPE = "<%=Google.RESPONSE_TYPE%>";
            var CLIENT_ID = "<%=Google.CLIENT_ID%>";
            var REDIRECT_URI = "<%=Google.REDIRECT_URI%>";
            var SCOPE = "<%=Google.SCOPE%>";
            var APPROVAL_PROMPT="<%=Google.APPROVAL_PROMPT%>"

            // Build authorization request endpoint
            // According OAuth 2 specification, all the request parameters should be URL encoded
            var requestEndpoint = AUTH_ENDPOINT + "?" +
                    "response_type=" + encodeURIComponent(RESPONSE_TYPE) + "&" +
                    "client_id=" + encodeURIComponent(CLIENT_ID) + "&" +
                    "redirect_uri=" + encodeURIComponent(REDIRECT_URI) + "&" +
                    "scope=" + encodeURIComponent(SCOPE) + "&" +
                    "approval_prompt=" + encodeURIComponent(APPROVAL_PROMPT);

            // Send to authorization request endpoint
            window.location.href = requestEndpoint;
        }
    </script>
    <div id="page-wrapper" class="sign-in-wrapper">
        <div class="graphs">
                <div class="sign-in-form">
                        <div class="sign-in-form-top">
                                <h1>Log in</h1>
                        </div>
                        <div class="signin">
                                <div class="signin-rit">
                                        <span class="checkbox1">
                                                 <label class="checkbox"><input type="checkbox" name="checkbox" checked="">Forgot Password ?</label>
                                        </span>
                                        <p><a href="#">Click Here</a> </p>
                                        <div class="clearfix"> </div>
                                </div>
                                <form method="POST" action="login">
                                <div class="log-input">
                                        <div class="log-input-left">
                                           <input type="text" class="user" name="email" value="Your Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Your Email';}"/>
                                        </div>
                                        <span class="checkbox2">
                                                 <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i></label>
                                        </span>
                                        <div class="clearfix"> </div>
                                </div>
                                <div class="log-input">
                                        <div class="log-input-left">
                                            <input type="password" class="lock" name="password" value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email address:';}"/>
                                        </div>
                                        <span class="checkbox2">
                                                 <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i></label>
                                        </span>
                                        <div class="clearfix"> </div>
                                </div>
                                <input type="submit" value="Log in">
                                
                                
                        </form>	 
                            <div class="log-input">
                                 <h4 style="text-align: center;padding: 10px">OR </h4>
                            </div>
                            <div class="log-input" style="text-align: center">
                                    <input type="image" src="resources/images/facebook.png" id="facebookButton" width="60%" height="60%">
                                    <input type="image" src="resources/images/linkedin.png" id="linkedInButton" width="60%" height="60%">
                                    <input type="image" src="resources/images/google.png" id="googleButton" width="60%" height="60%">
                                    <input type="image" src="resources/images/twitter.png" id="twitterButton" width="60%" height="60%">
                            </div>
                        </div>
                        <div class="new_people">
                                <h2>For New People</h2>
                                <p>Having hands on experience in creating innovative designs,I do offer design 
                                        solutions which harness.</p>
                                <a href="register.html">Register Now!</a>
                        </div>
                </div>
        </div>
</div>
<%@ include file="./components/footer.jsp" %>
