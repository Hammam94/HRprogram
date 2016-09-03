CREATE TABLE IF NOT EXISTS interviewers_interviewees(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    interviewee_id INTEGER NOT NULL,
    interviewer_id INTEGER NOT NULL
)