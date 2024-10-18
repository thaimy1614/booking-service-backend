package com.s_service.s_service.repository;

import com.s_service.s_service.dto.response.rating.staff.AverageRatingResponse;
import com.s_service.s_service.model.RatingStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingStaffRepository extends JpaRepository<RatingStaff, Integer> {
    @Query("SELECT " +
            "new com.s_service.s_service.dto.response.rating.staff.AverageRatingResponse(r.staffName, AVG(r.rating)) " +
            "FROM RatingStaff r " +
            "GROUP BY r.staffName")
    List<AverageRatingResponse> findAverageRatingGroupedByStaffName();

    List<RatingStaff> findRatingStaffByStaffName(String staffName);
}
