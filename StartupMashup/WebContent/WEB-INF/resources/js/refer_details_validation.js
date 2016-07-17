/*This file contains function for validating the Referral Form.*/

function validateReferralForm(){
	
	$('input[type="text"]').css('border','0px solid white');
	$('input[type="email"]').css('border','0px solid white')
	
	var form = document.forms['ReferDetails'];
	
	var name = form.name.value.trim();
	var emailid = form.emailId.value.trim();
	var phonenumber = form.phoneNumber.value.trim();
	var phoneNum = phonenumber.replace(/[^\d]/g, '');
	phoneNum = phoneNum.trim();
	var pnlength = phoneNum.length;
	var collegename = form.collegeName.value.trim();
	
	var refname = form.refName.value.trim();
	var refemailid = form.refEmailId.value.trim();
	var refphonenumber = form.refPhoneNumber.value.trim();
	var refphoneNum = refphonenumber.replace(/[^\d]/g, '');
	refphoneNum = refphoneNum.trim();
	var refpnlength = refphoneNum.length;
	
	var checkbox = form.terms.checked;
	
	var count=0;
	var errorMsg="";
	var flag=true;
	
	if(name==null || name==""){
		errorMsg="Field Name cannot be left vacant!!";
		count++;
		form.name.style.border="2px solid red";
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
	if(collegename==null || collegename==""){
		errorMsg="Field College Name cannot be left vacant!!"
		count++;
		form.collegeName.style.border="2px solid red";
		flag=false;
	}
	if(refname==null || refname==""){
		errorMsg="Field Your Name cannot be left vacant!!";
		count++;
		form.refName.style.border="2px solid red";
		flag=false;
	}
	if(refemailid==null || refemailid==""){
		errorMsg="Field Your Email Id cannot be left vacant!!";
		count++;
		form.refEmailId.style.border="2px solid red";
		flag=false;
	}
	var refemailContents = refemailid.search("@");
	if(refemailContents==-1){
		errorMsg="Enter a VALID Email Id!!";
		count++;
		form.refEmailId.style.border="2px solid red";
		flag=false;
	}
	if(refphonenumber==null || refphonenumber==""){
		errorMsg="Field Your Phone Number cannot be left vacant!!";
		count++;
		form.refPhoneNumber.style.border="2px solid red";
		flag=false;
	}
	if(refpnlength < 10 || refpnlength > 13){
		errorMsg="Enter a VALID Phone Number";
		count++;
		form.refPhoneNumber.style.border="2px solid red";
		flag=false;
	}
	if(checkbox==false){
		errorMsg="Please Agree to our Terms and Conditions before Submitting!"
		count++;
		flag=false;
	}
	
	if(count==0){
		flag=true;
	}
	
	if(flag==false){
		
		if(count>1)
			errorMsg="Multiple Fields are INVALID!";
		document.getElementById("errorMsg2").innerHTML=errorMsg;
	}
	
	
	return flag;
}