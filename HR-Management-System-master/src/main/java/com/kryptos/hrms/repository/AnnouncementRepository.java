package com.kryptos.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kryptos.hrms.model.Announcement;
import com.kryptos.hrms.request.AnnouncementRequestDTO;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
