package View;

import Other.Message;
import Other.PictureMessage;

import javax.swing.*;
import java.awt.*;

class MessageRenderer extends JPanel implements ListCellRenderer<Message> {
    private final JLabel sender = new JLabel();
    private final JLabel messageContent = new JLabel();
    private final JLabel messageTime = new JLabel();
    private final JLabel imageContent = new JLabel();
    MessageRenderer() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(sender);
        topPanel.add(messageTime);
        setLayout(new BorderLayout(8, 0));
        add(topPanel, BorderLayout.NORTH);
        add(imageContent, BorderLayout.CENTER);
        add(messageContent, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        messageContent.setFont(messageContent.getFont().deriveFont(Font.PLAIN, 11f));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Message> list, Message message, int index, boolean isSelected, boolean cellHasFocus) {
        sender.setText(message.getSender());
        messageContent.setText(message.getContent());
        messageTime.setText(message.getTimestamp().toString());
        if (message instanceof PictureMessage) {
            Icon icon = new ImageIcon(((PictureMessage) message).getImageBytes());
            imageContent.setIcon(icon);
        }
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        sender.setForeground(getForeground());
        messageContent.setForeground(getForeground());
        messageTime.setForeground(getForeground());
        setOpaque(true);
        return this;
    }

}
