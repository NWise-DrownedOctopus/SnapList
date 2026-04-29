# Snap List

A desktop buylist application for trading card shop employees. Built with Java Swing, MySQL, and JDBC.

---

## What It Does

Snap List lets store employees build a structured buylist of cards a customer wants to sell. Each employee logs in with their own account, manages their own card entries, and can filter by game, language, and condition. Administrators can view all cards across all users.

---

## Tech Stack

- Java Swing (UI)
- MySQL (persistence)
- JDBC with PreparedStatement
- BCrypt (password hashing)
- JUnit 5 (tests)

---

## Setup

### Prerequisites
- MySQL Server running on `localhost:3306`
- Java JDK 17+
- VS Code with Java Extension Pack

### Database
1. Open MySQL Workbench and run `schema.sql` from the project root
2. Copy `db.properties.example` to `db.properties` (in both the project root and `src/`)
3. Fill in your credentials:

```
db.url=jdbc:mysql://localhost:3306/snaplist_db
db.username=your_username
db.password=your_password
```

### Run
Open `AppUI.java` in VS Code and hit Run. The login screen will appear — register a new account to get started.

### Admin access
Admins are set directly in MySQL — there is no in-app admin registration:
```sql
UPDATE users SET is_admin = TRUE WHERE username = 'your_username';
```

---

## Features

**Authentication**
- Register and login with BCrypt-hashed passwords
- User-specific data — regular users only see their own cards
- Admin role sees all cards across all users

**Cards (full CRUD)**
- Add, edit, delete, and list cards
- Fields: game, set, name, condition, language, quantity
- All changes persist to MySQL

**Filtering**
- Game dropdown (MTG, Pokemon, Lorcana, etc.)
- Language dropdown (English, Japanese, Korean, etc.)
- Condition toggle buttons (NM / LP / MP / HP / DG)
- Filters can be combined

**Architecture**
- MVC layering: UI → Controller → Service → DAO → MySQL
- SwingWorker for background DB load with "Loading cards..." feedback
- JOIN query: cards joined with users, ordered by condition
- Two service-layer business rules with friendly error messages

**Tests**
- 3 JUnit 5 tests for `CardService` using an in-memory stub DAO
- All 3 passing

---

## Known Limitations

- Printing type is hardcoded to Normal
- Market price is a placeholder ($0.00)
- Shop quantity is a placeholder (0)
- Card sets are hardcoded in the UI dropdown
- No logout button — close and reopen the app to switch users

---

## Project Structure

```
src/
  AppUI.java               # Entry point
  controller/              # UI event handling
  service/                 # Business rules
  dao/                     # JDBC database access
  model/                   # Card, User, enums
  view/                    # Swing UI classes
test/
  service/CardServiceTest.java
lib/                       # JARs (MySQL connector, BCrypt, JUnit)
schema.sql                 # Database schema
db.properties.example      # Credentials template
```
