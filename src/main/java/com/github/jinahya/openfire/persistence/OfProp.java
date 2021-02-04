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

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * An abstract class for {@code ...Prop} classes.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@MappedSuperclass
public abstract class OfProp extends OfMapped {

    private static final long serialVersionUID = -1750574068804750182L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String ATTRIBUTE_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_PROP_VALUE = "propValue";

    public static final String ATTRIBUTE_NAME_PROP_VALUE = "propValue";

    // -----------------------------------------------------------------------------------------------------------------
    protected OfProp() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "name=" + name
               + ",propValue=" + propValue
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final OfProp<?> ofProp = (OfProp<?>) obj;
        return Objects.equals(name, ofProp.name)
               && Objects.equals(propValue, ofProp.propValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, propValue);
    }

    // ------------------------------------------------------------------------------------------------------------ name
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    // ------------------------------------------------------------------------------------------------------- propValue
    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(final String propValue) {
        this.propValue = propValue;
    }

    public Boolean getPropValueAsBoolean() {
        return ofNullable(getPropValue()).map(Boolean::valueOf).orElse(null);
    }

    public Integer getPropValueAsInteger() {
        return ofNullable(getPropValue()).map(Integer::valueOf).orElse(null);
    }

    public Long getPropValueAsLong() {
        return ofNullable(getPropValue()).map(Long::valueOf).orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_NAME, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_NAME)
    private String name;

    @NotNull
    @Column(name = COLUMN_NAME_PROP_VALUE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_PROP_VALUE)
    private String propValue;
}
