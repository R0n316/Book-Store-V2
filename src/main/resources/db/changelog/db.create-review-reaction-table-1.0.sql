--liquibase formatted sql

--changeset alex:1

CREATE TABLE review_reaction
(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    user_id int REFERENCES users(id),
    book_review_id int REFERENCES book_review(id) ON DELETE CASCADE ,
    reaction varchar(10),
    UNIQUE (user_id,book_review_id)
)