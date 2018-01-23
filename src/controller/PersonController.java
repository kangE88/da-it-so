package controller;

import javax.swing.JOptionPane;

import delegator.Delegator;
import dto.Person;
import service.PersonService;
import view.Login;
import view.SignUp;

public class PersonController {
	PersonService personService = new PersonService();
	
	public void Login() {
		new Login();
	}
	
	
	public void SignUp() {
		new SignUp();
	}
	
	public void Logout() {
		Delegator delegator = Delegator.getInstance();
		delegator.setCurrent_user(null);
		
		JOptionPane.showMessageDialog(null, "Sign out successfully");
		
		delegator.mainController.Main();
	}

	/*
	 * 2018-01-04 init by Parker
	 * if person is not null then move to list page.
	 */
	public Person signIn(String id, char[] pwd) {
		Person person = personService.getPerson(id, pwd);
		
		if(person!=null) {
			Delegator delegator = Delegator.getInstance();
			delegator.setCurrent_user(person);
		}
		
		return person;
	}
	
	public Person insert(Person person) {
		return personService.insert(person);
	}
	
	public boolean checkId(String id) {
		return personService.checkId(id);
	}
	
	
}
