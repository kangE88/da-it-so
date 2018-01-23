package controller;

import java.util.List;

import javax.swing.JOptionPane;

import admin.AdminAbilityDetail;
import admin.AdminAbilityList;
import admin.AdminItemDetail;
import admin.AdminItemList;
import admin.AdminMain;
import admin.AdminUserDetail;
import admin.AdminUserList;
import dto.AbilityBbs;
import dto.ItemBbs;
import dto.Person;
import service.AbilityService;
import service.AbilityServiceImpl;
import service.CategoryService;
import service.CategoryServiceImpl;
import service.ChatService;
import service.ChatServiceImpl;
import service.ItemBbsService;
import service.ItemBbsServiceImpl;
import service.PersonService;
import service.PersonServiceImpl;
import service.RoomService;
import service.RoomServiceImpl;

public class AdminController {
	AbilityServiceImpl abilityService = new AbilityService();
	CategoryServiceImpl categoryService = new CategoryService();
	ChatServiceImpl chatService = new ChatService();
	ItemBbsServiceImpl itemBbsService = new ItemBbsService();
	PersonServiceImpl personService = new PersonService();
	RoomServiceImpl roomService = new RoomService();
	
	public void AdminMain() {
		new AdminMain();
	}
	
	public void AdminItemDetail(ItemBbs itemBbs) {
		Person person = personService.getPersonById(itemBbs.getUser_id());
		new AdminItemDetail(itemBbs, person);
	}
	
	public void ItemList() {
		List<ItemBbs> itemList = itemBbsService.getAllItemBbs();
		new AdminItemList(itemList);
	}
	
	public void AbilityList() {
		List<AbilityBbs> abilityList = abilityService.getAllAbilityList();
		new AdminAbilityList(abilityList);
	}
	
	public void AdminAbilityDetail(AbilityBbs abilityBbs) {
		Person person = personService.getPersonById(abilityBbs.getUser_id());
		new AdminAbilityDetail(abilityBbs, person);
	}
	
	public void UserList() {
		List<Person> userList = personService.getAllPerson();
		new AdminUserList(userList);
	}
	
	public void AdminUserDetail(Person person) {
		List<ItemBbs> itemList = itemBbsService.getItemBbsByUserId(person.getId());
		List<AbilityBbs> abilityList = abilityService.getAbilityBbsByUserId(person.getId());
		
		new AdminUserDetail(person, itemList, abilityList);
	}
	
	public void DeleteItemBbsByAdmin(ItemBbs item) {
		boolean result = itemBbsService.DeleteItemBbsByAdmin(item);
		
		if(result) {
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
			List<ItemBbs> itemList = itemBbsService.getAllItemBbs();
			new AdminItemList(itemList);
		}else {
			JOptionPane.showMessageDialog(null, "삭제할 수 없습니다.");
			Person person = personService.getPersonById(item.getUser_id());
			new AdminItemDetail(item, person);
		}
	}
	
	public void CompleteItemBbsByAdmin(ItemBbs item) {
		boolean result = itemBbsService.CompleteItemBbsByAdmin(item);
		
		if(result) {
			JOptionPane.showMessageDialog(null, "완료상태로 변경 되었습니다.");
			List<ItemBbs> itemList = itemBbsService.getAllItemBbs();
			new AdminItemList(itemList);
		}else {
			JOptionPane.showMessageDialog(null, "변경할 수 없습니다.");
			Person person = personService.getPersonById(item.getUser_id());
			new AdminItemDetail(item, person);
		}
	}
	
	public void ContinueItemBbsByAdmin(ItemBbs item) {
		boolean result = itemBbsService.ContinueItemBbsByAdmin(item);
		
		if(result) {
			JOptionPane.showMessageDialog(null, "진행상태로 변경 되었습니다.");
			List<ItemBbs> itemList = itemBbsService.getAllItemBbs();
			new AdminItemList(itemList);
		}else {
			JOptionPane.showMessageDialog(null, "변경할 수 없습니다.");
			Person person = personService.getPersonById(item.getUser_id());
			new AdminItemDetail(item, person);
		}
	}
	
	public void DeleteAbilityBbsByAdmin(AbilityBbs ability) {
		boolean result = abilityService.DeleteAbilityBbsByAdmin(ability);
		
		if(result) {
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
			List<ItemBbs> itemList = itemBbsService.getAllItemBbs();
			new AdminItemList(itemList);
		}else {
			JOptionPane.showMessageDialog(null, "삭제할 수 없습니다.");
			Person person = personService.getPersonById(ability.getUser_id());
			new AdminAbilityDetail(ability, person);
		}
	}
	
	public void CompleteAbilityBbsByAdmin(AbilityBbs ability) {
		boolean result = abilityService.CompleteAbilityBbsByAdmin(ability);
		
		if(result) {
			JOptionPane.showMessageDialog(null, "완료상태로 변경 되었습니다.");
			List<ItemBbs> itemList = itemBbsService.getAllItemBbs();
			new AdminItemList(itemList);
		}else {
			JOptionPane.showMessageDialog(null, "변경할 수 없습니다.");
			Person person = personService.getPersonById(ability.getUser_id());
			new AdminAbilityDetail(ability, person);
		}
	}
	
	public void ContinueAbilityBbsByAdmin(AbilityBbs ability) {
		boolean result = abilityService.ContinueAbilityBbsByAdmin(ability);
		
		if(result) {
			JOptionPane.showMessageDialog(null, "진행상태로 변경 되었습니다.");
			List<ItemBbs> itemList = itemBbsService.getAllItemBbs();
			new AdminItemList(itemList);
		}else {
			JOptionPane.showMessageDialog(null, "변경할 수 없습니다.");
			Person person = personService.getPersonById(ability.getUser_id());
			new AdminAbilityDetail(ability, person);
		}
	}

	public void SerarchAbilityList(String search) {
		// TODO Auto-generated method stub
		List<AbilityBbs> abilityList = abilityService.AdminSearch(search);
		
		if(abilityList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.");
			abilityList = abilityService.getAllAbilityList();
		}
		new AdminAbilityList(abilityList);
	}

	public void SerarchItemList(String search) {
		// TODO Auto-generated method stub
		List<ItemBbs> itemList = itemBbsService.AdminSearch(search);
		
		if(itemList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.");
			itemList = itemBbsService.getAllItemBbs();
		}
		new AdminItemList(itemList);
		
	}

	public void SerarchUserList(String search) {
		// TODO Auto-generated method stub
		List<Person> personList = personService.AdminSearch(search);
		
		if(personList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.");
			personList = personService.getAllPerson();
		}
		new AdminUserList(personList);
	}
}
