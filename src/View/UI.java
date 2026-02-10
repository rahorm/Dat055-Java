package View;

import javax.swing.*;

public class UI {
    public static void init() {
        JButton btn = new JButton();
        btn.addActionListener((_) -> System.out.println("hallo"));
    }
}
