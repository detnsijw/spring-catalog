create table cart_items
(
    id         bigserial primary key,
    user_id    bigint references users,
    product_id bigint references products,
    quantity   integer not null
);

create table categories
(
    id   serial primary key,
    name varchar unique
);

create table options
(
    id          serial primary key,
    name        varchar,
    category_id integer references categories
);

create table order_products
(
    id         bigserial primary key,
    order_id   bigint  not null references orders,
    product_id bigint  not null references products,
    quantity   integer not null
);

create table orders
(
    id               bigserial primary key,
    user_id          bigint       not null references users,
    status           integer,
    delivery_address varchar(255) not null,
    date_of_order    timestamp
);

create table products
(
    id          serial primary key,
    name        varchar,
    category_id integer not null references categories,
    price       double precision,
    url_image   varchar
);

create table reviews
(
    id                 bigserial primary key,
    user_id            bigint   not null references users,
    product_id         bigint   not null references products,
    publication_status boolean  not null,
    estimation         smallint not null,
    estimation_text    text,
    estimation_data    timestamp
);

create table users
(
    id       bigserial primary key,
    name     varchar(255),
    lastname varchar(255),
    login    varchar(255) not null unique,
    password varchar(255) not null,
    role     integer,
    created  timestamp,
    email    varchar
);

create table values
(
    id         serial primary key,
    name       varchar,
    product_id integer references products,
    options_id integer references options
);
