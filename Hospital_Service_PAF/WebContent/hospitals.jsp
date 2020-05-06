<%@page import="com.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/hospitals.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">
				
				<h1 class="m-3">Hospital Service</h1>
			
				<form id="formHospital" name="formHospital" method="post" action="hospitals.jsp">
					Hospital Name : 
					<input id="hosName" name="hosName" type="text" class="form-control form-control-sm"><br>
					Hospital Address : 
					<input id="hosAddress" name="hosAddress" type="text" class="form-control form-control-sm"><br>
					Hospital Email : 
					<input id="hosEmail" name="hosEmail" type="text" class="form-control form-control-sm"><br>
					Hospital Phone : 
					<input id="hosPhone" name="itemDesc" type="text" class="form-control form-control-sm"><br>
					Hospital Charge : 
					<input id="hosCharge" name="hosCharge" type="text" class="form-control form-control-sm"><br>
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"><br>
					<input id="hidHosIDSave" name="hidHosIDSave" type="hidden" value="Save" >
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success">
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<%
					Hospital itemObj = new Hospital();
					out.print(itemObj.showHospitals());
				%>
			
			</div>
		</div>	
	</div>
	

</body>
</html>