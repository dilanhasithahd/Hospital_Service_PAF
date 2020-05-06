<%@page import="com.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%
   if (request.getParameter("hosName") != null)
   {
    Hospital itemObj = new Hospital();
    String stsMsg = "";
   
   if (request.getParameter("hidHosIDSave") == "")
    {
    stsMsg = itemObj.AddHospitals(request.getParameter("hosName"),
    request.getParameter("hosAddress"),
    request.getParameter("hosEmail"),
    request.getParameter("hosPhone"),
    request.getParameter("hosCharge"));
    }
   else
    {
    stsMsg = itemObj.updateHospital(request.getParameter("hidHosIDSave"),
    request.getParameter("hosName"),
    request.getParameter("hosAddress"),
    request.getParameter("hosEmail"),
    request.getParameter("hosPhone"),
    request.getParameter("hosCharge"));
    }
    session.setAttribute("statusMsg", stsMsg);
   } 
   
   if (request.getParameter("hidHosIDDelete") != null)
   {
	Hospital hosObj = new Hospital();
    String stsMsg =
    hosObj.removeHospitals(request.getParameter("hidHosIDDelete"));
    session.setAttribute("statusMsg", stsMsg);
   }
   %>
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
					<% out.print(session.getAttribute("statusMsg")); %>
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