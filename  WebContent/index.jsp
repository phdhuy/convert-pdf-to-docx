<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="./css/index.css">
</head>
<body>
	<%
    if (session.getAttribute("id") == null) {
      response.sendRedirect("login.jsp");  
    } 
	%>
<div class="container">
    <h1>Welcome to Our Website</h1>
    <header>
		<nav>
			<ul class="nav_link">
				<li><a href="index.jsp">Homepage</a></li>
				<li><a href="MyProfileServlet">Profile</a></li>
				<li><a href="">History Convert</a><li>
				<li><a href="LogoutServlet">Log out</a></li>
			</ul>
		</nav>
	</header>

	<div class="container">
		<div class="header">
			<i class="far fa-file-pdf"></i>
			<h1>Convert file PDF to Word</h1>
			<h2>Convert file PDF to Word</h2>
		</div>

		<div class="main">
			<div class="submain">
				<form action="UploadFileServlet" class="button" method="post" enctype="multipart/form-data">
					<i class="far fa-copy"></i> 
					<input type="file" id="file" name="files_upload" accept="application/pdf" multiple size="3000">
					<label for="file">CHỌN CÁC TỆP</label> 
					<input class="submit" type="submit" value="Chuyển đổi!">
				</form>
			</div>
		</div>

	</div>
</div>

</body>
</html>
