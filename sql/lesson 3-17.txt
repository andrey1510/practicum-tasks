CREATE TABLE product_categories (
    id SMALLSERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

INSERT INTO product_categories (name)
SELECT DISTINCT category
FROM products;

INSERT INTO suppliers (
    name,
    address,
    email,
    phone
)
VALUES
    ('ООО “Солёный персик”', 'г. Владимир, ул. Белая, д. 3-Б, к. 1', 'sol.pers@yandex.ru', '8674-34-92'),
    ('ОАО “Простор фантазии”', 'г. Самара, ул. Тухачевского, д. 231', 'office@profant.ru', '6985-256-66')
ON CONFLICT (name) DO UPDATE
SET 
    address = excluded.address,
    email = excluded.email,
    phone = excluded.phone;

UPDATE products
SET price = price * 1.05
WHERE origin_country = 'Китай'
RETURNING name;