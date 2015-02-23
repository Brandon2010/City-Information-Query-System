drop database cityinfosys;
create database cityinfosys;
use cityinfosys;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id int(10) NOT NULL auto_increment,
  name varchar(256) default NULL,
  password varchar(16) default NULL,
  del_soft char(1) default '0',
  primary key (id)
);

DROP TABLE IF EXISTS place_category;
CREATE TABLE place_category (
    category_no char(3) NOT NULL,
    category_name varchar(128) NOT NULL,
    primary key (category_no)
);

DROP TABLE IF EXISTS place_subcategory;
CREATE TABLE place_subcategory (
    subcategory_no char(3) NOT NULL,
    subcategory_name varchar(128) NOT NULL,
    category_no char(3) default NULL,
    primary key (subcategory_no),
    foreign key (category_no) references place_category(category_no)
    on delete cascade on update cascade
);

DROP TABLE IF EXISTS location_district;
CREATE TABLE location_district (
    district_no char(3) NOT NULL,
    district_name varchar(128) NOT NULL,
    primary key (district_no)
);

DROP TABLE IF EXISTS location_area;
CREATE TABLE location_area (
    area_no char(3) NOT NULL,
    area_name varchar(128) NOT NULL,
    district_no char(3) default NULL,
    primary key (area_no),
    foreign key (district_no) references location_district(district_no)
    on delete cascade on update cascade
);

DROP TABLE IF EXISTS location;
CREATE TABLE location (
    locationId int(10) NOT NULL auto_increment,
    location_name varchar(256) NOT NULL,
    location_area char(3) default NULL,
    latitude double NOT NULL,
    longitude double NOT NULL,
    primary key(locationId),
    foreign key (location_area) references location_area(area_no)
    on delete cascade on update cascade
);

DROP TABLE IF EXISTS place_information;
CREATE TABLE place_information (
    placeId int(10) NOT NULL auto_increment,
    place_name varchar(256) default NULL,
    place_category_no char(3) default NULL,
    locationId int(10) default NULL,
    description text default NULL,
    rating double default NULL,
    ratingAmount int(10) default 0,
    address varchar(512) default NULL,
    recommendation varchar(1024) default NULL,
    phone varchar(16) default NULL,
    price double default NULL,
    picture_address varchar(256) default NULL,
    del_soft char(1) default '0',
    primary key (placeId),
    foreign key (place_category_no) references place_subcategory(subcategory_no)
    on delete cascade on update cascade,
    foreign key(locationId) references location(locationId)
    on delete cascade on update cascade
);

DROP TABLE IF EXISTS user_rating_list;
CREATE TABLE user_rating_list (
    rating_id int(10) NOT NULL auto_increment,
    user_rating_number int(10) default NULL,
    comment varchar(2000) default NULL,
    place_id int(10) default NULL,
    user_id int(10) default NULL,
    del_soft char(1) default '0',
    primary key(rating_id),
    foreign key(place_id) references place_information(placeId)
    on delete cascade on update cascade,
    foreign key(user_id) references user(id)
    on delete cascade on update cascade
);

CREATE VIEW place AS SELECT placeId, place_name, l.locationId, location_name, latitude, longitude, description, rating, ratingAmount,
address, recommendation, phone, price, picture_address, del_soft, area_no, area_name, ld.district_no, district_name, 
subcategory_name, subcategory_no, category_name, ca.category_no FROM
place_information pi, location l, place_category ca, place_subcategory ps, location_area la, location_district ld
WHERE pi.place_category_no = ps.subcategory_no and pi.locationId = l.locationId and ps.category_no = ca.category_no
and la.district_no = ld.district_no and l.location_area = la.area_no;
commit ;