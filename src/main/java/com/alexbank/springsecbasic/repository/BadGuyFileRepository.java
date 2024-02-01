package com.alexbank.springsecbasic.repository;

import com.alexbank.springsecbasic.entity.BadGuyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BadGuyFileRepository extends JpaRepository<BadGuyFile, Long> {
    @Query(value = "SELECT b.`generated-text-name` FROM test_sec.`bad-guy-file` b\n" +
            "ORDER BY b.`date` DESC\n" +
            "LIMIT 1;", nativeQuery = true)
    String getLatestTextFileName();
}
