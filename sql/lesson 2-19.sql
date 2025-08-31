CREATE TABLE service_points (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    phone TEXT NOT NULL,
    address TEXT NOT NULL,
    manager TEXT NOT NULL,
    opens_at TIME NOT NULL,
    closes_at TIME NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp
);

ALTER TABLE service_points 
    ADD COLUMN days_off TEXT;

ALTER TABLE service_points
    ADD CONSTRAINT check_close_time
    CHECK (closes_at > opens_at);

CREATE TABLE suppliers (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL,
    phone TEXT NOT NULL,
    address TEXT NOT NULL,
    contact_person TEXT,
    bank_name TEXT,
    bank_account TEXT,
    created_at TIMESTAMP DEFAULT current_timestamp
);

ALTER TABLE suppliers
    ADD COLUMN comments TEXT, 
    ADD COLUMN debt NUMERIC(12, 2);
