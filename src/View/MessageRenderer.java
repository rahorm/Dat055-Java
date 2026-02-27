package View;

import Other.Message;

import javax.swing.*;
import java.awt.*;

class MessageRenderer extends JPanel implements ListCellRenderer<Message> {
    private final JLabel titleLabel = new JLabel();
    private final JLabel statusLabel = new JLabel();
    MessageRenderer() {
        setLayout(new BorderLayout(8, 0));
        add(titleLabel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.PLAIN, 11f));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Message> list, Message message, int index, boolean isSelected, boolean cellHasFocus) {
        titleLabel.setText(message.getSender());
        statusLabel.setText(message.getContent());
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        titleLabel.setForeground(getForeground());
        statusLabel.setForeground(getForeground());
        setOpaque(true);
        return this;
    }

}
