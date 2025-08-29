CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    created_at TIMESTAMP
); 

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    phone TEXT NOT NULL,
    address TEXT NOT NULL,
    birthday DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone TEXT NOT NULL,
    address TEXT NOT NULL,
    birthday DATE,
    created_at TIMESTAMP DEFAULT current_timestamp,
    UNIQUE (phone, address)
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    amount NUMERIC(9, 2) NOT NULL DEFAULT 0,
    paid BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP DEFAULT current_timestamp
);


CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    amount NUMERIC(9, 2) NOT NULL DEFAULT 0 CHECK (amount >= 0),
    paid BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP DEFAULT current_timestamp
);