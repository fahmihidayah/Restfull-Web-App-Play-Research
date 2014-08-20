# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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
  date_creation             timestamp not null,
  constraint uq_user_user_name unique (user_name),
  constraint pk_user primary key (id))
;

create sequence kelas_seq;

create sequence siswa_seq;

create sequence user_seq;

alter table siswa add constraint fk_siswa_kelas_1 foreign key (kelas_id_kelas) references kelas (id_kelas) on delete restrict on update restrict;
create index ix_siswa_kelas_1 on siswa (kelas_id_kelas);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists kelas;

drop table if exists siswa;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists kelas_seq;

drop sequence if exists siswa_seq;

drop sequence if exists user_seq;

