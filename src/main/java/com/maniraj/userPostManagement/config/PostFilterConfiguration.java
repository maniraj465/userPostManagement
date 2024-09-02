package com.maniraj.userPostManagement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

//This Configuration class is for static filter for Post bean
//@Configuration
public class PostFilterConfiguration {
    public PostFilterConfiguration (ObjectMapper objectMapper) {
        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(true);
        simpleFilterProvider.addFilter("PostBeanPostIdFilter", SimpleBeanPropertyFilter
                .filterOutAllExcept("userId", "postContent", "postImage", "hashTags", "likedUsers", "comments"));
        objectMapper.setFilterProvider(simpleFilterProvider);
    }
}
