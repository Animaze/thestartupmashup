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
			document.getElementById('errorMsg').innerHTML="Please login with correct details!";
		}
		if(res==true){
			window.location.href = "/StartupMashup/about";
		}
		return false;
	};

