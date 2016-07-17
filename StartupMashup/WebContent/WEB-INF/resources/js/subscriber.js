

function subscribe(){

 var emailId=$("#emailId").val();
 var emailContents=emailId.search("@");
 if(emailContents==-1|| emailId==null||emailId=="")
 {
	  alert("Enter a valid email address !!")
 }
 else{
	 $.ajax({
		 url:"/StartupMashup/save-subscriber",
			 data:{emailId:emailId},
				 method:"POST",
					 dataType:"json",
						 success:function(result){
							 if(result)
							 { alert("Subscribed successfully");
							
							 }
							 else{
								 alert("An error occured while processing your request , please reload the page and try again");
							 }
						
			
			 }
	 }); 
 }
 document.getElementById("emailId").value="";
 }
