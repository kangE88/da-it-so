package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import delegator.Delegator;
import dto.ChatDto;
import dto.RoomDto;

public class Room extends JFrame {
    List<ChatDto> list;
    Color commonColor = new Color(255,174,174);

    RoomDto room;
    int balloonHeight = 30;

    JTextField inputText;

    public Room(List<ChatDto> list, RoomDto room){
        super("Chatting Room");

        this.list = list;
        this.room = room;

        Container contentPane = getContentPane();
        contentPane.setBackground(commonColor);
        contentPane.setLayout(null);


        JPanel header = new JPanel();
        header.setBackground(commonColor);
        header.setBounds(0, 0, 375, 50);
        header.setLayout(null);


        JLabel backLabel = new JLabel(" < ");
        backLabel.setSize(30, 30);
        backLabel.setFont(new Font("", Font.BOLD, 30));
        backLabel.setForeground(Color.white);

        JButton back = new JButton();
        back.setBounds(0, 0, 50, 50);
        back.setForeground(commonColor);
        back.setBorder(new LineBorder(commonColor, 1));
        back.setBackground(commonColor);
        back.setOpaque(false);

        Delegator delegator = Delegator.getInstance();

        back.addActionListener((ActionEvent e)->{
            delegator.roomController.RoomList();
            this.dispose();
        });

        JLabel refreshLabel = new JLabel(" < ");
        refreshLabel.setSize(30, 30);
        refreshLabel.setFont(new Font("", Font.BOLD, 30));
        refreshLabel.setForeground(Color.white);

        JButton refresh = new JButton();
        refresh.setBounds(0, 0, 50, 50);
        refresh.setForeground(commonColor);
        refresh.setBorder(new LineBorder(commonColor, 1));

        refresh.addActionListener((ActionEvent e)->{
            delegator.roomController.Chat(this.room);
            this.dispose();
        });

        back.add(backLabel);
        header.add(back);

        JLabel headerLabel = new JLabel();


        if(delegator.getCurrent_user().getId().equals(room.getTarget_id())){
            headerLabel.setText(room.getUser_id());
        }else{
            headerLabel.setText(room.getTarget_id());
        }
        headerLabel.setForeground(Color.white);
        headerLabel.setFont(new Font("test", Font.BOLD, 20));
        headerLabel.setBounds(140, 10, 150, 20);
        header.add(headerLabel);

        contentPane.add(header);


        JPanel rooms = new JPanel();
        rooms.setLayout(null);
        rooms.setPreferredSize(new Dimension(340, list.size()*balloonHeight));
        
        for (int i=0; i<list.size(); i++){
            JPanel balloon = new JPanel();
            JLabel nameLabel = new JLabel();

            if(delegator.getCurrent_user().getId().equals(list.get(i).getUser_id())){
                balloon.setBounds(100, i*(balloonHeight+10), 255, balloonHeight);
                balloon.setBackground(commonColor);

            }else{
                balloon.setBounds(15, i*(balloonHeight+10), 255, balloonHeight);
                balloon.setBackground(Color.white);

            }
            balloon.setBorder(new LineBorder(Color.black, 1));


            JLabel roomLabel = new JLabel();
            roomLabel.setText(list.get(i).getUser_id() + "  :  " + list.get(i).getContent());
            roomLabel.setFont(new Font("Dialog", Font.BOLD, 10));
            roomLabel.setBounds(10, 20, 355, balloonHeight);

            rooms.add(nameLabel);
            balloon.add(roomLabel);
            rooms.add(balloon);
        }

        JScrollPane roomScr = new JScrollPane(rooms);
        roomScr.setBounds(0, 50, 375, 545);
        contentPane.add(roomScr);

        inputText = new JTextField();
        inputText.setBounds(5, 600, 295, 40);

        JScrollPane inputScr = new JScrollPane(inputText);
        inputScr.setBounds(5, 600, 295, 40);
        contentPane.add(inputScr);

        JButton inputBtn = new JButton("입력");
        inputBtn.setBounds(300, 600, 70, 40);
        inputBtn.addActionListener((ActionEvent e)->{
            ChatDto chat = new ChatDto();
            chat.setUser_id(delegator.getCurrent_user().getId());
            chat.setRoom_id(this.room.getSeq());
            chat.setContent(inputText.getText());

            boolean result = delegator.chatController.insert(chat);

            if(result){
                delegator.roomController.Chat(this.room);
                this.dispose();
            }
        });
        contentPane.add(inputBtn);

        setBounds(100, 100, 380, 680);
        setResizable(false);
        setVisible(true);
    }
}
