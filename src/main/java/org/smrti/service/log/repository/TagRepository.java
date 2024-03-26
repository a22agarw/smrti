package org.smrti.service.log.repository;

import org.smrti.service.log.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    @Query("SELECT t.logId FROM Tag t WHERE t.tagKey = :tagKey AND t.tagValue = :tagValue")
    List<String> findLogIdByTagKey(@Param("tagKey") String tagKey, @Param("tagValue") String tagValue);

    @Query("SELECT tags.logId FROM Tag tags WHERE tags.logId IN :logIds AND tags.tagKey = :tagkey AND tags.tagValue = :tagValue")
    List<String> findByLogIdAndTagKey(@Param("logIds") List<String> logIds, @Param("tagkey") String tagKey, @Param("tagValue") String tagValue);
}
