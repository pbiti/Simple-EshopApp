create database products;
use products;
create table if not exists items(
barcode(100) not null,
name(100) not null,
color(100) not null,
description(300) not null
primary key(barcode));