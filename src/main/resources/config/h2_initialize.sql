CREATE TABLE USER
(
    id       int auto_increment primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255)
);

INSERT INTO USER (username, password, email)
VALUES ('root', '!Biris.test202', 'root@mobigen.com');

INSERT INTO USER (username, password, email)
VALUES ('admin', 'admin123', 'admin@mobigen.com');

CREATE TABLE BOARD
(
    board_seq       int auto_increment primary key,
    title 			varchar(100),
    cntnts 			text,
    delete_yn    	varchar(1)
);

INSERT INTO BOARD (title, cntnts, delete_yn)
VALUES ('제목1', '내용1', 'N');

INSERT INTO BOARD (title, cntnts, delete_yn)
VALUES ('제목2', '내용2', 'Y');

INSERT INTO BOARD (title, cntnts, delete_yn)
VALUES ('제목3', '내용3', 'N');