<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.fileUpload"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body class="bg-light">
	<%
    if (session.getAttribute("id") == null) {
      response.sendRedirect("login.jsp");  
    } 
	%>
	<div class="container">
	 <h1>Welcome to Our Website</h1>
    <header>
		  <nav class="navbar navbar-expand-lg bg-primary">
             <div class="container-fluid">
               <a class="navbar-brand" style="color : white;" href="HomepageServlet">Homepage</a>
               <div class="collapse navbar-collapse" id="navbarSupportedContent">
                 <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                   <li class="nav-item">
                     <a class="nav-link active" aria-current="page" style="color : white;" href="MyProfileServlet">Profile</a>
                   </li>
                   <li class="nav-item">
                     <a class="nav-link" style="color : white;" href="ConvertfileServlet">History Convert</a>
                   </li>
                   <li class="nav-item">
                     <a class="nav-link" style="color : white;" href="LogoutServlet">Logout</a>
                   </li>
                 </ul>
               </div>
            </div>
          </nav>
	</header>
	
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<p class="text-center fs-3">PDF Upload</p>
						<table class="table mt-4">
			                   <thead>
				                   <tr>
					                   <th class="text-center" scope="col">Id</th>
					                   <th class="text-center" scope="col">File Name</th>
                                       <th class="text-center" scope="col">Convert</th>
				                   </tr>
			                   </thead>
			                   <tbody>

				                   <%
				                   List<fileUpload> uploadFiles = (List<fileUpload>) request.getAttribute("fileUploads");
									if (uploadFiles != null && !uploadFiles.isEmpty()) {
    								for (fileUpload fiUpload : uploadFiles) {
        							%>
        <tr>
            <td class="text-center"><%= fiUpload.getFid() %></td>
            <td class="text-center"><%= fiUpload.getFname() %></td>
            <td>
                <form method="post" action="ConvertfileServlet">
                    <input type="hidden" name="fileName" value="<%= fiUpload.getFname() %>">
                    <input type="hidden" name="fileId" value="<%= fiUpload.getFid() %>">
                    <div class="text-center">
                        <button class="btn btn-success" type="submit">Convert</button>
                    </div>
                </form>
            </td>
        </tr>
        <%
    }
} else {
    %>
    <tr>
        <td colspan="3">No files uploaded</td>
    </tr>
    <%
}
%>
								
				                 
			                   </tbody>
		                   </table>
						<form method="post" action="UploadfileServlet" enctype="multipart/form-data">
						   
							<div class="mb-3">
							<%
						     String msg = (String) session.getAttribute("message");
						     if (msg != null) {
						     %>
						     <h4 class="text-center text-success"><%=msg%></h4>
						     <%
						     session.removeAttribute("message");
						     }
						    %>
							     
								<label>PDF file</label> <input type="file" accept="application/pdf" name="files"
									class="form-control"  multiple>	       
							</div> 
							<div class="text-center">
							   <button  class="btn btn-outline-success" type="submit">Upload</button>
						    </div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
</body>
</html>
