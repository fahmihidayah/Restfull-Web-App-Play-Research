# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table siswa (
  nim                       varchar(255) not null,
  name                      varchar(255),
  address                   varchar(255),
  constraint pk_siswa primary key (nim))
;

create table user (
  id                        bigint not null,
  user_name                 varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence siswa_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists siswa;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists siswa_seq;

drop sequence if exists user_seq;

