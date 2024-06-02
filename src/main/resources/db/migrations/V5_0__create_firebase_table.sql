CREATE TABLE firebase
(
    firebase_id SERIAL PRIMARY KEY,
    user_id     INT NOT NULL,
    firebase_token  TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)  ON DELETE CASCADE
);
