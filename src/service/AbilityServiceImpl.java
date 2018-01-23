package service;

import java.util.List;

import dto.AbilityBbs;
import dto.ItemBbs;
import dto.Person;

public interface AbilityServiceImpl {
	public List<AbilityBbs> allAbilityList();
	public List<AbilityBbs> searchList(String searchWord);
	public List<AbilityBbs> getAllAbilityList();
	public boolean addAbility(AbilityBbs abilityDto);
	public List<AbilityBbs> getAbilityBbsByUserId(String user_id);
	public List<AbilityBbs> SelectAbilityCategories(int category_id);
	
	public boolean DeleteAbilityBbsByAdmin(AbilityBbs ability);
	public boolean CompleteAbilityBbsByAdmin(AbilityBbs ability);
	public boolean ContinueAbilityBbsByAdmin(AbilityBbs ability);
	public List<AbilityBbs> AdminSearch(String search);
}
