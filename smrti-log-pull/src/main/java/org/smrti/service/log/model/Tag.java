package org.smrti.service.log.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "db_tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "log_id")
    String logId;

    @Column(name = "tag_key")
    String tagKey;

    @Column(name = "tag_value")
    String tagValue;

    @Column(name = "timestamp")
    String timestamp;

    public Tag(String logId, String tagKey, String tagValue, String timestamp) {
        this.logId = logId;
        this.tagKey = tagKey;
        this.tagValue = tagValue;
        this.timestamp = timestamp;
    }
}
