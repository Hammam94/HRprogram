CREATE TABLE IF NOT EXISTS interviews (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name TEXT NOT NULL,
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)