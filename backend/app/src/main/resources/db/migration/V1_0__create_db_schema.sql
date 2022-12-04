CREATE TABLE IF NOT EXISTS users(
    id INTEGER PRIMARY KEY,
    username VARCHAR(40) NOT NULL UNIQUE CHECK (username <> ''),
    first_name VARCHAR(40) NOT NULL CHECK (first_name <> ''),
    second_name VARCHAR(40) NOT NULL CHECK (second_name <> ''),
    password VARCHAR(20) NOT NULL CHECK (password <> '')
);

CREATE TABLE IF NOT EXISTS post(
    id INTEGER PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    text VARCHAR(255) NOT NULL CHECK (text <> '')
);

CREATE TABLE IF NOT EXISTS comment(
    id INTEGER PRIMARY KEY,
    post_id INTEGER REFERENCES post(id),
    user_id INTEGER REFERENCES users(id),
    text VARCHAR(255) NOT NULL CHECK (text <> '')
);

CREATE TABLE IF NOT EXISTS subscription(
    id INTEGER PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    subscribed_id INTEGER REFERENCES users(id)
);

create sequence hibernate_sequence start 1 increment 1;