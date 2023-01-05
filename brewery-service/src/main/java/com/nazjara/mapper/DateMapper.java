package com.nazjara.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class DateMapper {

    LocalDateTime map(Timestamp ts) {
        if (ts != null) {
            return ts.toLocalDateTime();
        } else {
            return null;
        }
    }

    Timestamp map(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return Timestamp.valueOf(localDateTime);
        } else {
            return null;
        }
    }
}