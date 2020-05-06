$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event)
		{
		// Clear alerts---------------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		// Form validation-------------------
		var status = validateItemForm();
		if (status != true)
		 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
		 }
		// If valid------------------------
		 $("#formHospital").submit();
}); 

function validateItemForm()
{
if ($("#hosName").val().trim() == "")
 {
 return "Insert the hospital name.";
 }
if ($("#hosAddress").val().trim() == "")
 {
 return "Insert the hospital address.";
 }
if ($("#hosEmail").val().trim() == "")
 {
 return "Insert the email address.";
 }
if ($("#hosPhone").val().trim() == "")
{
return "Insert the phone number.";
}
var tmpPhne = $("#hosPhone").val().trim();
if (!$.isNumeric(tmpPhne))
 {
 return "Insert a numerical value for Phone Number.";
 }
if ($("#hosCharge").val().trim() == "")
{
return "Insert the charges.";
}
var tmpChrg = $("#hosCharge").val().trim();
if (!$.isNumeric(tmpChrg))
 {
 return "Insert a numerical value for Hospital Charegs.";
 }
 $("#hosCharge").val(parseFloat(tmpPrice).toFixed(2));

return true;
}

$(document).on("click", ".btnUpdate", function(event)
		{
			 $("#hidHosIDSave").val($(this).closest("tr").find('#hidHosIDUpdate').val());
			 $("#hosName").val($(this).closest("tr").find('td:eq(1)').text());
			 $("#hosAddress").val($(this).closest("tr").find('td:eq(2)').text());
			 $("#hosEmail").val($(this).closest("tr").find('td:eq(3)').text());
			 $("#hosPhone").val($(this).closest("tr").find('td:eq(4)').text());
			 $("#hosCharge").val($(this).closest("tr").find('td:eq(5)').text());
		});


