
	// These are the validations for the login form
		
		function sendEmail(){
			$('#loading_image').show();
			$('input[type="text"]').css('border', '0px solid white');
			$('input[type="password"]').css('border', '0px solid white');
		
			var form = document.forms['loginForm'];
			var errorMsg="";
			var userName = form.userName.value;
			if(userName=="" || userName==null){
				$('#loading_image').hide();
				errorMsg="Please fill in your username!";
				form.userName.style.border="2px solid red";
				document.getElementById("errorMsg").innerHTML=errorMsg;
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
		
			errorMsg="An email has been sent to your registered email id!!!";
			document.getElementById("errorMsg").innerHTML=errorMsg;
			 $('#loading_image').hide();
			return true;
			
		}

	function authoriseUser(hackathonId) {
		var errorMsg="";
		var form = document.forms['loginForm'];
		$('input[type="text"]').css('border', '0px solid white');
		$('input[type="password"]').css('border', '0px solid white');
		var res = true;
		var form = document.forms['loginForm'];
		
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
							errorMsg="Username or password is incorrect!";
							document.getElementById("errorMsg").innerHTML=errorMsg;
							
							res = false;
						}
					}

				});
		
	
		if(res==true){
			window.location.reload();
		
		}
		return false;
	};
