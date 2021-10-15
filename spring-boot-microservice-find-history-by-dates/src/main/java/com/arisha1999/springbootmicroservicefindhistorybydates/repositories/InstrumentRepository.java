package com.arisha1999.springbootmicroservicefindhistorybydates.repositories;

import com.arisha1999.springbootmicroservicefindhistorybydates.models.InstrumentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<InstrumentHistory, String>{

}
