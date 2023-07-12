CREATE TABLE IF NOT EXISTS character(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS character_info(
    id SERIAL PRIMARY KEY,
    character_id INT,
    birth_date DATE,
    gender CHAR(1),
    Foreign Key (character_id) REFERENCES character (id)
);

-- DROP TABLE character;
-- DROP TABLE character_info;