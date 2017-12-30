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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * An entity for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
public class OfMucService extends OfMapped {

    private static final long serialVersionUID = -3116060010690646853L;

    // -------------------------------------------------------------------------
    /**
     * The name of the target table of this entity. The value is
     * {@value #TABLE_NAME}.
     */
    public static final String TABLE_NAME = "ofMucService";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_SERVICE_ID = "serviceID";

    public static final String ATTRIBUTE_NAME_SERVICE_ID = "serviceId";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_SUBDOMAIN = "subdomain";

    public static final String ATTRIBUTE_NAME_SUBDOMAIN = "subdomain";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_DESCRIPTION = "description";

    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_IS_HIDDEN = "isHidden";

    public static final String ATTRIBUTE_NAME_HIDDEN = "hidden";

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "serviceId=" + serviceId
               + ",subdomain=" + subdomain
               + ",description=" + description
               + ",hidden=" + hidden
               + "}";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(subdomain);
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
        final OfMucService other = (OfMucService) obj;
        if (!Objects.equals(subdomain, other.subdomain)) {
            return false;
        }
        return true;
    }

    // --------------------------------------------------------------- serviceId
    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(final Long serviceId) {
        this.serviceId = serviceId;
    }

    public OfMucService serviceId(final Long serviceId) {
        setServiceId(serviceId);
        return this;
    }

    // --------------------------------------------------------------- subdomain
    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(final String subdomain) {
        this.subdomain = subdomain;
    }

    public OfMucService subdomain(final String subdomain) {
        setSubdomain(subdomain);
        return this;
    }

    // ------------------------------------------------------------- description
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public OfMucService description(final String description) {
        setDescription(description);
        return this;
    }

    // ------------------------------------------------------------------ hidden
    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }

    public OfMucService hidden(final boolean hidden) {
        setHidden(hidden);
        return this;
    }

    // -------------------------------------------------------------------------
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SERVICE_ID, nullable = false, unique = true)
    @NamedAttribute(ATTRIBUTE_NAME_SERVICE_ID)
    private Long serviceId;

    @NotNull
    //@Id
    @Column(name = COLUMN_NAME_SUBDOMAIN, nullable = false, unique = true)
    @NamedAttribute(ATTRIBUTE_NAME_SUBDOMAIN)
    private String subdomain;

    @Column(name = COLUMN_NAME_DESCRIPTION)
    @NamedAttribute(ATTRIBUTE_NAME_DESCRIPTION)
    private String description;

    @Column(name = COLUMN_NAME_IS_HIDDEN, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_HIDDEN)
    private boolean hidden;
}
