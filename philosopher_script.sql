
BEGIN TRANSACTION;

DROP TABLE IF EXISTS philosopher, school, idea;

CREATE TABLE philosopher(
    id SERIAL,
    philosopher_name VARCHAR(50),
    photo VARCHAR(300),
    nationality VARCHAR(50),
    era VARCHAR(50),
    favorited BOOLEAN,
    CONSTRAINT PK_philosopher PRIMARY KEY(id)
);

CREATE TABLE school(
    school_name VARCHAR(50),
    philosopher_id INTEGER,
    CONSTRAINT PK_school PRIMARY KEY(school_name, philosopher_id),
    CONSTRAINT FK_school_philosopher FOREIGN KEY (philosopher_id) REFERENCES philosopher(id)
);

CREATE TABLE idea(
    quote VARCHAR(2000),
    philosopher_id INTEGER,
    CONSTRAINT PK_idea PRIMARY KEY(quote, philosopher_id),
    CONSTRAINT FK_idea_philosopher FOREIGN KEY (philosopher_id) REFERENCES philosopher(id)
);

COMMIT;