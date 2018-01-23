package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.AbilityBbs;
import dto.Category;
import dto.Person;
import service.AbilityService;
import service.AbilityServiceImpl;
import service.CategoryService;
import view.AbilityDetail;
import view.AbilityMain;
import view.AbilityWrite;
import view.Main;

public class AbilityBbsController {
   AbilityServiceImpl abilityService = new AbilityService();
   CategoryService categoryService = new CategoryService();

   public void main() {
	   new Main();
   }

   public void allAbilityList() {
	   List<AbilityBbs> abilityDto = abilityService.allAbilityList();
	   List<Category> categoryList = categoryService.getAllCategories(1);
	   if(abilityDto.isEmpty()) {
		  abilityDto = new ArrayList<>();
		    
	   }
	  new AbilityMain(abilityDto, categoryList);
   }

   public void AbilityWrite(Person personDto) {
	   //getAllCategories == 0 Ability
	   //getAllCategories == 1 item
	   List<Category> categoryList = categoryService.getAllCategories(0);
	   new AbilityWrite(categoryList);
   }
   public void SelectAbilityCategories(int category_id){
	   List<AbilityBbs> abilityList = abilityService.SelectAbilityCategories(category_id);
	   List<Category> categoryList = categoryService.getAllCategories(1);
	   new AbilityMain(abilityList, categoryList);
   }

   public void AbilityDetail(AbilityBbs abilityDto) {
	   List<Category> categoryList = categoryService.getAllCategories(1);
	   new AbilityDetail(abilityDto, categoryList);
   }

   public void searchList(String searchWord){
	   List<AbilityBbs> searchList = abilityService.searchList(searchWord);
	   List<Category> categoryList = categoryService.getAllCategories(1);
	   if(searchList.size() == 0) {
		   JOptionPane.showMessageDialog(null, "검색 결과가 없습니다. ");
		   searchList = abilityService.allAbilityList();
		   new AbilityMain(searchList, categoryList);
	   }else {
		   new AbilityMain(searchList, categoryList);		   
	   }
   }
}
