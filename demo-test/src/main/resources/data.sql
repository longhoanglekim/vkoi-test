-- ===== USERS =====
insert into users (id, name, last_login)
values
    (1, 'Alice', '2025-10-10'),
    (2, 'Bob', '2025-10-11'),
    (3, 'Charlie', '2025-10-12');
ALTER TABLE users ALTER COLUMN id RESTART WITH 4;

-- ===== PRODUCTS =====
insert into products (id, name, description, price, stock)
values
    (1, 'Áo thun', 'Áo thun cotton trắng', 150000, 50),
    (2, 'Quần jean', 'Quần jean slim-fit xanh', 350000, 30),
    (3, 'Giày sneaker', 'Giày sneaker thể thao', 550000, 20);
ALTER TABLE products ALTER COLUMN id RESTART WITH 4;

-- ===== ORDERS =====
insert into orders (id, user_id, order_date, items_price, order_status)
values
    (1, 1, CURRENT_TIMESTAMP, 650000, 'PENDING'),
    (2, 2, CURRENT_TIMESTAMP, 550000, 'SUCCESS');
ALTER TABLE orders ALTER COLUMN id RESTART WITH 3;

-- ===== ORDER_PRODUCTS =====
insert into order_products (id, order_id, product_id, quantity)
values
    (1, 1, 1, 2),   -- order 1 mua 2 cái áo thun = 300k
    (2, 1, 2, 1),   -- order 1 mua 1 quần jean = 350k (tổng 650k)
    (3, 2, 3, 1);   -- order 2 mua 1 đôi giày = 550k
ALTER TABLE order_products ALTER COLUMN id RESTART WITH 4;