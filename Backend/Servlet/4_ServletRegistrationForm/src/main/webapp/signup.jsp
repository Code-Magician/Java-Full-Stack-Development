<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
<body
	style="background: url(assets/Bg3.jpg); background-size: cover; background-attachment: fixed;">
	<div class="container" style="margin-top: 10%;">
		<div class="row">
			<div class="col m6 offset-m3">
				<div class="card">
					<div class="card-content">
						<h2>Register Here</h2>

						<div class="form center-align">
							<form action="Register" method="post">
								<input type="text" name="name" placeholder="Enter user name">
								<input type="password" name="password"
									placeholder="Enter user password"> <input type="email"
									name="email" placeholder="Enter user email">
							<button class="btn waves-effect waves-light" type="submit"
								name="action">
								Submit <i class="material-icons right">send</i>
							</button>
							</form>
						</div>

						<div class="loader center-align"
							style="margin-top: 20px; display: none;">

							<div class="preloader-wrapper big active">
								<div class="spinner-layer spinner-blue-only">
									<div class="circle-clipper left">
										<div class="circle"></div>
									</div>
									<div class="gap-patch">
										<div class="circle"></div>
									</div>
									<div class="circle-clipper right">
										<div class="circle"></div>
									</div>
								</div>
							</div>

							<h5>Please Wait...</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.6.3.min.js"
		integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
		crossorigin="anonymous">
		
	</script>


	<script>
		$(document).ready(function() {
			console.log("Page is ready....");
		})
	</script>
</body>
</html>











