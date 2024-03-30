package org.example.spring.controller;

import org.example.spring.repository.RegistrationRepository;


public class DeleteEmployeeController {


	public boolean delete(RegistrationRepository registrationRepository, String id, String name, String date)
	{
		registrationRepository.updateStatusAndEndDate(id,name,"0",date);


		//DeleteEmployeeModel.delete(id, name,date);
		
		return true;
	}
	
}
