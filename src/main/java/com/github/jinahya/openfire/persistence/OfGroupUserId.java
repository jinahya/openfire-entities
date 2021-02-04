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

    private static final long serialVersionUID = -5744381966254544327L;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "groupName=" + groupName
               + ",username=" + username
               + ",administrator=" + administrator
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final OfGroupUserId that = (OfGroupUserId) obj;
        return administrator == that.administrator
               && Objects.equals(groupName, that.groupName)
               && Objects.equals(username, that.username)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, username, administrator);
    }

    // ------------------------------------------------------------------------------------------------------- groupName
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    // -------------------------------------------------------------------------------------------------------- username
    public String getUsername() {
        return username;
    }

    void setUsername(final String username) {
        this.username = username;
    }

    // --------------------------------------------------------------------------------------------------- administrator
    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(final boolean administrator) {
        this.administrator = administrator;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String groupName;

    private String username;

    private boolean administrator;
}
