UPDATE products
SET origin_country = 'Китай' 
WHERE code = 'BWC457030456' OR code = 'BWC450980456';

UPDATE suppliers
SET 
    bank_account = '4180566789764',  
    bank_name = 'Банк «Котикофф»' 
WHERE name = 'ОАО “Простор фантазии“';

DELETE FROM products 
WHERE price IS NULL;

TRUNCATE TABLE orders RESTART IDENTITY;