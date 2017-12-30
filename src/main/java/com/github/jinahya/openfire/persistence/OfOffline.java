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

import static com.github.jinahya.openfire.persistence.Utilities.copyOf;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import static java.util.Optional.ofNullable;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@IdClass(OfOfflineId.class)
@Entity
public class OfOffline implements Serializable {

    private static final long serialVersionUID = -7158107658001768204L;

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofOffline";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME
            = OfUser.COLUMN_NAME_USERNAME;

    public static final String ATRRIBUTE_NAME_USER = "user";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_MESSAGE_ID = "messageID";

    public static final String ATTRIBUTE_NAME_MESSAGE_ID = "messageId";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_CREATION_DATE = "creationDate";

    public static final String ATTRIBUTE_NAME_CREATION_DATE = "creationDate";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_MESSAGE_SIZE = "messageSize";

    public static final String ATTRIBUTE_NAME_MESSAGE_SIZE = "messageSize";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_STANZA = "stanza";

    public static final String ATTRIBUTE_NAME_STANZA = "stanza";

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfOffline() {
        super();
    }

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "user=" + user
               + ",messageId=" + messageId
               + ",creationDate=" + creationDate
               + ",messageSize=" + messageSize
               + ",stanza=" + stanza
               + "}";
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
        final OfOffline other = (OfOffline) obj;
        if (!Objects.equals(user, other.user)) {
            return false;
        }
        if (!Objects.equals(messageId, other.messageId)) {
            return false;
        }
        return true;
    }

    // -------------------------------------------------------------- idInstance
    public OfOfflineId getIdIsnstance() {
        return new OfOfflineId()
                .user(getUserUsername())
                .messageId(getMessageId());
    }

    // -------------------------------------------------------------------- user
    public OfUser getUser() {
        return user;
    }

    public void setUser(final OfUser user) {
        this.user = user;
    }

    public OfOffline user(final OfUser user) {
        setUser(user);
        return this;
    }

    public String getUserUsername() {
        return ofNullable(getUser()).map(OfUser::getUsername).orElse(null);
    }

    // --------------------------------------------------------------- messageId
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    // ------------------------------------------------------------ creationDate
    public Date getCreationDate() {
        return copyOf(creationDate);
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = copyOf(creationDate);
    }

    public OfOffline creationDate(final Date creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    // ------------------------------------------------------------- messageSize
    public int getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
    }

    public OfOffline messageSize(final int messageSize) {
        setMessageSize(messageSize);
        return this;
    }

    // ------------------------------------------------------------------ stanza
    public String getStanza() {
        return stanza;
    }

    public void setStanza(final String stanza) {
        this.stanza = stanza;
    }

    public OfOffline stanza(final String stanza) {
        setStanza(stanza);
        return this;
    }

    // -------------------------------------------------------------------------
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
                name = COLUMN_NAME_USERNAME,
                nullable = false,
                referencedColumnName = OfUser.COLUMN_NAME_USERNAME,
                updatable = false)
    private OfUser user;

    @NotNull
    @Id
    @Column(name = COLUMN_NAME_MESSAGE_ID, nullable = false, updatable = false)
    private Long messageId;

    @NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = Date015AttributeConverter.class)
    @Column(name = COLUMN_NAME_CREATION_DATE, nullable = false)
    private Date creationDate;

    @Column(name = COLUMN_NAME_MESSAGE_SIZE, nullable = false)
    private int messageSize;

    @NotNull
    @Column(name = COLUMN_NAME_STANZA, nullable = false)
    private String stanza;
}
