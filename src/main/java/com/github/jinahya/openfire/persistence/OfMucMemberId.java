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
 * The id class for {@link OfMucMember}.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfMucMemberId implements Serializable {

    private static final long serialVersionUID = -2188386672709798585L;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + ",room=" + room
               + ",jid=" + jid
               + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(room);
        hash = 41 * hash + Objects.hashCode(jid);
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
        final OfMucMemberId other = (OfMucMemberId) obj;
        if (!Objects.equals(jid, other.jid)) {
            return false;
        }
        if (!Objects.equals(room, other.room)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------- room

    /**
     * Returns the value of {@code room} attribute.
     *
     * @return the value of {@code room} attribute.
     */
    public Long getRoom() {
        return room;
    }

    void setRoom(final Long room) {
        this.room = room;
    }

    OfMucMemberId room(final Long room) {
        setRoom(room);
        return this;
    }

    // --------------------------------------------------------------------- jid

    /**
     * Returns the value of {@code jid} attribute.
     *
     * @return the value of {@code jid} attribute.
     */
    public String getJid() {
        return jid;
    }

    void setJid(final String jid) {
        this.jid = jid;
    }

    OfMucMemberId jid(final String jid) {
        setJid(jid);
        return this;
    }

    // -------------------------------------------------------------------------
    private Long room;

    private String jid;
}
