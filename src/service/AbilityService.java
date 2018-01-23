package service;

import java.util.List;

import dao.AbilityDao;
import dao.AbilityDaoImpl;
import dto.AbilityBbs;
import dto.Person;

public class AbilityService implements AbilityServiceImpl {
	AbilityDaoImpl abilityDao = new AbilityDao();

	public List<AbilityBbs> allAbilityList() {
		return abilityDao.allAbilityList();
	}

	public List<AbilityBbs> getAllAbilityList() {
		return abilityDao.getAllAbilityList();
	}
	@Override
	public List<AbilityBbs> searchList(String searchWord) {
		// TODO Auto-generated method stub
		return abilityDao.searchList(searchWord);
	}
	public boolean addAbility(AbilityBbs abilityDto) {
		return abilityDao.addAbility(abilityDto) ? true : false;
	}
	
	public List<AbilityBbs> getAbilityBbsByUserId(String user_id){
		return abilityDao.getAbilityBbsByUserId(user_id);
	}
	
	public List<AbilityBbs> SelectAbilityCategories(int category_id) {
		return abilityDao.SelectAbilityCategories(category_id);
	}


	@Override
	public boolean DeleteAbilityBbsByAdmin(AbilityBbs ability) {
		// TODO Auto-generated method stub
		return abilityDao.DeleteAbilityBbsByAdmin(ability);
	}

	@Override
	public boolean CompleteAbilityBbsByAdmin(AbilityBbs ability) {
		// TODO Auto-generated method stub
		return abilityDao.CompleteAbilityBbsByAdmin(ability);
	}

	@Override
	public boolean ContinueAbilityBbsByAdmin(AbilityBbs ability) {
		// TODO Auto-generated method stub
		return abilityDao.ContinueAbilityBbsByAdmin(ability);
	}

	// 관리자 전용 
	@Override
	public List<AbilityBbs> AdminSearch(String search) {
		// TODO Auto-generated method stub
		return abilityDao.AdminSearch(search);
	}
}
