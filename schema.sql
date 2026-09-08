CREATE TABLE users (
                       user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                       first_name TEXT NOT NULL,
                       last_name TEXT NOT NULL,
                       birth_date TEXT NOT NULL,
                       email TEXT NOT NULL,
                       ad_street TEXT NOT NULL,
                       ad_street_num TEXT NOT NULL,
                       ad_supplement TEXT,
                       ad_city TEXT NOT NULL,
                       ad_postal_code INTEGER NOT NULL
);

CREATE TABLE books (
                       book_id INTEGER PRIMARY KEY AUTOINCREMENT,
                       isbn TEXT NOT NULL,
                       title TEXT NOT NULL,
                       author TEXT,
                       available INTEGER
);

CREATE TABLE loans (
                       loan_id INTEGER PRIMARY KEY AUTOINCREMENT,
                       book_id INTEGER NOT NULL REFERENCES books(book_id),
                       user_id INTEGER NOT NULL REFERENCES users(user_id),
                       loan_start_date TEXT NOT NULL,
                       expected_return_date TEXT,
                       actual_return_date TEXT
);