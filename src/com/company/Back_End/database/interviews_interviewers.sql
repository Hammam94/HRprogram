CREATE TABLE IF NOT EXISTS interviews_interviewers(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    interview_id INTEGER NOT NULL,
    interviewer_id INTEGER NOT NULL
)