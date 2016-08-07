package com.react.controller.rest;

import com.react.controller.converter.CityConverter;
import com.react.controller.model.City;
import com.react.service.CacheService;
import com.react.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private static final Logger log = LoggerFactory.getLogger(CityController.class);
    private final CityService service;
    private final CityConverter cityConverter;
    private final GaugeService guageService;
    private final CacheService cacheService;
    private final StopWatch stopWatch = new StopWatch();

    @Autowired
    CityController(CityService service, CityConverter converter, GaugeService guageService, CacheService cacheService) {
        this.service = service;
        this.cityConverter = converter;
        this.guageService = guageService;
        this.cacheService = cacheService;
    }


    @ApiOperation(value = "findAllStartsWith", nickname = "findAllStartsWith")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = City.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "findAllStartsWith/{name}/{caseSensitive}", method = RequestMethod.GET)
    List<City> findAllStartsWith(@PathVariable("name") String name, @PathVariable("caseSensitive") Boolean caseSensitive) throws UnknownHostException {

        log.info(String.format("In CityController.findAllStartsWith with name:%s caseSensitive:%s", name, caseSensitive));

        List<City> cities = new ArrayList<>();

        try{
            //todo: tidy below block of code up
            String timesKey = "CityController.findAllStartsWith-times";
            String cacheValue = cacheService.get(timesKey);
            Integer numberOfTimesExecuted = Integer.valueOf(cacheValue) + 1;
            cacheService.add(timesKey, numberOfTimesExecuted.toString());

            //TODO: crop at 1 billion then start again
            String totalTimeTakenKey = "CityController.findAllStartsWith-totalTimeTaken";
            String totalTimeTakenValue = cacheService.get(totalTimeTakenKey);

            StartTimer();
            cities = cityConverter.ConvertEntitiesToModels(service.findAllStartsWith(name, caseSensitive));

            Long numberOfElapsedMilliseconds = StopTimer();
            Double totalTimeTakenNew = Double.valueOf(totalTimeTakenValue) + numberOfElapsedMilliseconds;
            cacheService.add(totalTimeTakenKey, totalTimeTakenNew.toString());

            //average
            Double averageTime = (double) Math.round(totalTimeTakenNew / numberOfTimesExecuted);

            guageService.submit("numberOfHits.CityController.findAllStartsWith", numberOfTimesExecuted);
            guageService.submit("averageTime.CityController.findAllStartsWith", averageTime);

        } catch(Exception exception){
            log.error("Exception in CityController.findAllStartsWith {}", exception.getMessage());
            StopTimer();
        }

        return cities;
    }

    @ApiOperation(value = "findAllContains", nickname = "findAllContains")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = City.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "findAllContains/{name}/{caseSensitive}", method = RequestMethod.GET)
    List<City> findAllContains(@PathVariable("name") String name, @PathVariable("caseSensitive") Boolean caseSensitive) throws UnknownHostException {

        log.info("In CityController.findAllContains with name:" + name + " caseSensitive:" + caseSensitive);

        List<City> cities = new ArrayList<>();

        try {
            //todo: tidy below block of code up
            String timesKey = "CityController.findAllContains-times";
            String cacheValue = cacheService.get(timesKey);
            Integer numberOfTimesExecuted = Integer.valueOf(cacheValue) + 1;
            cacheService.add(timesKey, numberOfTimesExecuted.toString());

            //TODO: crop at 1 billion then start again
            String totalTimeTakenKey = "CityController.findAllContains-totalTimeTaken";
            String totalTimeTakenValue = cacheService.get(totalTimeTakenKey);

            StartTimer();
            cities = cityConverter.ConvertEntitiesToModels(service.findAllContains(name, caseSensitive));

            Long numberOfElapsedMilliseconds = StopTimer();
            Double totalTimeTakenNew = Double.valueOf(totalTimeTakenValue) + numberOfElapsedMilliseconds;
            cacheService.add(totalTimeTakenKey, totalTimeTakenNew.toString());

            //average
            Double averageTime = (double) Math.round(totalTimeTakenNew / numberOfTimesExecuted);

            guageService.submit("numberOfHits.CityController.findAllContains", numberOfTimesExecuted);
            guageService.submit("averageTime.CityController.findAllContains", averageTime);

        } catch(Exception exception){
            log.error("Exception in CityController.findAllContains {}", exception.getMessage());
            StopTimer();
        }

        return cities;
    }

    private void StartTimer(){
        stopWatch.start();
    }

    private Long StopTimer(){
        stopWatch.stop();
        return stopWatch.getTotalTimeMillis();
    }
}
