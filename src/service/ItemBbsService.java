package service;

import java.util.List;

import dao.ItemBbsDao;
import dao.ItemBbsDaoImpl;
import dto.AbilityBbs;
import dto.ItemBbs;
import dto.Person;

public class ItemBbsService implements ItemBbsServiceImpl{
	ItemBbsDaoImpl itemDao = new ItemBbsDao();

	public List<ItemBbs> allItemList() {
		return itemDao.allItemList();
	}

	public boolean addItem(ItemBbs itemDto) {
		return itemDao.addItem(itemDto) ? true : false;
	}

	public List<ItemBbs> getAllItemBbs(){
		return itemDao.getAllItemBbs();
	}

	@Override
	public List<ItemBbs> searchList(String searchWord) {
		return itemDao.searchList(searchWord);
	}
	public List<ItemBbs> getItemBbsByUserId(String user_id) {
		// TODO Auto-generated method stub
		return itemDao.getItemBbsByUserId(user_id);
	}

	public boolean DeleteItemBbsByAdmin(ItemBbs item) {
		return itemDao.DeleteItemBbsByAdmin(item);
	}

	public boolean CompleteItemBbsByAdmin(ItemBbs item) {
		return itemDao.CompleteItemBbsByAdmin(item);
	}
	public boolean ContinueItemBbsByAdmin(ItemBbs item) {
		return itemDao.ContinueItemBbsByAdmin(item);
	}
	
	public List<ItemBbs> SelectItemCategories(int category_id){
		return itemDao.SelectItemCategories(category_id);
	}

	@Override
	public List<ItemBbs> AdminSearch(String search) {
		// TODO Auto-generated method stub
		return itemDao.AdminSearch(search);
	}

}
