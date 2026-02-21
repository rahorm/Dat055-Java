CREATE TABLE Users(
    username TEXT,
    pswd TEXT NOT NULL,
    PRIMARY KEY (username) ON UPDATE CASCADE
); 

CREATE TABLE Chats(
    chatId INT,
    name TEXT NOT NULL,
    PRIMARY KEY (chatId) ON UPDATE CASCADE
);

CREATE TABLE ChatMembers(
    chat INT,
    member TEXT,
    PRIMARY KEY (chat, member),
    FOREIGN KEY (chat) REFERENCES Chats(chatId) ON DELETE CASCADE,
    FOREIGN KEY (member) REFERENCES Users(username) ON DELETE CASCADE
);

CREATE TABLE ChatMessages(
    msgId INT,
    chat INT NOT NULL,
    sender TEXT NOT NULL,
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    content TEXT NOT NULL, --kan det vara null?, text can förvara upp till 65535 chars ~typ 20 sidor
    hasImg BOOLEAN NOT NULL,
    PRIMARY KEY (msgId),
    FOREIGN KEY (chat) REFERENCES Chats(chatId) ON DELETE CASCADE,
    FOREIGN KEY (sender) REFERENCES Users(username) ON DELETE CASCADE,
    FOREIGN KEY (chat, sender) REFERENCES ChatMembers(chat, member) ON DELETE CASCADE
);

------- Inte färdig ---------------
--CREATE TABLE Images(
    -- imgId ? eller räcker det med msgId
  --msg CHAR(14),
  --image BLOB NOT NULL,
  --PRIMARY KEY (msg/img ID),
  --FOREIGN KEY (msg) REFERENCES ChatMessages.msgId
--);
