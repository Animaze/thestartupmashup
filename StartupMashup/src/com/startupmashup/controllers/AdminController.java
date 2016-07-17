package com.startupmashup.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.startupmashup.bean.CompanyChallengeMapBean;
import com.startupmashup.bean.HackathonBean;
import com.startupmashup.bean.HackathonChallengeMapBean;
import com.startupmashup.bean.HackathonCompanyMapBean;
import com.startupmashup.bean.ParticipantBean;
import com.startupmashup.bean.ReferDetailsBean;
import com.startupmashup.bean.SkillBean;
import com.startupmashup.bean.TestimonialBean;
import com.startupmashup.service.AdminService;
import com.startupmashup.service.ChallengeService;
import com.startupmashup.service.ChallengeSkillMapService;
import com.startupmashup.service.CompanyChallengeMapService;
import com.startupmashup.service.CompanyService;
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

/* 
 * This Controller is responsible for the activities which the admin does. These are (in the order of methods) :
 * 
 * 	1. admin Login
 *		- welcome Page
 *		- logout
 * 	2. admin Logout
 * 	3. testimonials
 * 		- registration
 * 		- list
 * 		- edit
 * 	4. skill
 * 		- registration
 * 		- list
 * 		- edit
 * 	5. challenges
 * 		- registration
 * 		- list
 * 		- edit
 * 	6. hackathons
 * 		- registration
 * 		- list
 * 		- edit 
 * 	7. companies
 * 		- registration
 * 		- list
 * 		- edit
 * 	8. participant
 * 		- list of all users
 * 		- list of users in a particular hackathon
 * 		- list of participants in a particular challenge of a hackthon
 * 	9. referrals
 * 		- list		
 *  
 * */

@Controller
public class AdminController {

	ModelAndView mav = null;

	// Admin Home Page Methods

	@Autowired
	AdminService adminService;

	@Autowired
	TestimonialService testimonialService;
	@Autowired
	SkillService skillService;
	@Autowired
	ChallengeService challengeService;
	@Autowired
	HackathonService hackathonService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ReferDetailsService referDetailsService;
	@Autowired
	ParticipantService participantService;

	

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

/*	@Autowired
	ServletContext servletContext;*/
	
	//Resource Bundle, where the path to every Multipart File has been defined.
	ResourceBundle rb = ResourceBundle.getBundle("upload_path");
	


	@RequestMapping(value = "/adminHomePage", method = RequestMethod.GET)
	public ModelAndView goToAdminHomePage(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		String username = authentication.getName();

		session.setAttribute("adminUsername", username);

		mav = new ModelAndView("adminHomePage");
		return mav;
	}

	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public void adminLogout(HttpSession session) {
		session.invalidate();
	}



	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam("msg") String msg) {

		mav = new ModelAndView("login");
		mav.addObject("msg", msg);

		return mav;
	}

	

	// Testimonial methods

	

	@RequestMapping("/admin/testimonial-registration")
	public ModelAndView dislayTestimonialForm() {
		ModelAndView mav = new ModelAndView("testimonialRegistrationForm");

		mav.addObject("testimonialBean", new TestimonialBean());

		return mav;
	}

	@RequestMapping("/admin/save-testimonial-details")
	public ModelAndView saveTestimonialDetails(
			@ModelAttribute("testimonialBean") TestimonialBean testimonialBean,
			@RequestParam("testimonialImage") MultipartFile imageFile,
			@RequestParam("day") String day,
			@RequestParam("month") String month,
			@RequestParam("year") String year) {

		String eventDate = day + "-" + month + "-" + year;
		
		try {

			Date modifiedDate = new SimpleDateFormat("dd-MM-yyyy")
					.parse(eventDate);
			testimonialBean.setDate(modifiedDate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		String fileName = testimonialBean.getParticipantName() + "_"
				+ testimonialBean.getCompanyName() + "_"
				+ testimonialBean.getChallengeName() + ".png";
		fileName = fileName.replaceAll("//s", "");
		fileName = fileName.replaceAll(" ", "");
		File file = new File(rb.getString("testimonialUpload"), fileName);
		if (!imageFile.isEmpty()) {
			try {
				byte[] bytes = null;
				BufferedOutputStream stream = null;
				bytes = imageFile.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			testimonialBean
					.setImage("/StartupMashup/resources/testimonialImages/"
							+ fileName);
			

		}
		ModelAndView mav = new ModelAndView("success");

		testimonialService.saveTestimonialInfo(testimonialBean);

		return mav;
	}

	@RequestMapping("/admin/list-testimonials")
	public ModelAndView getTestimonialList() {

		List<TestimonialBean> listOfTestimonials = testimonialService
				.getTestimonialList();

		mav = new ModelAndView("testimonialList");
		mav.addObject("listOfTestimonials", listOfTestimonials);
		return mav;

	}

	@RequestMapping(value = "/admin/edit-testimonial", method = RequestMethod.POST)
	public ModelAndView editTestimonials(
			@RequestParam("testimonialId") int testimonialId) {
		TestimonialBean testimonialBean = testimonialService
				.getTestimonialById(testimonialId);
		mav = new ModelAndView("editTestimonialForm");
		mav.addObject("testimonialBean", testimonialBean);

		return mav;
	}

	@RequestMapping("/admin/save-edited-testimonial")
	public ModelAndView saveEditedDetails(
			@ModelAttribute("testimonialBean") TestimonialBean testimonialBean,
			@RequestParam("day") String day,
			@RequestParam("month") String month,
			@RequestParam("year") String year,
			@RequestParam("testimonialImage") MultipartFile testimonialImage,
			@RequestParam("previousDate") String previousDate,
			@RequestParam("previousImage") String previousImage) {

		if (-1 == (Integer.parseInt(day))) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd",
					Locale.ENGLISH);
			try {
				Date date = format.parse(previousDate);
				testimonialBean.setDate(date);
			} catch (ParseException e) {
			
				e.printStackTrace();
			}
		} else {
			String eventDate = day.trim() + "-" + month.trim() + "-"
					+ year.trim();
			try {
				Date modifiedDate = new SimpleDateFormat("dd-MM-yyyy")
						.parse(eventDate);
				testimonialBean.setDate(modifiedDate);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if (!testimonialImage.isEmpty()) {
			
			
			String fileName = testimonialBean.getParticipantName() + "_"
					+ testimonialBean.getCompanyName() + "_"
					+ testimonialBean.getChallengeName() + ".png";
			fileName = fileName.replaceAll(" ", "");
			fileName = fileName.replaceAll("//s", "");
			
			int index = previousImage.indexOf("/",1);
			
			String sub1 = previousImage.substring(0, index);
			
			String sub2 = previousImage.substring(index);
			
			String str = "C:/StartupMashup"+sub1+"/WebContent/WEB-INF"+sub2;
			
			File oldFile = new File(str);
			
			oldFile.delete();
		
			File file = new File(rb.getString("testimonialUpload"), fileName);

			try {
				byte[] bytes = null;
				BufferedOutputStream stream = null;
				bytes = testimonialImage.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			testimonialBean
					.setImage("/StartupMashup/resources/testimonialImages/"
							+ fileName);

		} else {
			testimonialBean.setImage(previousImage);
		}

		testimonialService.updateTestimonials(testimonialBean);
		ModelAndView mav = new ModelAndView("success");
		return mav;
	}
	
	// Skill Methods

		@RequestMapping("/admin/skill-registration")
		public ModelAndView displaySkillForm() {
			mav = new ModelAndView("skillRegistrationForm");
			mav.addObject("skillBean", new SkillBean());
			return mav;
		}

		@RequestMapping("/admin/save-skill-details")
		public ModelAndView saveSkillDetails(
				@ModelAttribute("skillBean") SkillBean skillBean) {
			mav = new ModelAndView("success");

			skillService.saveSkillInfo(skillBean);

			return mav;
		}

		@RequestMapping("/admin/list-skills")
		public ModelAndView getSkillList() {

			List<SkillBean> listOfSkills = skillService.getSkills();
			mav = new ModelAndView("skillList");
			mav.addObject("listOfSkills", listOfSkills);
			return mav;

		}

		@ResponseBody
		@RequestMapping(value = "/admin/edit-skill", method = RequestMethod.POST)
		public void editSkills(@RequestParam("skillId") int skillId,
				@RequestParam("skillName") String skillName, HttpSession session) {
			
			SkillBean skillBean = new SkillBean();
			skillBean.setId(skillId);
			skillBean.setName(skillName);
			
			skillService.updateSkills(skillBean);
		}
	
		// Challenge Method

		@RequestMapping(value = "/admin/challenge-registration")
		public ModelAndView registerChallenge() {

			List<SkillBean> skillBeanList = skillService.getSkills();
			
			mav = new ModelAndView("challengeRegistrationForm");
			mav.addObject(new ChallengeBean());
			
			mav.addObject("skillBeanList", skillBeanList);
			return mav;
		}

		@RequestMapping(value = "/admin/save-challenge-details", method = RequestMethod.POST)
		public ModelAndView saveChallengeDetails(
				@ModelAttribute("challengeBean") ChallengeBean challengeBean,
				@RequestParam("selectedSkillList") List<String> selectedSkillList) {

			challengeService.saveChallengeInfo(challengeBean, selectedSkillList);
			mav = new ModelAndView("success");
			return mav;
		}
		
		@RequestMapping(value = "/admin/list-challenges")
		public ModelAndView getListOfChallenges() {

			List<ChallengeSkillMapBean> challengeSkillMapBeanList = challengeSkillMapService.getChallengeSkillMapList();
			
			List<SkillBean> skillBeanList = skillService.getSkills();

			
			mav = new ModelAndView("challengeList");
			mav.addObject("listOfChallenges", challengeService.getChallenges());
			mav.addObject("challengeSkillMapBeanList", challengeSkillMapBeanList);
			mav.addObject("skillBeanList", skillBeanList);

			return mav;

		}

		

		@RequestMapping(value = "/admin/edit-challenges")
		public ModelAndView editChallenges(
				@RequestParam("challengeId") String selectedChallenge) {

			int challengeId = Integer.parseInt(selectedChallenge);
			
			List<SkillBean> skillBeanList = skillService.getSkills();
			
			ChallengeBean challengeBean = challengeService
					.getDetailsById(challengeId);
			
			List<Integer> mappedSkillIds = challengeSkillMapService
					.getMappedSkillIds(challengeId);
			
			List<SkillBean> selectedSkillBeanList = skillService.getSkillsById(mappedSkillIds);
			
			mav = new ModelAndView("editChallengeForm");
			mav.addObject("skillBeanList", skillBeanList);
			mav.addObject("challengeBean", challengeBean);
			mav.addObject("selectedSkillBeanList", selectedSkillBeanList);
			return mav;

		}

		@RequestMapping(value = "/admin/save-edited-challenge", method = RequestMethod.POST)
		public ModelAndView saveEditedDetails(
				@ModelAttribute("challengeBean") ChallengeBean challengeBean,
				@RequestParam("selectedSkillList") List<String> selectedSkillList) {

			challengeService.updateChallengeInfo(challengeBean, selectedSkillList);
			
			if (!selectedSkillList.get(0).equals("-1")) {
				challengeSkillMapService.editMappingDetails(challengeBean.getId(),
						selectedSkillList);
			}
				
			mav = new ModelAndView("success");
			return mav;

		}	
	
		// Hackathon Methods

		@RequestMapping(value = "/admin/hackathon-registration", method = RequestMethod.GET)
		public ModelAndView getHackathon() {
			mav = new ModelAndView("hackathonRegistrationForm");
			
			List<ChallengeBean> challengeBeanList = challengeService.getChallenges();
			
			mav.addObject("challengeBeanList", challengeBeanList);
			mav.addObject("hackathonBean", new HackathonBean());

			return mav;
		}

		@RequestMapping(value = "/admin/save-hackathon-details", method = RequestMethod.POST)
		public ModelAndView savehackathonDetails(
				@ModelAttribute("hackathonBean") HackathonBean hackathonBean,
				@RequestParam("day") String day,
				@RequestParam("month") String month,
				@RequestParam("year") String year,
				@RequestParam("challengeId") ArrayList<String> challengeId,
				@RequestParam("hackImage") MultipartFile hackImage) {
	
			String eventDate = day + "-" + month + "-" + year;

			try {
				Date modifiedDate = new SimpleDateFormat("dd-MM-yyyy")
						.parse(eventDate);
				hackathonBean.setDate(modifiedDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}

			
			String fileName = hackathonBean.getName() + ".png";
			fileName = fileName.replaceAll(" ", "");
			fileName = fileName.replaceAll("//s", "");
			File file = new File(rb.getString("hackathonUpload"), fileName);
			if (!hackImage.isEmpty()) {
				try {
					byte[] bytes = null;
					BufferedOutputStream stream = null;
					bytes = hackImage.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				hackathonBean
						.setImage("/StartupMashup/resources/hackathonImageUpload/"
								+ fileName);

				hackathonService.saveHackathonInfo(hackathonBean, challengeId);
				mav = new ModelAndView("success");
			} else {
				mav = new ModelAndView("error");

			}

			return mav;
		}
		
		@RequestMapping(value = "/admin/list-hackathons")
		public ModelAndView getListOfHackathons() {

			List<HackathonChallengeMapBean> mapBeanList = hackathonChallengeMapService.getHackathonChallengeMapList();
			
			List<ChallengeBean> challengeBeanList = challengeService.getChallenges();
			
			List<HackathonCompanyMapBean> mapBeanList2 = hackathonCompanyMapService.getList();
			
			List<CompanyBean> companyBeanList = companyService.getCompanyList();

			
			mav = new ModelAndView("showHackathons");
			mav.addObject("listOfHackathons", hackathonService.getHackathonList());
			mav.addObject("mapBeanList", mapBeanList);
			mav.addObject("challengeBeanList", challengeBeanList);
			mav.addObject("mapBeanList2", mapBeanList2);
			mav.addObject("companyBeanList", companyBeanList);

			return mav;

		}
		
		@RequestMapping("/admin/edit-hackathon")
		public ModelAndView displayHackathonForm(
				@RequestParam("hackathonId") String hackathonId) {

			mav = new ModelAndView("editHackathonForm");

			HackathonBean hackathonBean = hackathonService.getBean(hackathonId);

			ArrayList<HackathonChallengeMapBean> hackathonChallengeMapBeanList = hackathonChallengeMapService
					.getHackathonChallengeMapList();
			
			List<Integer> challengeIdList = new ArrayList<Integer>();
			
			for (HackathonChallengeMapBean hackathonChallengeMapBean : hackathonChallengeMapBeanList) {
				if (hackathonChallengeMapBean.getHackathonId() == Integer
						.parseInt(hackathonId))
					challengeIdList.add(hackathonChallengeMapBean
							.getChallengeId());
			}
			List<ChallengeBean> selectedChallengeBeanList = new ArrayList<ChallengeBean>();
			for (int chId : challengeIdList) {
				selectedChallengeBeanList
						.add(challengeService.getDetailsById(chId));
			}

			List<ChallengeBean> challengeBeanList = challengeService
					.getChallenges();

			mav.addObject("hackathonBean", hackathonBean);
			mav.addObject("selectedChallengeBeanList", selectedChallengeBeanList);
			mav.addObject("challengeBeanList", challengeBeanList);

			return mav;
		}

		@RequestMapping(value = "/admin/save-edited-hackathon", method = RequestMethod.POST)
		public ModelAndView editHackathonDetails(
				@ModelAttribute("hackathonBean") HackathonBean hackathonBean,
				@RequestParam("day") String day,
				@RequestParam("month") String month,
				@RequestParam("year") String year,
				@RequestParam("challengeId") ArrayList<String> challengeIdList,
				@RequestParam("hackImage") MultipartFile hackImage,
				@RequestParam("oldHackImage") String oldHackImage,
				@RequestParam("previousDate") String previousDate) {
						
			if (!hackImage.isEmpty()) {
				String fileName = hackathonBean.getName() + ".png";
				fileName = fileName.replaceAll(" ", "");
				fileName = fileName.replaceAll("//s", "");
				
				int index = oldHackImage.indexOf("/",1);
				
				String sub1 = oldHackImage.substring(0, index);
				
				String sub2 = oldHackImage.substring(index);
				
				String str = "C:/StartupMashup"+sub1+"/WebContent/WEB-INF"+sub2;
				
				File oldFile = new File(str);
				
				oldFile.delete();
				
				File file = new File(rb.getString("hackathonUpload"), fileName);
				try {
					byte[] bytes = null;
					BufferedOutputStream stream = null;
					bytes = hackImage.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				hackathonBean
						.setImage("/StartupMashup/resources/hackathonImageUpload/"
								+ fileName);

			} else {
				hackathonBean.setImage(oldHackImage);
			}

			if (day.equals("-1")) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd",
						Locale.ENGLISH);
				try {
					Date date = format.parse(previousDate);
					hackathonBean.setDate(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				String eventDate = day + "-" + month + "-" + year;
				try {
					Date modifiedDate = new SimpleDateFormat("dd-MM-yyyy")
							.parse(eventDate);
					hackathonBean.setDate(modifiedDate);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			hackathonService.editHackathonInfo(hackathonBean);

			if (!challengeIdList.get(0).equals("-1")) {
				hackathonChallengeMapService.updateMappingDetails(
						hackathonBean.getId(), challengeIdList);
			}

			ModelAndView mav = new ModelAndView("success");

			return mav;
		}

	// Company Methods

	@RequestMapping(value = "/admin/company-registration", method = RequestMethod.GET)
	public ModelAndView getCompany(HttpSession session) {
	
			mav = new ModelAndView("companyRegistrationForm");
			mav.addObject("companyBean", new CompanyBean());

			return mav;
	}



	@RequestMapping(value = "/admin/map-company-challenges-details", method = RequestMethod.POST)
	@ResponseBody
	public String mapCompanyChallengeDetails(
			@RequestParam("cId") int companyId,
			@RequestParam("hackId") int hackathonId,
			@RequestParam("listVal") String[] challengeId) {

		ArrayList<Integer> challengeIds = new ArrayList<Integer>();
		for (String s : challengeId) {
			if (s.equals("")) {
			} else
				challengeIds.add(Integer.parseInt(s));
		}
		companyChallengeMapService.saveMappingDetails(companyId, challengeIds);
		hackathonCompanyMapService.saveMappingDetails(companyId, hackathonId);

		return "true";
	}

	
	@RequestMapping(value = "/admin/save-company-details", method = RequestMethod.POST)
	public ModelAndView companyDetails(
			@ModelAttribute("companyBean") CompanyBean companyBean,
			@RequestParam("myLogo") MultipartFile logo) {

		
		String fileName = companyBean.getName() + "_logo"
				+ ".png";
		fileName = fileName.replaceAll("\\s", "");
		fileName = fileName.replaceAll(" ", "");
		File file = new File(rb.getString("companyLogoPath"), fileName);
		Path path = Paths.get(rb.getString("companyLogoPath"), fileName);
		if (!logo.isEmpty()) {
			try {
				byte[] bytes = null;
				BufferedOutputStream stream = null;
				bytes = logo.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			companyBean.setLogo("/StartupMashup/resources/companyLogoUpload/"
					+ fileName);

			int companyId = companyService.saveCompanyInfo(companyBean);

			mav = new ModelAndView("enterChallengeDetailsForCompany");
			
			mav.addObject("listOfHackathons",
					hackathonService.getHackathonList());
			
			mav.addObject("listOfChallenges", challengeService.getChallenges());
			
			mav.addObject("listOfHackathonChallengeMapBean",
					hackathonChallengeMapService.getHackathonChallengeMapList());
			
			mav.addObject("companyId", companyId);
		} else {
			mav = new ModelAndView("error");
			mav.addObject("msg",
					"Logo is not attached!");
		}

		return mav;
	}
	
	@RequestMapping(value = "/admin/list-companies")
	public ModelAndView getListOfCompany(HttpSession session) {
		
		List<HackathonCompanyMapBean> mapBeanList = hackathonCompanyMapService.getList();
		
		List<HackathonBean> hackathonBeanList = hackathonService.getHackathonList();

		
		mav = new ModelAndView("companyList");
		mav.addObject("listOfCompany", companyService.getCompanyList());
		mav.addObject("mapBeanList", mapBeanList);
		mav.addObject("hackathonBeanList", hackathonBeanList);
		
		return mav;
	}

	@RequestMapping(value = "/admin/edit-company-details", method = RequestMethod.POST)
	public ModelAndView editCompanyDetails(
			@RequestParam("companyId") int companyId) {
		
		CompanyBean companyBean = companyService.findCompanyById(companyId);
		
		mav = new ModelAndView("editCompanyDetails");
		mav.addObject("companyBean", companyBean);
		
		return mav;
	}

	@RequestMapping(value = "/admin/save-edited-company-details", method = RequestMethod.POST)
	public ModelAndView edittedCompanyDetails(HttpSession session,
			@ModelAttribute("companyBean") CompanyBean companyBean,
			@RequestParam("myLogo") MultipartFile logo,
			@RequestParam("oldLogo") String oldLogo) {
		
			
			
			if (!logo.isEmpty()) {
				String fileName = companyBean.getName() + "_logo" + ".png";
				fileName = fileName.replaceAll(" ", "");
				fileName = fileName.replaceAll("//s", "");
				int index = oldLogo.indexOf("/",1);
				
				String sub1 = oldLogo.substring(0, index);
				
				String sub2 = oldLogo.substring(index);
				
				String str = "C:/StartupMashup"+sub1+"/WebContent/WEB-INF"+sub2;
				
				File oldFile = new File(str);
				
				oldFile.delete();
				File file = new File(rb.getString("companyLogoPath"), fileName);
				try {
					byte[] bytes = null;
					BufferedOutputStream stream = null;
					bytes = logo.getBytes();
					stream = new BufferedOutputStream(
							new FileOutputStream(file));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				companyBean.setLogo("/StartupMashup/resources/companyImages/"
						+ fileName);

				companyService.updateCompanyInfo(companyBean);

			} else {
			
				companyBean.setLogo(oldLogo);
				companyService.updateCompanyInfo(companyBean);

			}

			ArrayList<CompanyChallengeMapBean> companyChallengeMapBeanList = companyChallengeMapService
					.findCompanyChallengeMapBeanByCompanyId(companyBean.getId());
			
			mav = new ModelAndView("enterEditedChallengeDetailsForCompany");
			
				mav.addObject("companyChallengeMapBean",
						companyChallengeMapBeanList);
			mav.addObject("companyHackathonMapBean", hackathonCompanyMapService.findHackathonCompanyMapBeanByCompanyId(companyBean.getId()));
			mav.addObject(
					"editPageMsg",
					"Select the new challenges for the company ,, if you want the same previous challenges just press submit button. ");
			mav.addObject("listOfHackathons",
					hackathonService.getHackathonList());
			mav.addObject("listOfChallenges", challengeService.getChallenges());
		
			mav.addObject("listOfHackathonChallengeMapBean",
					hackathonChallengeMapService.getHackathonChallengeMapList());
			mav.addObject("companyId", companyBean.getId());

			return mav;
	}

	
	ArrayList<Integer> challengeIds = new ArrayList<Integer>();
	ArrayList<Integer> hackathonIds = new ArrayList<Integer>();
	int companyId = 0;

	@RequestMapping(value = "/admin/map-edited-company-challenges-details", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView mapEditedCompanyChallengeDetails(
			@RequestParam("cId") int compId,
			@RequestParam("hackId") int hackathonId,
			@RequestParam("listVal") String[] challengeId,
			@RequestParam("result") String result) {
		String statement;

		if (result.equals("false")) {

			for (String s : challengeId) {
				if (s.equals("")) {
				} else
					challengeIds.add(Integer.parseInt(s));
			}
			companyId = compId;

			hackathonIds.add(hackathonId);
			statement = "true";
		} else {
			statement = "success";
			if (challengeIds.isEmpty()) {
			} else {
			
				
				companyChallengeMapService.updateMappingDetails(companyId,
						challengeIds);
				hackathonCompanyMapService.updateMappingDetails(companyId,
						hackathonIds);
				challengeIds = new ArrayList<Integer>();
				hackathonIds = new ArrayList<Integer>();
			}

		}

		mav = new ModelAndView(statement);

		return mav;
	}

	@RequestMapping(value = "/admin/saving-complete-details-for-company", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView saveCompleteDetailsForCompany(HttpSession session) {


			mav = new ModelAndView("success");

		return mav;
	}

	// Participant List

	
	@RequestMapping("/admin/list-users")
	public ModelAndView displayUsersList(HttpSession session) {

		
			mav = new ModelAndView("chooseVariousParticipants");

			mav.addObject("hackathonBeanList",
					hackathonService.getHackathonList());
			
			mav.addObject("challengeBeanList", challengeService.getChallenges());
			
			mav.addObject("hackathonChallengeMapBeanList",
					hackathonChallengeMapService.getHackathonChallengeMapList());

		return mav;
	}

	@RequestMapping(value = "/admin/participant-list-hackathon-challenge", method = RequestMethod.GET)
	public ModelAndView displayParticipantListHackathonChallenge(
			@RequestParam("hackathonId") int hackathonId,
			@RequestParam("challengeId") int challengeId) {

		List<ParticipantBean> participantBeanList = new ArrayList<ParticipantBean>();

		if (challengeId == -1) {
			participantBeanList = participantService
					.getParticipantList(hackathonId);
		} else {
			participantBeanList = participantService.getParticipantList(
					hackathonId, challengeId);
		}

		mav = new ModelAndView("participantList");
		mav.addObject("participantBeanList", participantBeanList);
		
		return mav;
	}

	@RequestMapping("/admin/participant-list")
	public ModelAndView displayParticipantList() {

		List<ParticipantBean> participantBeanList = participantService
				.getParticipantList();

		mav = new ModelAndView("participantList");
		mav.addObject("participantBeanList", participantBeanList);

		return mav;
	}
	
	@RequestMapping("/admin/list-referrals")
	public ModelAndView displayReferDetailsList(HttpSession session){
		
		
		mav = new ModelAndView("showReferDetails");
		
		List<ReferDetailsBean> referDetailsBeanList = referDetailsService.getReferDetailsList(); 
		
		mav.addObject("referDetailsBeanList", referDetailsBeanList);
		
		return mav;
	}
	
	//Subscriber methods 
		@Autowired SubscriberService subscriberService;
		@RequestMapping(value="/admin/list-subscriber")
		public ModelAndView getListOfSubscribers() {
			
			
			mav=new ModelAndView("subscribersList");
			mav.addObject("listOfsubscribers",subscriberService.getSubscribersList());
			
			return mav;
		}
		


}
