package com.vynohradov.requesttrap.repositories;

import com.vynohradov.requesttrap.entities.RequestData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestDataRepository extends JpaRepository<RequestData, Long> {

    List<RequestData> findByTrapId(String trapId);

}
