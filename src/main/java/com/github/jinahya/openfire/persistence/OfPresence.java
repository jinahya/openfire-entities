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

import static com.github.jinahya.openfire.persistence.Utilities.copyOf;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import static java.util.Optional.ofNullable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
public class OfPresence implements Serializable {

    private static final long serialVersionUID = 5643584503751740542L;

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofPresence";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME
            = OfUser.COLUMN_NAME_USERNAME;

    public static final String ATTRIBUTE_NAME_USER = "user";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_OFFLINE_PRESENCE = "offlinePresence";

    public static final String ATTRIBUTE_NAME_OFFLINE_PRESENCE
            = "offlinePresence";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_OFFLINE_DATE = "offlineDate";

    public static final String ATTRIBUTE_NAME_OFFLINE_DATE = "offlineDate";

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfPresence() {
        super();
    }

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "user=" + user
               + ",offlinePresence=" + offlinePresence
               + ",offlineDate=" + offlineDate
               + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(user);
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
        final OfPresence other = (OfPresence) obj;
        if (!Objects.equals(user, other.user)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------- user
    public OfUser getUser() {
        return user;
    }

    public void setUser(final OfUser user) {
        this.user = user;
    }

    public OfPresence user(final OfUser user) {
        setUser(user);
        return this;
    }

    @XmlAttribute
    public String getUserUsername() {
        return ofNullable(getUser()).map(OfUser::getUsername).orElse(null);
    }

    // --------------------------------------------------------- offlinePresence
    public String getOfflinePresence() {
        return offlinePresence;
    }

    public void setOfflinePresence(final String offlinePresence) {
        this.offlinePresence = offlinePresence;
    }

    public OfPresence name(final String offlinePresence) {
        setOfflinePresence(offlinePresence);
        return this;
    }

    // ------------------------------------------------------------- offlineDate
    public Date getOfflineDate() {
        return copyOf(offlineDate);
    }

    public void setOfflineDate(final Date offlineDate) {
        this.offlineDate = copyOf(offlineDate);
    }

    public OfPresence offlineDate(final Date offlineDate) {
        setOfflineDate(offlineDate);
        return this;
    }

    // -------------------------------------------------------------------------
    @JsonbTransient
    @XmlTransient
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
                name = COLUMN_NAME_USERNAME,
                nullable = false,
                referencedColumnName = OfUser.COLUMN_NAME_USERNAME,
                updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_USER)
    private OfUser user;

    @XmlElement(nillable = true)
    @Column(name = COLUMN_NAME_OFFLINE_PRESENCE)
    @NamedAttribute(ATTRIBUTE_NAME_OFFLINE_PRESENCE)
    private String offlinePresence;

    @XmlElement(required = true)
    @NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = Date015AttributeConverter.class)
    @Column(name = COLUMN_NAME_OFFLINE_DATE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_OFFLINE_DATE)
    private Date offlineDate;
}
