package dao;

import java.util.List;

import dto.Person;

public interface PersonDaoImpl {

	Person getPerson(String id, char[] pwd);

	Person getPersonById(String id);

	boolean insert(Person person);

	boolean checkId(String id);

	List<Person> getAllPerson();

	List<Person> AdminSearch(String search);

}
