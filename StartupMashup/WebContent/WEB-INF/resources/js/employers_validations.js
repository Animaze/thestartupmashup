/*This file contains function for validating the SignUp Form.*/

function validateEmployersForm(){
	$('input[type="text"]').css('border','0px solid white');
	$('input[type="email"]').css('border','0px solid white');
	 
	var form = document.forms['employersForm'];
	
	var companyname = form.companyName.value.trim();
	var employername = form.employerName.value.trim();
	var emailid = form.emailId.value.trim();
	var phonenumber = form.phoneNumber.value.trim();
	var phoneNum = phonenumber.replace(/[^\d]/g, '');
	phoneNum = phoneNum.trim();
	var pnlength=phoneNum.length;
	var checkbox=form.terms.checked;
	
	var errorMsg="";
	var flag="";
	var count=0;
	
	if(companyname==null || companyname==""){
		errorMsg="Field Company Name cannot be left vacant!!";
		count++;
		form.companyName.style.border="2px solid red";
		flag=false;
	}
	if(employername==null || employername==""){
		errorMsg="Field Employer Name cannot be left vacant!!";
		count++;
		form.employerName.style.border="2px solid red";
		flag=false;
	}
	if(emailid==null || emailid==""){
		errorMsg="Field Email Id cannot be left vacant!!";
		count++;
		form.emailId.style.border="2px solid red";
		flag=false;
	}
	var emailContents = emailid.search("@");
	if(emailContents==-1){
		errorMsg="Enter a VALID Email Id!!";
		count++;
		form.emailId.style.border="2px solid red";
		flag=false;
	}
	if(phonenumber==null || phonenumber==""){
		errorMsg="Field Phone Number cannot be left vacant!!";
		count++;
		form.phoneNumber.style.border="2px solid red";
		flag=false;
	}
	if(pnlength < 10 || pnlength > 13){
		errorMsg="Enter a VALID Phone Number";
		count++;
		form.phoneNumber.style.border="2px solid red";
		flag=false;
	}
	
	if(count==0){
		flag=true;
	}
	if(!checkbox){
		errorMsg="Please Agree to our Terms and Conditions before submitting!!";
		count++;
		
		flag=false;
	}
	
	if(flag==false){
		if(count>1)
			errorMsg="Multiple Fields are INVALID!";
		document.getElementById("errorMsg").innerHTML=errorMsg;
	}
	
	return flag;
}


function authoriseUser() {
	
	var res = true;
	var form = document.forms['loginForm'];
	var errorMsg="";
	
	var userName = form.userName.value;
	var password = form.password.value;


	$
			.ajax({
				url : "/StartupMashup/authorize-user-for-popup",
				dataType : "json",
				method : "POST",
				async : false,
				data : {
					userName : userName,
					password : password
				},
				success : function(result) {
					if (result) {
						res = true;
					} else {
						res = false;
					}
				}

			});
	if(res==false){
		document.getElementById('errorMsg2').innerHTML="Please login with correct details!";
	}
	if(res==true){
		window.location.href = "/StartupMashup/employers-registration";
	}
	return false;
};

