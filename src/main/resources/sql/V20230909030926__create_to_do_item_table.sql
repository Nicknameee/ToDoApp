CREATE TABLE IF NOT EXISTS to_do_items(
    id BIGSERIAL PRIMARY KEY ,
    title VARCHAR NOT NULL ,
    user_id BIGINT REFERENCES users(id) ,
    description VARCHAR ,
    issued_at  TIMESTAMP WITH TIME ZONE ,
    finish_by TIMESTAMP WITH TIME ZONE ,
    finished_at TIMESTAMP WITH TIME ZONE
)