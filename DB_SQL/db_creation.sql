create table library
(
    id       bigserial    not null
        constraint library_pkey
            primary key,
    address  varchar(200) not null,
    email    varchar(100),
    name     varchar(50)  not null,
    phone    varchar(45),
    zip_code varchar(5)   not null,
    city     varchar(50)
);

alter table library
    owner to admin_db_library;

create table stock
(
    id        bigserial not null
        constraint stock_pkey
            primary key,
    available integer,
    outside   integer,
    total     integer
);

alter table stock
    owner to admin_db_library;

create table book
(
    id               bigserial    not null
        constraint book_pkey
            primary key,
    author           varchar(40)  not null,
    cover_pic_url    varchar(255),
    language         varchar(50),
    name             varchar(50)  not null,
    overview         varchar(255),
    publication_date timestamp    not null,
    publisher        varchar(255) not null,
    subject          varchar(255),
    borrow_id        bigint,
    library_id       bigint
        constraint fkaojxagnfmppd09k35kye5eph5
            references library,
    stock_id         bigint
        constraint fkcdlp3bj6butapn6ixbomswr6
            references stock
);

alter table book
    owner to admin_db_library;

create table user_reader
(
    username      varchar(55)  not null
        constraint user_reader_pkey
            primary key,
    active        boolean      not null,
    address       varchar(100) not null,
    creation_date timestamp,
    email         varchar(100),
    firstname     varchar(255) not null,
    lastname      varchar(100) not null,
    password      varchar(255) not null,
    zip_code      varchar(5)   not null,
    city          varchar(50)
);

alter table user_reader
    owner to admin_db_library;

create table borrow
(
    id         bigserial not null
        constraint borrow_pkey
            primary key,
    closed     boolean   not null,
    date_end   timestamp,
    date_start timestamp,
    extra_time boolean   not null,
    bookid_id  bigint
        constraint fke838cg4fs2k5c42k5whlu2cnv
            references book,
    user_id    varchar(255)
        constraint fk7wy1me38vjlxi9vfv7p5tb9ta
            references user_reader
);

alter table borrow
    owner to admin_db_library;

alter table book
    add constraint fkch8cvxf27j7e0jy8ss458ie3g
        foreign key (borrow_id) references borrow;

create table library_role
(
    id                bigserial not null
        constraint library_role_pkey
            primary key,
    library_user_role varchar(255)
);

alter table library_role
    owner to admin_db_library;

create table user_reader_roles
(
    library_user_username varchar(255) not null
        constraint fkp77wxqwbkm47a4lnbnh1q0km9
            references user_reader,
    roles_id              bigint       not null
        constraint fk264r05hip8e34rq43io7lp05d
            references library_role
);

alter table user_reader_roles
    owner to admin_db_library;
    