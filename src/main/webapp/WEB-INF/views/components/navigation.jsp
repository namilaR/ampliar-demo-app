<div class="container">
	<div class="logo">
		<a href="/ampliar-demo-app"><span>Re</span>sale</a>
	</div>
	<div class="header-right">
		<a class="account" href="/ampliar-demo-app/login">Login</a> 
                
                <a class="account" href="/ampliar-demo-app/classified-all">All Ads</a> 
		<!-- Large modal -->
		<div class="selectregion">
			<button class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">Select Your Region</button>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Please Choose Your
								Location</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<select id="basic2" class="show-tick form-control" multiple>
										<optgroup label="Popular Cities">
											<option selected style="display: none; color: #eee;">Select City</option>
											<option>Colombo</option>
											<option>Kandy</option>
											<option>Galle</option>
                                                                                        <option>Ampara</option>
                                                                                        <option>Anuradhapura</option>
                                                                                        <option>Badulla</option>
                                                                                        <option>Batticaloa</option>
                                                                                        <option>Gampaha</option>
                                                                                        <option>Hambantota</option>
                                                                                        <option>Jaffna</option>
                                                                                        <option>Kalutara</option>
                                                                                        <option>Kegalle</option>
                                                                                        <option>Kilinochchi</option>
                                                                                        <option>Kurunegala</option>
                                                                                        <option>Mannar</option>
                                                                                        <option>Matale</option>
                                                                                        <option>Matara</option>
                                                                                        <option>Moneragala</option>
                                                                                        <option>Mullativu</option>
                                                                                        <option>Nuwara Eliya</option>
                                                                                        <option>Polonnaruwa</option>
                                                                                        <option>Puttalam</option>
                                                                                        <option>Ratnapura</option>
                                                                                        <option>Trincomalee</option>
                                                                                        <option>Vavuniya</option>                                                                                       
										</optgroup>
									</select>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<script>
				$('#myModal').modal('');
				</script>
		</div>
	</div>
</div>