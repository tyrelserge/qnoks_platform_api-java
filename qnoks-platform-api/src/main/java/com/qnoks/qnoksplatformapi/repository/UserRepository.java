package com.qnoks.qnoksplatformapi.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qnoks.qnoksplatformapi.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
  
  Optional<User> findByEmail(String paramString);
  Optional<User> findByMobile(String paramString);

  @Modifying
  @Query(value = "UPDATE user SET status=:status WHERE user_id=:userid", nativeQuery = true)
  Integer executeSetUserStatus(@Param("userid") Integer userId, @Param("status") String status);

  @Query(value = "SELECT * FROM user WHERE lastname LIKE :lastname% OR firstname LIKE %:firstname%", nativeQuery = true)
  List<User> fetchUserbyFilds(@Param("lastname") String lastname, @Param("firstname") String firstname);

}
