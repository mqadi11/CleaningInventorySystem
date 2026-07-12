-- ==========================================
-- UNIVERSITY CLEANING INVENTORY DATABASE SCHEMA
-- ==========================================

-- Clean up any existing tables to allow fresh rebuilding
DROP TABLE IF EXISTS inventory_issuances CASCADE;
DROP TABLE IF EXISTS inventory_items CASCADE;
DROP TABLE IF EXISTS users CASCADE;
---
-- 1. SYSTEM USERS TABLE
-- Tracks campus staff authorized to log into the desktop application.
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL, -- Built to safely store hashed passwords
    role VARCHAR(30) NOT NULL DEFAULT 'Staff', -- e.g., 'Admin', 'Staff Coordinator'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
---
-- 2. INVENTORY ITEMS TABLE
-- Stores specific cleaning consumables, tools, and industrial equipment.
CREATE TABLE inventory_items (
    item_id SERIAL PRIMARY KEY,
    item_name VARCHAR(120) NOT NULL UNIQUE,
    category VARCHAR(60) NOT NULL, -- e.g., 'Detergents', 'Equipment', 'Consumables'
    quantity_available INT NOT NULL DEFAULT 0,
    minimum_stock_level INT NOT NULL DEFAULT 5,
    unit_of_measure VARCHAR(20) DEFAULT 'Units', -- e.g., 'Liters', 'Boxes', 'Units'
    
    -- Safety Check Constraint: Stock can never drop below zero
    CONSTRAINT chk_positive_quantity CHECK (quantity_available >= 0),
    CONSTRAINT chk_positive_min_level CHECK (minimum_stock_level >= 0)
);
---
-- 3. INVENTORY ISSUANCES TABLE
-- Full tracking log detailing when stock is pulled, how much, and where it goes.
CREATE TABLE inventory_issuances (
    issuance_id SERIAL PRIMARY KEY,
    item_id INT NOT NULL,
    issued_to VARCHAR(100) NOT NULL,   -- Name of the cleaner or team leader receiving stock
    campus_location VARCHAR(100) NOT NULL, -- Destination (e.g., 'Block A', 'Residences', 'Lab 3')
    quantity_issued INT NOT NULL,
    date_issued TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    remarks TEXT,                         -- For custom notes like "Spill cleanup emergency"
    
    -- Relationships and Rules
    FOREIGN KEY (item_id) REFERENCES inventory_items(item_id) ON DELETE CASCADE,
    CONSTRAINT chk_positive_issuance CHECK (quantity_issued > 0)
);
---
-- 4. INITIAL TEST DATA SEEDING
-- Pre-populating the database so you have instantly workable data for your UI tables.

-- Default user credentials (Password hash representations)
INSERT INTO users (username, password_hash, role) VALUES
('admin', 'admin_secure_hash_placeholder', 'Admin'),
('staff1', 'staff_secure_hash_placeholder', 'Staff');
-- Initial Cleaning Stock
INSERT INTO inventory_items (item_name, category, quantity_available, minimum_stock_level, unit_of_measure) VALUES
('Heavy Duty Floor Cleaner', 'Detergents', 45, 10, 'Liters'),
('Microfiber Cloths (Pack of 10)', 'Consumables', 60, 15, 'Units'),
('Industrial Mop Buckets', 'Equipment', 8, 3, 'Units'),
('Bleach Ultra Disinfectant', 'Detergents', 12, 10, 'Liters'),
('Latex Gloves (Box of 100)', 'Consumables', 4, 8, 'Units');
-- This item will trigger a low stock alert flag!

-- Initial Issuance Records
INSERT INTO inventory_issuances (item_id, issued_to, campus_location, quantity_issued, remarks) VALUES
(1, 'Thabo Khumalo', 'Campus Block A - Main Hall', 2, 'Routine weekly deep clean'),
(2, 'Sarah Smith', 'Student Residences - Ground Floor', 3, 'Regular turnover stock'),
(4, 'Thabo Khumalo', 'Science Lab 3', 1, 'Chemical spill cleanup supply');