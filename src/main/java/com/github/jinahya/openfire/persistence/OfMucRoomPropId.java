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

/**
 * An id class for {@link OfMucRoom}.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class OfMucRoomPropId extends OfPropId<OfMucRoomPropId> {

    private static final long serialVersionUID = -9119364363025882196L;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "room=" + room
               + '}';
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 83 * hash + Objects.hashCode(room);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final OfMucRoomPropId other = (OfMucRoomPropId) obj;
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

    OfMucRoomPropId room(final Long room) {
        setRoom(room);
        return this;
    }

    // -------------------------------------------------------------------------
    private Long room;
}
