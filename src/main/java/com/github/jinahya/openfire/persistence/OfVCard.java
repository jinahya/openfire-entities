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

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
public class OfVCard implements Serializable {

    private static final long serialVersionUID = -4105766200827756560L;

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofUserFlag";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME
            = OfUser.COLUMN_NAME_USERNAME;

    public static final String COLUMN_NAME_VCARD = "vcard";

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfVCard() {
        super();
    }

    // ------------------------------------------------------------------ ofUser
    public OfUser getOfUser() {
        return ofUser;
    }

    public void setOfUser(final OfUser ofUser) {
        this.ofUser = ofUser;
    }

    public OfVCard ofUser(final OfUser ofUser) {
        setOfUser(ofUser);
        return this;
    }

    // ------------------------------------------------------------------- vcard
    public String getVcard() {
        return vcard;
    }

    public void setVcard(final String vcard) {
        this.vcard = vcard;
    }

    public OfVCard vcard(final String vcard) {
        setVcard(vcard);
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
    private OfUser ofUser;

    @XmlElement(required = true)
    @NotNull
    @Column(name = COLUMN_NAME_VCARD, nullable = false)
    private String vcard;
}
