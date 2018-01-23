package service;

import java.util.List;

import dto.AbilityBbs;
import dto.ItemBbs;

public interface ItemBbsServiceImpl {
	public List<ItemBbs> getAllItemBbs();
	public List<ItemBbs> allItemList();
	public List<ItemBbs> searchList(String searchWord);
	public List<ItemBbs> getItemBbsByUserId(String user_id);
	public boolean DeleteItemBbsByAdmin(ItemBbs item);
	public boolean CompleteItemBbsByAdmin(ItemBbs item);
	public boolean ContinueItemBbsByAdmin(ItemBbs item);
	public List<ItemBbs> SelectItemCategories(int Category_seq);
	public List<ItemBbs> AdminSearch(String search);
}
