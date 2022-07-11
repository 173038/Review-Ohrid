package com.example.reviewohrid.repository;

import com.example.reviewohrid.model.UserAnswerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerStatusRepository extends JpaRepository<UserAnswerStatus, Long> {
  //  UserAnswerStatus findById (Integer id);
}
//TODO: ova repo zakomentiraj go ako pukat na start