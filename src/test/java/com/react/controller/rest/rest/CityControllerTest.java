package com.react.controller.rest.rest;

import com.react.Application;
import com.react.controller.converter.CityConverter;
import com.react.controller.rest.CityController;
import com.react.data.entity.City;
import com.react.service.CityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CityControllerTest {

    private MockMvc mockMvc;

    @Mock
    CityService cityServiceMock;

    @Mock
    CityConverter cityConverterMock;

    @InjectMocks
    private CityController cityController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cityController).build();
    }

    @Test
    public void findAllStartsWithShouldReturn2CitiesStartingWithMan() throws Exception {
        //given
        List<City> citiesFromService =  new ArrayList<>();
        citiesFromService.add(new City.Builder("12345", "Manchester").build());
        citiesFromService.add(new City.Builder("123456", "Mansfield").build());

        List<com.react.controller.model.City> citiesAsModel = new ArrayList<>();
        citiesAsModel.add(new com.react.controller.model.City(citiesFromService.get(0).getId(), citiesFromService.get(0).getName()));
        citiesAsModel.add(new com.react.controller.model.City(citiesFromService.get(1).getId(), citiesFromService.get(1).getName()));

        String nameToFind = "Man";
        Boolean caseSensitive = true;

        //when
        when(cityServiceMock.findAllStartsWith(nameToFind, caseSensitive)).thenReturn(citiesFromService);
        when(cityConverterMock.ConvertEntitiesToModels(citiesFromService)).thenReturn(citiesAsModel);

        //then
        mockMvc.perform(get("/api/city/findAllStartsWith/" + nameToFind + "/" + caseSensitive))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name", is(citiesFromService.get(0).getName())))
                .andExpect(jsonPath("$[1].name", is(citiesFromService.get(1).getName())));

        verify(cityServiceMock, times(1)).findAllStartsWith(nameToFind, caseSensitive);
        verify(cityConverterMock, times(1)).ConvertEntitiesToModels(citiesFromService);
    }

    @Test
    public void findAllContainsShouldReturn3CitiesContainingCastle() throws Exception {
        //given
        List<City> citiesFromService =  new ArrayList<>();
        citiesFromService.add(new City.Builder("12345", "Newcastle Upon Tyne").build());
        citiesFromService.add(new City.Builder("123456", "Newcastle Under Lyme").build());
        citiesFromService.add(new City.Builder("1234567", "Newcastleton").build());

        List<com.react.controller.model.City> citiesAsModel = new ArrayList<>();
        citiesAsModel.add(new com.react.controller.model.City(citiesFromService.get(0).getId(), citiesFromService.get(0).getName()));
        citiesAsModel.add(new com.react.controller.model.City(citiesFromService.get(1).getId(), citiesFromService.get(1).getName()));
        citiesAsModel.add(new com.react.controller.model.City(citiesFromService.get(2).getId(), citiesFromService.get(2).getName()));

        String nameToFind = "castle";
        Boolean caseSensitive = false;

        //when
        when(cityServiceMock.findAllContains(nameToFind, caseSensitive)).thenReturn(citiesFromService);
        when(cityConverterMock.ConvertEntitiesToModels(citiesFromService)).thenReturn(citiesAsModel);

        //then
        mockMvc.perform(get("/api/city/findAllContains/" + nameToFind + "/" + caseSensitive))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name", is(citiesFromService.get(0).getName())))
                .andExpect(jsonPath("$[1].name", is(citiesFromService.get(1).getName())))
                .andExpect(jsonPath("$[2].name", is(citiesFromService.get(2).getName())));

        verify(cityServiceMock, times(1)).findAllContains(nameToFind, caseSensitive);
        verify(cityConverterMock, times(1)).ConvertEntitiesToModels(citiesFromService);
    }
}