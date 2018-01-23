package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import delegator.Delegator;

public class ImageDetail extends JFrame{

	public ImageDetail(String img) {
		super("ImageView");
		Delegator delegator = Delegator.getInstance();
		BufferedImage image = delegator.getImage(img);
		if(image == null) {
			image = delegator.getImage("icon/noimg.png");
		}
		ImageIcon imageIcon = new ImageIcon(image);
			
		JPanel imagePn = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(imageIcon.getImage(), 0, 0, 800, 600,  null);
				setOpaque(false);
				super.paintComponents(g);
			}
		};
		
		imagePn.setBounds(0, 0, 800, 600);
		add(imagePn);
		setBounds(0, 0, 800, 600);
		setLayout(null);
		setVisible(true);
	}
}
