package View;

import Controller.Controller;
import Other.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class UI {
    private ArrayList<Integer> availableChatIds;
    Controller controller;

    JFrame          frame           = new JFrame();
    JFrame          messageFrame    = new JFrame();
    JList<Message>  messageList     = new JList<Message>();
    JTextArea       messageInput    = new JTextArea();
    JLabel          activeChat      = new JLabel();
    JList <String>  chatList        = new JList<String>();
    JTextField      usernameInput   = new JTextField();
    JPasswordField  passwordInput   = new JPasswordField();
    JTextField      removeChatInput = new JTextField();
    JTextField      createChatInput = new JTextField();
    JTextField      addMemberInput  = new JTextField();

    public UI(Controller controller) {
        this.controller = controller;

        removeChatInput.setColumns(20);

        messageList.setCellRenderer(new MessageRenderer());

        messageFrame.setLayout(new GridBagLayout());

        messageInput.setColumns(20);
        messageInput.setRows(5);

        usernameInput.setColumns(20);
        usernameInput.setMaximumSize(usernameInput.getPreferredSize());
        usernameInput.setMinimumSize(usernameInput.getPreferredSize());

        passwordInput.setColumns(20);

        addMemberInput.setColumns(20);

        createChatInput.setColumns(20);

        SpringLayout layout = new SpringLayout();

        Container contentPane = frame.getContentPane();

////-------------------------------Buttons------------------------------------------------------------------------------
        // send a message as server
        JButton messageBtn = new JButton("send message");
        messageBtn.addActionListener((_) -> {
            controller.sendMessage(messageInput.getText());
            messageInput.setText("");
        });

        //logging is as user
        JButton loginBtn = new JButton("login");
        loginBtn.addActionListener((_) -> controller.login(usernameInput.getText(), passwordInput.getText()));

        JButton removeChatBtn = new JButton("remove chat");

        removeChatBtn.addActionListener((_) -> {
            String input = removeChatInput.getText().trim();
            if (!input.isEmpty()) {
                try {
                    // if it parses as int, use it as ID directly
                    int id = Integer.parseInt(input);
                    controller.removeChatRoom(id);
                } catch (NumberFormatException e) {
                    // otherwise treat it as a name, look up the ID
                    int id = controller.getChatIdByName(input);
                    if (id != -1) {
                        controller.removeChatRoom(id);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Chatroom not found: " + input);
                    }
                }
            }
        });

        JButton signUpBtn = new JButton("sign up");
        signUpBtn.addActionListener((_) -> controller.signUp(usernameInput.getText(), passwordInput.getText()));

        JButton createChatBtn = new JButton("create chat");
        createChatBtn.addActionListener((_) -> controller.addChatRoom(createChatInput.getText()));

        JButton addMemberBtn = new JButton("add user");
        addMemberBtn.addActionListener((_) -> controller.addMember(addMemberInput.getText()));

        JButton sendWithImageBtn = new JButton("send with image");
        sendWithImageBtn.addActionListener((_) -> addImageFileDialog());

////---------------------------------Add components to the frame--------------------------------------------------------
        //adds buttons to the UI
        contentPane.add(messageBtn);
        contentPane.add(createChatInput);
        contentPane.add(createChatBtn);

        contentPane.add(messageInput);
        contentPane.add(usernameInput);
        contentPane.add(passwordInput);
        contentPane.add(loginBtn);
        contentPane.add(signUpBtn);
        JScrollPane messageListPane = new JScrollPane(messageList);
        contentPane.add(messageListPane);
        contentPane.add(sendWithImageBtn);

        contentPane.add(activeChat);
        JScrollPane chatListPane = new JScrollPane(chatList);
        chatListPane.setPreferredSize(new Dimension(200, 100));
        contentPane.add(chatListPane);
        chatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatList.addListSelectionListener(e -> {
            controller.changeActiveRoom(availableChatIds.get(e.getFirstIndex()));
        });

        contentPane.add(removeChatBtn);
        contentPane.add(removeChatInput);
        contentPane.add(addMemberInput);
        contentPane.add(addMemberBtn);

////------------------------------Setup constraints---------------------------------------------------------------------

        //position messageInput
        layout.putConstraint("East", messageInput, 10, "East", contentPane);
        layout.putConstraint("West", messageInput, 0, "West", messageListPane);
        layout.putConstraint("South", contentPane, 50, "South", messageInput);

        //position "Send Message" button
        layout.putConstraint("North", messageBtn, 10, "South", messageInput);
        layout.putConstraint("West", messageBtn, 0, "West", messageListPane);

        //position send with image button
        layout.putConstraint("West", sendWithImageBtn, 10, "East", messageBtn);
        layout.putConstraint("East", sendWithImageBtn, 10, "East", contentPane);
        layout.putConstraint("North", sendWithImageBtn, 10, "South", messageInput);

        //position messageListPane
        layout.putConstraint("North", messageInput, 10, "South", messageListPane);
        layout.putConstraint("East", contentPane, 0, "East", messageListPane);
        layout.putConstraint("West", messageListPane, 10, "East", usernameInput);

        //position activeChat
        layout.putConstraint("West", activeChat, 0, "West", contentPane);

        //position chatListPane
        layout.putConstraint("West", chatListPane, 0, "West", contentPane);
        layout.putConstraint("North", chatListPane, 10, "South", activeChat);

        //position usernameInput
        layout.putConstraint("North", usernameInput, 10, "South", chatListPane);

        //position passwordInput
        layout.putConstraint("North", passwordInput, 10, "South", usernameInput);

        //position loginBtn
        layout.putConstraint("North", loginBtn, 10, "South", passwordInput);

        //position signUpBtn
        layout.putConstraint("West", signUpBtn, 10, "East", loginBtn);
        layout.putConstraint("North", signUpBtn, 10, "South", passwordInput);

        //position for removeChatInput
        layout.putConstraint("North", removeChatInput, 10, "South", loginBtn);

        //position for removeChatBtn
        layout.putConstraint("North", removeChatBtn, 10, "South", removeChatInput);

        //position for addMemberInput
        layout.putConstraint("North", addMemberInput, 10, "South", removeChatBtn);

        //position for addMemberBtn
        layout.putConstraint("North", addMemberBtn, 10, "South", addMemberInput);

        //position for createChatInput
        layout.putConstraint("North", createChatInput, 10, "South", addMemberBtn);

        //position for createChatBtn
        layout.putConstraint("North", createChatBtn, 10, "South", createChatInput);

        //setup för fonstret
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(layout);
        frame.setVisible(true);
    }

    /**
    * Updates the message list with new messages.
    *
    * This is called by the Controller whenever the chat receives new messages.
    * It replaces all items in the messageList with the provided list.
    *
    * @param msg An ArrayList of Message objects to display.
    */
    public void setMsg(ArrayList<Message> msg) {
        Vector<Message> vector = new Vector<Message>(msg);
        messageList.setListData(vector);
    }

    /**
    * Updates the chat room list with the current available rooms.
    *
    * Called by the Controller when the list of chat rooms changes
    * (e.g. a room was added or removed).
    *
    * @param chatNames An ArrayList of chat room name strings.
    * @param chatIds An Integer ArrayList of chat room IDs.
    */
    public void setAvailableChats(ArrayList<String> chatNames, ArrayList<Integer> chatIds) {
        Vector<String> vector = new Vector<>(chatNames);
        chatList.setListData(vector);
        this.availableChatIds = chatIds;
    }

    /**
    * Opens a file chooser dialog and sends the selected image as a picture message.
    */
    private void addImageFileDialog() {
        JFileChooser fileDialog = new JFileChooser();
        if (fileDialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            controller.sendPictureMessage(fileDialog.getSelectedFile().getAbsolutePath(), messageInput.getText());
        }
    }

    /**
    * Displays a popup dialog with the given status message.
    *
    * @param statusMessage the message to display in type String.
    */
    public void displayStatusMessage(String statusMessage) {
        JOptionPane.showMessageDialog(frame, statusMessage);
    }

    public void setActiveChat(String activeChatName) {
        activeChat.setText(activeChatName);
    }

}