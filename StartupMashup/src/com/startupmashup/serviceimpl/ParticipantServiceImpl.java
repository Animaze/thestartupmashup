package com.startupmashup.serviceimpl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






import com.startupmashup.bean.ParticipantBean;
import com.startupmashup.dao.ParticipantDao;
import com.startupmashup.service.ParticipantService;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	ParticipantDao participantDao;

	@Override
	public void saveParticipantInfo(ParticipantBean participantBean) {

		participantDao.saveParticipantInfo(participantBean);
	}

	@Override
	public ParticipantBean authoriseUser(String username, String password) {
		return participantDao.authoriseUser(username, password);
	}

	/*@Override
	public String uploadResume(MultipartFile file, ParticipantBean participantBean) {
	
String fileName = participantBean.getFirstName()+".docx";
		
		String result=null;
        if (!file.isEmpty()) {
            try {
            	File myFile = new File(fileName);
            	
            	participantBean.setResume(myFile.getAbsolutePath());
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(myFile));
                stream.write(bytes);
                stream.close();
                result= "success";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        	result= "participantRegistrationFormD";
        }
        return result;
	}*/
	public List<String> getUserNames(){
		List<String> userNamesList = participantDao.getUserNames();
		
		return userNamesList;
	}

	@Override
	public ArrayList<ParticipantBean> getParticipantList() {
		return participantDao.getParticipantList();
	}

	@Override
	public ParticipantBean getDetailsById(int userId) {
		
		return participantDao.getDetailsById(userId);
	}

	@Override
	public void saveEditedProfile(ParticipantBean participantBean,int userId) {
		
		participantDao.saveEditedProfile(participantBean,userId);
		
	}

	@Override
	public List<ParticipantBean> getParticipantList(int hackathonId) {
		
		return participantDao.getParticipantList(hackathonId);
		
	}

	@Override
	public List<ParticipantBean> getParticipantList(int hackathonId,
			int challengeId) {
		
		return participantDao.getParticipantList(hackathonId, challengeId);
		
	}

	@Override
	public void updateParticipant(int participantId,ParticipantBean participantBean) {
		participantDao.updateParticipant(participantId,participantBean);
	}

	@Override
	public ParticipantBean getDetailsByUsername(String username) {
		return participantDao.getDetailsByUsername(username);
	}

	@Override
	public ParticipantBean getParticipantBeanByToken(String token) {
		return participantDao.getParticipantBeanByToken(token);
	}

	@Override
	public void updatePassword(String participantId, String newPassword) {
		participantDao.updatePassword(participantId,newPassword);
	}

	
	
}
