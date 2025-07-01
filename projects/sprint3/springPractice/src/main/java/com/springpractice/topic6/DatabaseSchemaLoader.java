package com.springpractice.topic6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class DatabaseSchemaLoader {

    private final Resource schema;

    public DatabaseSchemaLoader(@Value("classpath:schema.sql") Resource schema) {
        this.schema = schema;
    }

    public InputStream readSchema() throws IOException {
        return schema.getInputStream();
    }

}
