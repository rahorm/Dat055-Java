package View;

import Controller.Controller;
import Model.ChatRoomFacade;
import Other.Message;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UI {
    Controller controller;
    JTextArea messages;
    JTextArea messageInput;
    JTextField usernameInput;
    JPasswordField passwordInput;

    public UI(Controller controller) {
        this.messages = new JTextArea();
        messages.setEditable(false);
        messages.setColumns(20);
        messages.setRows(10);


        this.controller = controller;

        this.messageInput = new JTextArea();
        messageInput.setColumns(20);
        messageInput.setRows(5);

        this.usernameInput = new JTextField();
        usernameInput.setColumns(20);

        this.passwordInput = new JPasswordField();
        passwordInput.setColumns(20);

        SpringLayout layout = new SpringLayout();

        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();

        //testknapp
        JButton btn = new JButton();
        btn.addActionListener((_) -> System.out.println("hallo"));
        btn.setSize(100, 100);

        //skickar ett meddelande till servern
        JButton btn2 = new JButton("send message");
        btn2.addActionListener((_) -> controller.sendMessage(messageInput.getText(), "User1"));
        btn2.setSize(100, 100);

        //loggar in som en användare
        JButton loginBtn = new JButton("login");
        //@TODO skapa loginmetod i controller och använd getpassword istället för gettext;
        loginBtn.addActionListener((_) -> controller.login(usernameInput.getText(), passwordInput.getText()));

        //lägger till knappar till ui:n
        //frame.add(btn);
        contentPane.add(btn2);
        contentPane.add(messages);
        contentPane.add(messageInput);
        contentPane.add(usernameInput);
        contentPane.add(passwordInput);
        contentPane.add(loginBtn);

        //constraints för springlayout

        //position messageInput
        layout.putConstraint("East", messageInput, 10, "East", contentPane);

        //position "Send Message" button
        layout.putConstraint("North", btn2, 10, "South", messageInput);
        layout.putConstraint("South", btn2, 10, "South", contentPane);
        layout.putConstraint("East", btn2, 10, "East", contentPane);

        //position message
        layout.putConstraint("North", messageInput, 10, "South", messages);
        layout.putConstraint("East", messages, 10, "East", contentPane);

        //position usernameInput
        layout.putConstraint("West", usernameInput, 10, "West", contentPane);

        //position passwordInput
        layout.putConstraint("North", passwordInput, 10, "South", usernameInput);

        //position för loginBtn
        layout.putConstraint("North", loginBtn, 10, "South", passwordInput);

        //setup för fönstret
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(layout);
        frame.setVisible(true);
    }

    public void setMsg(ArrayList<Message> msg) {
        String s = "";
        for (Message message : msg) {
            s = s.concat(message.getContent());
            s = s.concat("\n");
        }
        messages.setText(s);
    }
}