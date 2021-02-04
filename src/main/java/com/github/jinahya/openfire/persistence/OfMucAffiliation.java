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
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Optional.ofNullable;
import static java.util.logging.Logger.getLogger;

/**
 * An entity for {@link #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@IdClass(OfMucAffiliationId.class)
public class OfMucAffiliation extends OfMapped {

    private static final long serialVersionUID = -5426962290876256599L;

    // -------------------------------------------------------------------------
    private static final Logger logger
            = getLogger(lookup().lookupClass().getName());

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofMucAffiliation";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ROOM_ID
            = OfMucRoom.COLUMN_NAME_ROOM_ID;

    public static final String ATTRIBUTE_NAME_ROOM = "room";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_JID = "jid";

    public static final String ATTRIBUTE_NAME_JID = "jid";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_AFFILIATION = "affiliation";

    public static final String ATTRIBUTE_NAME_AFFILIATION = "affiliation";

    // -------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public OfMucAffiliation() {
        super();
    }

    // -------------------------------------------------------------- idInstance

    /**
     * Returns an id instance of this entity.
     *
     * @return an id instance.
     */
    public OfMucAffiliationId getIdInstance() {
        return new OfMucAffiliationId().room(getRoomRoomId()).jid(getJid());
    }

    // -------------------------------------------------------------------- room
    public OfMucRoom getRoom() {
        return room;
    }

    public void setRoom(final OfMucRoom room) {
        this.room = room;
    }

    public OfMucAffiliation room(final OfMucRoom room) {
        setRoom(room);
        return this;
    }

    /**
     * Returns the value of {@code room.roomId}.
     *
     * @return the value of {@code room.roomId}
     */
    public Long getRoomRoomId() {
        return ofNullable(getRoom()).map(OfMucRoom::getRoomId).orElse(null);
    }

    // --------------------------------------------------------------------- jid
    public String getJid() {
        return jid;
    }

    public void setJid(final String jid) {
        this.jid = jid;
    }

    public OfMucAffiliation jid(final String jid) {
        setJid(jid);
        return this;
    }

    // ------------------------------------------------------------- affiliation
    public int getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(final int affiliation) {
        this.affiliation = affiliation;
    }

    public OfMucAffiliation affiliation(final int affiliation) {
        setAffiliation(affiliation);
        return this;
    }

    // -------------------------------------------------------------------------
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
                name = COLUMN_NAME_ROOM_ID,
                nullable = false,
                referencedColumnName = OfMucRoom.COLUMN_NAME_ROOM_ID,
                updatable = false)
    private OfMucRoom room;

    @NotNull
    @Id
    @Column(name = COLUMN_NAME_JID, nullable = false)
    private String jid;

    @NotNull
    @Column(name = COLUMN_NAME_AFFILIATION, nullable = false)
    private int affiliation;
}
