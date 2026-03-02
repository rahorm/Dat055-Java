/*******************************************************************************************
To reset the database:
In this file, run all queries in step 1, this deletes all the tables
In this file, run all queries in step 2, this creates all tables again
In file inserts.sql, run all queries in the file, this fills all the tables with test data
*******************************************************************************************/

-- STEP 1 --
DROP TABLE Images;
DROP TABLE ChatMessages;
DROP TABLE ChatMembers;
DROP TABLE Users;
DROP TABLE Chats;

-- Step 2 --
CREATE TABLE Users(
    username TEXT,
    pswd TEXT NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE Chats(
    chatId INT,
    name TEXT NOT NULL,
    PRIMARY KEY (chatId)
);

CREATE TABLE ChatMembers(
    chat INT,
    member TEXT,
    PRIMARY KEY (chat, member),
    FOREIGN KEY (chat) REFERENCES Chats(chatId)
        ON DELETE CASCADE
         ON UPDATE CASCADE,
    FOREIGN KEY (member) REFERENCES Users(username)
        ON DELETE CASCADE
         ON UPDATE CASCADE
);

CREATE TABLE ChatMessages(
    msgId INT,
    chat INT NOT NULL,
    sender TEXT NOT NULL,
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    content TEXT NOT NULL, --kan det vara null?, text can förvara upp till 65535 chars ~typ 20 sidor
    hasImg BOOLEAN NOT NULL,
    imgId INT,
    PRIMARY KEY (msgId),
    CHECK(hasImg AND (imgId IS NOT NULL)),
    FOREIGN KEY (chat) REFERENCES Chats(chatId)
        ON DELETE CASCADE
         ON UPDATE CASCADE,
    FOREIGN KEY (sender) REFERENCES Users(username)
        ON DELETE CASCADE
         ON UPDATE CASCADE,
    FOREIGN KEY (chat, sender) REFERENCES ChatMembers(chat, member)
        ON DELETE CASCADE
         ON UPDATE CASCADE
);

------- Inte testad ---------------
CREATE TABLE Images(
    imgId INT,
    message INT NOT NULL,
    image BYTEA NOT NULL,
    PRIMARY KEY (imgId),
    FOREIGN KEY (message) REFERENCES ChatMessages(msgId)
        ON DELETE CASCADE
         ON UPDATE CASCADE
);