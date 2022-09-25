package com.qnoks.qnoksplatformapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qnoks.qnoksplatformapi.entity.Staff;
import com.qnoks.qnoksplatformapi.entity.User;

@Repository
@Transactional
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Modifying
    @Query(value = "INSERT INTO staff (role_id, user_id) VALUES (?1, ?2)", nativeQuery = true)
    public Integer saveStaff(Integer roleId, Integer userId);

    @Query(value = "SELECT * FROM staff ORDER BY staff_id DESC LIMIT 1", nativeQuery = true)
    public Staff fetchLastSavingStaff();

    public Staff findStaffByUser(User user);

    @Modifying
    @Query(value = "UPDATE staff SET status=:status WHERE staff_id=:staffid", nativeQuery = true)
    public Integer executeSetStaffStatus(@Param("staffid") Integer staffId, @Param("status") String status);
}
