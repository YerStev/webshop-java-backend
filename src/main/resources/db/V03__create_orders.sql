create table orders
(
    id          varchar(36)  not null,
    customer_id varchar(36)  not null,
    order_time  timestamp    not null,
    status      varchar(255) not null,
    primary key (id)
);

create table order_positions
(
    id              varchar(36),
    order_entity_id varchar(36) not null,
    product_id      varchar(36) not null,
    quantity        bigint      not null
);

alter table order_positions
    add constraint order_positions_product_id_fk
        foreign key (product_id)
            references products;

alter table order_positions
    add constraint order_positions_order_id_fk
        foreign key (order_entity_id)
            references orders;

alter table orders
    add constraint orders_customer_id_fk
        foreign key (customer_id)
            references customers;

