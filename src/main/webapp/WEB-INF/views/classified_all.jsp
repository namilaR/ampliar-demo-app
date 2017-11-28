<%@ include file="./components/header.jsp" %>
	</div>
	<div class="banner text-center">
	  <div class="container">    
			<h1>Sell or Advertise   <span class="segment-heading">    anything online </span> with Resale</h1>
                        <br>
			<a href="/ampliar-demo-app/post-ad">Post Free Ad</a>
	  </div>
	</div>
	<!-- Products -->
	<div class="total-ads main-grid-border">
		<div class="container">
			<div class="select-box">
				<div class="select-city-for-local-ads ads-list">
					<label>Select your city to see local ads</label>
						<select>
						 <%@ include file="./components/locations.jsp" %>
                                                </select>
				</div>
				<div class="browse-category ads-list">
					<label>Browse Categories</label>
					<select class="selectpicker show-tick" data-live-search="true">
					  <option data-tokens="Mobiles">All</option>
					  <option data-tokens="Mobiles">Mobiles</option>
					  <option data-tokens="Electronics & Appliances">Electronics & Appliances</option>
					  <option data-tokens="Cars">Cars</option>
					  <option data-tokens="Bikes">Bikes</option>
					  <option data-tokens="Furniture">Furniture</option>
					  <option data-tokens="Pets">Pets</option>
					  <option data-tokens="Books, Sports & Hobbies">Books, Sports & Hobbies</option>
					  <option data-tokens="Fashion">Fashion</option>
					  <option data-tokens="Kids">Kids</option>
					  <option data-tokens="Services">Services</option>
					  <option data-tokens="Jobs">Jobs</option>
					  <option data-tokens="Real Estate">Real Estate</option>
					</select>
				</div>
				<div class="search-product ads-list">
					<label>Search for a specific product</label>
					<div class="search">
						<div id="custom-search-input">
						<div class="input-group">
							<input type="text" class="form-control input-lg" placeholder="Buscar" />
							<span class="input-group-btn">
								<button class="btn btn-info btn-lg" type="button">
									<i class="glyphicon glyphicon-search"></i>
								</button>
							</span>
						</div>
					</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="all-categories">
				<h3> Select your category and find the perfect ad</h3>
				<ul class="all-cat-list">
					<li><a href="mobiles.html">Mobiles <span class="num-of-ads"></span></a></li>
					<li><a href="electronics-appliances.html">Electronics & Appliances  <span class="num-of-ads"></span></a></li>
					<li><a href="real-estate.html">Real Estate  <span class="num-of-ads"></span></a></li>
					<li><a href="furnitures.html">Furniture    <span class="num-of-ads"></span></a></li>
					<li><a href="pets.html">Pets   <span class="num-of-ads"></span></a></li>
					<li><a href="books-sports-hobbies.html">Books, Sports & Hobbies    <span class="num-of-ads"></span></a></li>
					<li><a href="fashion.html">Fashion   <span class="num-of-ads"></span></a></li>
					<li><a href="kids.html">Kids   <span class="num-of-ads"></span></a></li>
					<li><a href="services.html">Services   <span class="num-of-ads"></span></a></li>
					<li><a href="cars.html">Cars   <span class="num-of-ads"></span></a></li>
				</ul>
			</div>
			<ol class="breadcrumb" style="margin-bottom: 5px;">
			  <li><a href="index.html">Home</a></li>
			  <li class="active">All Ads</li>
			</ol>
			<div class="ads-grid">
				<div class="side-bar col-md-3">
					<div class="search-hotel">
					<h3 class="sear-head">Name contains</h3>
					<form>
						<input type="text" value="Product name..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Product name...';}" required="">
						<input type="submit" value=" ">
					</form>
				</div>
				
				<div class="range">
					<h3 class="sear-head">Price range</h3>
							<ul class="dropdown-menu6">
								<li>
									                
									<div id="slider-range"></div>							
										<input type="text" id="amount" style="border: 0; color: #ffffff; font-weight: normal;" />
									</li>			
							</ul>
							<!---->
							<script type="text/javascript" src="js/jquery-ui.js"></script>
							<script type='text/javascript'>//<![CDATA[ 
							$(window).load(function(){
							 $( "#slider-range" ).slider({
										range: true,
										min: 0,
										max: 9000,
										values: [ 50, 6000 ],
										slide: function( event, ui ) {  $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
										}
							 });
							$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) + " - $" + $( "#slider-range" ).slider( "values", 1 ) );

							});//]]>  

							</script>
							
				</div>
				
				</div>
				<div class="ads-display col-md-9">
					<div class="wrapper">					
					<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
					  <ul id="myTab" class="nav nav-tabs nav-tabs-responsive" role="tablist">
						<li role="presentation" class="active">
						  <a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">
							<span class="text">All Ads</span>
						  </a>
						</li>
					  </ul>
					  <div id="myTabContent" class="tab-content">
						<div role="tabpanel" class="tab-pane fade in active" id="home" aria-labelledby="home-tab">
						   <div>
                                                        <div id="container">

                                                            <ul class="list" id="add-list">
                                                                    
                                                            </ul>

                                                        </div>
                                                    </div>
						</div>
					
					  </div>
					</div>
				</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>	
	</div>

 <script>

     $( document ).ready(function() {
               getAllAds();
            });

     function getAllAds(){

          $("#add-list").html('');

          $.ajax({url: "http://localhost:8080/ampliar-demo-app/api-get-all-ads", success: function(result){
                   
                   var ads_list_html = '';
                   var image_id = 1;
                    jQuery.each(result, function() {                    
                        var brand = this.brand;
                        var condition = this.condition;
                        var model = this.model;
                        var price = this.price;
                        var status = this.status;
                        var title = this.title;
                        var localAreaName = this.districtLoacalArea.localAreaName;
                        var advertismentDistrict = this.advertismentDistrict.districtName;
                        
                        ads_list_html += '<a href="single.html">'
                                                +'<li>'
                                                +'<img src="http://localhost:8080/ampliar-demo-app/resources/images/m'+image_id+'.jpg" title="" alt="" />'
                                                +'<section class="list-left">'
                                                +'<h5 class="title">'+title+'</h5>'
                                                +'<span class="adprice">LKR '+price+'</span>'
                                                +'<p class="catpath">'+brand+' | '+ model +'</p>'
                                                +'</section>'
                                                +'<section class="list-right">'
                                                +'<span class="cityname">'+advertismentDistrict+' | '+localAreaName+'</span>'
                                                +'</section>'
                                                +'<div class="clearfix"></div>'
                                                +'</li>' 
                                            +' </a>';
                                    image_id ++;
                                    console.log(ads_list_html);
                      });
              
                         $("#add-list").html(ads_list_html);

                }});
     }

 </script>    
<%@ include file="./components/footer.jsp" %>