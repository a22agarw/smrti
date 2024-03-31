package org.smrti.service.log.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "re_db_logs")
public class Log {

    @Id
    String id;

    @Field(value = "log")
    Object log;
}
