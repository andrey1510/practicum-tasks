INSERT INTO suppliers (name, address, phone, email, contact_person, bank_name, bank_account)
VALUES ('��� �������', '�. ������, ��. 3-� ������, �. 345', '687-44-55', 'contact@ptichka.ru', '������� ����� ��������', '��� �����', '40801234567890'); 

INSERT INTO customers (name, email, phone, address, birthday) VALUES 
    ('������ �������', 'egorov.a@mail.ru', '+7(912)0764567', '������, ������� ��. 75/101', '1994-08-09'),
    ('�������� ��������', 'sveta2000@yandex.ru', '810 34-345-88', '�����, ��. ������� 3�, ��. 17', null);

INSERT INTO products (name, category, price, amount, description, origin_country, code) VALUES 
    ('����� �������', '�����', '3200', '10', '����������� ����. ������������ �. ������', '������', '���098057483'), 
    ('�������� �������', '�����', '2800', default, '�����. ������������� ����.', '�����', '���789030456'), 
    ('��������� �������', '�����', '1200', '20', '����� ����� � ��������', '������', '���456787651');