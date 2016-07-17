package com.startupmashup.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupmashup.bean.TestimonialBean;
import com.startupmashup.dao.TestimonialDao;
import com.startupmashup.service.TestimonialService;

@Service
public class TestimonialServiceImpl implements TestimonialService {

	@Autowired
	TestimonialDao testimonialDao;
	
	public void saveTestimonialInfo(TestimonialBean testimonialBean){
		testimonialDao.saveTestimonialInfo(testimonialBean);
	}

	@Override
	public ArrayList<TestimonialBean> getTestimonialList() {
		
		return testimonialDao.getTestimonialList();
	}

	@Override
	public void updateTestimonials(TestimonialBean testimonialBean) {
		testimonialDao.updateTestimonials(testimonialBean);
	}

	@Override
	public TestimonialBean getTestimonialById(int testimonialId) {
	return testimonialDao.getTestimonialById(testimonialId);
	}

	
	
}
