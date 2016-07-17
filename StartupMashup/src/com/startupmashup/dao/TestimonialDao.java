package com.startupmashup.dao;

import java.util.ArrayList;

import com.startupmashup.bean.TestimonialBean;

public interface TestimonialDao {

	public void saveTestimonialInfo(TestimonialBean testimonialBean);

	public ArrayList<TestimonialBean> getTestimonialList();

	public void updateTestimonials(TestimonialBean testimonialBean);

	public TestimonialBean getTestimonialById(int testimonialId);
	
}
