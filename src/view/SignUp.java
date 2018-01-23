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

public class SignUp extends JFrame implements ActionListener {

	private JButton loginBtn, logoutBtn, signBtn, checkIdBtn;
	private JTextField id, phone, nick;
	private JPasswordField pwd, pwd2;
	private JButton signUpBtn;
	private boolean hidden = false;

	public SignUp() {
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
		
		// headerlogo
		headerLogo.setBounds(15, 25, 71, 15);
		headerLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				delegator.mainController.Main();
				dispose();
			}
		});
		headerPn.add(headerLogo);
		contentPane.add(headerPn);

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
		} else {
			// logoutBtn
			logoutBtn = new JButton("로그아웃");
			logoutBtn.setBounds(1240, 20, 100, 30);
			logoutBtn.setOpaque(false); // 투명하게
			logoutBtn.setBorderPainted(false);// 외곽선 없애줌
			logoutBtn.setFont(new Font("로그아웃", Font.BOLD, 12));
			logoutBtn.setBackground(commonRedColor);
			logoutBtn.setForeground(Color.white);
			logoutBtn.setContentAreaFilled(false);
			headerPn.add(logoutBtn);
		}

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

		loginLogo.setBounds(350, 40, 300, 70);
		login.add(loginLogo);

		Font labelFont = new Font("fonts", Font.BOLD, 20);

		JLabel titleLabel = new JLabel("회원 가입");
		titleLabel.setBounds(450, 130, 300, 50);
		titleLabel.setFont(new Font("font", Font.BOLD, 30));
		titleLabel.setForeground(commonRedColor);
		login.add(titleLabel);

		JLabel idLabel = new JLabel("e-mail");
		idLabel.setBounds(140, 200, 170, 50);
		idLabel.setFont(labelFont);
		login.add(idLabel);

		id = new JTextField();
		id.setBounds(310, 200, 300, 50);
		id.setBackground(commonGrayColor);
		login.add(id);

		checkIdBtn = new JButton("중복 확인");
		checkIdBtn.setBounds(620, 200, 100, 50);
		checkIdBtn.setBorder(new LineBorder(commonRedColor, 2));
		checkIdBtn.addActionListener(this);
		login.add(checkIdBtn);

		JLabel pwdLabel = new JLabel("Password");
		pwdLabel.setBounds(140, 260, 170, 50);
		pwdLabel.setFont(labelFont);
		login.add(pwdLabel);

		pwd = new JPasswordField();
		pwd.setBounds(310, 260, 410, 50);
		pwd.setBackground(commonGrayColor);
		login.add(pwd);

		JLabel pwd2Label = new JLabel("Password Check");
		pwd2Label.setBounds(140, 320, 170, 50);
		pwd2Label.setFont(labelFont);
		login.add(pwd2Label);

		pwd2 = new JPasswordField();
		pwd2.setBounds(310, 320, 410, 50);
		pwd2.setBackground(commonGrayColor);
		login.add(pwd2);

		JLabel nickLabel = new JLabel("Nickname");
		nickLabel.setBounds(140, 380, 170, 50);
		nickLabel.setFont(labelFont);
		login.add(nickLabel);

		nick = new JTextField();
		nick.setBounds(310, 380, 410, 50);
		nick.setBackground(commonGrayColor);
		login.add(nick);

		JLabel phoneLabel = new JLabel("Phone");
		phoneLabel.setBounds(140, 440, 150, 50);
		phoneLabel.setFont(labelFont);
		login.add(phoneLabel);

		phone = new JTextField();
		phone.setBounds(310, 440, 410, 50);
		phone.setBackground(commonGrayColor);
		login.add(phone);

		signUpBtn = new JButton("Sign Up");
		signUpBtn.setBounds(730, 440, 200, 50);
		signUpBtn.setBorder(new LineBorder(commonRedColor, 2));
		signUpBtn.addActionListener(this);
		login.add(signUpBtn);

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

		if (obj == signUpBtn) {
			String _id = id.getText();
			char[] _pwd = pwd.getPassword();

			System.out.println(hidden);

			if (_id.length() < 5) {
				JOptionPane.showMessageDialog(null, "id length must be more than 5");
				pwd.setText("");
			} else if (_pwd.length < 5) {
				JOptionPane.showMessageDialog(null, "password length must be more than 5");
				pwd.setText("");
			} else if (!checkPwd()) {
				JOptionPane.showMessageDialog(null, "비밀 번호가 일치하지 않습니다.");
				pwd.setText("");
				pwd2.setText("");
			} else if (!checkPhone()) {
				JOptionPane.showMessageDialog(null, "전화번호는 숫자만 입력해주세요. ");
			} else {
				Person person = new Person();
				person.setId(id.getText());
				person.setPwd(pwd.getPassword());
				person.setNick(nick.getText());
				person.setPhone(phone.getText());

				Person resultPerson = null;

				if (hidden) {
					resultPerson = delegator.personController.insert(person);

					if (resultPerson != null) {
						JOptionPane.showMessageDialog(null, "회원 가입에 성공했습니다. ");
						delegator.personController.Login();
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "회원 가입에 실패했습니다. 다시 시도해주세요. ");
						pwd.setText("");
						pwd2.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요.");
				}
			}
		} else if (obj == loginBtn) {
			delegator.personController.Login();
			this.dispose();
		} else if (obj == logoutBtn) {
			delegator.personController.Logout();
			this.dispose();
		} else if (obj == checkIdBtn) {
			boolean result = delegator.personController.checkId(id.getText());

			if (result) {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다. ");
				hidden = true;
			} else {
				JOptionPane.showMessageDialog(null, "이미 사용중 아이디 입니다. ");
				hidden = false;
			}
		}
	}

	// 비밀번호가 같은지 확인하는 메소드
	private boolean checkPwd() {
		char[] _pwd1 = pwd.getPassword();
		char[] _pwd2 = pwd2.getPassword();

		if (_pwd1.length != _pwd2.length) {
			return false;
		} else {
			for (int i = 0; i < _pwd1.length; i++) {
				if (_pwd1[i] != _pwd2[i]) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkPhone() {
		String _phone = phone.getText();

		for (int i = 0; i < _phone.length(); i++) {
			char ch = _phone.charAt(i);
			if ((int) ch < 47 || (int) ch > 58) {
				return false;
			}
		}
		return true;
	}
}
