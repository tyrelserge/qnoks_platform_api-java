package com.qnoks.qnoksplatformapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qnoks.qnoksplatformapi.entity.Client;
import com.qnoks.qnoksplatformapi.entity.User;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, Integer>{

    @Modifying
    @Query(value = "INSERT INTO client (user_id) VALUES (?)", nativeQuery = true)
    public int saveClient(Integer userId);

    @Query(value = "SELECT * FROM client ORDER BY client_id DESC LIMIT 1", nativeQuery = true)
    public Client fetchLastSavingClient();

    @Modifying
    @Query(value = "UPDATE client SET status=:status WHERE client_id=:clientid", nativeQuery = true)
    public int executeSetClientStatus(@Param("clientid") Integer clientId, @Param("status") String status);

    public Client findClientByUser(User user);
    
}
