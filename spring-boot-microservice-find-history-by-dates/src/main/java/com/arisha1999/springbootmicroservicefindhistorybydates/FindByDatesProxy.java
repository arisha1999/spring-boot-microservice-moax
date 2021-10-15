package com.arisha1999.springbootmicroservicefindhistorybydates;
import com.arisha1999.springbootmicroservicefindhistorybydates.models.History;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="find-instrument-last-price")
@RibbonClient(name="find-instrument-last-price")
public interface FindByDatesProxy {
    @GetMapping("/instruments/{ticker}/{date}")
    public History retrieveInstrument
            (@PathVariable String ticker, @PathVariable String date);
}
