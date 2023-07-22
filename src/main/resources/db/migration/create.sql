CREATE TABLE IF NOT EXISTS school (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES school (id)
);

-- DROP TABLE school;
-- DROP TABLE student;