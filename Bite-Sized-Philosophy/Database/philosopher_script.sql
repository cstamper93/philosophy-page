rollback;

BEGIN TRANSACTION;

DROP TABLE IF EXISTS philosopher, school, ideas;

CREATE TABLE philosopher(
    id SERIAL,
    philosopher_id CHAR(10) UNIQUE,
    philosopher_name VARCHAR(50),
    photo VARCHAR(100),
    nationality VARCHAR(50),
    era VARCHAR(50),
    CONSTRAINT PK_philosopher PRIMARY KEY(id)
);

CREATE TABLE school(
    school_name VARCHAR(50),
    philosopher_id CHAR(10),
    CONSTRAINT PK_school PRIMARY KEY(school_name, philosopher_id),
    CONSTRAINT FK_school_philosopher FOREIGN KEY (philosopher_id) REFERENCES philosopher(philosopher_id)
);

CREATE TABLE idea(
    quote VARCHAR(500),
    philosopher_id CHAR(10),
    CONSTRAINT PK_idea PRIMARY KEY(quote, philosopher_id),
    CONSTRAINT FK_idea_philosopher FOREIGN KEY (philosopher_id) REFERENCES philosopher(philosopher_id)
);

COMMIT;