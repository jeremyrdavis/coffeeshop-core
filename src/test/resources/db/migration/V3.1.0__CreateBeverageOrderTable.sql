create table beverage_order (
   id int8 not null,
    beverage int4,
    order_id int8 not null,
    primary key (id)
);

create table coffee_order_beverage_order (
   Order_id int8 not null,
    beverageOrder_id int8 not null
);

