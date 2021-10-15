package com.arisha1999.springbootmicroservicefindinstrumentlastprice.repositories;

import com.arisha1999.springbootmicroservicefindinstrumentlastprice.models.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, String> {

}
