-- init.sql
CREATE TABLE IF NOT EXISTS ratings (
                                       id SERIAL PRIMARY KEY,
                                       user_id BIGINT NOT NULL ,
                                       rated_user_id BIGINT NOT NULL,
                                       user_rating SMALLINT NOT NULL CHECK (user_rating >= 1 AND user_rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX idx_rated_user_id ON ratings(rated_user_id);