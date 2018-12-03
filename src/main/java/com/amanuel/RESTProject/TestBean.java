package com.amanuel.RESTProject;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter(value = "beanFilter")
public class TestBean {

    private String fieldOne;

    private String fieldTwo;

    public TestBean(String fieldOne, String fieldTwo) {
        this.fieldOne = fieldOne;
        this.fieldTwo = fieldTwo;
    }

    public String getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
    }

    public String getFieldTwo() {
        return fieldTwo;
    }

    public void setFieldTwo(String fieldTwo) {
        this.fieldTwo = fieldTwo;
    }
}
