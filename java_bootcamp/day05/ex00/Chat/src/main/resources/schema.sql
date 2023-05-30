drop schema if exists chat cascade;
drop table if exists chat.user, chat.message, chat.chatroom, chat.user_chatroom;

create schema if not exists chat;

create table if not exists chat.user
(
    id       serial primary key,
    name     varchar(20) not null unique,
    password varchar(20) not null
);

create table if not exists chat.chatroom
(
    id         serial primary key,
    chat_name  varchar(20) not null unique,
    chat_owner integer     not null references chat.user (id)
);

create table if not exists chat.message
(
    id        serial primary key,
    sender_id integer not null references chat.user (id),
    room_id   integer not null references chat.chatroom (id),
    text      text    not null,
    lDateTime timestamp default CURRENT_TIMESTAMP
);
