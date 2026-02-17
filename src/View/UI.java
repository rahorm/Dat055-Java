package View;

import Controller.Controller;
import Other.Message;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class UI {
    Controller controller;
    JLabel messages;

    public UI(Controller controller) {
        this.messages = new JLabel();
        this.controller = controller;
        LayoutManager layout = new GridLayout(1, 1);

        JFrame frame = new JFrame();

        JButton btn = new JButton();
        btn.addActionListener((_) -> System.out.println("hallo"));
        btn.setSize(100, 100);

        JButton btn2 = new JButton("create new chat room");
        btn2.addActionListener((_) -> controller.sendMessage("hello server", 1, LocalDateTime.now()));
        btn2.setSize(100, 100);

        frame.add(btn);
        frame.add(btn2);
        frame.add(messages);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(layout);
        frame.setVisible(true);
    }

    public void setMsg(Message[] msg) {
        messages.setText(msg[0].getContent());
    }
}