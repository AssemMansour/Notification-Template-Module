package com.api.repo;

import com.api.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM notification LIMIT 1")
    @Transactional
    void deleteTop();

    @Query(nativeQuery = true, value = "SELECT * FROM notification WHERE status = \"TO_BE_SENT\" LIMIT 1")
    Notification getNext();

}
