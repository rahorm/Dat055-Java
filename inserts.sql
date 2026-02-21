-- chatId, chatName
INSERT INTO chats VALUES (1, 'Chat1');
INSERT INTO chats VALUES (2, 'Chat2');
INSERT INTO chats VALUES (3, 'Chat3');

-- username, password
INSERT INTO Users VALUES ('User1', 'Paswd1');
INSERT INTO Users VALUES ('User2', 'Paswd2');
INSERT INTO Users VALUES ('User3', 'Paswd3');
INSERT INTO Users VALUES ('User4', 'Paswd4');
INSERT INTO Users VALUES ('User5', 'Paswd5');

-- chatId, member
INSERT INTO ChatMembers VALUES (1, 'User1');
INSERT INTO ChatMembers VALUES (1, 'User2');
INSERT INTO ChatMembers VALUES (1, 'User3');

INSERT INTO ChatMembers VALUES (2, 'User2');
INSERT INTO ChatMembers VALUES (2, 'User4');

INSERT INTO ChatMembers VALUES (3, 'User1');
INSERT INTO ChatMembers VALUES (3, 'User3');
INSERT INTO ChatMembers VALUES (3, 'User4');

-- msgId, chatId, sender, time, content, hasImg
INSERT INTO ChatMessages VALUES (0, 1, 'User3', '2026-01-29 14:56:59', 'This is text', false);
INSERT INTO ChatMessages VALUES (1, 1, 'User2', '2026-01-29 15:15:09', 'This is also text', false);
INSERT INTO ChatMessages VALUES (2, 1, 'User2', '2026-01-29 15:16:49', 'This is more text', false);
INSERT INTO ChatMessages VALUES (3, 1, 'User1', '2026-01-30 12:12:39', 'This is most definitely text', false);

INSERT INTO ChatMessages VALUES (4, 2, 'User4', '2026-01-29 14:56:59', 'This is text in another chat', false);
INSERT INTO ChatMessages VALUES (5, 2, 'User2', '2026-01-29 15:15:09', 'This is also text in another chat', false);
INSERT INTO ChatMessages VALUES (6, 2, 'User4', '2026-01-29 15:16:49', 'This is more text in another chat', false);

INSERT INTO ChatMessages VALUES (7, 3, 'User3', '2026-01-29 14:56:59', 'This is text in a third chat', false);
INSERT INTO ChatMessages VALUES (8, 3, 'User4', '2026-01-29 15:15:09', 'This is also text in a third chat', false);
INSERT INTO ChatMessages VALUES (9, 3, 'User3', '2026-01-29 15:16:49', 'This is more text in a third chat', false);
INSERT INTO ChatMessages VALUES (10, 3, 'User1', '2026-01-30 12:12:39', 'This is most definitely text in a third chat', false);



