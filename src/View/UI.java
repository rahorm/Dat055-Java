package View;

import Controller.Controller;
import Model.ChatRoomFacade;
import Other.Message;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

public class UI {
    JFrame frame;
    JFrame messageFrame;
    JList<Message> messageList;
    Controller controller;
    JTextField roomInput;
    JTextField removeChatInput;
    JTextArea messages;
    JTextArea messageInput;
    JTextField usernameInput;
    JPasswordField passwordInput;

    public UI(Controller controller) {
        this.messages = new JTextArea();
        messages.setEditable(false);
        messages.setColumns(20);
        messages.setRows(10);

        this.roomInput = new JTextField();
        roomInput.setColumns(20);

        this.removeChatInput = new JTextField();
        removeChatInput.setColumns(20);

        this.messageList = new JList<Message>();
        messageList.setCellRenderer(new MessageRenderer());

        this.messageFrame = new JFrame();
        messageFrame.setLayout(new GridBagLayout());
        //messageFrame.add(messageList);
        //messageFrame.add(messageInput);

        this.controller = controller;

        this.messageInput = new JTextArea();
        messageInput.setColumns(20);
        messageInput.setRows(5);

        this.usernameInput = new JTextField();
        usernameInput.setColumns(20);

        this.passwordInput = new JPasswordField();
        passwordInput.setColumns(20);

        SpringLayout layout = new SpringLayout();

        frame = new JFrame();
        Container contentPane = frame.getContentPane();

        //skickar ett meddelande till servern
        JButton messageBtn = new JButton("send message");
        messageBtn.addActionListener((_) -> {
            controller.sendMessage(messageInput.getText(), "User1");
            messageInput.setText("");
        });

        //loggar in som en användare
        JButton loginBtn = new JButton("login");
        //@TODO skapa loginmetod i controller och använd getpassword istället för gettext;
        loginBtn.addActionListener((_) -> controller.login(usernameInput.getText(), passwordInput.getText()));

        JButton changeRoomBtn = new JButton("change room");
        changeRoomBtn.addActionListener((_) -> controller.changeActiveRoom(Integer.parseInt(roomInput.getText())));

        JButton removeChatBtn = new JButton("remove chat");
        removeChatBtn.addActionListener((_) -> controller.removeChatRoom(Integer.parseInt(removeChatInput.getText())));

        JButton signUpBtn = new JButton("sign up");
        signUpBtn.addActionListener((_) -> controller.signUp(usernameInput.getText(), passwordInput.getText()));

        //lägger till knappar till ui:n
        contentPane.add(messageBtn);
        //contentPane.add(messages);
        contentPane.add(messageInput);
        contentPane.add(usernameInput);
        contentPane.add(passwordInput);
        contentPane.add(loginBtn);
        JScrollPane messageListPane = new JScrollPane(messageList);
        messageListPane.setPreferredSize(new Dimension(200, 200));
        messageListPane.setMinimumSize(new Dimension(200, 20));
        contentPane.add(messageListPane);
        contentPane.add(removeChatBtn);
        contentPane.add(removeChatInput);
        contentPane.add(roomInput);
        contentPane.add(changeRoomBtn);

        //constraints för springlayout

        //position messageInput
        layout.putConstraint("East", messageInput, 10, "East", contentPane);

        //position "Send Message" button
        layout.putConstraint("North", messageBtn, 10, "South", messageInput);
        //layout.putConstraint("South", messageBtn, 10, "South", contentPane);
        layout.putConstraint("East", messageBtn, 10, "East", contentPane);

        //position messageListPane
        layout.putConstraint("North", messageInput, 10, "South", messageListPane);
        layout.putConstraint("East", contentPane, 10, "East", messageListPane);
        layout.putConstraint("West", messageListPane, 10, "East", usernameInput);


        //position usernameInput
        layout.putConstraint("West", usernameInput, 10, "West", contentPane);

        //position passwordInput
        layout.putConstraint("North", passwordInput, 10, "South", usernameInput);

        //position för loginBtn
        layout.putConstraint("North", loginBtn, 10, "South", passwordInput);

        //position för roomInput
        layout.putConstraint("North", roomInput, 10, "South", loginBtn);

        //position för changeRoomBtn
        layout.putConstraint("North", changeRoomBtn, 10, "South", roomInput);

        //position för removeChatInput
        layout.putConstraint("North", removeChatInput, 10, "South", changeRoomBtn);

        //position för removeChatBtn
        layout.putConstraint("North", removeChatBtn, 10, "South", removeChatInput);

        //setup för fönstret
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(layout);
        frame.setVisible(true);
    }

    public void setMsg(ArrayList<Message> msg) {
        /*
        String s = "";
        for (Message message : msg) {
            s = s.concat(message.getContent());
            s = s.concat("\n");
        }
        messages.setText(s);
         */
        Vector<Message> vector = new Vector<Message>(msg);
        messageList.setListData(vector);
    }
}