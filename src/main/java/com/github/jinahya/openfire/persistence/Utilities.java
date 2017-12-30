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

import java.time.format.DateTimeFormatter;
import java.util.Date;
import static java.util.Optional.ofNullable;

/**
 * A utility class.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
final class Utilities {

    // -------------------------------------------------------------------------
    /**
     * Returns a copy of given value.
     *
     * @param date the value to be copied
     * @return a copy of given value.
     */
    static Date copyOf(final Date date) {
        return ofNullable(date).map(v -> new Date(v.getTime())).orElse(null);
    }

    /**
     * Formats given date using {@link DateTimeFormatter#ISO_INSTANT}.
     *
     * @param date the value for format
     * @return formatted result; {@code null} if the {@code date} is null.
     */
    static String isozOf(final Date date) {
        return ofNullable(date)
                .map(Date::toInstant)
                .map(DateTimeFormatter.ISO_INSTANT::format)
                .orElse(null);
    }

    // -------------------------------------------------------------------------    
    /**
     * Creates a new instance.
     */
    private Utilities() {
        super();
    }
}
