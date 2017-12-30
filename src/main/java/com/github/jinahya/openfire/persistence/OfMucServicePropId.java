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

/**
 * An id class for {@link OfMucServiceProp}.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfMucServicePropId extends OfPropId<OfMucServicePropId> {

    private static final long serialVersionUID = 403478860013540971L;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "service=" + service
               + "}";
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 83 * hash + Objects.hashCode(service);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final OfMucServicePropId other = (OfMucServicePropId) obj;
        if (!Objects.equals(service, other.service)) {
            return false;
        }
        return true;
    }

    // ----------------------------------------------------------------- service
    public Long getService() {
        return service;
    }

    void setService(final Long service) {
        this.service = service;
    }

    OfMucServicePropId service(final Long service) {
        setService(service);
        return this;
    }

    // -------------------------------------------------------------------------
    private Long service;
}
