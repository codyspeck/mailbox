# Mailbox

```sql
create table inbox (
    id           bigint       not null primary key auto_increment,
    payload      json         not null,
    type         varchar(100) not null,
    created_at   datetime     not null,
    processed_at datetime     null,
    locked_until datetime     null
);

create table outbox (
    id           bigint       not null primary key auto_increment,
    payload      json         not null,
    type         varchar(100) not null,
    created_at   datetime     not null,
    processed_at datetime     null,
    locked_until datetime     null
);
```
