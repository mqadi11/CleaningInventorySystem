-- UNIVERSITY CLEANING INVENTORY DATABASE SCHEMA

-- Clean up existing tables to allow fresh rebuilding
DROP TABLE IF EXISTS issuance_transactions CASCADE;
DROP TABLE IF EXISTS inventory_issuances CASCADE;
DROP TABLE IF EXISTS inventory_items CASCADE;
DROP TABLE IF EXISTS materials CASCADE;
DROP TABLE IF EXISTS suppliers CASCADE;
DROP TABLE IF EXISTS cleaners CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- 1. SYSTEM USERS TABLE
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. CLEANERS TABLE (Matches TablePlus structure)
CREATE TABLE cleaners (
    cleaner_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    employee_number VARCHAR(50),
    department VARCHAR(50)
);

-- 3. SUPPLIERS TABLE
CREATE TABLE suppliers (
    supplier_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    contact_person VARCHAR(100)
);

-- 4. MATERIALS TABLE
CREATE TABLE materials (
    material_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    quantity_in_stock INT,
    reorder_level INT,
    supplier_id INT REFERENCES suppliers(supplier_id),
    description TEXT
);

-- 5. INVENTORY ITEMS TABLE
CREATE TABLE inventory_items (
    item_id SERIAL PRIMARY KEY,
    item_name VARCHAR(120) NOT NULL UNIQUE,
    category VARCHAR(60) NOT NULL,
    quantity_available INT NOT NULL DEFAULT 0,
    minimum_stock_level INT NOT NULL DEFAULT 5,
    unit_of_measure VARCHAR(20) DEFAULT 'Units',
    CONSTRAINT chk_positive_quantity CHECK (quantity_available >= 0),
    CONSTRAINT chk_positive_min_level CHECK (minimum_stock_level >= 0)
);

-- 6. ISSUANCE TRANSACTIONS TABLE
CREATE TABLE issuance_transactions (
    transaction_id SERIAL PRIMARY KEY,
    cleaner_id INT REFERENCES cleaners(cleaner_id),
    material_id INT REFERENCES materials(material_id),
    issued_by INT REFERENCES users(user_id),
    date_issued TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 7. INVENTORY ISSUANCES TABLE
CREATE TABLE inventory_issuances (
    issuance_id SERIAL PRIMARY KEY,
    item_id INT REFERENCES inventory_items(item_id) ON DELETE CASCADE,
    issued_to VARCHAR(100) NOT NULL,
    campus_location VARCHAR(100) NOT NULL,
    quantity_issued INT NOT NULL,
    date_issued TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    remarks TEXT,
    CONSTRAINT chk_positive_issuance CHECK (quantity_issued > 0)
);