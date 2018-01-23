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
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import delegator.Delegator;
import dto.AbilityBbs;
import dto.Person;

public class AdminAbilityDetail extends JFrame implements ActionListener, MouseListener {
	private JPanel headerPn, headerLogo, sidePn, logoPn, imagePannel, iteminfoPn, itemImagePn, subimagePn, detailPn, subimage1, subimage2, subimage3,
			subimage4;
	private JButton loginBtn, logoutBtn, signupBtn, searchBtn, backBtn, delBtn;
	private JButton itemListBtn, userListBtn, abilityListBtn;
	private JTextField searchTextF;
	private JLabel titleLb, sellLb, detailtitleLb, cateLb, explanationLb;
	private JButton completeBtn, continueBtn, chatBtn;

	Person person = null;
	AbilityBbs ability = null;
	String noImgUrl = "icon/noimg.png";

	public AdminAbilityDetail(AbilityBbs ability, Person person) {

		this.person = person;
		this.ability = ability;

		Container cn = getContentPane();

		cn.setBounds(0, 0, 1680, 900);
		cn.setBackground(Color.white);

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
		// scrollPane.add(detailPn);

		Delegator delegator = Delegator.getInstance();
		// headerlogo
		BufferedImage headerImg = delegator.getImage("icon/headerlogo.png");
		ImageIcon headerimage = new ImageIcon(headerImg);
		headerLogo = new JPanel() {
			// 사이즈맞게 배경삽임
			public void paintComponent(Graphics g) {
				g.drawImage(headerimage.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		headerLogo.setBounds(15, 25, 71, 15);
		headerLogo.addMouseListener(this);
		headerPn.add(headerLogo);
		

		if (delegator.getCurrent_user() == null) {
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
		} else {
			// logoutBtn
			logoutBtn = new JButton("로그아웃");
			logoutBtn.setBounds(1250, 20, 100, 30);
			logoutBtn.setOpaque(false); // 투명하게
			logoutBtn.setBorderPainted(false);// 외곽선 없애줌
			logoutBtn.setFont(new Font("로그아웃", Font.BOLD, 12));
			logoutBtn.setBackground(commonRedColor);
			logoutBtn.setForeground(Color.white);
			logoutBtn.addActionListener(this);
			headerPn.add(logoutBtn);
		}


		// 1050
		// sidePn
		Color sideC = new Color(250, 250, 250);
		sidePn = new JPanel();
		sidePn.setBounds(0, 60, 400, 1000);
		sidePn.setLayout(null);
		sidePn.setBackground(sideC);

		BufferedImage logoImg = delegator.getImage("icon/logo.png");
		ImageIcon logoIcon = new ImageIcon(logoImg);
		logoPn = new JPanel() {
			// 사이즈맞게 배경삽임
			public void paintComponent(Graphics g) {
				g.drawImage(logoIcon.getImage(), 0, 0, null);
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
		BufferedImage searchImg = delegator.getImage("icon/search.png");
		ImageIcon searchIcon = new ImageIcon(searchImg);
		searchBtn = new JButton(searchIcon);
		searchBtn.setBounds(300, 160, 40, 40);
		searchBtn.setOpaque(false); // 투명하게
		searchBtn.addActionListener((ActionEvent e)->{
			delegator.adminController.SerarchAbilityList(searchTextF.getText());
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

		// sidePn.setBounds(0, 60, 400, 1000);
		// imagePannel
		imagePannel = new JPanel();
		imagePannel.setBounds(70, 20, 420, 650);
		imagePannel.setLayout(null);
		imagePannel.setBorder(new LineBorder(Color.red, 2));
		imagePannel.setBackground(Color.white);
		// titleLb
		titleLb = new JLabel(ability.getTitle());
		titleLb.setFont(new Font(ability.getTitle(), Font.BOLD, 25));
		titleLb.setBounds(10, 30, 400, 30);
		titleLb.setOpaque(false);
		titleLb.setBackground(Color.DARK_GRAY);

		imagePannel.add(titleLb);
		// Seller

		sellLb = new JLabel(person.getId());
		sellLb.setBounds(10, 75, 400, 20);
		sellLb.setFont(new Font(person.getId(), Font.BOLD, 12));
		sellLb.setOpaque(false);

		imagePannel.add(sellLb);
		
		BufferedImage itemImage = delegator.getImage(ability.getImgurl1());
		if (itemImage == null) {
			itemImage = delegator.getImage(noImgUrl);
		}
		ImageIcon itemIcon = new ImageIcon(itemImage);

		itemImagePn = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(itemIcon.getImage(), 0, 0, 400, 400, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		itemImagePn.setBounds(10, 115, 400, 400);
		itemImagePn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				delegator.abilityBbsController.ImageView(ability.getImgurl1());
			}
		});
		imagePannel.add(itemImagePn);

		// itemsub
		subimagePn = new JPanel();
		subimagePn.setLayout(new GridLayout(1, 4));
		subimagePn.setBounds(0, 550, 420, 100);
		// subimagePn.setBackground(Color.PINK);

		int compX = subimagePn.getWidth() / 4;
		int compY = subimagePn.getHeight();

		// 서브 이미지1
		BufferedImage subItemImg1 = delegator.getImage(ability.getImgurl1());
		if (subItemImg1 == null) {
			subItemImg1 = delegator.getImage(noImgUrl);
		}
		ImageIcon subItemIcon1 = new ImageIcon(subItemImg1);
		subimage1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(subItemIcon1.getImage(), 0, 0, compX, compY, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		subimage1.setBorder(new LineBorder(commonRedColor, 2));
		subimage1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				String img = ability.getImgurl1();
				delegator.abilityBbsController.ImageView(img);
			}
		});
		subimagePn.add(subimage1);

		// 서브이미지2
		BufferedImage subItemImg2 = delegator.getImage(ability.getImgurl2());
		if (subItemImg2 == null) {
			subItemImg2 = delegator.getImage(noImgUrl);
		}
		ImageIcon subItemIcon2 = new ImageIcon(subItemImg2);
		subimage2 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(subItemIcon2.getImage(), 0, 0, compX, compY, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		subimage2.setBorder(new LineBorder(commonRedColor, 2));
		
		subimage2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				delegator.abilityBbsController.ImageView(ability.getImgurl2());
			}
		});
		subimagePn.add(subimage2);
		// 서브이미지3

		BufferedImage subItemImg3 = delegator.getImage(ability.getImgurl3());
		if (subItemImg3 == null) {
			subItemImg3 = delegator.getImage(noImgUrl);
		}
		ImageIcon subItemIcon3 = new ImageIcon(subItemImg3);
		subimage3 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(subItemIcon3.getImage(), 0, 0, compX, compY, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		subimage3.setBorder(new LineBorder(commonRedColor, 2));
		subimage3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				delegator.abilityBbsController.ImageView(ability.getImgurl3());
			}
		});
		subimagePn.add(subimage3);

		// 서브이미지4
		BufferedImage subItemImg4 = delegator.getImage(ability.getImgurl4());
		if (subItemImg4 == null) {
			subItemImg4 = delegator.getImage(noImgUrl);
		}
		ImageIcon subItemIcon4 = new ImageIcon(subItemImg4);
		subimage4 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(subItemIcon4.getImage(), 0, 0, compX, compY, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		subimage4.setBorder(new LineBorder(commonRedColor, 2));
		subimage4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				delegator.abilityBbsController.ImageView(ability.getImgurl4());
			}
		});
		subimagePn.add(subimage4);

		imagePannel.add(subimagePn);
		detailPn.add(imagePannel);

		// itemdetailPn

		iteminfoPn = new JPanel();
		iteminfoPn.setLayout(null);
		iteminfoPn.setBounds(580, 100, 340, 400);
		iteminfoPn.setBackground(Color.white);
		iteminfoPn.setBorder(new LineBorder(Color.red, 2));

		// detailtitleLb
		detailtitleLb = new JLabel(ability.getTitle());
		detailtitleLb.setBounds(10, 10, 340, 30);
		detailtitleLb.setFont(new Font(ability.getTitle(), Font.BOLD, 20));
		iteminfoPn.add(detailtitleLb);

		// Ability

		String key = ability.getAbility();
		int rowSize = 0;

		String[] keyarray;
		keyarray = key.split("-key-");

		if (keyarray.length % 3 == 0) {
			rowSize = keyarray.length / 3;
		} else {
			rowSize = keyarray.length / 3 + 1;
		}

		JPanel keywordPanel = new JPanel();
		keywordPanel.setLocation(10, 70);
		keywordPanel.setSize(240, 40);
		keywordPanel.setBackground(Color.white);
		keywordPanel.setLayout(null);

		JLabel keywordLabel[][] = new JLabel[rowSize][3];

		int k = 0;
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < 3; j++) {

				keywordLabel[i][j] = new JLabel();
				keywordLabel[i][j].setOpaque(true);
				keywordLabel[i][j].setBackground(Color.pink);
				keywordLabel[i][j].setHorizontalAlignment(SwingConstants.LEFT);
				keywordLabel[i][j].setText("#" + keyarray[k]);
				keywordLabel[i][j].setSize(70, 30);
				keywordLabel[i][j].setLocation((j * 80), (i * 40));

				keywordPanel.add(keywordLabel[i][j]);
				k++;
				if (k == keyarray.length) {
					break;
				}
			}
		}

		iteminfoPn.add(keywordPanel);

		// categori
		cateLb = new JLabel("카테고리 : " + ability.getCategory_id());
		cateLb.setBounds(10, 100, 80, 30);
		cateLb.setOpaque(true);
		cateLb.setBackground(Color.white);
		iteminfoPn.add(cateLb);

		// item explanation
		explanationLb = new JLabel("저품 설명 : " + ability.getContent());
		explanationLb.setBounds(10, 150, 300, 10 * ability.getContent().length());
		explanationLb.setOpaque(true);
		explanationLb.setBackground(Color.white);
		explanationLb.setVerticalAlignment(SwingConstants.TOP);
		iteminfoPn.add(explanationLb);

		// chatBtn
		backBtn = new JButton("뒤로 가기");
		backBtn.setBounds(630, 505, 240, 35);
		backBtn.setBorder(new LineBorder(commonRedColor));
		backBtn.addActionListener(this);
		detailPn.add(backBtn);

		detailPn.add(iteminfoPn);

		JLabel state = new JLabel();
		state.setFont(new Font("state", Font.BOLD, 15));
		state.setBounds(580, 40, 200, 15);
		detailPn.add(state);

		if (ability.getState() == 0) {
			// 진행중...
			state.setText("진행중");

			completeBtn = new JButton("완료 상태로 변경");
			completeBtn.setBounds(630, 550, 240, 35);
			completeBtn.setBorder(new LineBorder(commonRedColor));
			completeBtn.addActionListener(this);
			detailPn.add(completeBtn);

			delBtn = new JButton("관리자 권한 삭제");
			delBtn.setBounds(630, 595, 240, 35);
			delBtn.setBorder(new LineBorder(commonRedColor));
			delBtn.addActionListener(this);
			detailPn.add(delBtn);
		} else if (ability.getState() == 1) {
			// 완료됨...
			state.setText("완료됨 ");

			continueBtn = new JButton("진행 상태로 변경");
			continueBtn.setBounds(630, 550, 240, 35);
			continueBtn.setBorder(new LineBorder(commonRedColor));
			continueBtn.addActionListener(this);
			detailPn.add(continueBtn);

			delBtn = new JButton("관리자 권한 삭제");
			delBtn.setBounds(630, 595, 240, 35);
			delBtn.setBorder(new LineBorder(commonRedColor));
			delBtn.addActionListener(this);
			detailPn.add(delBtn);
		} else if (ability.getState() == 2) {
			// 삭제됨...
			state.setText("삭제됨 ");

			continueBtn = new JButton("진행 상태로 변경");
			continueBtn.setBounds(630, 550, 240, 35);
			continueBtn.setBorder(new LineBorder(commonRedColor));
			continueBtn.addActionListener(this);
			detailPn.add(continueBtn);

		} else if (ability.getState() == 3) {
			// 관리자에 의해 삭제됨...
			state.setText("관리자에 의해 삭제됨 ");

			continueBtn = new JButton("진행 상태로 변경");
			continueBtn.setBounds(630, 550, 240, 35);
			continueBtn.setBorder(new LineBorder(commonRedColor));
			continueBtn.addActionListener(this);
			detailPn.add(continueBtn);
		}

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
		Delegator delegator = Delegator.getInstance();
		JButton btn = (JButton) e.getSource();

		if (btn == backBtn) {
			delegator.adminController.ItemList();
			this.dispose();
		} else if(btn == delBtn) {
			delegator.adminController.DeleteAbilityBbsByAdmin(this.ability);
			this.dispose();
		} else if(btn == completeBtn) {
			delegator.adminController.CompleteAbilityBbsByAdmin(this.ability);
			this.dispose();
		} else if(btn == continueBtn) {
			delegator.adminController.ContinueAbilityBbsByAdmin(this.ability);
			this.dispose();
		} else if(btn == loginBtn) {
			delegator.personController.Login();
			this.dispose();
		} else if(btn == logoutBtn) {
			delegator.personController.Logout();
			this.dispose();
		} else if(btn == signupBtn) {
			delegator.personController.SignUp();
			this.dispose();
		} else if(btn == itemListBtn) {
			delegator.adminController.ItemList();
			this.dispose();
		} else if(btn == abilityListBtn) {
			delegator.adminController.AbilityList();
			this.dispose();
		} else if(btn == userListBtn) {
			delegator.adminController.UserList();
			this.dispose();
		} else if(btn == chatBtn) {
			delegator.roomController.RoomList();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// headerLogo 클릭 시 Main페이지로
		JPanel headerLogo = (JPanel) e.getComponent();

		if (e.getComponent().equals(headerLogo)) {
			Delegator delegator = Delegator.getInstance();
			delegator.abilityBbsController.main();
			dispose();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("프레스확인");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
