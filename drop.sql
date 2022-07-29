    alter table photo_metadatas 
       drop 
       foreign key FKd44d59ikh1nuql7wy5a2n7s36;

    alter table photo_tag 
       drop 
       foreign key FKckpb0ndsh4aa7sstog4e5jtbg;

    alter table photo_tag 
       drop 
       foreign key FKqs7h31sl91i6i0iddgwd0yf41;

    alter table photos 
       drop 
       foreign key FKnm381g1ktlpsorbtpco2ljhuv;

    drop table if exists photo_metadatas;

    drop table if exists photo_tag;

    drop table if exists photos;

    drop table if exists tags;

    drop table if exists users;
