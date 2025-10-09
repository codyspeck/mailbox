package com.speck.mailbox.lib.data.repositories;

import com.speck.mailbox.lib.data.entities.Message;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class MessageDao {

    private final JdbcTemplate jdbcTemplate;

    public MessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Message> get(String table, int count) {
        var sql = String.format(
                """
                SELECT m.id, m.payload, m.type
                FROM %s m
                LIMIT ?;
                """,
                table);

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Message.class), count);
    }

    public void insert(String table, Message mailboxMessage) {
        var sql = String.format(
                """
                INSERT INTO %s (payload, type, created_at)
                VALUES (?, ?, ?);
                """,
                table);

        jdbcTemplate.update(
                sql,
                mailboxMessage.getPayload(),
                mailboxMessage.getType(),
                mailboxMessage.getCreatedAt());
    }

    public void setProcessedAt(String table, List<Message> messages) {
        if (messages.isEmpty()) {
            return;
        }

        var sql = String.format(
                """
                UPDATE %s SET processed_at = ? WHERE id IN (?)
                """,
                table);

        jdbcTemplate.update(
                sql,
                Instant.now(),
                messages.stream().map(Message::getId).toList());
    }

}
