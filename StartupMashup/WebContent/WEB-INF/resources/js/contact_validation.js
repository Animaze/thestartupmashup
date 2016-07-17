
function validateForm()
{
	
	$('input[type="text"]').css('border','0px solid white');
	$('input[type="email"]').css('border','0px solid white');
	$('textarea').css('border','0px solid white');
	var form = document.forms['contactForm'];
	var name=document.forms["contactForm"]["name"].value;
	var emailid=document.forms["contactForm"]["emailId"].value;
	var phonenumber=document.forms["contactForm"]["phoneNumber"].value;
	var query=document.forms["contactForm"]["query"].value;
	
	var checkbox = form.terms.checked;
	
	name=name.trim();
	phonenumber=phonenumber.trim();
	phonenumber=phonenumber.replace(/[^\d]/g, '');
	emailid=emailid.trim();
	query=query.trim();
	
	var count=0;
	var errorMsg="";
	var res=true;
	var pnlength = phonenumber.length;
	
	var emailContents=emailid.search("@");
	if(name== null || name=="")
		{
		errorMsg="Name must be filled out.";
		count++;	
		form.name.style.border="2px solid red";
		res=false;
		}
	if(emailid== null || emailid=="" )
		{
		errorMsg="Email Id must be filled."
		count++;
		form.emailId.style.border="2px solid red";
		res=false;
		}
	
	if (checkbox == false) {
		errorMsg = "Please Agree to our Terms and Conditions before Submitting!"
		count++;
		res = false;
	}
	if(query== null || query=="" )
	{
		errorMsg="query must be filled out";
		count++;
		form.query.style.border="2px solid red";
		res=false;
	
	}
	if(emailContents==-1){
		errorMsg="Enter a VALID Email Id!!";
		count++;
		form.emailId.style.border="2px solid red";
		res=false;
	}
	if(phonenumber== null || phonenumber=="" )
	{
		errorMsg="Field Phone Number cannot be left vacant!!";
		count++;
		form.phoneNumber.style.border="2px solid red";
		res=false;
	
	}
	
	if(pnlength < 10 || pnlength > 13){
		errorMsg="Enter a VALID Phone Number";
		count++;
		form.phoneNumber.style.border="2px solid red";
		res=false;
	}
	
	if(count==0){
		res=true;
	}
	
	if(res==false){
		if(count>1)
			errorMsg="Multiple Fields are INVALID!";
		document.getElementById("errorMsg").innerHTML=errorMsg;
	}
	
	return res;
}

