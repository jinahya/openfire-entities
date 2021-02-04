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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static java.util.Optional.ofNullable;

/**
 * An attribute converter for converting {@code Date} attributes to/from {@code %015d}-formatted milliseconds character
 * columns.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Converter
public class ZonedLocalDateTime015AttributeConverter
        implements AttributeConverter<ZonedDateTime, String> {

    // -------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final ZonedDateTime attribute) {
        return ofNullable(attribute)
                .map()
        return ofNullable(attribute).map(converter::convertToDatabaseColumn).map(LocalDateTime::from).orElse(null);
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(final String dbData) {
        return ofNullable(dbData)
                .map(converter::convertToEntityAttribute)
                .map(d -> d.toInstant().atZone(ZoneId.systemDefault())Offset.UTC).atZone(ZoneId)LocalDateTime.from()::from).orElse(null);
    }

    private final Date015AttributeConverter converter = new Date015AttributeConverter();
}
