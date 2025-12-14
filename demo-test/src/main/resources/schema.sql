
create table users (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name varchar(255) not null,
                    last_login date
);
create table products (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     name varchar(255) not null,
                     description varchar(255),
                     price decimal(9,2) not null,
                     stock int not null
);
create table orders (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     user_id bigint not null,
                     order_date datetime,
                     items_price decimal(10,2) not null,
                     order_status varchar(255) default 'PENDING',
                     note varchar(255),
                     foreign key (user_id) references users(id)
);
create table order_products (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    order_id bigint not null,
                    product_id bigint not null,
                    quantity int not null,
                    foreign key (order_id) references orders(id),
                    foreign key (product_id) references products(id)
);
