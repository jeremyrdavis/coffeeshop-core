create table kitchen_order (
   id int8 not null,
    menuItem int4,
    order_id int8 not null,
    primary key (id)
);

create table coffee_order_kitchen_order (
   Order_id int8 not null,
    kitchenOrder_id int8 not null
);



