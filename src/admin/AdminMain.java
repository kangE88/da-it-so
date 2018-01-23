package admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import delegator.Delegator;

public class AdminMain extends JFrame implements ActionListener {

	private JButton searchBtn;
	private JTextField searchTextF;
	private JPanel headerLogo;
	
	JButton itemListBtn, abilityListBtn, userListBtn, chatBtn;

	String iconImgUrl = "/Users/parker/Desktop/img/icon/";
//	String iconImgUrl = "E:\\icon/";

	public AdminMain() {

		Container contentPane = getContentPane();
		
		contentPane.setBounds(0, 0, 1680, 1050);
		contentPane.setBackground(Color.white);
		
		// header
		headerLogo = new JPanel() {
			ImageIcon headerimage = new ImageIcon(iconImgUrl + "headerlogo.png");

			// 사이즈맞게 배경삽임
			public void paintComponent(Graphics g) {
				g.drawImage(headerimage.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};

		// logo
		JPanel logoPn = new JPanel() {
			ImageIcon image = new ImageIcon(iconImgUrl + "logo.png");

			// 사이즈맞게 배경삽임
			public void paintComponent(Graphics g) {
				g.drawImage(image.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};


		// Header
		Color commonRedColor = new Color(218, 0, 0);
		JPanel headerPn = new JPanel();
		headerPn.setBackground(commonRedColor);
		headerPn.setSize(1680, 60);
		headerPn.setLayout(null);

		// headerlogo
		headerLogo.setBounds(15, 25, 71, 15);
		headerPn.add(headerLogo);

		// sidePn
		Color sideC = new Color(250, 250, 250);
		JPanel sidePn = new JPanel();
		sidePn.setBounds(0, 60, 400, 1000);
		sidePn.setLayout(null);
		sidePn.setBackground(sideC);

		// logoPn
		logoPn.setBounds(40, 30, 300, 66);
		sidePn.add(logoPn);

//		// SearchText
//		searchTextF = new JTextField("검색어");
//		searchTextF.setBounds(40, 160, 260, 40);
//		searchTextF.setBorder(new LineBorder(commonRedColor, 5));
//		sidePn.add(searchTextF);
//
//		// searchBtn
//		searchBtn = new JButton(new ImageIcon(iconImgUrl + "search.png"));
//		searchBtn.setBounds(300, 160, 40, 40);
//		searchBtn.setOpaque(false); // 투명하게
//		searchBtn.setContentAreaFilled(false);// 내용영역 채우기x
//		sidePn.add(searchBtn);
		
		JLabel adminLabel = new JLabel();
		adminLabel.setText("관리자 페이지");
		adminLabel.setFont(new Font("font", Font.BOLD, 25));
		adminLabel.setForeground(commonRedColor);
		adminLabel.setBounds(130, 180, 260, 25);
		sidePn.add(adminLabel);

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
