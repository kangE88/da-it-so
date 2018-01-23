package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import delegator.Delegator;
import dto.Person;

public class Login extends JFrame implements ActionListener {

	private JButton signupBtn;
	private JTextField id;
	private JPasswordField pwd;
	private JButton signInBtn;

	public Login() {

		JPanel headerPn;

		// mainView
		Container contentPane = getContentPane();

		// Header
		Color commonRedColor = new Color(218, 0, 0);
		Color commonGrayColor = new Color(250, 250, 250);

		headerPn = new JPanel();
		headerPn.setBackground(commonRedColor);
		headerPn.setSize(1350, 60);
		headerPn.setLayout(null);

		// signupBtn
		signupBtn = new JButton("회원가입");
		signupBtn.setBounds(1240, 20, 100, 30);
		signupBtn.setOpaque(false); // 투명하게
		signupBtn.setBorderPainted(false);// 외곽선 없애줌
		signupBtn.setFont(new Font("회원가입", Font.BOLD, 12));
		signupBtn.setBackground(commonRedColor);
		signupBtn.setForeground(Color.white);
		signupBtn.addActionListener(this);
		headerPn.add(signupBtn);

		Delegator delegator = Delegator.getInstance();

		// headerlogo
		BufferedImage headerImg = delegator.getImage("icon/headerlogo.png");
		ImageIcon headerimage = new ImageIcon(headerImg);
		JPanel headerLogo = new JPanel() {
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
		contentPane.add(headerPn);

		// main area
		JPanel main = new JPanel();
		main.setLayout(null);
		main.setBackground(new Color(250, 250, 250));
		main.setBounds(0, 0, 1350, 750);

		// login area
		JPanel login = new JPanel();
		login.setLayout(null);
		login.setBackground(Color.white);
		login.setBorder(new LineBorder(commonRedColor, 3));
		login.setLocation(170, 110);
		login.setSize(1000, 550);

		
		BufferedImage logoImg = delegator.getImage("icon/logo.png");
		ImageIcon logoIcon = new ImageIcon(logoImg);
		
		JPanel loginLogo = new JPanel() {
			// 사이즈맞게 배경삽임
			public void paintComponent(Graphics g) {
				g.drawImage(logoIcon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};

		loginLogo.setBounds(350, 80, 300, 70);

		login.add(loginLogo);

		Font labelFont = new Font("fonts", Font.BOLD, 20);

		JLabel idLabel = new JLabel("e-mail");
		idLabel.setBounds(150, 220, 150, 50);
		idLabel.setFont(labelFont);
		login.add(idLabel);

		id = new JTextField();
		id.setBounds(300, 220, 400, 50);
		id.setBackground(commonGrayColor);
		login.add(id);

		JLabel pwdLabel = new JLabel("Password");
		pwdLabel.setBounds(150, 310, 150, 50);
		pwdLabel.setFont(labelFont);
		login.add(pwdLabel);

		pwd = new JPasswordField();
		pwd.setBounds(300, 310, 400, 50);
		pwd.setBackground(commonGrayColor);
		login.add(pwd);

		signInBtn = new JButton("Sign In");
		signInBtn.setBounds(720, 220, 100, 135);
		signInBtn.setBorder(new LineBorder(commonRedColor, 2));
		signInBtn.addActionListener(this);
		signInBtn.setContentAreaFilled(false);
		login.add(signInBtn);

		main.add(login);
		contentPane.add(main);

		setBounds(0, 0, 1350, 750);
		setLayout(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Delegator delegator = Delegator.getInstance();
		Object obj = e.getSource();

		if (obj == signInBtn) {
			String _id = id.getText();
			char[] _pwd = pwd.getPassword();

			if (_id.length() < 5) {
				JOptionPane.showMessageDialog(null, "id length must be more than 5");
				pwd.setText("");
			} else if (_pwd.length < 5) {
				JOptionPane.showMessageDialog(null, "password length must be more than 5");
				pwd.setText("");
			} else {
				Person person = delegator.personController.signIn(id.getText(), pwd.getPassword());

				if (person != null) {
					// login success
					_id = person.getId();
					if (_id.equals("admin")) {
						System.out.println("Admin Login");
						delegator.adminController.AdminMain();
						this.dispose();
					} else {
						System.out.println("User Login");
						delegator.mainController.Main();
						this.dispose();
					}
				} else {
					// login fail
					JOptionPane.showMessageDialog(null, "wrong password");
					pwd.setText("");
				}
			}
		} else if (obj == signupBtn) {
			delegator.personController.SignUp();
			this.dispose();
		}
	}

}
