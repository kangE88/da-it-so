package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import delegator.Delegator;

public class Main extends JFrame implements ActionListener {

	
	private JButton loginBtn, logoutBtn, signupBtn;
	private JButton item, ability;
	private JLabel adminContact;
	
	public Main() {
		JPanel headerPn;
		
		Delegator delegator = Delegator.getInstance();
		BufferedImage headerlogo = delegator.getImage("icon/headerlogo.png");
	
		ImageIcon headerImage = new ImageIcon(headerlogo);
		// header
		JPanel headerLogo = new JPanel() {
			// 사이즈맞게 배경삽임
			public void paintComponent(Graphics g) {
				g.drawImage(headerImage.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};

		// mainView
		Container contentPane = getContentPane();
		
		// Header
		Color commonRedColor = new Color(218, 0, 0);
		Color commonGrayColor = new Color(250, 250, 250);
		
		headerPn = new JPanel();
		headerPn.setBackground(commonRedColor);
		headerPn.setSize(1350, 60);
		headerPn.setLayout(null);

		// headerlogo
		headerLogo.setBounds(15, 25, 71, 15);
		headerPn.add(headerLogo);
		
		if(delegator.getCurrent_user()==null) {
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
		}else {
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

		contentPane.add(headerPn);

		// main area
		JPanel main = new JPanel();
		main.setLayout(null);
		main.setBackground(new Color(250, 250, 250));

		main.setBounds(0, 0, 1350, 750);
		
		JPanel center = new JPanel();
		center.setLayout(null);
		center.setBorder(new LineBorder(commonRedColor, 3));
		center.setLocation(170, 110);
		center.setSize(1000, 550);
		
		BufferedImage itemImage = delegator.getImage("icon/item.png");
		ImageIcon itemIcon = new ImageIcon(itemImage);
		item = new JButton(itemIcon);
		item.setBounds(90, 100, 400, 350);
        item.setBorderPainted(false);
        item.setContentAreaFilled(false);
        item.setFocusable(false);
		item.addActionListener(this);
		center.add(item);
		
		BufferedImage abilityImage = delegator.getImage("icon/ability.png");
		ImageIcon abilityIcon = new ImageIcon(abilityImage);
		ability = new JButton(abilityIcon);
		ability.setBounds(510, 100, 400, 350);
        ability.setBorderPainted(false);
        ability.setContentAreaFilled(false);
        ability.setFocusable(false);
		ability.addActionListener(this);
		center.add(ability);
		
		adminContact = new JLabel("관리자에게 문의하기");
		adminContact.setLayout(new FlowLayout());
		adminContact.setLocation(600, 675);
		adminContact.setForeground(commonRedColor);
		adminContact.setSize(200, 20);
		adminContact.setFont(new Font("admin", Font.BOLD, 15));
		adminContact.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(delegator.getCurrent_user() == null) {
					JOptionPane.showMessageDialog(null, "로그인 후 이용 가능합니다.");
				}else {
					delegator.roomController.checkRoom("admin");					
				}
			}
		});
		contentPane.add(adminContact);
		
		contentPane.add(center);

		setBounds(0, 0, 1350, 750);
		setLayout(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Delegator delegator = Delegator.getInstance();
		Object obj = e.getSource();
		
		if(obj == loginBtn) {
			delegator.personController.Login();
			this.dispose();
		}else if(obj == signupBtn) {
			delegator.personController.SignUp();
			this.dispose();
		}else if(obj == logoutBtn) {
			delegator.personController.Logout();
			this.dispose();
		}
		
		//select itemMain
		if(obj == item) {
			System.out.println("==itemMain select==");
			delegator.itemBbsController.allItemList();
			this.dispose();
		}
		//select AbilityMain 
		if(obj == ability){
			System.out.println("==abilityMain select==");
			delegator.abilityBbsController.allAbilityList();
			this.dispose();
		}
	}

}
