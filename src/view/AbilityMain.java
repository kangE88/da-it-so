package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import delegator.Delegator;
import dto.AbilityBbs;
import dto.Category;
import dto.Person;

public class AbilityMain extends JFrame implements ActionListener{
	//list panel
	private JPanel listPn, thumPn;
	private JLabel imgLa, txtLa, contentLabel;
	private JButton addBtn;

	//side panel
	private JPanel headerPn, headerLogo, sidePn, logoPn, catePn, detailPn;
	private JButton loginBtn, logoutBtn, signupBtn, searchBtn;
	private JTextField searchTextF;

	JPanel category;
	
	String smallNoImgUrl = "icon/smallNoimg.png";

	Color mainRed = new Color(218, 0, 0);
	Color mainGray = new Color(250, 250, 250);
	Color mainPink = new Color(255, 174, 174);
	Color mainBlack = Color.BLACK;

	List<AbilityBbs> abilityList = null;
	List<Category> m_categoryList = null;

	public AbilityMain(List<AbilityBbs> abilityList, List<Category> categoryList) {
		Delegator delegator = Delegator.getInstance();
		this.abilityList = abilityList;
		this.m_categoryList = categoryList;

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

		if(delegator.getCurrent_user()==null) {
			// loginBtn
			loginBtn = new JButton("로그인");
			loginBtn.setBounds(1240, 20, 100, 30);
			loginBtn.setOpaque(false); // 투명하게
			loginBtn.setBorderPainted(false);// 외곽선 없애줌
			loginBtn.setFont(new Font("로그인", Font.BOLD, 12));
			loginBtn.setBackground(mainRed);
			loginBtn.setForeground(Color.white);
			loginBtn.addActionListener(this);
			loginBtn.setContentAreaFilled(false);
			headerPn.add(loginBtn);

			// SignBtn
			signupBtn = new JButton("회원가입");
			signupBtn.setBounds(1180, 20, 100, 30);
			signupBtn.setOpaque(false); // 투명하게
			signupBtn.setBorderPainted(false);// 외곽선 없애줌
			signupBtn.setFont(new Font("회원가입", Font.BOLD, 12));
			signupBtn.setBackground(mainRed);
			signupBtn.setForeground(Color.white);
			signupBtn.addActionListener(this);
			signupBtn.setContentAreaFilled(false);
			headerPn.add(signupBtn);
		}else {
			// logoutBtn
			logoutBtn = new JButton("로그아웃");
			logoutBtn.setBounds(1240, 20, 100, 30);
			logoutBtn.setOpaque(false); // 투명하게
			logoutBtn.setBorderPainted(false);// 외곽선 없애줌
			logoutBtn.setFont(new Font("로그아웃", Font.BOLD, 12));
			logoutBtn.setBackground(mainRed);
			logoutBtn.setForeground(Color.white);
			logoutBtn.addActionListener(this);
			logoutBtn.setContentAreaFilled(false);
			headerPn.add(logoutBtn);
		}

		// sidePn
		Color sideC = new Color(250, 250, 250);
		sidePn = new JPanel();
		sidePn.setBounds(0, 60, 400, 1000);
		sidePn.setLayout(null);
		sidePn.setBackground(sideC);

	
		BufferedImage logoImg = delegator.getImage("icon/logo.png");
		ImageIcon logoIcon = new ImageIcon(logoImg);
		logoPn = new JPanel() {

			public void paintComponent(Graphics g) {
				g.drawImage(logoIcon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		logoPn.setBounds(40, 30, 300, 66);

		sidePn.add(logoPn);

		// SearchText
		searchTextF = new JTextField("검색");
		searchTextF.setBounds(40, 160, 260, 40);
		searchTextF.setBorder(new LineBorder(commonRedColor, 5));
		sidePn.add(searchTextF);

		// searchBtn
		BufferedImage searchImg = delegator.getImage("icon/search.png");
		ImageIcon searchIcon = new ImageIcon(searchImg);
		searchBtn = new JButton(searchIcon);
		searchBtn.setBounds(300, 160, 40, 40);
		searchBtn.setOpaque(false);
		searchBtn.setContentAreaFilled(false);
		searchBtn.addActionListener(this);
		sidePn.add(searchBtn);

		// catePn
		catePn = new JPanel();
		catePn.setLayout(new GridLayout(3, 3, 10, 10));
		catePn.setBounds(25, 290, 350, 350);
		catePn.setBackground(Color.WHITE);

		categoryList.stream().forEach(System.out::println);
		for(int i=0; i < categoryList.size(); i++) {
			System.out.println("ability/"+ categoryList.get(i).getTitle() +".png");
			BufferedImage categoryImage = delegator.getImage("ability/"+ categoryList.get(i).getTitle() +".png");
			
			ImageIcon categoryIcon;
			if(categoryImage == null) {
				categoryImage = delegator.getImage(smallNoImgUrl);		
			}
			categoryIcon = new ImageIcon(categoryImage);
			
			JPanel category = new JPanel() {
				public void paintComponent(Graphics g) {
					g.drawImage(categoryIcon.getImage(), 0, 0, null);
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

		listPn = new JPanel();
		listPn.setLayout(null);

		int thumPnCount = (abilityList.size() / 2) + 1;

		listPn.setPreferredSize(new Dimension(1280, 170 * thumPnCount));
		listPn.setLocation(0, 0);
		listPn.setBackground(mainPink);


		addBtn = new JButton("+");
		addBtn.setBounds(170, 35, 100, 50);
		addBtn.addActionListener(this);

		// thumPn
		thumPn = new JPanel();
		thumPn.setLayout(null);
		thumPn.setBounds(15, 50, 440, 120);
		thumPn.setBorder(new LineBorder(mainRed, 1));
		thumPn.setBackground(Color.white);
		thumPn.add(addBtn);

		int j = 0;
		for (int i = 0; i < abilityList.size(); i++) {

			JPanel thumPn1 = new JPanel();
			thumPn1.setLayout(null);

			JPanel thumPn2 = new JPanel();
			thumPn2.setLayout(null);
			
			JLabel stateLabel = new JLabel();
			stateLabel.setSize(100, 20);
			stateLabel.setFont(new Font("stateLabel", Font.BOLD, 15));

			if (i % 2 == 0) {
				thumPn1.setBounds(460, (170 * j) + 50, 440, 120);
				thumPn1.setName(String.valueOf(i));

				BufferedImage image = delegator.getImage(abilityList.get(i).getImgurl1());
				if(image == null) {
					image = delegator.getImage(smallNoImgUrl); 
				}
				ImageIcon icon = new ImageIcon(image);
				
				imgLa = new JLabel() {
					@Override
					protected void paintComponent(Graphics g) {
						// TODO Auto-generated method stub
						g.drawImage(icon.getImage(), 0, 0, 200, 120, null);
						setOpaque(false);
						super.paintComponents(g);
					}
				};
				imgLa.setBounds(0, 0, 200, 120);

				txtLa = new JLabel(abilityList.get(i).getTitle().replaceAll("\n", " "));
				contentLabel = new JLabel(abilityList.get(i).getContent());
				
				stateLabel.setLocation(380, 10);
				if(abilityList.get(i).getState()==0) {
					imgLa.setBorder(new LineBorder(mainRed, 1));		
					stateLabel.setForeground(commonRedColor);
					stateLabel.setText("진행중");
				} else if(abilityList.get(i).getState()==1) {
					imgLa.setBorder(new LineBorder(mainBlack, 2));
					stateLabel.setText("완료됨");
				}
				
				imgLa.setBounds(0, 0, 200, 120);
				txtLa.setBounds(210, 10, 300, 50);
				txtLa.setFont(new Font("font", Font.BOLD, 20));
				contentLabel.setBounds(210, 30, 300, 70);
				contentLabel.setForeground(Color.gray);
				thumPn1.add(stateLabel);
				thumPn1.add(contentLabel);
				thumPn1.add(txtLa);
				thumPn1.add(imgLa);
				j++;
			} else {
				thumPn1.setBounds(15, (170 * j) + 50, 440, 120);
				thumPn1.setName(String.valueOf(i));
				
				BufferedImage image = delegator.getImage(abilityList.get(i).getImgurl1());
				if(image == null) {
					image = delegator.getImage(smallNoImgUrl); 
				}
				ImageIcon icon = new ImageIcon(image);
				
				imgLa = new JLabel() {
					@Override
					protected void paintComponent(Graphics g) {
						// TODO Auto-generated method stub
						g.drawImage(icon.getImage(), 0, 0, 200, 120, null);
						setOpaque(false);
						super.paintComponents(g);
					}
				};
				
				txtLa = new JLabel(abilityList.get(i).getTitle().replaceAll("\n", " "));
				contentLabel = new JLabel(abilityList.get(i).getContent());
				
				stateLabel.setLocation(380, 10);
				if(abilityList.get(i).getState()==0) {
					imgLa.setBorder(new LineBorder(mainRed, 1));
					stateLabel.setForeground(commonRedColor);
					stateLabel.setText("진행중");
				} else if(abilityList.get(i).getState()==1) {
					imgLa.setBorder(new LineBorder(mainBlack, 2));
					stateLabel.setText("완료됨");
				}
				
				imgLa.setBounds(0, 0, 200, 120);
				txtLa.setBounds(210, 10, 300, 50);
				txtLa.setFont(new Font("font", Font.BOLD, 20));
				contentLabel.setBounds(210, 30, 300, 70);
				contentLabel.setForeground(Color.gray);
				thumPn1.add(stateLabel);
				thumPn1.add(contentLabel);
				thumPn1.add(txtLa);
				thumPn1.add(imgLa);
			}

			thumPn1.setBorder(new LineBorder(mainRed, 1));
			thumPn1.setBackground(Color.white);
			thumPn1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					int index = Integer.parseInt(thumPn1.getName());
					int state = abilityList.get(index).getState();

					Delegator delegator = Delegator.getInstance();
					delegator.abilityBbsController.AbilityDetail(abilityList.get(index));
					dispose();
				}
			});

			listPn.add(thumPn1);
		}
		listPn.add(thumPn);

		JScrollPane scroll;
		scroll = new JScrollPane(listPn);
		scroll.setBounds(400, 60, 935, 680);
		scroll.setBackground(mainPink);
		add(scroll);
		add(sidePn);
		add(headerPn);
		setBounds(0, 0, 1350, 750);
		setLayout(null);
		setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Delegator delegator = Delegator.getInstance();
		Person personDto = null;

		Object obj = e.getSource();

		if( obj== addBtn ) {
			personDto = delegator.getCurrent_user();

			if(personDto == null) {
				JOptionPane.showMessageDialog(null, "로그인 해주세요.");
				delegator.personController.Login();
				this.dispose();
			}else {
				delegator.abilityBbsController.AbilityWrite();
				this.dispose();
			}
		}else if(obj == loginBtn) {
			delegator.personController.Login();
			this.dispose();
		}else if(obj == signupBtn) {
			delegator.personController.SignUp();
			this.dispose();
		}else if(obj == logoutBtn) {
			int result =delegator.personController.Logout();
			if (result == 0) {
				this.dispose();
			}	
		}else if(obj == searchBtn) {
			String searchWord = searchTextF.getText();
			delegator.abilityBbsController.searchList(searchWord);
			this.dispose();
		}
	}
}
