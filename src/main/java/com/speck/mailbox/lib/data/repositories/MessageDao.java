package com.speck.mailbox.lib.data.repositories;

import com.speck.mailbox.lib.data.entities.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageDao {

    private final JdbcTemplate jdbcTemplate;

    public Message get(String table, long messageId) {
        var sql = String.format(
                """
                SELECT m.id, m.payload, m.type, m.created_at, m.processed_at, m.locked_until
                FROM %s m
                WHERE id = ?
                LIMIT 1
                FOR UPDATE;
                """,
                table);

        return jdbcTemplate
                .query(sql, new BeanPropertyRowMapper<>(Message.class), messageId)
                .getFirst();
    }

    public List<Message> get(String table, int count) {
        var sql = String.format(
                """
                SELECT m.id, m.payload, m.type, m.created_at, m.processed_at, m.locked_until
                FROM %s m
                LIMIT ?
                FOR UPDATE SKIP LOCKED;
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

    public void setLockedUntil(String table, Instant lockedUntil, List<Message> messages) {
        if (messages.isEmpty()) {
            return;
        }

        var sql = String.format(
                """
                UPDATE %s SET locked_until = ? WHERE id IN (?);
                """,
                table);

        jdbcTemplate.update(
                sql,
                lockedUntil,
                messages.stream().map(Message::getId).toList());
    }

    public void setProcessedAt(String table, Instant processedAt, Message message) {
        var sql = String.format(
                """
                UPDATE %s SET processed_at = ? WHERE id = ?;
                """,
                table);

        jdbcTemplate.update(sql, processedAt, message.getId());
    }
}
