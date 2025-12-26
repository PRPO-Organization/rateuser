

-- Switch to the database manually after running this block
\c user_ratings;

-- Users table
CREATE TABLE IF NOT EXISTS users (
                                     user_id SERIAL PRIMARY KEY,   -- SERIAL instead of AUTO_INCREMENT
                                     username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Ratings table
CREATE TABLE IF NOT EXISTS ratings (
                                       rating_id SERIAL PRIMARY KEY,
                                       user_id INT NOT NULL,
                                       rated_user_id INT NOT NULL,
                                       rating SMALLINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    rated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_id, rated_user_id),  -- one rating per user per user
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (rated_user_id) REFERENCES users(user_id) ON DELETE CASCADE
    );
