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

## Roadmap

- Support queueing in `MessagePipelineImpl`
- Support backpressure in `MessagePipelineImpl`
- Support parallelism in `MessagePipelineImpl` 
- Support batch message handling
- Support message key for deduplication
- Support archiving (deleting) old messages