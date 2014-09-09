# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table absensi (
  id_absensi                bigint not null,
  date                      timestamp not null,
  hadir                     boolean not null,
  GURU_NIK                  varchar(255),
  ID_MATAPELAJARAN          bigint,
  SISWA_NIM                 varchar(255),
  KETERANGAN                varchar(255),
  constraint pk_absensi primary key (id_absensi))
;

create table auth (
  id_auth                   bigint not null,
  auth_token                varchar(255),
  constraint pk_auth primary key (id_auth))
;

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

create table mata_pelajaran (
  id_mata_pelajaran         bigint not null,
  nama_mata_pelajaran       varchar(255),
  constraint pk_mata_pelajaran primary key (id_mata_pelajaran))
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
  user_name                 varchar(256) not null,
  sha_password              varbinary(64) not null,
  password                  varchar(255),
  constraint uq_user_user_name unique (user_name),
  constraint pk_user primary key (id))
;

create sequence absensi_seq;

create sequence auth_seq;

create sequence guru_seq;

create sequence kelas_seq;

create sequence mata_pelajaran_seq;

create sequence siswa_seq;

create sequence user_seq;

alter table absensi add constraint fk_absensi_guru_1 foreign key (GURU_NIK) references guru (nik) on delete restrict on update restrict;
create index ix_absensi_guru_1 on absensi (GURU_NIK);
alter table absensi add constraint fk_absensi_mataPelajaran_2 foreign key (ID_MATAPELAJARAN) references mata_pelajaran (id_mata_pelajaran) on delete restrict on update restrict;
create index ix_absensi_mataPelajaran_2 on absensi (ID_MATAPELAJARAN);
alter table absensi add constraint fk_absensi_siswa_3 foreign key (SISWA_NIM) references siswa (nim) on delete restrict on update restrict;
create index ix_absensi_siswa_3 on absensi (SISWA_NIM);
alter table guru add constraint fk_guru_account_4 foreign key (account_id) references user (id) on delete restrict on update restrict;
create index ix_guru_account_4 on guru (account_id);
alter table siswa add constraint fk_siswa_kelas_5 foreign key (kelas_id_kelas) references kelas (id_kelas) on delete restrict on update restrict;
create index ix_siswa_kelas_5 on siswa (kelas_id_kelas);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists absensi;

drop table if exists auth;

drop table if exists guru;

drop table if exists kelas;

drop table if exists mata_pelajaran;

drop table if exists siswa;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists absensi_seq;

drop sequence if exists auth_seq;

drop sequence if exists guru_seq;

drop sequence if exists kelas_seq;

drop sequence if exists mata_pelajaran_seq;

drop sequence if exists siswa_seq;

drop sequence if exists user_seq;

