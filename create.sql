    create table photo_metadatas (
       id integer not null auto_increment,
        created datetime(6),
        height integer,
        width integer,
        photo_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table photo_tag (
       photo_id integer not null,
        tag_id integer not null,
        primary key (photo_id, tag_id)
    ) engine=InnoDB;

    create table photos (
       id integer not null auto_increment,
        description TEXT,
        title varchar(255),
        main_url varchar(200) not null,
        user_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table tags (
       id integer not null auto_increment,
        text varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table users (
       id integer not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table photos 
       add constraint UK_fn0fxlmtxno93u3mgoj7h7ljk unique (main_url);

    alter table photo_metadatas 
       add constraint FKd44d59ikh1nuql7wy5a2n7s36 
       foreign key (photo_id) 
       references photos (id);

    alter table photo_tag 
       add constraint FKckpb0ndsh4aa7sstog4e5jtbg 
       foreign key (tag_id) 
       references tags (id);

    alter table photo_tag 
       add constraint FKqs7h31sl91i6i0iddgwd0yf41 
       foreign key (photo_id) 
       references photos (id);

    alter table photos 
       add constraint FKnm381g1ktlpsorbtpco2ljhuv 
       foreign key (user_id) 
       references users (id);
