UPDATE movie AS m
SET rating = 'R'
FROM film_category AS fc, category AS c
WHERE
    m.film_id = fc.film_id
    AND c.category_id = fc.category_id
    AND c.name = 'Children'
    AND m.rating = 'NC-17';
    
UPDATE movie AS m
SET description = description || '. Actor Johnny Cage takes part in the film.'    
FROM film_actor fa, actor a
WHERE
    m.film_id = fa.film_id
    AND fa.actor_id = a.actor_id
    AND a.first_name = 'Johnny'
    AND a.last_name = 'Cage';

UPDATE movie AS m
    SET description = description || '. Actor Johnny Cage takes part in the film.'
FROM film_actor AS fa
INNER JOIN actor AS a USING (actor_id)
WHERE 
    fa.film_id = m.film_id
    AND a.first_name = 'Johnny'
    AND a.last_name = 'Cage';

DELETE FROM invoice_line il 
USING track t
WHERE 
    il.track_id = t.track_id
    AND t.name = 'Balls to the Wall'; 