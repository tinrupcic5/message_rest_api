CREATE TABLE users
(
    user_id      SERIAL PRIMARY KEY,
    username     TEXT NOT NULL UNIQUE,
    password     TEXT NOT NULL,
    email        TEXT NOT NULL UNIQUE,
    phone_number TEXT NOT NULL UNIQUE
);
