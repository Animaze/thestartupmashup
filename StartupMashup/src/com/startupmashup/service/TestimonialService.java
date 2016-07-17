package com.startupmashup.service;

import java.util.ArrayList;
import java.util.List;

import com.startupmashup.bean.TestimonialBean;

public interface TestimonialService {

	public void saveTestimonialInfo(TestimonialBean testimonialBean);

	public ArrayList<TestimonialBean> getTestimonialList();

	public void updateTestimonials(TestimonialBean testimonialBean);

	public TestimonialBean getTestimonialById(int testimonialId);


	
}
