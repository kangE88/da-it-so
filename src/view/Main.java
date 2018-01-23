package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import delegator.Delegator;

public class Main extends JFrame implements ActionListener {

	
	private JButton loginBtn, logoutBtn, signupBtn;
	private JButton item, ability;
	
	JButton test;
	
	public Main() {

		String iconImgUrl = "/Users/parker/Desktop/img/icon/";
		JPanel headerPn;

		// header
		JPanel headerLogo = new JPanel() {
			ImageIcon headerimage = new ImageIcon(iconImgUrl + "headerlogo.png");

			// 사이즈맞게 배경삽임
			public void paintComponent(Graphics g) {
				g.drawImage(headerimage.getImage(), 0, 0, null);
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

		Delegator delegator = Delegator.getInstance();
		
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
		}else {
			// logoutBtn
			logoutBtn = new JButton("로그아웃");
			logoutBtn.setBounds(1240, 20, 100, 30);
			logoutBtn.setOpaque(false); // 투명하게
			logoutBtn.setBorderPainted(false);// 외곽선 없애줌
			logoutBtn.setFont(new Font("로그아웃", Font.BOLD, 12));
			logoutBtn.setBackground(commonRedColor);
			logoutBtn.setForeground(Color.white);
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
		
		item = new JButton("item");
		item.setBounds(90, 100, 400, 350);
		item.addActionListener(this);
		center.add(item);
		
		ability = new JButton("ability");
		ability.setBounds(510, 100, 400, 350);
		ability.addActionListener(this);
		center.add(ability);
		
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
