package view;

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
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

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

import delegator.Delegator;
import dto.Category;
import dto.ItemBbs;

public class ItemDetail extends JFrame implements ActionListener {
	private JPanel headerPn, headerLogo, sidePn, logoPn, catePn, imagePannel, iteminfoPn, itemImagePn, subimagePn,
			detailPn, subimage1, subimage2, subimage3, subimage4;
	private JButton logoutBtn, signupBtn, searchBtn, loginBtn, chatBtn, listBtn, deleteBtn, completeBtn, continueBtn;
	private JTextField searchTextF;
	private JLabel titleLb, sellLb, detailtitleLb, priceLb, cateLb, explanationLb,detailExplanationLb;

	JPanel category;

	String noImgUrl = "icon/noimg.png";
	Color textColor = new Color(68, 62, 62); 
	Color lineColor = new Color(255, 145, 152);

	ItemBbs m_itemDto = null;
	List<Category> m_categoryList = null;

	public ItemDetail(ItemBbs itemDto, List<Category> categoryList, Category itemCategory) {
		Delegator delegator = Delegator.getInstance();
		this.m_itemDto = itemDto;
		this.m_categoryList = categoryList;

		Container cn = getContentPane();

		cn.setBounds(0, 0, 1350, 750);
		cn.setBackground(Color.white);

		// if (itemDto.getImgurl1() == null ||
		// itemDto.getImgurl1().equals("userImg/null")) {
		// itemDto.setImgurl1(noImgUrl);
		// }
		// if (itemDto.getImgurl2() == null ||
		// itemDto.getImgurl2().equals("userImg/null")) {
		// itemDto.setImgurl2(noImgUrl);
		// }
		// if (itemDto.getImgurl3() == null ||
		// itemDto.getImgurl3().equals("userImg/null")) {
		// itemDto.setImgurl3(noImgUrl);
		// }
		// if (itemDto.getImgurl4() == null ||
		// itemDto.getImgurl4().equals("userImg/null")) {
		// itemDto.setImgurl4(noImgUrl);
		// }

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
		detailPn.setPreferredSize(new Dimension(1015, 1500));
		detailPn.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(detailPn);
		scrollPane.setBounds(400, 60, 935, 680);

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

		if (delegator.getCurrent_user() == null) {
			// loginBtn
			loginBtn = new JButton("로그인");
			loginBtn.setBounds(1240, 20, 100, 30);
			loginBtn.setOpaque(false); // 투명하게
			loginBtn.setBorderPainted(false);// 외곽선 없애줌
			loginBtn.setContentAreaFilled(false);
			loginBtn.setFont(new Font("로그인", Font.BOLD, 12));
			loginBtn.setBackground(commonRedColor);
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
			signupBtn.setBackground(commonRedColor);
			signupBtn.setContentAreaFilled(false);
			signupBtn.setForeground(Color.white);
			signupBtn.addActionListener(this);
			signupBtn.setContentAreaFilled(false);
			headerPn.add(signupBtn);
		} else {
			// logoutBtn
			logoutBtn = new JButton("로그아웃");
			logoutBtn.setBounds(1240, 20, 100, 30);
			logoutBtn.setOpaque(false); // 투명하게
			logoutBtn.setBorderPainted(false);// 외곽선 없애줌
			logoutBtn.setFont(new Font("로그아웃", Font.BOLD, 12));
			logoutBtn.setBackground(commonRedColor);
			logoutBtn.setContentAreaFilled(false);
			logoutBtn.setForeground(Color.white);
			logoutBtn.addActionListener(this);
			logoutBtn.setContentAreaFilled(false);
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
			BufferedImage categoryImage = delegator.getImage("item/" + categoryList.get(i).getTitle() + ".png");
			ImageIcon categoryIcon = new ImageIcon(categoryImage);

			JPanel category = new JPanel() {
				public void paintComponent(Graphics g) {
					g.drawImage(categoryIcon.getImage(), 0, 0, null);
					setOpaque(false);
					super.paintComponents(g);
				}
			};
			category.setBorder(new LineBorder(commonRedColor, 1));
			category.setName(categoryList.get(i).getSeq() + "");
			category.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					int seq = Integer.parseInt(category.getName());

					Delegator delegator = Delegator.getInstance();
					delegator.itemBbsController.SelectItemCategories(seq);
					dispose();
				}
			});
			catePn.add(category);
		}

		sidePn.add(catePn);

		// sidePn.setBounds(0, 60, 400, 1000);
		// imagePannel
		imagePannel = new JPanel();
		imagePannel.setBounds(70, 0, 420, 650);
		imagePannel.setLayout(null);
		imagePannel.setBorder(new LineBorder(lineColor, 1));
		imagePannel.setBackground(Color.white);
		// titleLb
		titleLb = new JLabel(m_itemDto.getTitle());

		if (itemDto.getState() == 1) {
			titleLb.setText(m_itemDto.getTitle() + " - 판매 완료 - ");
		}
		titleLb.setFont(new Font(m_itemDto.getTitle(), Font.BOLD, 25));
		titleLb.setBounds(10, 30, 400, 30);
		titleLb.setOpaque(false);
		titleLb.setBackground(Color.DARK_GRAY);

		imagePannel.add(titleLb);
		// Seller

		sellLb = new JLabel("작성자 : " + m_itemDto.getUser_id());
		sellLb.setBounds(10, 75, 400, 20);
		sellLb.setFont(new Font(m_itemDto.getUser_id(), Font.BOLD, 12));
		sellLb.setOpaque(false);

		imagePannel.add(sellLb);
		// itemImage
		BufferedImage itemImage = delegator.getImage(itemDto.getImgurl1());
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
				delegator.itemBbsController.ImageView(itemDto.getImgurl1());
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
		BufferedImage subItemImg1 = delegator.getImage(itemDto.getImgurl1());
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
		subimage1.setBorder(new LineBorder(lineColor, 1));
		subimage1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				String img = itemDto.getImgurl1();
				delegator.itemBbsController.ImageView(img);
			}
		});
		subimagePn.add(subimage1);

		// 서브이미지2
		BufferedImage subItemImg2 = delegator.getImage(itemDto.getImgurl2());
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
		subimage2.setBorder(new LineBorder(lineColor, 1));
		System.out.println("2");
		subimage2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				delegator.itemBbsController.ImageView(itemDto.getImgurl2());
			}
		});
		subimagePn.add(subimage2);
		// 서브이미지3

		BufferedImage subItemImg3 = delegator.getImage(itemDto.getImgurl3());
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
		subimage3.setBorder(new LineBorder(lineColor, 1));
		subimage3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				delegator.itemBbsController.ImageView(itemDto.getImgurl3());
			}
		});
		subimagePn.add(subimage3);

		// 서브이미지4
		BufferedImage subItemImg4 = delegator.getImage(itemDto.getImgurl4());
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
		subimage4.setBorder(new LineBorder(lineColor, 1));
		subimage4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Delegator delegator = Delegator.getInstance();
				delegator.itemBbsController.ImageView(itemDto.getImgurl4());
			}
		});
		subimagePn.add(subimage4);

		imagePannel.add(subimagePn);
		detailPn.add(imagePannel);

		// itemdetailPn
		BufferedImage detailPnImg = delegator.getImage("icon/detailPn.png");
		ImageIcon detailPnimage = new ImageIcon(detailPnImg);
		iteminfoPn = new JPanel() {
		 public void paintComponent(Graphics g) {
			 g.drawImage(detailPnimage.getImage(),0, 0, null);
			 setOpaque(false);
			 super.paintComponent(g);
		 }
			
		};
		iteminfoPn.setLayout(null);
		iteminfoPn.setBounds(550, 135, 340, 400);
		iteminfoPn.setBackground(Color.white);
		//iteminfoPn.setBorder(new LineBorder(commonRedColor, 1));

		// detailtitleLb
		detailtitleLb = new JLabel(m_itemDto.getTitle());
		detailtitleLb.setForeground(textColor);

		if (itemDto.getState() == 1) {
			detailtitleLb.setText(m_itemDto.getTitle() + " - 판매 완료 - ");
		}

		detailtitleLb.setBounds(10, 10, 340, 30);
		detailtitleLb.setFont(new Font(m_itemDto.getTitle(), Font.BOLD, 20));
		iteminfoPn.add(detailtitleLb);

		// detailprice
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		 double price = m_itemDto.getPrice();
		nf.format(price);
	
		priceLb = new JLabel(nf.format(price)+"원 ");
		priceLb.setBounds(10, 45, 340, 30);
		priceLb.setForeground(textColor);
		priceLb.setFont(new Font(nf.format(price)+" 원", Font.BOLD, 15));
		iteminfoPn.add(priceLb);

		// keyword

		
		String key = m_itemDto.getKeyword();
		int rowSize = 0;

		String[] keyarray = null;
		
		if(key != null) {
			keyarray = key.split("-key-");
			if (keyarray.length % 3 == 0) {
				rowSize = keyarray.length / 3;
			} else {
				rowSize = keyarray.length / 3 + 1;
			}
			
			
		}else {
			keyarray = new String[2];
			keyarray[0] =  "물품";
			keyarray[1] =  "판매";
		}
		
			BufferedImage keywordPnImg = delegator.getImage("icon/detailPn.png");
			ImageIcon keywordPnimage = new ImageIcon(detailPnImg);
			JPanel keywordPanel = new JPanel() {
			 public void paintComponent(Graphics g) {
				 g.drawImage(keywordPnimage.getImage(),0, 0, null);
				 setOpaque(false);
				 super.paintComponent(g);
			 }
				
			};
	
		keywordPanel.setLocation(10, 90);
		keywordPanel.setSize(240, 40);
		keywordPanel.setBackground(Color.red);
		keywordPanel.setLayout(null);
		
		JLabel keywordLabel[][] = new JLabel[rowSize][3];
		
		int k = 1;
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < 3; j++) {
				if (k == keyarray.length) {
					break;
				}
				
				keywordLabel[i][j] = new JLabel();
				keywordLabel[i][j].setOpaque(true);
				keywordLabel[i][j].setBackground(new Color(255, 145, 152));
				keywordLabel[i][j].setForeground(Color.WHITE);
				keywordLabel[i][j].setHorizontalAlignment(SwingConstants.LEFT);
				keywordLabel[i][j].setText("#" + keyarray[k]);
				keywordLabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				keywordLabel[i][j].setSize(70, 30);
				
				keywordLabel[i][j].setLocation((j * 80), (i * 40));
				
				
				keywordPanel.add(keywordLabel[i][j]);
				k++;
			}
		}
		iteminfoPn.add(keywordPanel);


		// categori
		cateLb = new JLabel("카테고리 :  " + itemCategory.getDescription());
		cateLb.setBounds(10, 130, 200, 30);
		cateLb.setForeground(textColor);
		
		
		iteminfoPn.add(cateLb);

		// item explanation
		
		//BufferedImage explanationLb1 = delegator.getImage("icon/detailPn.png");
		//ImageIcon explanationLbimg = new ImageIcon(explanationLb1);
	//	explanationLb = new JLabel("제품 설명 ",explanationLbimg,JLabel.LEFT);
		explanationLb = new JLabel("제품 설명 ");
		//explanationLb.setOpaque(false);
		explanationLb.setBounds(10, 180, 100,20);
		explanationLb.setForeground(textColor);
		//explanationLb.setOpaque(true);
		//explanationLb.setBackground(Color.white);
		explanationLb.setVerticalAlignment(SwingConstants.TOP);
		iteminfoPn.add(explanationLb);

		//detailExplanationLb
		detailExplanationLb = new JLabel();
		detailExplanationLb.setText("<html><p>"+m_itemDto.getContent()+"</p></html>");
		detailExplanationLb.setBounds(10, 210, 300, 200);
	//	detailExplanationLb.setOpaque(true);
		//detailExplanationLb.setBackground(Color.white);
		detailExplanationLb.setForeground(new Color(120, 110, 110));
		detailExplanationLb.setVerticalAlignment(SwingConstants.TOP);
		iteminfoPn.add(detailExplanationLb);

		// chatBtn
		BufferedImage chatImage = delegator.getImage("icon/chatting.png");
		ImageIcon chatIcon = new ImageIcon(chatImage);
		chatBtn = new JButton(chatIcon);
		chatBtn.setBounds(545, 55, 350, 60);
		chatBtn.setOpaque(false);
		chatBtn.setBorderPainted(false);
		chatBtn.setFocusPainted(false);
		chatBtn.setContentAreaFilled(false);
		chatBtn.addActionListener(this);
	

		// deleteBtn
		BufferedImage deleteImage = delegator.getImage("icon/delete.png");
		ImageIcon deleteIcon = new ImageIcon(deleteImage);
		deleteBtn = new JButton(deleteIcon);
		deleteBtn.setBounds(545, 555, 165, 35);
		deleteBtn.setOpaque(false);
		deleteBtn.setBorderPainted(false);// 외곽선 없애줌
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setFocusPainted(false);
		deleteBtn.addActionListener(this);

		// go back to list button
		BufferedImage backImage = delegator.getImage("icon/back.png");
		ImageIcon backIcon = new ImageIcon(backImage);
		listBtn = new JButton(backIcon);
		// listBtn.setBounds(200, 10, 120, 30);
		listBtn.setBounds(728, 555, 170, 35);
		listBtn.setOpaque(false);
		listBtn.setForeground(commonRedColor);
		listBtn.setBorderPainted(false);// 외곽선 없애줌
		listBtn.setContentAreaFilled(false);
		listBtn.setFocusPainted(false);
		listBtn.addActionListener(this);
		// iteminfoPn.add(listBtn);
		// iteminfoPn.setBounds(580, 135, 340, 400);

		// completeBtn
		BufferedImage completeImage = delegator.getImage("icon/complete.png");
		ImageIcon completeIcon = new ImageIcon(completeImage);
		completeBtn = new JButton(completeIcon);
		completeBtn.setBounds(550, 590, 340, 40);
		completeBtn.setOpaque(false);
		completeBtn.setBorderPainted(false);// 외곽선 없애줌
		completeBtn.setContentAreaFilled(false);
		completeBtn.setFocusPainted(false);
		completeBtn.addActionListener(this);

		detailPn.add(completeBtn);
		detailPn.add(listBtn);
		detailPn.add(deleteBtn);
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
		Delegator delegator = Delegator.getInstance();
		JButton obj = (JButton) e.getSource();

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
			int result = delegator.personController.Logout();
			if (result == 0) {
				this.dispose();
			}
		} else if (obj == searchBtn) {
			String searchWord = searchTextF.getText();
			delegator.itemBbsController.searchList(searchWord);
			this.dispose();
		} else if (obj == listBtn) {
			delegator.itemBbsController.allItemList();
			this.dispose();

		} else if (obj == deleteBtn) {

			if (delegator.getCurrent_user() != null) {
				System.out.println("login Success");
				String WriteId = m_itemDto.getUser_id();
				String ViewId = delegator.getCurrent_user().getId();

				if (ViewId.equals(WriteId)) {
					boolean deleteCK = delegator.itemBbsController.setDeleteItemBbs(m_itemDto);
					if (deleteCK) {
						JOptionPane.showMessageDialog(null, "글이 삭제 되었습니다.");
						delegator.itemBbsController.allItemList();
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "삭제가 정상적으로 이루어지지 않았습니다.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "작성자만이 게시글을 삭제할 수 있습니다.");
				}

			} else {
				JOptionPane.showMessageDialog(null, "로그인이 필요합니다.");
				delegator.personController.Login();
				this.dispose();
			}
		} else if (obj == completeBtn) {
			if (delegator.getCurrent_user() != null) {
				System.out.println("login Success");
				String WriteId = m_itemDto.getUser_id();
				String ViewId = delegator.getCurrent_user().getId();

				if (ViewId.equals(WriteId)) {
					boolean completeCK = delegator.itemBbsController.setCompleteItemBbs(m_itemDto);

					if (completeCK) {
						JOptionPane.showMessageDialog(null, "완료 처리 되었습니다.");
						delegator.itemBbsController.allItemList();
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "완료 처리할 수 없습니다.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "작성자만이 완료 할 수 있습니다.");
				}

			} else {
				JOptionPane.showMessageDialog(null, "로그인이 필요합니다.");
				delegator.personController.Login();
				this.dispose();
			}

		}
	}
}
