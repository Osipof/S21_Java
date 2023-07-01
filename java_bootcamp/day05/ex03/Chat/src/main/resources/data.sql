insert into chat.user (name, password) VALUES ('user1', '1111');
insert into chat.user (name, password) VALUES ('user2', '2222');
insert into chat.user (name, password) VALUES ('user3', '3333');
insert into chat.user (name, password) VALUES ('user4', '4444');
insert into chat.user (name, password) VALUES ('user5', '5555');
insert into chat.user (name, password) VALUES ('user6', '6666');
insert into chat.user (name, password) VALUES ('user7', '7777');

insert into chat.chatroom (chat_name, chat_owner) values ('chat1', 1);
insert into chat.chatroom (chat_name, chat_owner) values ('chat2', 2);
insert into chat.chatroom (chat_name, chat_owner) values ('chat3', 3);
insert into chat.chatroom (chat_name, chat_owner) values ('chat4', 4);
insert into chat.chatroom (chat_name, chat_owner) values ('chat5', 5);

insert into chat.message (sender_id, room_id, text, ldatetime) VALUES (1, 2, 'Hello, world!', to_timestamp('2023/05/29 10:12:48', 'YYYY/MM/DD HH:MI:SS'));
insert into chat.message (sender_id, room_id, text, ldatetime) VALUES (2, 2, 'Hello, user2!', to_timestamp('2023/05/29 10:12:48', 'YYYY/MM/DD HH:MI:SS'));
insert into chat.message (sender_id, room_id, text, ldatetime) VALUES (3, 2, 'Hello, my friends!', to_timestamp('2023/05/29 10:12:48', 'YYYY/MM/DD HH:MI:SS'));
insert into chat.message (sender_id, room_id, text, ldatetime) VALUES (7, 1, 'What is your name?', to_timestamp('2023/05/29 10:12:48', 'YYYY/MM/DD HH:MI:SS'));
insert into chat.message (sender_id, room_id, text, ldatetime) VALUES (6, 1, 'Oh, my name is user6!', to_timestamp('2023/05/29 10:12:48', 'YYYY/MM/DD HH:MI:SS'));
