UPDATE products
SET origin_country = '�����' 
WHERE code = 'BWC457030456' OR code = 'BWC450980456';

UPDATE suppliers
SET 
    bank_account = '4180566789764',  
    bank_name = '���� ����������' 
WHERE name = '��� �������� ��������';

DELETE FROM products 
WHERE price IS NULL;

TRUNCATE TABLE orders RESTART IDENTITY;