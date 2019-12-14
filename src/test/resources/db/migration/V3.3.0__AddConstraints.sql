alter table if exists beverage_order
   add constraint FK2nqyfid5ks6a8mlhkxe6cdlrl
   foreign key (order_id)
   references coffee_order;

alter table if exists coffee_order_beverage_order
   add constraint UK_sinpifu1mgpnggx882htnlt40 unique (beverageOrder_id);

alter table if exists coffee_order_beverage_order
    add constraint FKpjafmtrpdswmmrogubehsn29
    foreign key (beverageOrder_id)
    references beverage_order;

alter table if exists coffee_order_beverage_order
    add constraint FK6k5wd5r462wx166ic1mlxpc2n
    foreign key (Order_id)
    references coffee_order;

alter table if exists coffee_order_kitchen_order
   add constraint UK_3vqsln0f4m2k4md5kopm95mgf unique (kitchenOrder_id);

alter table if exists coffee_order_kitchen_order
   add constraint FKnxt6grf1jd7wn7mkxh1uumq65
   foreign key (Order_id)
   references coffee_order;

alter table if exists kitchen_order
    add constraint FKn3i6qu1poaoya565nua5jhyc2
    foreign key (order_id)
    references coffee_order;