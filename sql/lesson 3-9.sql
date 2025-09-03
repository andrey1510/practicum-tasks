SELECT name, description, origin_country, price 
FROM products 
WHERE category = 'Модные аксессуары' AND amount > 0 ORDER BY price DESC;

SELECT name, description, origin_country, price 
FROM products 
WHERE category = 'Модные аксессуары' AND amount > 0 
ORDER BY price DESC 
LIMIT 5 OFFSET 10;

SELECT DISTINCT ON origin_country, price
FROM products 
WHERE amount > 0 
ORDER BY price ASC;

SELECT DISTINCT ON (origin_country) origin_country, price
FROM products
WHERE amount > 0
ORDER BY origin_country ASC, price ASC