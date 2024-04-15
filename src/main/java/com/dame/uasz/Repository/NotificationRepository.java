package com.dame.uasz.Repository;

import com.dame.uasz.Model.Demande;
import com.dame.uasz.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
