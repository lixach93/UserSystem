create type user_role as enum ('ADMIN','USER');
create type user_status as enum ('ACTIVE','INACTIVE');


CREATE TABLE user_account
(
    id         serial primary key,
    user_name  character varying(16) unique not null,
    password   character varying(255)       not null,
    first_name character varying(16)        not null,
    last_name  character varying(16)        not null,
    role       user_role,
    status     user_status,
    date       timestamp default now()      not null

)
