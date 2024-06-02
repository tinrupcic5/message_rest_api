CREATE TABLE messages
(
    message_id   SERIAL PRIMARY KEY,
    message_text TEXT NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    chat_id      INTEGER NOT NULL,
    user_id      INTEGER NOT NULL,
    is_read      BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (chat_id) REFERENCES chats (chat_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
