package service;

import java.util.List;

import dto.Person;

public interface PersonServiceImpl {
	public Person getPerson(String id, char[] pwd);
	public Person getPersonById(String id);
	public boolean checkPerson(String id, char[] pwd);
	public Person insert(Person person);
	public boolean checkId(String id);
	public List<Person> getAllPerson();
	public List<Person> AdminSearch(String search);
}
