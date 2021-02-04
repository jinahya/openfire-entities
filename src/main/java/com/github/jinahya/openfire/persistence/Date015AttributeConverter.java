/*
 * Copyright 2017 Jin Kwon &lt;onacit at gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.openfire.persistence;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

import static java.util.Optional.ofNullable;

/**
 * An attribute converter for converting {@code Date} attributes to/from {@code %015d}-formatted milliseconds character
 * columns.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Converter
public class Date015AttributeConverter implements AttributeConverter<Date, String> {

    @Override
    public String convertToDatabaseColumn(final Date attribute) {
        return ofNullable(attribute)
                .map(Date::getTime)
                .map(converter::convertToDatabaseColumn)
                .orElse(null);
    }

    @Override
    public Date convertToEntityAttribute(final String dbData) {
        return ofNullable(dbData)
                .map(converter::convertToEntityAttribute)
                .map(Date::new)
                .orElse(null);
    }

    private final Long015AttributeConverter converter = new Long015AttributeConverter();
}
