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
import static java.lang.invoke.MethodHandles.lookup;
import java.util.Objects;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 * An id class of {@link OfMucAffiliation}.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfMucAffiliationId implements Serializable {

    private static final long serialVersionUID = -9044618175609332381L;

    // -------------------------------------------------------------------------
    private static final Logger logger
            = getLogger(lookup().lookupClass().getName());

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "room=" + room
               + ",jid=" + jid
               + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(room);
        hash = 59 * hash + Objects.hashCode(jid);
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
        final OfMucAffiliationId other = (OfMucAffiliationId) obj;
        if (!Objects.equals(jid, other.jid)) {
            return false;
        }
        if (!Objects.equals(room, other.room)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------------- room
    public Long getRoom() {
        return room;
    }

    void setRoom(final Long room) {
        this.room = room;
    }

    OfMucAffiliationId room(final Long room) {
        setRoom(room);
        return this;
    }

    // --------------------------------------------------------------------- jid
    public String getJid() {
        return jid;
    }

    void setJid(final String jid) {
        this.jid = jid;
    }

    OfMucAffiliationId jid(final String jid) {
        setJid(jid);
        return this;
    }

    // -------------------------------------------------------------------------
    private Long room;

    private String jid;
}
