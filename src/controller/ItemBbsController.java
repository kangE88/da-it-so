package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.Category;
import dto.ItemBbs;
import dto.Person;
import service.CategoryService;
import service.CategoryServiceImpl;
import service.ItemBbsService;
import service.ItemBbsServiceImpl;
import view.ImageDetail;
import view.ItemDetail;
import view.ItemMain;
import view.ItemWrite;
import view.Main;

public class ItemBbsController {
	ItemBbsServiceImpl itemService = new ItemBbsService();
	CategoryServiceImpl categoryService = new CategoryService();

	public void main() {
		new Main();
	}

	public void allItemList() {
		List<ItemBbs> itemdto = itemService.allItemList();
		List<Category> categoryList = categoryService.getAllCategories(0);
		if (itemdto == null) {
			itemdto = new ArrayList<>();
		}

		new ItemMain(itemdto, categoryList);
	}

	public void itemWrite() {
		List<Category> categoryList = categoryService.getAllCategories(0);
		new ItemWrite(categoryList);
	}

	public void itemDetail(ItemBbs itemDto) {
		List<Category> categoryList = categoryService.getAllCategories(0);
		Category itemCategory = categoryService.getCategory(itemDto.getCategory_id());
		if(itemDto.getSeq()==0) {
			itemDto = itemService.getItemBbsByTitleAndContent(itemDto);
		}
		
		new ItemDetail(itemDto, categoryList, itemCategory);
	}

	public void SelectItemCategories(int category_id) {
		List<ItemBbs> itemList = itemService.SelectItemCategories(category_id);
		List<Category> categoryList = categoryService.getAllCategories(0);
		new ItemMain(itemList, categoryList);
	}

	public void searchList(String searchWord) {
		List<ItemBbs> itemList = itemService.searchList(searchWord);
		List<Category> categoryList = categoryService.getAllCategories(0);

		System.out.println("controller: ");
		itemList.stream().forEach(System.out::println);
		if (itemList.size() != 0) {
			new ItemMain(itemList, categoryList);
		} else {
			JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.");
			itemList = itemService.allItemList();
			new ItemMain(itemList, categoryList);
		}
	}

	public boolean setCompleteItemBbs(ItemBbs itemDto) {
		// TODO Auto-generated method stub
		return itemService.CompleteItemBbs(itemDto);
	}

	public boolean setDeleteItemBbs(ItemBbs itemDto) {
		// TODO Auto-generated method stub
		return itemService.DeleteItemBbs(itemDto);
	}

	public void ImageView(String img) {
		new ImageDetail(img);
	}

	public boolean insert(ItemBbs itemDto) {
		// TODO Auto-generated method stub
		boolean result = itemService.addItem(itemDto);
		
		if(result) {
			return true;
		} else {
			return false;
		}
		
	}

	public ItemBbs getAbilityByTilteAndContent(ItemBbs itemDto) {
		// TODO Auto-generated method stub
		return itemService.getItemBbsByTitleAndContent(itemDto);
	}
}
