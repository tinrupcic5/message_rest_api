CREATE TABLE participants
(
    participant_id SERIAL PRIMARY KEY,
    chat_id        INTEGER NOT NULL,
    user_id        INTEGER NOT NULL,
    FOREIGN KEY (chat_id) REFERENCES chats (chat_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
