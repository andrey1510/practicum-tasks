SELECT 
    category,
    sales_q_4::REAL / (sales_q_1 + sales_q_2 + sales_q_3 + sales_q_4)
FROM sales_report
ORDER BY category ASC;

SELECT 
    name,
    CASE 
        WHEN origin_country = 'Россия' THEN 'Россия'
        WHEN origin_country IS NULL THEN 'Не указано'
        ELSE 'Импорт'
    END AS origin
FROM 
    products;

UPDATE products 
SET 
    CASE 
        WHEN origin_country = 'РОсс'

UPDATE products
SET price = 
    CASE 
        WHEN origin_country = 'Россия' THEN price * 0.95 
        WHEN origin_country = 'Турция' THEN price * 1.03   
        ELSE price     
    END
WHERE amount > 0;