A project of Java Programming on PostgreSQL Database training.

-Maven structure,

-Ready runnable Primefaces (v6.2 JSF: v2.2.18, Servlet: v4.0.1) configuration on jetty (9.4.14.v20181114),

-PostgreSQL (v9.5) configuration with PostgreSQL Driver (42.2.5),

-Classic JDBC configuration and test,

-Hibernate (v5.4.0.Final) ORM configuration and test,

---------
Used table scripts:

create table sp_user
(
  user_name varchar(50) not null
    constraint sp_user_pk
    primary key,
  password  varchar(50) not null
);

create unique index sp_user_user_name_uindex
  on sp_user (user_name);

create table sp_user_pleasure
(
  user_name varchar(50) not null
    constraint sp_user_pleasure_fk
    references sp_user,
  date      date        not null,
  pleasure  char        not null
);
