package view;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import delegator.Delegator;
import dto.AbilityBbs;
import dto.Category;
import service.AbilityService;

public class AbilityWrite extends JFrame implements ActionListener {

	private JButton loginBtn, logoutBtn, signupBtn, searchBtn, imgAdd1, imgAdd2, imgAdd3, imgAdd4, writeBtn, listBtn;
	private JTextField searchTextF, titleTextF, img1TextF, img2TextF, img3TextF, img4TextF, abilityTextF;
	private JTextPane contentTextPn;
	private JPanel headerLogo;
	private JComboBox cateCombo;

	JPanel category;

	private JFileChooser jfc = new JFileChooser();
	private String filename1, filename2, filename3, filename4;

	JPanel headerPn, sidePn, logoPn, catePn, writePn;
	JLabel cateLb, titleLb, imgLb1, imgLb2, imgLb3, imgLb4, abilityLb, contentLb;

	List<Category> categoryList = null;

	public AbilityWrite(List<Category> categoryList) {

		Delegator delegator = Delegator.getInstance();
		this.categoryList = categoryList;

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

		// logo
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

		// mainView
		Container cn = getContentPane();

		cn.setBounds(0, 0, 1350, 750);
		cn.setBackground(Color.white);

		setBounds(0, 0, 1350, 750);
		setLayout(null);
		setVisible(true);

		// Header
		Color commonRedColor = new Color(218, 0, 0);
		headerPn = new JPanel();
		headerPn.setBackground(commonRedColor);
		headerPn.setSize(1350, 60);
		headerPn.setLayout(null);

		// headerlogo
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
			loginBtn.setContentAreaFilled(false);
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

		// logoPn
		logoPn.setBounds(40, 30, 300, 66);
		sidePn.add(logoPn);

		// SearchText
		searchTextF = new JTextField("검색어");
		searchTextF.setBounds(40, 160, 260, 40);
		searchTextF.setBorder(new LineBorder(commonRedColor, 5));
		sidePn.add(searchTextF);

		// searchBtn
		// searchBtn
		BufferedImage searchImg = delegator.getImage("icon/search.png");
		ImageIcon searchIcon = new ImageIcon(searchImg);
		searchBtn = new JButton(searchIcon);
		searchBtn.setBounds(300, 160, 40, 40);
		searchBtn.setOpaque(false); // 투명하게
		searchBtn.setContentAreaFilled(false);// 내용영역 채우기x
		searchBtn.addActionListener(this);
		sidePn.add(searchBtn);

		// catePn
		catePn = new JPanel();
		catePn.setLayout(new GridLayout(3, 3, 10, 10));
		catePn.setBounds(25, 290, 350, 350);
		catePn.setBackground(Color.WHITE);

		for (int i = 0; i < categoryList.size(); i++) {
			BufferedImage categoryImage = delegator.getImage("ability/"+ categoryList.get(i).getTitle() +".png");
			ImageIcon categoryIcon = new ImageIcon(categoryImage);
			
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

		// listPn
		writePn = new JPanel();
		writePn.setLayout(null);
		writePn.setBounds(380, 30, 1050, 670);

		// go back to list button
		listBtn = new JButton("목록으로 돌아가기");
		listBtn.setBounds(500, 100, 120, 30);
		listBtn.setOpaque(false);
		listBtn.setForeground(commonRedColor);
		listBtn.addActionListener(this);
		writePn.add(listBtn);

		// category
		cateLb = new JLabel("카테고리");
		cateLb.setBounds(100, 100, 100, 30);
		writePn.add(cateLb);

		String category[] = new String[categoryList.size()];

		for (int i = 0; i < category.length; i++) {
			category[i] = categoryList.get(i).getDescription();
		}

		cateCombo = new JComboBox(category);
		cateCombo.setBounds(210, 100, 150, 30);
		writePn.add(cateCombo);

		// title
		titleLb = new JLabel("제목");
		titleLb.setBounds(100, 150, 150, 30);
		writePn.add(titleLb);

		titleTextF = new JTextField();
		titleTextF.setBounds(210, 150, 150, 30);
		writePn.add(titleTextF);

		// img
		imgLb1 = new JLabel("사진1");
		imgLb1.setBounds(100, 190, 100, 30);
		writePn.add(imgLb1);

		img1TextF = new JTextField();
		img1TextF.setBounds(210, 190, 300, 30);
		writePn.add(img1TextF);

		imgAdd1 = new JButton("이미지 1");
		imgAdd1.setBounds(520, 190, 100, 30);
		imgAdd1.addActionListener(this);
		writePn.add(imgAdd1);

		imgLb2 = new JLabel("사진2");
		imgLb2.setBounds(100, 230, 100, 30);
		writePn.add(imgLb2);

		img2TextF = new JTextField();
		img2TextF.setBounds(210, 230, 300, 30);
		writePn.add(img2TextF);

		imgAdd2 = new JButton("이미지 2");
		imgAdd2.setBounds(520, 230, 100, 30);
		imgAdd2.addActionListener(this);
		writePn.add(imgAdd2);

		imgLb3 = new JLabel("사진3");
		imgLb3.setBounds(100, 270, 100, 30);
		writePn.add(imgLb3);

		img3TextF = new JTextField();
		img3TextF.setBounds(210, 270, 300, 30);
		writePn.add(img3TextF);

		imgAdd3 = new JButton("이미지 3");
		imgAdd3.setBounds(520, 270, 100, 30);
		imgAdd3.addActionListener(this);
		writePn.add(imgAdd3);

		imgLb4 = new JLabel("사진4");
		imgLb4.setBounds(100, 310, 100, 30);
		writePn.add(imgLb4);

		img4TextF = new JTextField();
		img4TextF.setBounds(210, 310, 300, 30);
		writePn.add(img4TextF);

		imgAdd4 = new JButton("이미지 4");
		imgAdd4.setBounds(520, 310, 100, 30);
		imgAdd4.addActionListener(this);
		writePn.add(imgAdd4);

		// abilityLb
		abilityLb = new JLabel("보유능력");
		abilityLb.setBounds(100, 350, 100, 30);
		writePn.add(abilityLb);

		JLabel infoLabel = new JLabel("보유 능력을 등록해주세요.");
		infoLabel.setBounds(220, 380, 300, 20);
		writePn.add(infoLabel);

		abilityTextF = new JTextField();
		abilityTextF.setBounds(210, 350, 300, 30);
		abilityTextF.setText("ex) #javascript #css #java");
		writePn.add(abilityTextF);

		// content
		contentLb = new JLabel("내용");
		contentLb.setBounds(100, 410, 100, 30);
		writePn.add(contentLb);

		contentTextPn = new JTextPane();
		contentTextPn.setBounds(210, 410, 410, 200);
		writePn.add(contentTextPn);

		writeBtn = new JButton("등록");
		writeBtn.setBounds(520, 610, 100, 30);
		writeBtn.addActionListener(this);
		writePn.add(writeBtn);

		add(sidePn);
		add(headerPn);
		add(writePn);

	}


	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton) e.getSource();

		Delegator delegator = Delegator.getInstance();

		if (btn == listBtn) {
			delegator.abilityBbsController.allAbilityList();
			this.dispose();
		} else if (btn == loginBtn) {
			delegator.personController.Login();
			this.dispose();
		} else if (btn == signupBtn) {
			delegator.personController.SignUp();
			this.dispose();
		} else if (btn == logoutBtn) {
			int result =delegator.personController.Logout();
			if (result == 0) {
				this.dispose();
			}	
		} else if (btn == searchBtn) {
			String searchWord = searchTextF.getText();
			delegator.abilityBbsController.searchList(searchWord);
			this.dispose();
		}

		if (btn == imgAdd1) {
			if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				img1TextF.setText(jfc.getSelectedFile().toString());
				filename1 = jfc.getSelectedFile().getName();
			}

		}
		if (btn == imgAdd2) {
			if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				img2TextF.setText(jfc.getSelectedFile().toString());
				filename2 = jfc.getSelectedFile().getName();
			}
		}
		if (btn == imgAdd3) {
			if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				img3TextF.setText(jfc.getSelectedFile().toString());
				filename3 = jfc.getSelectedFile().getName();
			}
		}
		if (btn == imgAdd4) {
			if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인
				img4TextF.setText(jfc.getSelectedFile().toString());
				filename4 = jfc.getSelectedFile().getName();
			}
		}

		if (e.getActionCommand().equals("등록")) {
			if (!img1TextF.getText().isEmpty()) {
				filesend fs = new filesend(img1TextF.getText(), delegator.getCurrent_user().getId()+"-fileSecretKey0917-"+filename1);
			}
			if (!img2TextF.getText().isEmpty()) {
				filesend fs = new filesend(img2TextF.getText(), delegator.getCurrent_user().getId()+"-fileSecretKey0917-"+filename2);
			}
			if (!img3TextF.getText().isEmpty()) {
				filesend fs = new filesend(img3TextF.getText(), delegator.getCurrent_user().getId()+"-fileSecretKey0917-"+filename3);
			}
			if (!img4TextF.getText().isEmpty()) {
				filesend fs = new filesend(img4TextF.getText(), delegator.getCurrent_user().getId()+"-fileSecretKey0917-"+filename4);
			}

			AbilityBbs abilityDto = new AbilityBbs();
			String id = delegator.getCurrent_user().getId();

			int categoryIndex = cateCombo.getSelectedIndex();
			abilityDto.setCategory_id(categoryList.get(categoryIndex).getSeq());
			abilityDto.setTitle(titleTextF.getText());
			
			abilityDto.setImgurl1("userImg/"+delegator.getCurrent_user().getId()+"/"+filename1);
			abilityDto.setImgurl2("userImg/"+delegator.getCurrent_user().getId()+"/"+filename2);
			abilityDto.setImgurl3("userImg/"+delegator.getCurrent_user().getId()+"/"+filename3);
			abilityDto.setImgurl4("userImg/"+delegator.getCurrent_user().getId()+"/"+filename4);
			
			if (abilityTextF.getText().equals("ex) #javascript #css #java")) {
				abilityDto.setAbility("");
			} else {
				abilityDto.setAbility(abilityTextF.getText());
			}
			abilityDto.setContent(contentTextPn.getText());
			abilityDto.setUser_id(id);

			if(abilityDto.getTitle().equals("")) {
				JOptionPane.showMessageDialog(null, "제목을 입력해주세요.");
			}else if(abilityDto.getContent().equals("")){
				JOptionPane.showMessageDialog(null, "내용을 입력해주세요.");
			}else {
				boolean result = delegator.abilityBbsController.insert(abilityDto);
				if(result) {
					JOptionPane.showMessageDialog(null, "게시글이 등록 됐습니다.");
					System.out.println(abilityDto);
					delegator.abilityBbsController.AbilityDetail(abilityDto);
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "게시글이 등록할 수 없습니다.");
				}				
			}
			
		}
	}
}
