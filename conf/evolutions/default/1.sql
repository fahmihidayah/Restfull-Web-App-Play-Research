# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table guru (
  nik                       varchar(255) not null,
  name                      varchar(255),
  address                   varchar(255),
  phone                     varchar(255),
  email                     varchar(255),
  account_id                bigint,
  constraint pk_guru primary key (nik))
;

create table kelas (
  id_kelas                  bigint not null,
  nama_kelas                varchar(255),
  constraint pk_kelas primary key (id_kelas))
;

create table siswa (
  nim                       varchar(255) not null,
  name                      varchar(255),
  address                   varchar(255),
  kelas_id_kelas            bigint,
  constraint pk_siswa primary key (nim))
;

create table user (
  id                        bigint not null,
  auth_token                varchar(255),
  user_name                 varchar(256) not null,
  sha_password              varbinary(64) not null,
  password                  varchar(255),
  constraint uq_user_user_name unique (user_name),
  constraint pk_user primary key (id))
;

create sequence guru_seq;

create sequence kelas_seq;

create sequence siswa_seq;

create sequence user_seq;

alter table guru add constraint fk_guru_account_1 foreign key (account_id) references user (id) on delete restrict on update restrict;
create index ix_guru_account_1 on guru (account_id);
alter table siswa add constraint fk_siswa_kelas_2 foreign key (kelas_id_kelas) references kelas (id_kelas) on delete restrict on update restrict;
create index ix_siswa_kelas_2 on siswa (kelas_id_kelas);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists guru;

drop table if exists kelas;

drop table if exists siswa;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists guru_seq;

drop sequence if exists kelas_seq;

drop sequence if exists siswa_seq;

drop sequence if exists user_seq;

