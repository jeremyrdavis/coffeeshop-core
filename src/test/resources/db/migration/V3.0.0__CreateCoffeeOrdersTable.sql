create table coffee_order (
    id int8 not null,
    name varchar(255),
    beverage varchar(255),
    orderNumber varchar(255),
    status int4,
    primary key (id)
);