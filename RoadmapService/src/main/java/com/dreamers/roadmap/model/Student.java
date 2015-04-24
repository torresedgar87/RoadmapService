package com.dreamers.roadmap.model;

public class Student extends User
{
	private String schoolName;
	
	public Student()
	{
		
	}
	
	public void setSchoolName(String schoolName)
	{
		this.schoolName = schoolName;
	}
	
	public String getSchoolName()
	{
		return schoolName;
	}
}
