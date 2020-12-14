create table user (
    id int not null auto_increment primary key,
    lastname varchar(40) not null,
    firstname varchar(40),
    username varchar(40),
    email varchar(200),
    password varchar(265) not null,
    enabled boolean not null default true,
    created_at timestamp not null default current_timestamp
);