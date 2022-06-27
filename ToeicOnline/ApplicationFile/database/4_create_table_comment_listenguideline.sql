use toeiconline;
CREATE TABLE listenguideline (
listenguidelineid bigint not null primary key auto_increment,
title varchar(512) null,
image varchar(255) null,
content text null,
createddate timestamp null,
modifieddate timestamp null

);


CREATE TABLE comment (
commentid bigint not null primary key auto_increment,
content text null,
userid bigint null,
listenguidelineid bigint null,
createddate timestamp null
);