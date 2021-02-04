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
import java.util.Objects;
import java.util.logging.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Optional.ofNullable;
import static java.util.logging.Logger.getLogger;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@IdClass(OfMucMemberId.class)
@Entity
public class OfMucMember extends OfMapped {

    private static final long serialVersionUID = -526903256883348399L;

    // -------------------------------------------------------------------------
    private static final Logger logger
            = getLogger(lookup().lookupClass().getName());

    // -------------------------------------------------------------------------

    /**
     * The name of the target table of this entity. The value is {@value #TABLE_NAME}.
     */
    public static final String TABLE_NAME = "ofMucMember";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ROOM_ID
            = OfMucRoom.COLUMN_NAME_ROOM_ID;

    public static final String ATTRIBUTE_NAME_ROOM = "room";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_JID = "jid";

    public static final String ATTRIBUTE_NAME_JID = "jid";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_NICKNAME = "nickname";

    public static final String ATTRIBUTE_NAME_NICKNAME = "nickname";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_FIRST_NAME = "firstName";

    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_LAST_NAME = "lastName";

    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_URL = "url";

    public static final String ATTRIBUTE_NAME_URL = "url";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_EMAIL = "email";

    public static final String ATTRIBUTE_NAME_EMAIL = "email";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_FAQENTRY = "faqentry";

    public static final String ATTRIBUTE_NAME_FAQENTRY = "faqentry";

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "room=" + room
               + ",jid=" + jid
               + ",nickname=" + nickname
               + ",firstName=" + firstName
               + ",lastName=" + lastName
               + ",url=" + url
               + ",email=" + email
               + ",faqentry=" + faqentry
               + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(room);
        hash = 29 * hash + Objects.hashCode(jid);
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
        final OfMucMember other = (OfMucMember) obj;
        if (!Objects.equals(jid, other.jid)) {
            return false;
        }
        if (!Objects.equals(room, other.room)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------- idInstance
    public OfMucMemberId getIdInstance() {
        return new OfMucMemberId().room(getRoomRoomId()).jid(getJid());
    }

    // -------------------------------------------------------------------- room
    public OfMucRoom getRoom() {
        return room;
    }

    public void setRoom(final OfMucRoom room) {
        this.room = room;
    }

    public OfMucMember room(final OfMucRoom room) {
        setRoom(room);
        return this;
    }

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

    public OfMucMember jid(final String jid) {
        setJid(jid);
        return this;
    }

    // ---------------------------------------------------------------- nickname
    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public OfMucMember nickname(final String nickname) {
        setNickname(nickname);
        return this;
    }

    // --------------------------------------------------------------- firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public OfMucMember firstName(final String firstName) {
        setFirstName(firstName);
        return this;
    }

    // ---------------------------------------------------------------- lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public OfMucMember lastName(final String lastName) {
        setLastName(lastName);
        return this;
    }

    // --------------------------------------------------------------------- url
    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public OfMucMember url(final String url) {
        setUrl(url);
        return this;
    }

    // ------------------------------------------------------------------- email
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public OfMucMember email(final String email) {
        setEmail(email);
        return this;
    }

    // ---------------------------------------------------------------- faqentry
    public String getFaqentry() {
        return faqentry;
    }

    public void setFaqentry(final String faqentry) {
        this.faqentry = faqentry;
    }

    public OfMucMember faqentry(final String faqentry) {
        setFaqentry(faqentry);
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
    @NamedAttribute(ATTRIBUTE_NAME_ROOM)
    private OfMucRoom room;

    @NotNull
    @Id
    @Column(name = COLUMN_NAME_JID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_JID)
    private String jid;

    @Column(name = COLUMN_NAME_NICKNAME)
    @NamedAttribute(ATTRIBUTE_NAME_NICKNAME)
    private String nickname;

    @Column(name = COLUMN_NAME_FIRST_NAME)
    @NamedAttribute(ATTRIBUTE_NAME_FIRST_NAME)
    private String firstName;

    @Column(name = COLUMN_NAME_LAST_NAME)
    @NamedAttribute(ATTRIBUTE_NAME_LAST_NAME)
    private String lastName;

    @Column(name = COLUMN_NAME_URL)
    @NamedAttribute(ATTRIBUTE_NAME_URL)
    private String url;

    @Column(name = COLUMN_NAME_EMAIL)
    @NamedAttribute(ATTRIBUTE_NAME_EMAIL)
    private String email;

    @Column(name = COLUMN_NAME_FAQENTRY)
    @NamedAttribute(ATTRIBUTE_NAME_FAQENTRY)
    private String faqentry;
}
