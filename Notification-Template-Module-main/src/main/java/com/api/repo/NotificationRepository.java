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
    public void deleteTop();

    @Query(value = "SELECT *, 0 AS clazz_ FROM notification LIMIT 1", nativeQuery = true)
    public Notification getNext();

}
