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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 * @see <a href="https://download.igniterealtime.org/openfire/docs/latest/documentation/database-guide.html#ofGroupUser">ofGoupUser</a>
 */
@Entity
@IdClass(OfGroupUserId.class)
@Table(name = OfGroupUser.TABLE_NAME)
public class OfGroupUser extends OfMapped {

    private static final long serialVersionUID = -4240253293616689097L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofGroupUser";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_GROUP_NAME = OfGroup.COLUMN_NAME_GROUP_NAME;

    public static final String ATTRIBUTE_NAME_GROUP_NAME = "groupName";

    public static final String ATTRIBUTE_NAME_GROUP = "group";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME = OfUser.COLUMN_NAME_USERNAME;

    public static final String ATTRIBUTE_NAME_USERNAME = "username";

    public static final String ATTRIBUTE_NAME_USER = "user";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ADMINISTRATOR = "administrator";

    public static final String ATTRIBUTE_NAME_ADMINISTRATOR = "administrator";

    // -----------------------------------------------------------------------------------------------------------------
    public OfGroupUser() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "group=" + group
               + ",user=" + user
               + ",administrator=" + administrator
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final OfGroupUser that = (OfGroupUser) obj;
        return administrator == that.administrator
               && Objects.equals(group, that.group)
               && Objects.equals(user, that.user)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, user, administrator);
    }

    // ----------------------------------------------------------------------------------------------- groupName / group
    String getGroupName() {
        return groupName;
    }

    void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    public OfGroup getGroup() {
        return group;
    }

    public void setGroup(final OfGroup group) {
        this.group = group;
        this.setGroupName(ofNullable(this.group).map(OfGroup::getGroupName).orElse(null));
    }

    // ------------------------------------------------------------------------------------------------- username / user
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public OfUser getUser() {
        return user;
    }

    public void setUser(final OfUser user) {
        this.user = user;
        setUsername(ofNullable(this.user).map(OfUser::getUsername).orElse(null));
    }

    // --------------------------------------------------------------------------------------------------- administrator
    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(final boolean administrator) {
        this.administrator = administrator;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @NotNull
    @JoinColumn(name = COLUMN_NAME_GROUP_NAME, nullable = false, insertable = true, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_GROUP_NAME)
    private String groupName;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_GROUP_NAME, nullable = false, insertable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_GROUP)
    private OfGroup group;

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @NotNull
    @JoinColumn(name = COLUMN_NAME_USERNAME, nullable = false, insertable = true, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_USERNAME)
    private String username;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_USERNAME, nullable = false, insertable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_USER)
    private OfUser user;

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_ADMINISTRATOR, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ADMINISTRATOR)
    private boolean administrator;
}
