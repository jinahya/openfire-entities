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
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(OfPrivacyListId.class)
@Entity
public class OfPrivacyList implements Serializable {

    private static final long serialVersionUID = -1769575697381121094L;

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofPrivacyList";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME
            = OfUser.COLUMN_NAME_USERNAME;

    public static final String ATTRIBUTE_NAME_USER = "user";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_IS_DEFAULT = "isDefault";

    public static final String ATTRIBUTE_NAME_DEFAULT = "default__";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_LIST = "list";

    public static final String ATTRIBUTE_NAME_LIST = "list";

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfPrivacyList() {
        super();
    }

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "user=" + user
               + ",name=" + name
               + ",default=" + default__
               + ",list=" + list
               + "}";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(user);
        hash = 29 * hash + Objects.hashCode(name);
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
        final OfPrivacyList other = (OfPrivacyList) obj;
        if (!Objects.equals(name, other.name)) {
            return false;
        }
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

    public OfPrivacyList user(final OfUser user) {
        setUser(user);
        return this;
    }

    // -------------------------------------------------------------------- name
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public OfPrivacyList name(final String name) {
        setName(name);
        return this;
    }

    // ----------------------------------------------------------------- default
    public boolean isDefault() {
        return default__;
    }

    public void setDefault(final boolean default__) {
        this.default__ = default__;
    }

    public OfPrivacyList default__(final boolean default__) {
        setDefault(default__);
        return this;
    }

    // -------------------------------------------------------------------- list
    public String getList() {
        return list;
    }

    public void setList(final String list) {
        this.list = list;
    }

    public OfPrivacyList list(final String list) {
        setList(list);
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
    private OfUser user;

    @XmlElement(required = true)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_NAME)
    private String name;

    @XmlElement(required = true)
    @Column(name = COLUMN_NAME_IS_DEFAULT, nullable = false)
    private boolean default__;

    @XmlElement(required = true)
    @NotNull
    @Column(name = COLUMN_NAME_LIST, nullable = false)
    private String list;
}
