SELECT name, phone, email 
FROM suppliers;

SELECT name, category, description, price 
FROM products 
WHERE category = '������ ����������' AND description ILIKE '%�����%' AND amount > 0;
 
SELECT name, category, price, amount 
FROM products 
WHERE origin_country = '�����' AND (price <= 1000 OR amount <= 3);