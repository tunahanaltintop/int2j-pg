<p>A project of Java Programming on PostgreSQL Database training.</p>

<ul>
    <li>Maven structure</li>
    <li>Ready runnable Primefaces (v6.2 JSF: v2.2.18, Servlet: v4.0.1) configuration on jetty (9.4.14.v20181114)</li>
    <li>PostgreSQL (v9.5) configuration with PostgreSQL Driver (42.2.5)</li>
    <li>Classic JDBC configuration and test</li>
    <li>Hibernate (v5.4.0.Final) ORM configuration and test</li>
</ul>

<p>Note: For Debugging on IntellijIDEA "Maven Projects Tool->Plugins->jetty->jetty:run" right click and debug.</p>

<p>Note: Training docs are in docs folder.</p>

</br>

<p>Used table scripts:</p>

<pre>
create table sp_user
(
  user_name varchar(50) not null
    constraint sp_user_pk
    primary key,
  password  varchar(50) not null
);

create unique index sp_user_user_name_uindex
  on sp_user (user_name);
</pre>

<pre>
create table sp_user_pleasure
(
  user_name varchar(50) not null
    constraint sp_user_pleasure_fk
    references sp_user,
  date      date        not null,
  pleasure  char        not null,
  p_id      numeric(19) not null
    constraint sp_user_pleasure_pk
    primary key
);

create unique index sp_user_pleasure_p_id_uindex
  on sp_user_pleasure (p_id);
  
CREATE SEQUENCE hibernate_sequence START 1;
</pre>
