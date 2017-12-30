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
import javax.json.bind.annotation.JsonbProperty;
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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@XmlRootElement
@Entity
@IdClass(OfGroupUserId.class)
public class OfGroupUser extends OfMapped {

    private static final long serialVersionUID = -4240253293616689097L;

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofGroupUser";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_GROUP_NAME
            = OfGroup.COLUMN_NAME_GROUP_NAME;

    public static final String ATTRIBUTE_NAME_GROUP = "group";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME
            = OfUser.COLUMN_NAME_USERNAME;

    public static final String ATTRIBUTE_NAME_USER = "user";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ADMINISTRATOR = "administrator";

    public static final String ATTRIBUTE_NAME_ADMINISTRATOR = "administrator";

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
                + "group=" + group
                + ",user=" + user
                + ",administrator=" + administrator
                + "}";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(group);
        hash = 17 * hash + Objects.hashCode(user);
        hash = 17 * hash + (administrator ? 1 : 0);
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
        final OfGroupUser other = (OfGroupUser) obj;
        if (administrator != other.administrator) {
            return false;
        }
        if (!Objects.equals(group, other.group)) {
            return false;
        }
        if (!Objects.equals(user, other.user)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------- idInstance
    @JsonbTransient
    @XmlTransient
    public OfGroupUserId getIdInstance() {
        return new OfGroupUserId()
                .group(getGroupGroupName())
                .user(getUserUsername())
                .administrator(isAdministrator());
    }

    // ------------------------------------------------------------------- group
    public OfGroup getGroup() {
        return group;
    }

    public void setGroup(final OfGroup group) {
        this.group = group;
    }

    public OfGroupUser group(final OfGroup group) {
        setGroup(group);
        return this;
    }

    @XmlAttribute
    public String getGroupGroupName() {
        return ofNullable(getGroup()).map(OfGroup::getGroupName).orElse(null);
    }

    // -------------------------------------------------------------------- user
    public OfUser getUser() {
        return user;
    }

    public void setUser(final OfUser user) {
        this.user = user;
    }

    public OfGroupUser user(final OfUser user) {
        setUser(user);
        return this;
    }

    @XmlAttribute
    public String getUserUsername() {
        return ofNullable(getUser()).map(OfUser::getUsername).orElse(null);
    }

    // ----------------------------------------------------------- administrator
    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(final boolean administrator) {
        this.administrator = administrator;
    }

    public OfGroupUser administrator(final boolean administrator) {
        setAdministrator(administrator);
        return this;
    }

    // -------------------------------------------------------------------------
    @JsonbTransient
    @XmlTransient
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            name = COLUMN_NAME_GROUP_NAME,
            nullable = false,
            referencedColumnName = OfGroup.COLUMN_NAME_GROUP_NAME,
            updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_GROUP)
    private OfGroup group;

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

    @JsonbProperty()
    @XmlElement(required = true)
    @Id
    @Column(name = COLUMN_NAME_ADMINISTRATOR, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ADMINISTRATOR)
    private boolean administrator;
}
