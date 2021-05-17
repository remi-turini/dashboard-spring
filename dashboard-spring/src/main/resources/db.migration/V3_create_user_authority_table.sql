create table user_authority (
    id int not null auto_increment primary key,
    user_id int not null,
    authority_id int not null,
    foreign key (user_id) references user(id),
    foreign key (authority_id) references authority(id)
);