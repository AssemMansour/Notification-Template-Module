package com.api.repo;

import com.api.model.Notification;
import com.api.model.SmsNotification;
import com.api.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SmsRepository extends JpaRepository<SmsNotification, Long> {

}
