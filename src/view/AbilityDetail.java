package view;

import java.awt.Color;
import java.awt.Component;
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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.ItemBbsController;
import delegator.Delegator;
import dto.AbilityBbs;
import dto.Category;
import dto.ItemBbs;
import dto.Person;

public class AbilityDetail extends JFrame implements ActionListener {
	private JPanel headerPn, headerLogo, sidePn, logoPn, catePn, imagePannel, iteminfoPn, itemImagePn, subimagePn,
			detailPn, subimage1, subimage2, subimage3, subimage4;
	private JButton loginBtn, logoutBtn, signupBtn, MypageBtn, searchBtn, talkBtn, chatBtn, listBtn;
	private JTextField searchTextF;
	private JLabel titleLb, sellLb, detailtitleLb, cateLb, explanationLb;

	// String iconImgUrl = "C:\\icon\\";
	String iconImgUrl = "/Users/parker/Desktop/img/icon/";
	String nullimgURL = "c:\\icon\\nullimg.png";
	
	AbilityBbs abilityDto = null;
	List<Category> categoryList = null;

	// 삭제버튼추가필요
	// 완료 버튼 추가 시 STATE 1로 변경 STATE = 0 등록시 STATE = 1 완료 STATE = 2 삭제 STATE = 3
	// 관리자에의한 삭제
	public AbilityDetail(AbilityBbs abilityDto, List<Category> categoryList) {

		Delegator delegator = Delegator.getInstance();
		this.abilityDto = abilityDto;
		this.categoryList = categoryList;

		Container cn = getContentPane();

		cn.setBounds(0, 0, 1350, 750);
		cn.setBackground(Color.white);

		
		if (abilityDto.getImgurl1().equals("")) {
			abilityDto.setImgurl1(nullimgURL);
		}
		if (abilityDto.getImgurl2().equals("")) {
			abilityDto.setImgurl1(nullimgURL);
		}
		if (abilityDto.getImgurl3().equals("")) {
			abilityDto.setImgurl1(nullimgURL);
		}
		if (abilityDto.getImgurl4().equals("")) {
			abilityDto.setImgurl1(nullimgURL);
		}
		
		// Header
		Color commonRedColor = new Color(218, 0, 0);
		headerPn = new JPanel();
		headerPn.setBackground(commonRedColor);
		headerPn.setSize(1350, 60);
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
		// scrollPane.add(detailPn);

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
				dispose();
			}
		});
		headerPn.add(headerLogo);

		if (delegator.getCurrent_user() == null) {
			// loginBtn
			loginBtn = new JButton("로그인");
			loginBtn.setBounds(1240, 20, 100, 30);
			loginBtn.setOpaque(false); // 투명하게
			loginBtn.setBorderPainted(false);// 외곽선 없애줌
			loginBtn.setFont(new Font("로그인", Font.BOLD, 12));
			loginBtn.setBackground(commonRedColor);
			loginBtn.setForeground(Color.white);
			loginBtn.addActionListener(this);
			headerPn.add(loginBtn);

			// SignBtn
			signupBtn = new JButton("회원가입");
			signupBtn.setBounds(1180, 20, 100, 30);
			signupBtn.setOpaque(false); // 투명하게
			signupBtn.setBorderPainted(false);// 외곽선 없애줌
			signupBtn.setFont(new Font("회원가입", Font.BOLD, 12));
			signupBtn.setBackground(commonRedColor);
			signupBtn.setForeground(Color.white);
			signupBtn.addActionListener(this);
			headerPn.add(signupBtn);
		} else {
			// logoutBtn
			logoutBtn = new JButton("로그아웃");
			logoutBtn.setBounds(1240, 20, 100, 30);
			logoutBtn.setOpaque(false); // 투명하게
			logoutBtn.setBorderPainted(false);// 외곽선 없애줌
			logoutBtn.setFont(new Font("로그아웃", Font.BOLD, 12));
			logoutBtn.setBackground(commonRedColor);
			logoutBtn.setForeground(Color.white);
			logoutBtn.addActionListener(this);
			headerPn.add(logoutBtn);
		}

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
		searchBtn.addActionListener(this);
		// searchBtn.setBackground();

		searchBtn.setContentAreaFilled(false);// 내용영역 채우기x

		sidePn.add(searchBtn);

		// catePn
		catePn = new JPanel();
		catePn.setLayout(new GridLayout(3, 3, 10, 10));
		catePn.setBounds(25, 290, 350, 350);
		catePn.setBackground(Color.WHITE);

		for (int i = 0; i < categoryList.size(); i++) {
			ImageIcon categoryImage = new ImageIcon(iconImgUrl + categoryList.get(i).getTitle() + ".png");
			System.out.println(iconImgUrl + categoryList.get(i).getTitle() + ".png");
			JPanel category = new JPanel() {
				public void paintComponent(Graphics g) {
					g.drawImage(categoryImage.getImage(), 0, 0, null);
					setOpaque(false);
					super.paintComponents(g);
				}
			};
			category.setBorder(new LineBorder(commonRedColor, 2));
			category.setName(categoryList.get(i).getSeq() + "");
			category.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					int seq = Integer.parseInt(category.getName());

					Delegator delegator = Delegator.getInstance();
					delegator.abilityBbsController.SelectAbilityCategories(seq);
					dispose();
				}
			});
			catePn.add(category);
		}

		sidePn.add(catePn);

		// imagePannel
		imagePannel = new JPanel();
		imagePannel.setBounds(70, 20, 420, 650);
		imagePannel.setLayout(null);
		imagePannel.setBorder(new LineBorder(Color.red, 2));
		imagePannel.setBackground(Color.white);
		// titleLb
		titleLb = new JLabel(abilityDto.getTitle());
		titleLb.setFont(new Font(abilityDto.getTitle(), Font.BOLD, 25));
		titleLb.setBounds(10, 30, 400, 30);
		titleLb.setOpaque(false);
		titleLb.setBackground(Color.DARK_GRAY);

		imagePannel.add(titleLb);

		// Seller

		sellLb = new JLabel("작성자 : " + abilityDto.getUser_id());
		sellLb.setBounds(10, 75, 400, 20);
		sellLb.setFont(new Font(abilityDto.getUser_id(), Font.BOLD, 12));
		sellLb.setOpaque(false);

		imagePannel.add(sellLb);
		// itemImage
		itemImagePn = new JPanel() {
			ImageIcon itemImage = new ImageIcon(abilityDto.getImgurl1());

			public void paintComponent(Graphics g) {
				g.drawImage(itemImage.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		itemImagePn.setBounds(60, 115, 400, 500);
		imagePannel.add(itemImagePn);

		// itemsub
		subimagePn = new JPanel();
		subimagePn.setLayout(new GridLayout(1, 4, 10, 5));
		subimagePn.setBounds(10, 530, 400, 90);
		// subimagePn.setBackground(Color.PINK);

		// 서브 이미지1
		subimage1 = new JPanel() {
			ImageIcon image1 = new ImageIcon(abilityDto.getImgurl1());

			public void paintComponent(Graphics g) {
				g.drawImage(image1.getImage(), 0, 0, 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		subimage1.setBorder(new LineBorder(Color.red,2));
		subimagePn.add(subimage1);

		// 서브이미지2
		subimage2 = new JPanel() {
			ImageIcon image2 = new ImageIcon(abilityDto.getImgurl2());

			public void paintComponent(Graphics g) {
				g.drawImage(image2.getImage(), 0, 0, 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		subimage2.setBorder(new LineBorder(Color.red,2));
		subimagePn.add(subimage2);
		// 서브이미지3
		subimage3 = new JPanel() {
			ImageIcon image3 = new ImageIcon(abilityDto.getImgurl3());

			public void paintComponent(Graphics g) {
				g.drawImage(image3.getImage(), 0, 0, 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		subimage3.setBorder(new LineBorder(Color.red,2));
		subimagePn.add(subimage3);

		// 서브이미지4
		subimage4 = new JPanel() {
			ImageIcon image4 = new ImageIcon(abilityDto.getImgurl4());

			public void paintComponent(Graphics g) {
				g.drawImage(image4.getImage(), 0, 0, 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		subimage4.setBorder(new LineBorder(Color.red,2));
		subimagePn.add(subimage4);

		imagePannel.add(subimagePn);
		detailPn.add(imagePannel);

		// itemdetailPn

		iteminfoPn = new JPanel();
		iteminfoPn.setLayout(null);
		iteminfoPn.setBounds(550, 135, 340, 400);
		iteminfoPn.setBackground(Color.white);
		iteminfoPn.setBorder(new LineBorder(Color.red, 2));

		// detailtitleLb
		detailtitleLb = new JLabel(abilityDto.getTitle());
		detailtitleLb.setBounds(10, 10, 340, 30);
		detailtitleLb.setFont(new Font(abilityDto.getTitle(), Font.BOLD, 20));
		iteminfoPn.add(detailtitleLb);

		// Ability

		String key = abilityDto.getAbility().replace(",", "-key-");
		System.out.println("key값 : " + key);
		int rowSize = 0;
		String[] keyarray;
		keyarray = new String[rowSize * 3];
		keyarray = key.split("-key-");

		if (keyarray.length % 3 == 0) {
			rowSize = keyarray.length / 3;
		} else {
			rowSize = keyarray.length / 3 + 1;
		}

		JPanel keywordPanel = new JPanel();
		keywordPanel.setLocation(10, 70);
		keywordPanel.setSize(240, 30 * rowSize);
		keywordPanel.setBackground(Color.white);
		keywordPanel.setLayout(null);

		JLabel keywordLabel[][] = new JLabel[rowSize][3];

		int k = 0;
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < keyarray.length; j++) {

				keywordLabel[i][j] = new JLabel();
				keywordLabel[i][j].setOpaque(true);
				keywordLabel[i][j].setBackground(Color.pink);
				keywordLabel[i][j].setHorizontalAlignment(SwingConstants.LEFT);
				keywordLabel[i][j].setText("#" + keyarray[k]);
				keywordLabel[i][j].setSize(70, 30);
				keywordLabel[i][j].setLocation((j * 80), (i * 40));

				keywordPanel.add(keywordLabel[i][j]);
				k++;
			}
		}
		iteminfoPn.add(keywordPanel);

		// go back to list button
		listBtn = new JButton("목록으로 돌아가기");
		listBtn.setBounds(200, 10, 120, 30);
		listBtn.setOpaque(false);
		listBtn.setForeground(commonRedColor);
		listBtn.addActionListener(this);
		iteminfoPn.add(listBtn);
		// iteminfoPn.setBounds(580, 135, 340, 400);

		// category
		cateLb = new JLabel("카테고리 : " + abilityDto.getCategory_id());
		cateLb.setBounds(10, 100, 80, 30);
		cateLb.setOpaque(true);
		cateLb.setBackground(Color.white);
		iteminfoPn.add(cateLb);

		// item explanation
		explanationLb = new JLabel("저품 설명 : " + abilityDto.getContent());
		explanationLb.setBounds(10, 150, 300, 10 * abilityDto.getContent().length());
		explanationLb.setOpaque(true);
		explanationLb.setBackground(Color.white);
		explanationLb.setVerticalAlignment(SwingConstants.TOP);
		iteminfoPn.add(explanationLb);

		// chatBtn
		chatBtn = new JButton(new ImageIcon(iconImgUrl + "chatting.png"));
		chatBtn.setBounds(630, 555, 240, 34);
		chatBtn.setOpaque(false);
		chatBtn.setBorderPainted(false);
		chatBtn.setFocusPainted(false);
		chatBtn.addActionListener(this);

		detailPn.add(chatBtn);
		detailPn.add(iteminfoPn);

		add(sidePn);
		add(headerPn);

		add(scrollPane);
		setBounds(0, 0, 1350, 750);
		setLayout(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		Delegator delegator = Delegator.getInstance();

		if (obj == chatBtn) {
			if (delegator.getCurrent_user() != null) {
				String target_id = sellLb.getText().replaceAll("작성자 : ", "");
				delegator.roomController.checkRoom(target_id);
			} else {
				JOptionPane.showMessageDialog(null, "로그인 해주세요");
				delegator.personController.Login();
				this.dispose();
			}
		} else if (obj == loginBtn) {
			delegator.personController.Login();
			this.dispose();
		} else if (obj == signupBtn) {
			delegator.personController.SignUp();
			this.dispose();
		} else if (obj == logoutBtn) {
			delegator.personController.Logout();
			this.dispose();
		} else if (obj == searchBtn) {
			String searchWord = searchTextF.getText();
			delegator.abilityBbsController.searchList(searchWord);
		} else if (obj == listBtn) {
			delegator.abilityBbsController.allAbilityList();
			this.dispose();
		}
	}
}
