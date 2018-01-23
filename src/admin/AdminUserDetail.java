package admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import delegator.Delegator;
import dto.AbilityBbs;
import dto.ItemBbs;
import dto.Person;

public class AdminUserDetail extends JFrame implements ActionListener {
	private JPanel headerPn, headerLogo, sidePn, logoPn, detailPn;
	private JButton loginBtn, logoutBtn, signupBtn, searchBtn, talkBtn, chatBtn;
	private JButton itemListBtn, userListBtn, abilityListBtn;
	private JTextField searchTextF;

//	String iconImgUrl = "/Users/parker/Desktop/img/icon/";
	String iconImgUrl = "E:\\icon/";
	
	int itemHeight = 60;
	
	Person person = null;
	List<ItemBbs> itemList = null;
	List<AbilityBbs> abilityList = null;
	
	public AdminUserDetail(Person person, List<ItemBbs> itemList, List<AbilityBbs> abilityList) {
		
		
		this.person = person;
		this.itemList = itemList;
		this.abilityList = abilityList;
		
		Container contentPane = getContentPane();
		
		contentPane.setBounds(0, 0, 1680, 900);
		contentPane.setBackground(Color.white);

		// Header
		Color commonRedColor = new Color(218, 0, 0);
		headerPn = new JPanel();
		headerPn.setBackground(commonRedColor);
		headerPn.setSize(1680, 60);
		headerPn.setLayout(null);

		// detailPn
		detailPn = new JPanel();
		detailPn.setBackground(Color.white);
		detailPn.setLocation(0, 0);
		detailPn.setPreferredSize(new Dimension(1005, 1500));
		detailPn.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(detailPn);
		scrollPane.setBounds(400, 60, 1100, 900);
		scrollPane.setBackground(Color.black);

		// headerlogo
		ImageIcon headerimage = new ImageIcon(iconImgUrl + "headerlogo.png");
		headerLogo = new JPanel() {
			// 사이즈맞게 배경삽임
			public void paintComponent(Graphics g) {
				g.drawImage(headerimage.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		headerLogo.setBounds(15, 25, 71, 15);
		headerLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				delegator.mainController.Main();
			}
		});
		headerPn.add(headerLogo);
		
		Delegator delegator = Delegator.getInstance();
		
		if(delegator.getCurrent_user()==null) {
			// loginBtn
			loginBtn = new JButton("로그인");
			loginBtn.setBounds(1190, 20, 100, 30);
			loginBtn.setOpaque(false); // 투명하게
			loginBtn.setBorderPainted(false);// 외곽선 없애줌
			loginBtn.setFont(new Font("로그인", Font.BOLD, 12));
			loginBtn.setBackground(commonRedColor);
			loginBtn.setForeground(Color.white);
			loginBtn.addActionListener(this);
			headerPn.add(loginBtn);
			
			// SignBtn
			signupBtn = new JButton("회원가입");
			signupBtn.setBounds(1130, 20, 100, 30);
			signupBtn.setOpaque(false); // 투명하게
			signupBtn.setBorderPainted(false);// 외곽선 없애줌
			signupBtn.setFont(new Font("회원가입", Font.BOLD, 12));
			signupBtn.setBackground(commonRedColor);
			signupBtn.setForeground(Color.white);
			signupBtn.addActionListener(this);
			headerPn.add(signupBtn);			
		}else {
			// logoutBtn
			logoutBtn = new JButton("로그아웃");
			logoutBtn.setBounds(1250, 20, 100, 30);
			logoutBtn.setOpaque(false); // 투명하게
			logoutBtn.setBorderPainted(false);// 외곽선 없애줌
			logoutBtn.setFont(new Font("로그아웃", Font.BOLD, 12));
			logoutBtn.setBackground(commonRedColor);
			logoutBtn.setForeground(Color.white);
			headerPn.add(logoutBtn);			
		}

		// 1050
		// sidePn
		Color sideC = new Color(250, 250, 250);
		sidePn = new JPanel();
		sidePn.setBounds(0, 60, 400, 1000);
		sidePn.setLayout(null);
		sidePn.setBackground(sideC);

		ImageIcon image = new ImageIcon(iconImgUrl + "logo.png");
		logoPn = new JPanel() {
			// 사이즈맞게 배경삽임
			public void paintComponent(Graphics g) {
				g.drawImage(image.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		logoPn.setBounds(40, 30, 300, 66);

		sidePn.add(logoPn);

		// SearchText
		searchTextF = new JTextField("검색어");
		searchTextF.setBounds(40, 160, 260, 40);
		searchTextF.setBorder(new LineBorder(commonRedColor, 5));
		sidePn.add(searchTextF);

		// searchBtn
		searchBtn = new JButton(new ImageIcon(iconImgUrl + "search.png"));
		searchBtn.setBounds(300, 160, 40, 40);
		searchBtn.setOpaque(false); // 투명하게
		searchBtn.addActionListener((ActionEvent e)->{
			delegator.adminController.SerarchUserList(searchTextF.getText());
			dispose();
		});
		// searchBtn.setBackground();

		searchBtn.setContentAreaFilled(false);// 내용영역 채우기x

		sidePn.add(searchBtn);
		
		// btnPanel
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(4, 1, 10, 10));
		btnPanel.setBounds(25, 290, 350, 350);
		btnPanel.setBackground(Color.WHITE);

		itemListBtn = new JButton("모든 상품글 보기 ");
		itemListBtn.setBorder(new LineBorder(commonRedColor, 2));
		itemListBtn.addActionListener(this);
		btnPanel.add(itemListBtn);

		abilityListBtn = new JButton("모든 인력글 보기 ");
		abilityListBtn.setBorder(new LineBorder(commonRedColor, 2));
		abilityListBtn.addActionListener(this);
		btnPanel.add(abilityListBtn);

		userListBtn = new JButton("모든 유저 정보 보기 ");
		userListBtn.setBorder(new LineBorder(commonRedColor, 2));
		userListBtn.addActionListener(this);
		btnPanel.add(userListBtn);
		
		chatBtn = new JButton("관리자 채팅 열기 ");
		chatBtn.setBorder(new LineBorder(commonRedColor, 2));
		chatBtn.addActionListener(this);
		btnPanel.add(chatBtn);

		sidePn.add(btnPanel);

		// main view
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(5,  1));
		main.setBounds(400, 60, 1280, 330);
		
		JPanel userIDPanel = new JPanel();
		userIDPanel.setLayout(null);
		
		JLabel uesrID = new JLabel();
		uesrID.setText("ID");
		uesrID.setBounds(100, 50, 200, 20);
		userIDPanel.add(uesrID);
		
		JLabel userID2 = new JLabel();
		userID2.setText(person.getId());
		userID2.setBounds(300, 50, 200, 20);
		userIDPanel.add(userID2);
		
		main.add(userIDPanel);
		
		
		JPanel userPhonePanel = new JPanel();
		userPhonePanel.setLayout(null);
		
		JLabel uesrPhone = new JLabel();
		uesrPhone.setText("Phone");
		uesrPhone.setBounds(100, 50, 200, 20);
		userPhonePanel.add(uesrPhone);
		
		JLabel uesrPhone2 = new JLabel();
		uesrPhone2.setText(person.getPhone());
		uesrPhone2.setBounds(300, 50, 200, 20);
		userPhonePanel.add(uesrPhone2);
		
		main.add(userPhonePanel);
		

		
		JPanel userNickPanel = new JPanel();
		userNickPanel.setLayout(null);
		
		JLabel userNick = new JLabel();
		userNick.setText("nickname");
		userNick.setBounds(100, 50, 200, 20);
		userNickPanel.add(userNick);
		
		JLabel userNick2 = new JLabel();
		userNick2.setText(person.getNick());
		userNick2.setBounds(300, 50, 200, 20);
		userNickPanel.add(userNick2);
		
		main.add(userNickPanel);
		


		
		JPanel userCreatedPanel = new JPanel();
		userCreatedPanel.setLayout(null);
		
		JLabel userCreated = new JLabel();
		userCreated.setText("Join date");
		userCreated.setBounds(100, 50, 200, 20);
		userCreatedPanel.add(userCreated);
		
		JLabel userCreated2 = new JLabel();
		userCreated2.setText(person.getCreated_at());
		userCreated2.setBounds(300, 50, 200, 20);
		userCreatedPanel.add(userCreated2);
		
		main.add(userCreatedPanel);
		
		contentPane.add(main);
		
		
		String columnNames[] = { "제목 ", "생성일 ", "상태" };
		Object rowData[][] = new Object[abilityList.size()][columnNames.length];		
		
		for(int i=0; i<abilityList.size(); i++) {
			rowData[i][0] = abilityList.get(i).getTitle();
			rowData[i][1] = abilityList.get(i).getCreated_at();
			if (abilityList.get(i).getState() == 0) {
				rowData[i][2] = "게시중.";				
			} else if (abilityList.get(i).getState() == 1) {
				rowData[i][2] = "완료됨.";				
			}  else if (abilityList.get(i).getState() == 2) {
				rowData[i][2] = "삭됨.";				
			}  else if (abilityList.get(i).getState() == 3) {
				rowData[i][2] = "관리자에 의해 삭제됨.";				
			} 
		}
		
		JTable atable = new JTable(rowData, columnNames);
		atable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = atable.getSelectedRow();

				Delegator delegator = Delegator.getInstance();
				delegator.adminController.AdminAbilityDetail(abilityList.get(index));
				dispose();
			}
		});
		
		JLabel alabel = new JLabel();
		alabel.setText("인력글 작성 현황");
		alabel.setBounds(400, 390, 475, 20);
		alabel.setFont(new Font("font", Font.BOLD, 17));
		alabel.setBackground(commonRedColor);
		alabel.setForeground(Color.white);
		contentPane.add(alabel);
		
		JScrollPane aScrPane = new JScrollPane(atable);
		aScrPane.setBounds(400, 410, 475, 690);
		contentPane.add(aScrPane);

		
		
		Object irowData[][] = new Object[itemList.size()][columnNames.length];		
		
		for(int i=0; i<itemList.size(); i++) {
			irowData[i][0] = itemList.get(i).getTitle();
			irowData[i][1] = itemList.get(i).getCreated_at();
			if (itemList.get(i).getState() == 0) {
				irowData[i][2] = "게시중.";				
			} else if (itemList.get(i).getState() == 1) {
				irowData[i][2] = "완료됨.";				
			}  else if (itemList.get(i).getState() == 2) {
				irowData[i][2] = "삭됨.";				
			}  else if (itemList.get(i).getState() == 3) {
				irowData[i][2] = "관리자에 의해 삭제됨.";				
			} 
		}
		
		JTable itable = new JTable(irowData, columnNames);
		itable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = itable.getSelectedRow();
				
				Delegator delegator = Delegator.getInstance();
				delegator.adminController.AdminItemDetail(itemList.get(index));
			}
		});
		
		JLabel ilabel = new JLabel();
		ilabel.setText("상품글 작성 현황");
		ilabel.setBounds(880, 390, 475, 20);
		ilabel.setFont(new Font("font", Font.BOLD, 17));
		ilabel.setBackground(commonRedColor);
		ilabel.setForeground(Color.white);
		contentPane.add(ilabel);
		
		
		JScrollPane iScrPane = new JScrollPane(itable);
		iScrPane.setBounds(880, 410, 475, 690);
		contentPane.add(iScrPane);
		

		contentPane.add(sidePn);
		contentPane.add(headerPn);
		
		setBounds(0, 0, 1350, 750);
		setLayout(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Delegator delegator = Delegator.getInstance();
		Object obj = e.getSource();
		
		if(obj == itemListBtn) {
			delegator.adminController.ItemList();
			this.dispose();
		}else if(obj == abilityListBtn) {
			delegator.adminController.AbilityList();
			this.dispose();
		}else if(obj == userListBtn) {
			delegator.adminController.UserList();
			this.dispose();
		} else if(obj == chatBtn) {
			delegator.roomController.RoomList();
		}
	}
}
