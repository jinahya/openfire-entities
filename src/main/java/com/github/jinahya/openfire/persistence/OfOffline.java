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
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@IdClass(OfOfflineId.class)
@Table(name = OfOffline.TABLE_NAME)
public class OfOffline extends OfMapped {

    private static final long serialVersionUID = -7158107658001768204L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofOffline";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME = OfUser.COLUMN_NAME_USERNAME;

    public static final String ATTRIBUTE_NAME_USERNAME = "username";

    public static final String ATTRIBUTE_NAME_USER = "user";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_MESSAGE_ID = "messageID";

    public static final String ATTRIBUTE_NAME_MESSAGE_ID = "messageId";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CREATION_DATE = "creationDate";

    public static final String ATTRIBUTE_NAME_CREATION_DATE = "creationDate";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_MESSAGE_SIZE = "messageSize";

    public static final String ATTRIBUTE_NAME_MESSAGE_SIZE = "messageSize";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_STANZA = "stanza";

    public static final String ATTRIBUTE_NAME_STANZA = "stanza";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public OfOffline() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + '{'
               + "user=" + user
               + ",messageId=" + messageId
               + ",creationDate=" + creationDate
               + ",messageSize=" + messageSize
               + ",stanza=" + stanza
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final OfOffline that = (OfOffline) obj;
        return messageSize == that.messageSize
               && Objects.equals(user, that.user)
               && Objects.equals(messageId, that.messageId)
               && Objects.equals(creationDate, that.creationDate)
               && Objects.equals(stanza, that.stanza)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, messageId, creationDate, messageSize, stanza);
    }

    // ------------------------------------------------------------------------------------------------- username / user
    String getUsername() {
        return username;
    }

    void setUsername(final String username) {
        this.username = username;
    }

    public OfUser getUser() {
        return user;
    }

    public void setUser(final OfUser user) {
        this.user = user;
        setUsername(ofNullable(this.user).map(OfUser::getUsername).orElse(null));
    }

    // ------------------------------------------------------------------------------------------------------- messageId
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(final Long messageId) {
        this.messageId = messageId;
    }

    // ---------------------------------------------------------------------------------------------------- creationDate
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    // ----------------------------------------------------------------------------------------------------- messageSize
    public int getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(final int messageSize) {
        this.messageSize = messageSize;
    }

    // ---------------------------------------------------------------------------------------------------------- stanza
    public String getStanza() {
        return stanza;
    }

    public void setStanza(final String stanza) {
        this.stanza = stanza;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_USERNAME, nullable = false, insertable = true, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_USERNAME)
    private String username;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_USERNAME, nullable = false, insertable = false, updatable = false)
    private OfUser user;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_MESSAGE_ID, nullable = false, insertable = true, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_MESSAGE_ID)
    private Long messageId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = LocalDateTime015AttributeConverter.class)
    @Column(name = COLUMN_NAME_CREATION_DATE, nullable = false)
    private LocalDateTime creationDate;

    @PositiveOrZero
    @Column(name = COLUMN_NAME_MESSAGE_SIZE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_MESSAGE_SIZE)
    private int messageSize;

    @NotNull
    @Column(name = COLUMN_NAME_STANZA, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_STANZA)
    private String stanza;
}
