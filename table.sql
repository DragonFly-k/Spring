use java
CREATE TABLE role
(
    [id]      [bigint] IDENTITY (1,1) NOT NULL PRIMARY KEY,
    [name]    [varchar](255)          NULL,
)

CREATE TABLE scooter
(
    [id]      [bigint] IDENTITY (1,1) NOT NULL PRIMARY KEY,
    [model]   [varchar](255)          NULL,
    [price]   [float]                 NULL,
)
CREATE TABLE account
(
    [id]       [bigint] IDENTITY (1,1) NOT NULL PRIMARY KEY,
    [email]    [varchar](255)          NULL,
    [login]    [varchar](255)          NULL,
    [password] [varchar](255)          NULL,
    [role_id]  [bigint]                DEFAULT 1 FOREIGN KEY REFERENCES role(id),
)
CREATE TABLE account_scooter
(
    [id]         [bigint] IDENTITY (1,1) NOT NULL primary key,
    [rent_time]  [datetime2](7)          NULL,
    [account_id] [bigint]                NULL foreign key references account(id),
    [scooter_id] [bigint]                NULL foreign key references scooter(id)
)

insert into role (name) values ('BUYER');
insert into role (name) values ('ADMIN');