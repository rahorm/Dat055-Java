package View;

import Controller.Controller;
import Other.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class UI {
    private ArrayList<Integer> availableChatIds;
    JFrame frame;
    JFrame messageFrame;
    JList<Message> messageList;
    JList <String> chatList;
    Controller controller;
    JTextField roomInput;
    JTextField removeChatInput;
    JTextArea messages;
    JTextArea messageInput;
    JTextField usernameInput;
    JPasswordField passwordInput;
    JTextField createChatInput = new JTextField();
    JTextField addMemberInput = new JTextField();


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

        this.chatList = new JList<>(); //tom chatRoom llista först

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

        addMemberInput.setColumns(20);

        createChatInput.setColumns(20);

        SpringLayout layout = new SpringLayout();

        frame = new JFrame();
        Container contentPane = frame.getContentPane();

        //skickar ett meddelande till servern
        JButton messageBtn = new JButton("send message");
        messageBtn.addActionListener((_) -> {
            controller.sendMessage(messageInput.getText());
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


        JButton createChatBtn = new JButton("create chat");
        createChatBtn.addActionListener((_) -> controller.addChatRoom(createChatInput.getText()));

        JButton addMemberBtn = new JButton("add user");
        addMemberBtn.addActionListener((_) -> controller.addMember(addMemberInput.getText()));

        JButton sendWithImageBtn = new JButton("send with image");
        sendWithImageBtn.addActionListener((_) -> addImageFileDialog());

        //lägger till knappar till ui:n
        contentPane.add(messageBtn);
        //contentPane.add(messages);
        contentPane.add(createChatInput);
        contentPane.add(createChatBtn);

        contentPane.add(messageInput);
        contentPane.add(usernameInput);
        contentPane.add(passwordInput);
        contentPane.add(loginBtn);
        contentPane.add(signUpBtn);
        JScrollPane messageListPane = new JScrollPane(messageList);
        //messageListPane.setPreferredSize(new Dimension(200, 200));
        //messageListPane.setMinimumSize(new Dimension(200, 20));
        contentPane.add(messageListPane);
        contentPane.add(sendWithImageBtn);

        JScrollPane chatListPane = new JScrollPane(chatList);
        chatListPane.setPreferredSize(new Dimension(200, 100));
        contentPane.add(chatListPane);
        chatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatList.addListSelectionListener(_ -> {
            controller.changeActiveRoom(availableChatIds.get(chatList.getSelectedIndex()));
        });

        contentPane.add(removeChatBtn);
        contentPane.add(removeChatInput);
        contentPane.add(roomInput);
        contentPane.add(changeRoomBtn);
        contentPane.add(addMemberInput);
        contentPane.add(addMemberBtn);

        //constraints för springlayout
        //pitfalls: write layout.putConstraint("North", thing1, pad, "South", thing2);
        //            NOT layout.putConstraint("South", thing1, pad, "North", thing2);
        //unless you want overlapping components
        //same with East and West
        //layout.putConstraint("West", thing1, pad, "East", thing2); not overlapping
        //layout.putConstraint("East", thing1, pad, "West", thing2); overlapping

        //position messageInput
        layout.putConstraint("East", messageInput, 10, "East", contentPane);

        //position "Send Message" button
        layout.putConstraint("North", messageBtn, 10, "South", messageInput);

        //position send with image button
        layout.putConstraint("East", messageBtn, 10, "West", sendWithImageBtn);
        layout.putConstraint("East", sendWithImageBtn, 10, "East", contentPane);
        layout.putConstraint("North", sendWithImageBtn, 10, "South", messageInput);

        //position messageListPane
        layout.putConstraint("North", messageInput, 10, "South", messageListPane);
        layout.putConstraint("East", contentPane, 10, "East", messageListPane);
        layout.putConstraint("West", messageListPane, 10, "East", usernameInput);

        //position chatListPane
        layout.putConstraint("West", chatListPane, 0, "West", contentPane);

        //position usernameInput
        layout.putConstraint("North", usernameInput, 10, "South", chatListPane);

        //position passwordInput
        layout.putConstraint("North", passwordInput, 10, "South", usernameInput);

        //position loginBtn
        layout.putConstraint("North", loginBtn, 10, "South", passwordInput);

        //position signUpBtn
        layout.putConstraint("West", signUpBtn, 10, "East", loginBtn);
        layout.putConstraint("North", signUpBtn, 10, "South", passwordInput);

        //position för roomInput
        layout.putConstraint("North", roomInput, 10, "South", loginBtn);

        //position för changeRoomBtn
        layout.putConstraint("North", changeRoomBtn, 10, "South", roomInput);

        //position för removeChatInput
        layout.putConstraint("North", removeChatInput, 10, "South", changeRoomBtn);

        //position för removeChatBtn
        layout.putConstraint("North", removeChatBtn, 10, "South", removeChatInput);

        //position för addMemberInput
        layout.putConstraint("North", addMemberInput, 10, "South", removeChatBtn);

        //position för addMemberBtn
        layout.putConstraint("North", addMemberBtn, 10, "South", addMemberInput);

        //position för createChatInput
        layout.putConstraint("North", createChatInput, 10, "South", addMemberBtn);

        //position för createChatBtn
        layout.putConstraint("North", createChatBtn, 10, "South", createChatInput);

        //setup för fönstret
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

    /**
     * Updates the chat room list with the current available rooms.
     *
     * Called by the Controller when the list of chat rooms changes
     * (e.g. a room was added or removed).
     *
     * @param chatNames An ArrayList of chat room name strings.
     */
    public void setAvailableChats(ArrayList<String> chatNames, ArrayList<Integer> chatIds) {
        Vector<String> vector = new Vector<>(chatNames);
        chatList.setListData(vector);
        this.availableChatIds = chatIds;
    }

    private void addImageFileDialog() {
        JFileChooser fileDialog = new JFileChooser();
        if (fileDialog.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            controller.sendPictureMessage(fileDialog.getSelectedFile().getAbsolutePath(), messageInput.getText());
        }
    }

}