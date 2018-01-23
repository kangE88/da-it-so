package delegator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.AbilityBbsController;
import controller.AdminController;
import controller.CategoryController;
import controller.ChatController;
import controller.ItemBbsController;
import controller.MainController;
import controller.PersonController;
import controller.RoomController;
import db.DBConnection;
import db.MySqlConnection;
import db.OracleConnection;
import dto.Person;

public class Delegator {
	private static Delegator instance = null;
	private Person current_user = null;

	public AbilityBbsController abilityBbsController;
	public CategoryController categoryController;
	public ChatController chatController;
	public ItemBbsController itemBbsController;
	public PersonController personController;
	public RoomController roomController;
	public MainController mainController;
	public AdminController adminController;

	public DBConnection DBConnector;

	private String imgUrl = "c:\\image\\";

	private Delegator() {

		abilityBbsController = new AbilityBbsController();
		categoryController = new CategoryController();
		chatController = new ChatController();
		itemBbsController = new ItemBbsController();
		personController = new PersonController();
		roomController = new RoomController();
		mainController = new MainController();
		adminController = new AdminController();

		DBConnector = new OracleConnection();
//		DBConnector = new MySqlConnection();
	}

	public static Delegator getInstance() {
		if (instance == null) {
			instance = new Delegator();
		}
		return instance;
	}

	public Person getCurrent_user() {
		return current_user;
	}

	public void setCurrent_user(Person current_user) {
		this.current_user = current_user;
	}

	public BufferedImage getImage(String filename) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imgUrl + filename));
		} catch (IOException e) {
		}

		return img;
	}

}
