CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(32) UNIQUE NOT NULL ,
    password VARCHAR NOT NULL ,
    role VARCHAR NOT NULL ,
    status VARCHAR NOT NULL ,
    timezone VARCHAR
)