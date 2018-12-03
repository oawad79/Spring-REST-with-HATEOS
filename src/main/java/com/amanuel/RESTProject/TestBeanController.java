package com.amanuel.RESTProject;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/beans")
public class TestBeanController {

    @GetMapping
    public MappingJacksonValue getAllBeans() {

        // creating a TestBean object
        TestBean beanOne = new TestBean("valOne", "valTwo");

        // I don't want any field in my response from TestBean except fieldOne
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("fieldOne");

        // Filter is applied to TestBean object with beanFilter id --> @JsonFilter
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("beanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(beanOne);

        mapping.setFilters(filterProvider);

        return mapping;
    }



}
