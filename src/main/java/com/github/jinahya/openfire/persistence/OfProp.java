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

import java.util.Objects;
import static java.util.Optional.ofNullable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * An abstract class for {@code Prop} classes.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 * @param <T> subclass type parameter
 */
@MappedSuperclass
public abstract class OfProp<T extends OfProp<T>> extends OfMapped {

    private static final long serialVersionUID = -1750574068804750182L;

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_PROP_VALUE = "propValue";

    public static final String ATTRIBUTE_NAME_PROP_VALUE = "propValue";

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "name=" + name
               + ",propValue=" + propValue
               + "}";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(name);
        //hash = 89 * hash + Objects.hashCode(propValue);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OfProp<?> other = (OfProp<?>) obj;
        if (!Objects.equals(name, other.name)) {
            return false;
        }
//        if (!Objects.equals(propValue, other.propValue)) {
//            return false;
//        }
        return true;
    }

    // -------------------------------------------------------------------- name
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @SuppressWarnings("unchecked")
    public T name(final String name) {
        setName(name);
        return (T) this;
    }

    // --------------------------------------------------------------- propValue
    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(final String propValue) {
        this.propValue = propValue;
    }

    @SuppressWarnings("unchecked")
    public T propValue(final String propValue) {
        setPropValue(propValue);
        return (T) this;
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

    // -------------------------------------------------------------------------
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
