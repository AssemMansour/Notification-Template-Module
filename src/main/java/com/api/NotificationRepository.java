package com.api;

import com.api.model.Notification;
import com.api.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM notification ORDER BY id ASC LIMIT 1")
    @Transactional
    void deleteTop();
}
