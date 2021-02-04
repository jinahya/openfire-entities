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
 * An id class for {@link OfOffline}.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfOfflineId implements Serializable {

    private static final long serialVersionUID = -7223070216575511876L;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "user=" + user
               + ",messageId=" + messageId
               + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(user);
        hash = 67 * hash + Objects.hashCode(messageId);
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
        final OfOfflineId other = (OfOfflineId) obj;
        if (!Objects.equals(user, other.user)) {
            return false;
        }
        if (!Objects.equals(messageId, other.messageId)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------- user
    public String getUser() {
        return user;
    }

    void setUser(final String user) {
        this.user = user;
    }

    OfOfflineId user(final String user) {
        setUser(user);
        return this;
    }

    // --------------------------------------------------------------- messageId
    public Long getMessageId() {
        return messageId;
    }

    void setMessageId(final Long messageId) {
        this.messageId = messageId;
    }

    OfOfflineId messageId(final Long messageId) {
        setMessageId(messageId);
        return this;
    }

    // -------------------------------------------------------------------------
    private String user;

    private Long messageId;
}
