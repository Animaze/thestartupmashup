
	var firstname = "";
	var lastname = "";
	var emailid = "";
	var phonenumber = "";

	$(function() {
		firstname = '${participantBean.firstName}';
		lastname = '${participantBean.lastName}';
		emailid = '${participantBean.emailId}';
		phonenumber = '${participantBean.phoneNumber}';

	});
	
		function checkChanges() {
		
		var hackathonId = $
		{
			hackathonId
		}
		;
		$('input[type="text"]').css('border','0px solid white');
		$('input[type="email"]').css('border','0px solid white');
		var count=0;
		var errorMsg="";
		
		var form = document.forms['Participant'];
		var res = true;
		var challengeSelected = $('#selectedChallenge' + " :selected").val();
		if (challengeSelected == -1) {
			errorMsg="Please select a challenge to continue!";
			count++;
			form.selectedChallenge.style.border="2px solid red";
			res=false;
		
		}

		$
				.ajax({
					url : "/StartupMashup/check-if-already-registered",
					dataType : "json",
					method : "GET",
					data : {
						hackathonId : hackathonId
					},
					async : false,
					success : function(result) {
						if (result) {
							errorMsg="You've registered already! Cannot register for more than one challenge!";
							count++;
							//form.name.style.border="2px solid red";
							res=false;
							
						}
					}

				});
	
		var newfirstname = form.firstName.value;
		var newlastname = form.lastName.value;
		var newemailid = form.emailId.value;
		var newphonenumber = form.phoneNumber.value;
		var pnlength = newphonenumber.length;
		
		newfirstname=newfirstname.trim();
		newphonenumber=newphonenumber.trim();
		newphonenumber=newphonenumber.replace(/[^\d]/g, '');
		newemailid=newemailid.trim();
		newlastname=newlastname.trim();
		

		if(newfirstname==null || newfirstname==""){
			errorMsg="Field First Name cannot be left vacant!!";
			count++;
			form.firstName.style.border="2px solid red";
			res=false;
		}
		if(newlastname==null || newlastname==""){
			errorMsg="Field Last Name cannot be left vacant!!";
			count++;
			form.lastName.style.border="2px solid red";
			res=false;
		}
		if(newemailid==null || newemailid==""){
			errorMsg="Field Email Id cannot be left vacant!!";
			count++;
			form.emailId.style.border="2px solid red";
			res=false;
		}
		var emailContents = newemailid.search("@");
		if(emailContents==-1){
			errorMsg="Enter a VALID Email Id!!";
			count++;
			form.emailId.style.border="2px solid red";
			res=false;
		}
		if(newphonenumber==null || newphonenumber==""){
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
		
		if (newfirstname != firstname || newlastname != lastname
				|| newemailid != emailid || newphonenumber != phonenumber) {
			
			
		
			$.ajax({
				url : "/StartupMashup/set-flag?changeFlag=1",
				dataType : "json",
				method : "POST",
				async : false,
				success : function(){
					
				}
			});

		} else {
			
			$.ajax({
				url : "/StartupMashup/set-flag?changeFlag=0",
				dataType : "json",
				method : "POST",
				async : false,
				success : function(){
					
				}
				
			});
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
		
		
function authoriseUser2(hackathonId) {
	var errorMsg2="";
	var form = document.forms['registeredLoginForm'];
	$('input[type="text"]').css('border', '0px solid white');
	$('input[type="password"]').css('border', '0px solid white');
		var res = true;
		var form = document.forms['registeredLoginForm'];
		
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
							form.userName.style.border="2px solid red";
							form.password.style.border="2px solid red";
							errorMsg2="Please login with correct details!";
							document.getElementById("errorMsg2").innerHTML=errorMsg2;
						
							
							res = false;
						}
					}

				});
		

		
		if(res==true){
			window.location.reload();
		
		}
		return false;
	};
	
	function sendEmail2(){
		$('#loading_image').show();
		$('input[type="text"]').css('border', '0px solid white');
		$('input[type="password"]').css('border', '0px solid white');
	
		var form = document.forms['registeredLoginForm'];
		var errorMsg2="";
		var userName = form.userName.value;
		if(userName=="" || userName==null){
			$('#loading_image').hide();
			errorMsg2="Please fill in your username!";
			form.userName.style.border="2px solid red";
			document.getElementById("errorMsg2").innerHTML=errorMsg2;
			return false;	
		}
		
		$.ajax({
			url : "/StartupMashup/send-email",
			dataType : "json",
			method : "POST",
			async : false,
			data : {
				userName : userName
			},
			success : function() {
				
			}


		});
	
		errorMsg2="An email has been sent to your registered email id!!!";
		document.getElementById("errorMsg2").innerHTML=errorMsg2;
		 $('#loading_image').hide();
		return true;
		
	};
