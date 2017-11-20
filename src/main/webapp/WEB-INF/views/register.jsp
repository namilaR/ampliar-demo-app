<%@ include file="./components/header.jsp" %>
<script>
        function showDiv(elem){
            if(elem.value === "Other")
                document.getElementById('hidden_div').style.display = "block";
            else
                document.getElementById('hidden_div').style.display = "none";
            
        }
        
</script>
<div id="page-wrapper" class="sign-in-wrapper">
        <div class="graphs">
                <div class="sign-up">
                    <form method="POST" action="register">
                        <h1>Create an account</h1>
                        <p class="creating">Having hands on experience in creating innovative designs,I do offer design 
                                solutions which harness.</p>
                        <h2>Personal Information</h2>
                        <div class="sign-u">
                                <div class="sign-up1">
                                        <h4>Name* :</h4>
                                </div>
                                <div class="sign-up2">
                                       
                                    <input type="text" name="name" placeholder="" required=""/>
                                       
                                </div>
                                <div class="clearfix"> </div>
                        </div>
                        <div class="sign-u">
                                <div class="sign-up1">
                                        <h4>Email Address* :</h4>
                                </div>
                                <div class="sign-up2">
                                       
                                    <input type="text" name="email" placeholder="" required=""/>
                                       
                                </div>
                                <div class="clearfix"> </div>
                        </div>
                        <div class="sign-u">
                                <div class="sign-up1">
                                        <h4>Password* :</h4>
                                </div>
                                <div class="sign-up2">
                                     
                                    <input type="password" name="password" placeholder="" required=""/>
                                       
                                </div>
                                <div class="clearfix"> </div>
                        </div>
                        <div class="sign-u">
                                <div class="sign-up1">
                                        <h4>Confirm Password* :</h4>
                                </div>
                                <div class="sign-up2">
                                       
                                    <input type="password" name="confpassword" placeholder="" required=""/>
                                        
                                </div>
                                <div class="clearfix"> </div>
                        </div>
                        <div class="sign-u">
                                <div class="sign-up1">
                                        <h4>Security Question* :</h4>
                                </div>
                                <div class="sign-up2">
                                    <div style="width: 100%;padding: 20px 5px">
                                    <select name="sec_question" class="form-control" onchange="showDiv(this)">
                                        <option value="Name of your favourite childhood pet" selected>Name of your favourite childhood pet</option>
                                        <option value="Your mother's maiden name">Your mother's maiden name</option>
                                        <option value="Where were you born?">Where were you born?</option>
                                        <option value="Other">Other</option>
                                    
                                    </select>
                                    </div>
                                </div>
                                <div class="clearfix"> </div>
                        </div>
                        <div class="sign-u" id="hidden_div" style="display: none">
                                <div class="sign-up1">
                                        
                                </div>
                                <div class="sign-up2">
                                     
                                    <input placeholder="Enter your question" type="text" name="other_question">
                                       
                                </div>
                                <div class="clearfix"> </div>
                        </div>
                        <div class="sign-u">
                                <div class="sign-up1">
                                        <h4>Answer* :</h4>
                                </div>
                                <div class="sign-up2">
                                       
                                    <input type="text" name="answer" placeholder="" required=""/>
                                        
                                </div>
                                <div class="clearfix"> </div>
                        </div>
                        <div class="sub_home">
                                <div class="sub_home_left">      
                                    <input type="submit" value="Create">   
                                </div>
                                <div class="sub_home_right">
                                        <p>Go Back to <a href="index.html">Home</a></p>
                                </div>
                                <div class="clearfix"> </div>
                        </div>
                    </form>
                </div>
        </div>
</div>
<%@ include file="./components/footer.jsp" %>
