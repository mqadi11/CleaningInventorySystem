# University Cleaning Inventory & Issuance System

**Belgium Campus iTversity — Programming 37(8)1 Project**
**Track B: Java Desktop Application (Java Swing)**

A desktop application for managing university cleaning inventory, suppliers, cleaners, and stock issuance — built in Java using NetBeans (Swing) and PostgreSQL.

## Overview

This system enables university staff to securely manage cleaning materials, suppliers, cleaners, stock issuances, and inventory records through a user-friendly desktop interface. It was developed as a group project demonstrating Core Java, Object-Oriented Programming (OOP), GUI development, and CRUD operations against a relational database.

## Features

- **User Authentication** — registration, login, logout, password validation
- **Dashboard** — summary statistics: total materials, low-stock items, total cleaners, recent stock issuances
- **Materials Management (CRUD)** — add, view, update, delete materials; track stock and reorder levels; search and filter
- **Suppliers Management (CRUD)** — add, view, update, delete supplier records and contact details
- **Cleaners Management (CRUD)** — add, view, update, delete cleaner records
- **Stock Issuance Management (CRUD)** — issue materials to cleaners, automatic stock deduction, prevention of over-issuing, full issuance history
- **Reports** — inventory report, low-stock report, issuance history, material usage report
- **Low-stock alerts** — materials at or below reorder level are automatically flagged
- **Validation & exception handling** — required field checks, duplicate prevention, negative stock prevention, meaningful error messages

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java (Core Java, OOP) |
| GUI Framework | Java Swing (NetBeans GUI Builder) |
| Database | PostgreSQL |
| Database Access | JDBC |
| IDE | Apache NetBeans |

## Architecture (MVC)

```
View (JFrame forms)
   ↓ calls only
Controller (business logic & validation)
   ↓ calls only
Model / DAO (data objects & database access)
   ↓
PostgreSQL Database
```

- **Model** — Plain Java classes representing table rows (e.g. `Supplier.java`, `Material.java`, `Cleaner.java`)
- **DAO (Data Access Object)** — Handles all SQL/JDBC operations for each entity
- **Controller** — Validates input and coordinates between View and DAO, enforcing business rules
- **View** — Swing forms handling user interaction

### OOP Principles Demonstrated
- **Encapsulation** — private fields with public getters/setters across all model classes
- **Abstraction** — DAO classes hide SQL/JDBC details behind simple method calls (e.g. `addSupplier()`, `getAllMaterials()`)
- **Inheritance / Polymorphism** — applied where relevant across shared form/entity behaviour

## Database Schema

Core tables (see `schema.sql` for full definitions):

- `users` — system users, roles, authentication credentials
- `cleaners` — cleaning staff records
- `suppliers` — supplier/vendor contact details
- `materials` — stock items, linked to `suppliers` via foreign key
- `inventory_items` — general inventory items
- `inventory_issuances` — records of items issued to campus locations
- `issuance_transactions` — transaction log linking cleaners, materials, and issuing users

## Setup Instructions

### 1. Prerequisites
- JDK 17 or later
- Apache NetBeans IDE
- PostgreSQL installed and running locally
- 

### 2. Database Setup
1. Create a new database:
   ```sql
   CREATE DATABASE CIS;
   ```
2. Run `schema.sql` against it (via pgAdmin Query Tool or command line):
   ```bash
   psql -U postgres -d CIS -f schema.sql
   ```

### 3. Configure Database Connection
1. Copy `db.properties.example` to a new file named `db.properties`, placed at the **root of the source folder** (`src/db.properties`).
2. Fill in your local PostgreSQL credentials:
   ```properties
   db.url=jdbc:postgresql://localhost:5432/CIS
   db.user=postgres
   db.password=your_local_password
   ```
 **Note:** `db.properties` is excluded from version control (see `.gitignore`) since credentials are specific to each developer's local machine. Only `db.properties.example` is committed to the shared repository.

### 4. Run the Project
1. Open the project in NetBeans (**File → Open Project**).
2. Right-click the project → **Clean and Build**.
3. Run the application's main/login entry point to launch the system.


## Module Ownership

| Module | Responsible |
|---|---|
| Materials Management (CRUD) | Mutshidzi Nduvheni |
| Suppliers Management (CRUD) | Mutshidzi Nduvheni |
| User Authentication | Mkhanyisi Mqadi|
| Dashboard | Roebin Uys |
| Cleaners Management (CRUD) | Gontse Moleijane |
| Stock Issuance Management (CRUD) | Roebin Uys |
| Reports | Tetelo Phahladira |

# Note

- All SQL queries use `PreparedStatement` with parameterized values to prevent SQL injection.
- Database credentials are kept in `db.properties`, excluded from Git via `.gitignore`.
- Each developer maintains their own local `db.properties` with their own PostgreSQL credentials.
- Passwords are validated and not stored in plain text.

