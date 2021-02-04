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

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@IdClass(OfMucRoomPropId.class)
public class OfMucRoomProp extends OfProp<OfMucRoomProp> {

    private static final long serialVersionUID = -6469468042118345539L;

    // -------------------------------------------------------------------------

    /**
     * The name of the target table to which this class binds. The value is {@value #TABLE_NAME}.
     */
    public static final String TABLE_NAME = "ofMucRoomProp";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ROOM_ID
            = OfMucRoom.COLUMN_NAME_ROOM_ID;

    public static final String ATTRIBUTE_NAME_ROOM = "room";

    // -------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public OfMucRoomProp() {
        super();
    }

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
        hash = 89 * hash + Objects.hashCode(room);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        final OfMucRoomProp other = (OfMucRoomProp) obj;
        if (!Objects.equals(room, other.room)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------- idInstance
    public OfMucRoomPropId getIdInstance() {
        return new OfMucRoomPropId().room(getRoomRoomId()).name(getName());
    }

    // -------------------------------------------------------------------- room
    public OfMucRoom getRoom() {
        return room;
    }

    public void setRoom(final OfMucRoom room) {
        this.room = room;
    }

    public OfMucRoomProp room(final OfMucRoom room) {
        setRoom(room);
        return this;
    }

    public Long getRoomRoomId() {
        return ofNullable(getRoom()).map(OfMucRoom::getRoomId).orElse(null);
    }

    // -------------------------------------------------------------------------
    //@NotNull
    @Id
    @ManyToOne()
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
                name = COLUMN_NAME_ROOM_ID,
                nullable = false,
                referencedColumnName = OfMucRoom.COLUMN_NAME_ROOM_ID,
                updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ROOM)
    private OfMucRoom room;
}
