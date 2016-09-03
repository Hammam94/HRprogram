CREATE TABLE IF NOT EXISTS interviews_interviewees(
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    interview_id INTEGER NOT NULL,
    interviewee_id INTEGER NOT NULL
)