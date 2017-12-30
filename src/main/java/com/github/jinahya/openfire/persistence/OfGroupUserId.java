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

/**
 * The id class of {@link OfGroupUser}.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfGroupUserId implements Serializable {

    private static final long serialVersionUID = -6033961814304650850L;

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
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(group);
        hash = 67 * hash + Objects.hashCode(user);
        hash = 67 * hash + (administrator ? 1 : 0);
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
        final OfGroupUserId other = (OfGroupUserId) obj;
        if (!Objects.equals(group, other.group)) {
            return false;
        }
        if (!Objects.equals(user, other.user)) {
            return false;
        }
        if (administrator != other.administrator) {
            return false;
        }
        return true;
    }

    // ------------------------------------------------------------------- group
    public String getGroup() {
        return group;
    }

    void setGroup(final String group) {
        this.group = group;
    }

    OfGroupUserId group(final String group) {
        setGroup(group);
        return this;
    }

    // -------------------------------------------------------------------- user
    public String getUser() {
        return user;
    }

    void setUser(final String user) {
        this.user = user;
    }

    OfGroupUserId user(final String user) {
        setUser(user);
        return this;
    }

    // ----------------------------------------------------------- administrator
    public boolean isAdministrator() {
        return administrator;
    }

    void setAdministrator(final boolean administrator) {
        this.administrator = administrator;
    }

    OfGroupUserId administrator(final boolean administrator) {
        setAdministrator(administrator);
        return this;
    }

    // -------------------------------------------------------------------------
    private String group; // ofGroup.groupName

    private String user; // ofUser.username

    private boolean administrator;
}
