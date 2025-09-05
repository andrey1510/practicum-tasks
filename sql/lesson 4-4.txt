SELECT DISTINCT c.name 
FROM actor a
    JOIN film_actor fa ON a.actor_id = fa.actor_id
    JOIN film_category fc ON fa.film_id = fc.film_id
    JOIN category c ON fc.category_id = c.category_id
WHERE a.first_name = 'Emily' AND a.last_name = 'Dee';

SELECT DISTINCT c.name 
FROM category c
    JOIN film_category fc ON c.category_id = fc.category_id
WHERE fc.film_id IN (
    SELECT fa.film_id 
    FROM film_actor fa
        JOIN actor a ON fa.actor_id = a.actor_id
    WHERE a.first_name = 'Emily' AND a.last_name = 'Dee'
);

SELECT s.last_name employee_last_name, m.last_name manager_last_name 
FROM staff s 
    LEFT JOIN staff m ON s.reports_to = m.employee_id;
    
SELECT t.name, CAST(i.invoice_date AS date)
FROM track t 
    LEFT JOIN invoice_line il ON t.track_id = il.track_id 
    LEFT JOIN invoice i ON il.invoice_id = i.invoice_id;

SELECT title 
FROM movie m 
    LEFT JOIN film_actor fa ON m.film_id = fa.film_id
    LEFT JOIN actor a ON a.actor_id = fa.actor_id
WHERE a.actor_id IS NULL;

SELECT ar.name 
FROM artist ar 
    LEFT JOIN album al ON ar.artist_id = al.artist_id; 
WHERE al.artist_id IS NULL;