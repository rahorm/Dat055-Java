package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class UI {
    Controller controller;
    public UI(Controller controller) {
        this.controller = controller;
        LayoutManager layout = new GridLayout(1, 1);

        JFrame frame = new JFrame();

        JButton btn = new JButton();
        btn.addActionListener((_) -> System.out.println("hallo"));
        btn.setSize(100, 100);

        JButton btn2 = new JButton("create new chat room");
        btn2.addActionListener((_) -> controller.addChatRoom());
        btn2.setSize(100, 100);

        frame.add(btn);
        frame.add(btn2);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(layout);
        frame.setVisible(true);
    }
}
