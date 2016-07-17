package com.startupmashup.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.startupmashup.bean.ChallengeBean;
import com.startupmashup.bean.ChallengeSkillMapBean;
import com.startupmashup.bean.CompanyBean;
import com.startupmashup.bean.ContactBean;
import com.startupmashup.bean.EmployersBean;
import com.startupmashup.bean.HackathonBean;
import com.startupmashup.bean.ParticipantBean;
import com.startupmashup.bean.ReferDetailsBean;
import com.startupmashup.bean.SkillBean;
import com.startupmashup.bean.TestimonialBean;
import com.startupmashup.service.ChallengeService;
import com.startupmashup.service.ChallengeSkillMapService;
import com.startupmashup.service.CompanyChallengeMapService;
import com.startupmashup.service.ContactService;
import com.startupmashup.service.EmployersService;
import com.startupmashup.service.HackathonChallengeMapService;
import com.startupmashup.service.HackathonCompanyMapService;
import com.startupmashup.service.HackathonService;
import com.startupmashup.service.ParticipantChallengeMapService;
import com.startupmashup.service.ParticipantHackathonMapService;
import com.startupmashup.service.ParticipantService;
import com.startupmashup.service.ReferDetailsService;
import com.startupmashup.service.SkillService;
import com.startupmashup.service.SubscriberService;
import com.startupmashup.service.TestimonialService;

@Controller
public class ParticipantController {

	ModelAndView mav = null;
	@Autowired
	HackathonService hackathonService;
	@Autowired
	ChallengeService challengeService;
	@Autowired
	ParticipantService participantService;
	@Autowired
	ReferDetailsService referDetailsService;
	@Autowired
	ContactService contactService;
	@Autowired
	SkillService skillService;
	@Autowired
	SubscriberService subscriberService;
	@Autowired
	EmployersService employersService;
	@Autowired
	TestimonialService testimonialService;
	@Autowired
	ChallengeSkillMapService challengeSkillMapService;
	@Autowired
	CompanyChallengeMapService companyChallengeMapService;
	@Autowired
	HackathonChallengeMapService hackathonChallengeMapService;
	@Autowired
	HackathonCompanyMapService hackathonCompanyMapService;
	@Autowired
	ParticipantChallengeMapService participantChallengeMapService;
	@Autowired
	ParticipantHackathonMapService participantHackathonMapService;



	String result = "";
	String sessionTimeOutMsg = "";
	
	static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    private int liveHackathonCount;
   
    @ResponseBody
    @RequestMapping("/send-email")
    public ModelAndView sendEmailTest(@RequestParam("userName") String username){
        
    	ParticipantBean participantBean=participantService.getDetailsByUsername(username);
    	
    	long clickedDateTime = new Date().getTime();
    	
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
try{
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(participantBean.getEmailId()));
        generateMailMessage.setSubject("Set new password for Startup Mashup..");
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String token=encoder.encodePassword(username, "");
        String url="http://localhost:8080/StartupMashup/forgot-password?token="+token+"&sm="+clickedDateTime;
        String emailBody = "Hello "+participantBean.getFirstName()+", <br><br>"
        		+ "Click on the following link to reset your password :"
        		+ "<br><br>"+
        		url
        		+
        		"<br><br>"
        		+ "Regards,<br><br>"
        		+ "The StartupMashup Team.";
        generateMailMessage.setContent(emailBody, "text/html");
        Transport transport = getMailSession.getTransport("smtp");
         
        transport.connect("smtp.gmail.com", "thestartupmashup@myrefers.com", "myrefers@goodkarma");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
}catch(Exception e){
    e.printStackTrace();
}
        mav=new ModelAndView("success");
        return mav;
    }
    
    @RequestMapping("/forgot-password")
    public ModelAndView forgotPassword(@RequestParam("token") String token,
    		@RequestParam("sm") long clickedDateTime){
    	
    	mav=new ModelAndView("forgotPassword");
    	
    	long currentDateTime = new Date().getTime();
    	
    	if(currentDateTime-clickedDateTime > 1*60*1000){
    		mav.addObject("errorMsg", "Sorry! Your Link has expired. Request a New Link.");
    		return mav;
    	}
    	
    	ParticipantBean participantBean=participantService.getParticipantBeanByToken(token);
    	
    	mav.addObject("participantBean",participantBean);
    	return mav;
    }

    @RequestMapping("/update-password")
    public ModelAndView updatePassword(@RequestParam("participantId") String participantId,@RequestParam("newPassword") String newPassword){
    	
 
    	participantService.updatePassword(participantId,newPassword);
    	mav=new ModelAndView("home");
    	return mav;
    }
	// This method is used to display the home page of the site.

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displayOptions(HttpSession session) {

		liveHackathonCount=0;
		ModelAndView mav = new ModelAndView("home");
		session.setMaxInactiveInterval(60 * 30);
		List<HackathonBean> listOfHackathons = hackathonService
				.getHackathonList();
		
		
		
		List<HackathonBean> upcomingMashups = new ArrayList<HackathonBean>();
		List<HackathonBean> previousMashups = new ArrayList<HackathonBean>();

		List<TestimonialBean> listOfTestimonials = new ArrayList<TestimonialBean>();

		listOfTestimonials = testimonialService.getTestimonialList();

		if (listOfHackathons != null && !listOfHackathons.isEmpty()) {
			for (HackathonBean hackathonBean : listOfHackathons) {

				if (!hackathonBean.getStatus().equals("live")) {
					previousMashups.add(hackathonBean);
				} else {
					upcomingMashups.add(hackathonBean);
					liveHackathonCount++;
				}
			}
		}


		
		session.setAttribute("listOfTestimonials", listOfTestimonials);
		session.setAttribute("listOfHackathons", listOfHackathons);
		session.setAttribute("upcomingMashups", upcomingMashups);
		session.setAttribute("previousMashups", previousMashups);
		session.setAttribute("liveHackathonCount", liveHackathonCount);
	

		if (session.isNew()) {
		
			session.setAttribute("status", "loggedOut");
			
			session.setAttribute("userId", -1);
			session.setAttribute("userName", "");
		}
		return mav;

	}

	
	
	// This method is called when the participant wants to login to his account.

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView userLogin() {

		mav = new ModelAndView("participantLogin");
		mav.addObject("participantBean", new ParticipantBean());
		return mav;

	}

		// This method will be called to authorise a user from the pop-up.

	@RequestMapping(value = "/authorize-user-for-popup", method = RequestMethod.POST)
	@ResponseBody
	public String authorizeUserForPopUp(
			@RequestParam("userName") String username,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String passwordEnc = encoder.encodePassword(password, "");

		ParticipantBean participantBean = participantService.authoriseUser(
				username, passwordEnc);
		if (participantBean.getId() != 0) {

			HttpSession session = request.getSession(true);
			session.setAttribute("userId", participantBean.getId());
			session.setAttribute("userName", participantBean.getUserName());
			session.setMaxInactiveInterval(60 * 10);
		
			session.setAttribute("status", "loggedIn");
			
			return "true";

		}

		return "false";

	}

	@RequestMapping(value = "/privacy-policy", method = RequestMethod.GET)
	public ModelAndView getPrivacyPolicy() {

		mav = new ModelAndView("privacyPolicy");

		return mav;

	}

	// This method will be used to view the list of all hackathons.

	@RequestMapping(value = "/list-hackathon")
	public ModelAndView listHackathons(HttpSession session) {
		ParticipantBean participantBean = new ParticipantBean();

		String result = "";
		String sessionTimeOutMsg = "";
		if (!session.isNew()) {
			participantBean = participantService
					.getDetailsById((Integer) session.getAttribute("userId"));
			result = "showHackathonsToParticipant";
		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("listOfHackathons", hackathonService.getHackathonList());
		mav.addObject("participantBean", participantBean);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		mav.addObject("referDetailsBean", new ReferDetailsBean());
		return mav;
	}

	// This method is called when the participant wants to view his profile.

	@RequestMapping("/view-profile")
	public ModelAndView showProfile(HttpSession session) {

		ParticipantBean participantBean = new ParticipantBean();
		result = "";
		sessionTimeOutMsg = "";
		String loggedOut = "";
		List<HackathonBean> listOfRegisteredHackathons = new ArrayList<HackathonBean>();
		if ( session.getAttribute("status").equals("loggedIn")) {

			if (!session.isNew()) {

				participantBean = participantService
						.getDetailsById((Integer) session
								.getAttribute("userId"));
				listOfRegisteredHackathons = participantHackathonMapService
						.getMappedHackathons((Integer) session
								.getAttribute("userId"));
				result = "profile";

			} else {
				sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
				result = "home";
			}

		} else {
			loggedOut = "you have already logged out";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("participantBean", participantBean);
		mav.addObject("listOfRegisteredHackathons", listOfRegisteredHackathons);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		mav.addObject("loggedOut", loggedOut);
		return mav;

	}

	// This method is called when the participant wants to edit his profile.

	@RequestMapping("/edit-profile")
	public ModelAndView editProfile(HttpSession session) {

		result = "";
		sessionTimeOutMsg = "";
		ParticipantBean participantBean = new ParticipantBean();
		if (!session.isNew()) {
			participantBean = participantService
					.getDetailsById((Integer) session.getAttribute("userId"));

			result = "editProfile";

		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("participantBean", participantBean);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		return mav;

	}

	// This method is called to save the edited profile.

	@RequestMapping(value = "/save-profile", method = RequestMethod.POST)
	public ModelAndView saveProfile(
			@ModelAttribute("participantBean") ParticipantBean participantBean,
			@RequestParam("resumeFile") MultipartFile resume,
			@RequestParam("newPassword") String newPassword,
			HttpSession session) {

		result = "";
		sessionTimeOutMsg = "";
		if (!session.isNew()) {
			int userId = (Integer) session.getAttribute("userId");
			if(!newPassword.isEmpty()){
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				String passwordEnc = encoder.encodePassword(newPassword, "");
				participantBean.setPassword(passwordEnc);
			}
			ResourceBundle rb = ResourceBundle.getBundle("uplaod_path");
			String fileName = participantBean.getFirstName() + "@" + "MyRefers"
					+ ".docx";
			File file = new File(rb.getString("resumePath"), fileName);

			if (!resume.isEmpty()) {
				try {
					byte[] bytes = null;
					BufferedOutputStream stream = null;
					bytes = resume.getBytes();
					stream = new BufferedOutputStream(
							new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				participantBean
						.setResume("/StartupMashup/WEB-INF/resources/resumeUpload/"
								+ fileName);
			} else {
				participantBean.setResume(participantBean.getResume());
			}

			participantService.saveEditedProfile(participantBean, userId);

			result = "home";

		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("successMsg", "Profile edited succesffully");
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		return mav;

	}

	// This method will be called when the participant wants to logout.

	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session,HttpServletRequest request) {

		session.invalidate();
		/*servletContext.setAttribute("status", "loggedOut");*/
		
		
		
		List<HackathonBean> listOfHackathons = hackathonService
				.getHackathonList();
		
		List<HackathonBean> upcomingMashups = new ArrayList<HackathonBean>();
		List<HackathonBean> previousMashups = new ArrayList<HackathonBean>();

		List<TestimonialBean> listOfTestimonials = new ArrayList<TestimonialBean>();

		listOfTestimonials = testimonialService.getTestimonialList();

		if (listOfHackathons != null && !listOfHackathons.isEmpty()) {
			for (HackathonBean hackathonBean : listOfHackathons) {

				if (!hackathonBean.getStatus().equals("live")) {
					previousMashups.add(hackathonBean);
				} else {
					upcomingMashups.add(hackathonBean);
				}
			}
		}

		
		

		
		HttpSession sessionNew=request.getSession(true);
		sessionNew.setAttribute("status", "loggedOut");
		sessionNew.setAttribute("userId", -1);
		
		sessionNew.setAttribute("listOfTestimonials", listOfTestimonials);
		sessionNew.setAttribute("listOfHackathons", listOfHackathons);
		sessionNew.setAttribute("upcomingMashups", upcomingMashups);
		sessionNew.setAttribute("previousMashups", previousMashups);
		

		
		mav = new ModelAndView("home");
		return mav;

	}

	// This method will be called to show details about the site.

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about(HttpSession session) {

		result = "";
		sessionTimeOutMsg = "";
		if (!session.isNew()) {
			result = "about";
		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		return mav;

	}

	// This method will be called when the participant wants to contact the
	// admin with some queries.

	@RequestMapping(value = "/contact-us", method = RequestMethod.GET)
	public ModelAndView getContactUsForm(HttpSession session) {

		ParticipantBean participantBean = new ParticipantBean();
		sessionTimeOutMsg = "";
		result = "";
		if (!session.isNew()) {
			participantBean = participantService
					.getDetailsById((Integer) session.getAttribute("userId"));

			result = "contactUs";
			
		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("contactBean", new ContactBean());
		mav.addObject("participantBean", participantBean);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		return mav;

	}

	// This method will be called to save information about the user contacting
	// the site's admin.

	@RequestMapping(value = "/save-contact-details", method = RequestMethod.POST)
	public ModelAndView contactformdetails(
			@ModelAttribute("contactBean") ContactBean contactBean) {

		mav = new ModelAndView("home");
		contactService.saveContactInfo(contactBean);
		return mav;

	}

	// This method will be called when a new user wants to signup.

	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public ModelAndView signUp(HttpSession session) {

		sessionTimeOutMsg = "";
		result = "";
		if (!session.isNew()) {
			result = "signup";
		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		mav.addObject("participantBean", new ParticipantBean());
		return mav;

	}

	@RequestMapping(value = "/check-if-already-registered")
	@ResponseBody
	public String checkIfRegistered(
			@RequestParam("hackathonId") int hackathonId, HttpSession session) {
		result = participantHackathonMapService.checkIfAlreadyRegistered(
				hackathonId, (Integer) session.getAttribute("userId"));
		return result;

	}

	// This method will be called when the user wants to register for a
	// particular hackathon.

	@RequestMapping(value="/view-startup-details",method=RequestMethod.GET)
	public ModelAndView displayStartupDetails(
			@RequestParam("hackathonId") String hackId, HttpSession session) {

		ParticipantBean participantBean = new ParticipantBean();
		List<CompanyBean> listOfCompanies = new ArrayList<CompanyBean>();
		int hackathonId = Integer.parseInt(hackId);
		List<ChallengeBean> listOfChallenges = challengeService
				.getChallengesListByHackathonId(hackathonId);

		List<SkillBean> listOfSkills = null;
		List<ChallengeSkillMapBean> listOfChallengeSkillMapBeans = null;
		sessionTimeOutMsg = "";
		result = "";
		HackathonBean hackathonBean = hackathonService.getBean(hackId);
		listOfSkills = skillService.getSkills();
		
		listOfChallengeSkillMapBeans = challengeSkillMapService.getChallengeSkillMapList();
		listOfCompanies = hackathonCompanyMapService.getCompanyMappedToHackathon(hackathonId);
		if(hackathonBean.getStatus().equalsIgnoreCase("live")){
			if (!session.isNew()) {

				result = "event";
				participantBean = participantService
						.getDetailsById((Integer) session.getAttribute("userId"));
				
			
			} else {
				sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
				result = "home";
				participantBean.setId(-1);
			}
		}else{
			result = "previousEvent";
		}
		
		
		mav = new ModelAndView(result);
		mav.addObject("hackathonId", hackathonId);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		mav.addObject("participantBean", participantBean);
		mav.addObject("listOfChallenges", listOfChallenges);
		mav.addObject("hackathonBean", hackathonBean);
		mav.addObject("listOfCompanies", listOfCompanies);
		mav.addObject("listOfSkills", listOfSkills);
		mav.addObject("listOfChallengeSkillMapBeans",listOfChallengeSkillMapBeans);
		
		return mav;

	}

	// This method will be called to check the login/logout status of a
	// participant.

	@RequestMapping("/check-status")
	@ResponseBody
	public String checkStatus(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if ((Integer) session.getAttribute("userId") == -1) {
			return "false";
		}
		return "true";

	}

	// This method is called to map the hackathon and participant.

	@RequestMapping("/map-hackathon-participant")
	@ResponseBody
	public void showChallengesList(
			@RequestParam("hackathonId") int hackathonId,
			@RequestParam("userId") int participantId, HttpSession session) {

		participantHackathonMapService.saveMappingDetails(hackathonId, participantId);

	}

	// This method is used to get the list of challenges for a particular
	// hackathon identified by it's hackathon ID.

	@RequestMapping("/get-challenges")
	public ModelAndView challengeRegistration(
			@RequestParam("hackathonId") int hackathonId, HttpSession session) {

		sessionTimeOutMsg = "";
		result = "";
		if (!session.isNew()) {
			List<ChallengeBean> listOfChallenges = challengeService
					.getChallengesListByHackathonId(hackathonId);
			result = "challengeSelection";
			mav.addObject("listOfChallenges", listOfChallenges);
		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		return mav;

	}

	// This method is used to map a participant with the challenge selected by
	// him.

	@RequestMapping("/map-challenge-participant")
	public ModelAndView registerUserForChallenge(
			@RequestParam("registeredChallenge") int challengeId,
			@RequestParam("participantId") int participantId,
			HttpSession session) {

		sessionTimeOutMsg = "";
		result = "";

		if (!session.isNew()) {
			if ((Integer) session.getAttribute("userId") != -1) {
				participantId = (Integer) session.getAttribute("userId");
			}
			participantChallengeMapService.saveMappingDetails(challengeId, participantId);
			result = "home";
		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		return mav;

	}

	@RequestMapping(value = "/check-username")
	@ResponseBody
	public String checkUsername(@RequestParam("username") String username) {

		List<String> userNamesList = participantService.getUserNames();
		for (String str : userNamesList) {
			if (str.equals(username)) {
				return "true";
			}
		}
		return "false";

	}

	@RequestMapping(value = "/set-flag", method = RequestMethod.POST)
	@ResponseBody
	public void setFlag(@RequestParam("changeFlag") String changeFlag,
			HttpSession session) {
		if (Integer.parseInt(changeFlag) == 1) {
			session.setAttribute("flag", 1);
		} else {
			session.setAttribute("flag", 0);
		}

	}

	// This method will be called to save signup details.

	@RequestMapping(value = "/save-signup-details", method = RequestMethod.POST)
	public ModelAndView saveSignUpDetails(HttpSession session,
			@ModelAttribute("participantBean") ParticipantBean participantBean,
			@RequestParam("resumeFile") MultipartFile resume) {
		
		String emailPassword = participantBean.getPassword();
		sessionTimeOutMsg = "";
		result = "";
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String passwordEnc = encoder.encodePassword(participantBean.getPassword(), "");
		participantBean.setPassword(passwordEnc);
		
		if (!session.isNew()) {
			String fileName = participantBean.getFirstName() + "@Myrefers"
					+ ".docx";
			ResourceBundle rb = ResourceBundle.getBundle("upload_path");
			File file = new File(rb.getString("resumePath"), fileName);
			if (!resume.isEmpty()) {
				try {
					byte[] bytes = null;
					BufferedOutputStream stream = null;
					bytes = resume.getBytes();
					stream = new BufferedOutputStream(
							new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				participantBean
						.setResume("/StartupMashup/resources/resumeUpload/"
								+ fileName);
				participantService.saveParticipantInfo(participantBean);
				result = "home";
				
				mailServerProperties = System.getProperties();
		        mailServerProperties.put("mail.smtp.port", "587");
		        mailServerProperties.put("mail.smtp.auth", "true");
		        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
		        mailServerProperties.put("mail.smtp.starttls.enable", "true");
		try{
		        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		        generateMailMessage = new MimeMessage(getMailSession);
		        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(participantBean.getEmailId()));
		        generateMailMessage.setSubject("Startup Mashup Registration");
		       
		       
		        String emailBody = "Congratulations "+participantBean.getFirstName()+", !!!!<br><br>"
		        		+ "You have successfully registered with Startup Mashup !!!"
		        		+"<br><br>Its time to get your dream job. Your username and password are"
		        		+ "<br><br><b>Username:  "+participantBean.getUserName()
		        		+ "</b><br><br><b>Password:  "+ emailPassword
		        		+ "</b><br><br> "
		        		+ "Regards,<br><br>"
		        		+ "The StartupMashup Team."
		        		+"<img src='http://localhost:8080/StartupMashup/resources/images/logo.png'></img>" ;
		        generateMailMessage.setContent(emailBody, "text/html");
		        Transport transport = getMailSession.getTransport("smtp");
		         
		        transport.connect("smtp.gmail.com", "thestartupmashup@myrefers.com", "myrefers@goodkarma");
		        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		        transport.close();
		}catch(Exception e){
		    e.printStackTrace();
		}
				
				
				
				

				
				
			}
		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		return mav;

	}

	// This method will be called when an existing user wants to register for an
	// event.

	@RequestMapping(value = "/save-hackathon-form-details", method = RequestMethod.POST)
	public ModelAndView saveFormDetails(
			@ModelAttribute("participantBean") ParticipantBean participantBean,
			@ModelAttribute("hackathonId") int hackathonId,
			@RequestParam("selectedChallenge") String challengeId,
			HttpSession session) {
	
		int participantId = (Integer) session.getAttribute("userId");
		participantHackathonMapService.saveMappingDetails(hackathonId, participantId);
		participantChallengeMapService.saveMappingDetails(Integer.parseInt(challengeId),
				participantId);
		hackathonService.incrementCount(hackathonId);
		if ((Integer) session.getAttribute("flag") == 1) {
			participantService
					.updateParticipant(participantId, participantBean);
		}
		HackathonBean hackathonBean=hackathonService.getBean(String.valueOf(hackathonId));
		mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
try{
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(participantBean.getEmailId()));
        generateMailMessage.setSubject("Your Registration for The Startup Mashup on "+hackathonBean.getDate());
       
       
        String emailBody = "Congratulations "+participantBean.getFirstName()+", !!!!<br><br>"
        		+ "Thanks for registering with us! Your profile has reached safely to us and is in the process of evaluation."
        		+"<br><br>So brace yourself for The Startup Mashup  "+hackathonBean.getName()+" on "+hackathonBean.getDate() 
        		
        		+ "<br><br> We will be reminding you before the event to block your calender. "
        		+ "<br><br> P.S. You will have to bring your own laptop for hackathon along with two copies of resume. "
        		+ "<br><br>You can contact The Startup Mashup team on the below coordinates; "
        		+ "<br><br>Yash Saxena : 8800446343 "
        		+ "<br><br>Akshat Goel : 9818779066 "
        		+ "<br><br> Regards,<br><br>"
        		+ "The StartupMashup Team.";
        generateMailMessage.setContent(emailBody, "text/html");
        Transport transport = getMailSession.getTransport("smtp");
         
        transport.connect("smtp.gmail.com", "thestartupmashup@myrefers.com", "myrefers@goodkarma");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
}catch(Exception e){
    e.printStackTrace();
}
		

		mav = new ModelAndView("home");
		return mav;

	}

	// This method will save the REFERRED FRIENDS DETAILS

	@RequestMapping(value = "/save-refer-details", method = RequestMethod.POST)
	public ModelAndView saveReferFormDetails(
			@RequestParam("flag") String flag,
			@ModelAttribute("referDetailsBean") ReferDetailsBean referDetailsBean,
			@RequestParam("referredHackathonId") String hackathonId, HttpSession session) {

		sessionTimeOutMsg = "";
		result = "";
		int hackId = Integer.parseInt(hackathonId);

		if (!session.isNew()) {
			referDetailsBean.setHackathonId(hackId);
			referDetailsService.saveReferDetailsInfo(referDetailsBean);
			
			HackathonBean hackathonBean=hackathonService.getBean(String.valueOf(hackathonId));
			mailServerProperties = System.getProperties();
	        mailServerProperties.put("mail.smtp.port", "587");
	        mailServerProperties.put("mail.smtp.auth", "true");
	        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
	        mailServerProperties.put("mail.smtp.starttls.enable", "true");
	try{
	        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
	        generateMailMessage = new MimeMessage(getMailSession);
	        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(referDetailsBean.getRefEmailId()));
	        generateMailMessage.setSubject(referDetailsBean.getRefName()+" has referred you for The Startup Mashup  "+hackathonBean.getName());
	       
	       
	        String emailBody = "Hi "+referDetailsBean.getName()
	        		+"<br><br> Your Friend "+referDetailsBean.getRefName()+" has referred you for The Startup Mashup "+hackathonBean.getName()+" on "+hackathonBean.getDate()
	        		+"<br><br> If you will get hired on the event , "+referDetailsBean.getRefName()+"  will get a referral reward. Additionally both of you will get a flipkart vouchar worth INR 500."
	        		+"<br><br> To register for the event apply through the link given below."
	        		+"<br><br>http://localhost:8080/StartupMashup/view-startup-details?hackathonId="+hackathonBean.getId()	       	
	        		+ "<br><br> Regards,<br><br>"
	        		+ "The StartupMashup Team.";
	        generateMailMessage.setContent(emailBody, "text/html");
	        Transport transport = getMailSession.getTransport("smtp");
	         
	        transport.connect("smtp.gmail.com", "thestartupmashup@myrefers.com", "myrefers@goodkarma");
	        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
	        transport.close();
	}catch(Exception e){
	    e.printStackTrace();
	}
			
			
			
			
			
			
			
			if (flag.equals("stay")) {
				result = "showHackathonsToParticipant";
			} else {
				result = "home";
			}
		} else {
			sessionTimeOutMsg = "Your session has timed out ! Please login again to proceed further!";
			result = "home";
		}
		mav = new ModelAndView(result);
		mav.addObject("sessionTimeOutMsg", sessionTimeOutMsg);
		return mav;

	}

	// This method will be called to display an error page when some error
	// occurs.

	@RequestMapping("/error")
	public ModelAndView errorPage() {

		ModelAndView mav = new ModelAndView("error");
		return mav;

	}

	// This method will open a page for employers looking to do mashups.

	@RequestMapping(value = "/employers", method = RequestMethod.GET)
	public ModelAndView getEmployers() {

		mav = new ModelAndView("employers");

		return mav;

	}

	// This method will be called to register an employer for a mashup.

	@RequestMapping(value = "/employers-registration", method = RequestMethod.GET)
	public ModelAndView getEmployersRegistration() {

		mav = new ModelAndView("employersRegistration");
		mav.addObject("employersBean", new EmployersBean());

		return mav;

	}

	// This method saves the details of the Employers.

	@RequestMapping(value = "/save-employers-details", method = RequestMethod.POST)
	public ModelAndView saveEmployersDetails(
			@ModelAttribute("employersBean") EmployersBean employersBean) {

		employersService.saveEmployersInfo(employersBean);
		mav = new ModelAndView("successRegister");
		return mav;

	}

	// This method will save the details of a subscriber.

	@RequestMapping(value = "/save-subscriber", method = RequestMethod.POST)
	@ResponseBody
	public String saveSubscriber(@RequestParam("emailId") String emailId) {
		subscriberService.saveSubscriberInfo(emailId);
		return "true";
	}

	// This method will be called to view press details.

	@RequestMapping(value = "/press", method = RequestMethod.GET)
	public ModelAndView press() {
		mav = new ModelAndView("press");
		return mav;
	}

}
