package view;

import delegator.Delegator;
import dto.RoomDto;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class RoomList extends JFrame implements MouseListener {
    List<RoomDto> list = null;
    Color commonColor = new Color(255,174,174);

    int roomHeight = 70;
    public RoomList(List<RoomDto> list){
        super("Room List");

        this.list = list;

        Container contentPane = getContentPane();
        contentPane.setBackground(commonColor);
        contentPane.setLayout(null);


        JPanel header = new JPanel();
        header.setBackground(commonColor);
        header.setBounds(0, 0, 375, 50);
        header.setLayout(null);

        JLabel headerLabel = new JLabel();
        headerLabel.setFont(new Font("잇소톡", Font.BOLD, 20));
        headerLabel.setText("잇소톡");
        headerLabel.setBounds(150, 5, 75, 20);
        headerLabel.setForeground(Color.white);
        header.add(headerLabel);
        Delegator delegator = Delegator.getInstance();

        JLabel headerId = new JLabel();
        headerId.setFont(new Font(delegator.getCurrent_user().getId(), Font.ITALIC, 15));
        headerId.setText(delegator.getCurrent_user().getId());
        headerId.setBounds(150, 30, 100, 15);
        headerId.setForeground(Color.white);
        header.add(headerId);

        contentPane.add(header);



        JPanel rooms = new JPanel();
        rooms.setLayout(null);
        rooms.setPreferredSize(new Dimension(340, list.size()*roomHeight));

        for (int i=0; i<list.size(); i++){
            JPanel room = new JPanel();

            room.setBounds(10, i*(roomHeight+1), 340, roomHeight);
            room.setBorder(new LineBorder(commonColor, 2));
            room.setBackground(Color.white);
            room.setLayout(null);

            JLabel roomLabel = new JLabel();

            if(delegator.getCurrent_user().getId().equals(list.get(i).getTarget_id())){
                roomLabel.setText(list.get(i).getUser_id()+" 님과의 대화");
            }else{
                roomLabel.setText(list.get(i).getTarget_id()+" 님과의 대화");
            }
            roomLabel.setFont(new Font("Dialog", Font.BOLD, 15));
            roomLabel.setBounds(30, 20, 330, 30);

            room.add(roomLabel);
            rooms.add(room);
        }
        rooms.addMouseListener(this);

        JScrollPane roomScr = new JScrollPane(rooms);
        roomScr.setBounds(0, 50, 375, 545);
        contentPane.add(roomScr);

        JButton exit = new JButton("EXIT");
        exit.setBounds(0, 595, 375, 50);
        exit.setBackground(Color.BLACK);
        exit.setForeground(commonColor);
        exit.addActionListener((ActionEvent e)->{
            this.dispose();
        });
        contentPane.add(exit);

        setBounds(100, 100, 375, 667);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Delegator delegator = Delegator.getInstance();

        if(e!=null){
            int i = e.getY()/(roomHeight+1);
            
            if( i < list.size()) {
            	delegator.roomController.Chat(list.get(i));            	
            	this.dispose();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
