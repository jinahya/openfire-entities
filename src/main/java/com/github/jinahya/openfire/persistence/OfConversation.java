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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import static com.github.jinahya.openfire.persistence.Utilities.isozOf;

/**
 * The entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
public class OfConversation extends OfMapped {

    private static final long serialVersionUID = -8556282042062757153L;

    // -------------------------------------------------------------------------
    /**
     * The name of the target table. The value is {@value #TABLE_NAME}.
     */
    public static final String TABLE_NAME = "ofConversation";

    // -------------------------------------------------------------------------
    /**
     * The target column name to which {@value #ATTRIBUTE_NAME_CONVERSATION_ID}
     * attribute is bound.
     */
    public static final String COLUMN_NAME_CONVERSATION_ID = "conversationID";

    /**
     * The source attribute name from which
     * {@value #COLUMN_NAME_CONVERSATION_ID} column is bound.
     */
    public static final String ATTRIBUTE_NAME_CONVERSATION_ID
            = "conversationId";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ROOM = "room";

    public static final String ATTRIBUTE_NAME_ROOM = "room";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_IS_EXTERNAL = "isExternal";

    public static final String ATTRIBUTE_NAME_EXTERNAL = "external";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_START_DATE = "startDate";

    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_LAST_ACTIVITY = "lastActivity";

    public static final String ATTRIBUTE_NAME_LAST_ACTIVITY = "lastActivity";

    // -------------------------------------------------------------------------
    /**
     * The name of the target column to which
     * {@value #ATTRIBUTE_NAME_MESSAGE_COUNT} attribute is bound.
     */
    public static final String COLUMN_NAME_MESSAGE_COUNT = "messageCount";

    /**
     * The name of the source attribute from which
     * {@value #COLUMN_NAME_MESSAGE_COUNT} column is bound.
     */
    public static final String ATTRIBUTE_NAME_MESSAGE_COUNT = "messageCount";

    // -------------------------------------------------------------------------
    /**
     * Returns the value for {@value #ATTRIBUTE_NAME_ROOM} attribute from given
     * arguments. The result is
     * {@code <ofMucRoom.name>@<ofMucRoom.service.subdomain>.<xmppDomain>}.
     *
     * @param ofMucRoom the room
     * @param xmppDomain the XMPP domain
     * @return the value for {@value #ATTRIBUTE_NAME_ROOM} attribute.
     */
    public static String room(final OfMucRoom ofMucRoom,
                              final String xmppDomain) {
        if (ofMucRoom == null) {
            throw new NullPointerException("room is null");
        }
        if (ofMucRoom.getName() == null) {
            throw new IllegalArgumentException("room.name is null");
        }
        if (ofMucRoom.getService() == null) {
            throw new IllegalArgumentException("room.service is null");
        }
        if (ofMucRoom.getService().getSubdomain() == null) {
            throw new IllegalArgumentException(
                    "room.service.subdomain is null");
        }
        if (xmppDomain == null) {
            throw new NullPointerException("domain is null");
        }
        return ofMucRoom.getName()
               + "@" + ofMucRoom.getService().getSubdomain()
               + "." + xmppDomain;
    }

    // -------------------------------------------------------------------------
    /**
     * Creates a new instance.
     */
    public OfConversation() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "{"
               + "conversationId=" + conversationId
               + ",room=" + room
               + ",external=" + external
               + ",startDate=" + startDate
               + ",startDateIsoz=" + getStartDateIsoz()
               + ",lastActivity=" + lastActivity
               + ",lastActivityIsoz=" + getLastActivityIsoz()
               + ",messageCount=" + messageCount
               + "}";
    }

    // ---------------------------------------------------------- conversationId
    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(final Long conversationId) {
        this.conversationId = conversationId;
    }

    public OfConversation conversationId(final Long conversationId) {
        setConversationId(conversationId);
        return this;
    }

    // -------------------------------------------------------------------- room
    public String getRoom() {
        return room;
    }

    public void setRoom(final String room) {
        this.room = room;
    }

    public OfConversation room(final String room) {
        setRoom(room);
        return this;
    }

    // ---------------------------------------------------------------- external
    public boolean isExternal() {
        return external;
    }

    public void setExternal(final boolean external) {
        this.external = external;
    }

    public OfConversation external(final boolean external) {
        setExternal(external);
        return this;
    }

    // --------------------------------------------------------------- startDate
    public Date getStartDate() {
        return copyOf(startDate);
    }

    public void setStartDate(final Date startDate) {
        this.startDate = copyOf(startDate);
    }

    public OfConversation startDate(final Date startDate) {
        setStartDate(startDate);
        return this;
    }

    public String getStartDateIsoz() {
        return isozOf(getStartDate());
    }

    // ------------------------------------------------------------ lastActivity
    public Date getLastActivity() {
        return copyOf(lastActivity);
    }

    public void setLastActivity(final Date lastActivity) {
        this.lastActivity = copyOf(lastActivity);
    }

    public OfConversation lastActivity(final Date lastActivity) {
        setLastActivity(lastActivity);
        return this;
    }

    public String getLastActivityIsoz() {
        return isozOf(getLastActivity());
    }

    // ------------------------------------------------------------ messageCount
    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(final int messageCount) {
        this.messageCount = messageCount;
    }

    public OfConversation messageCount(final int messageCount) {
        setMessageCount(messageCount);
        return this;
    }

    // -------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_CONVERSATION_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CONVERSATION_ID)
    private Long conversationId;

    @Basic
    @Column(name = COLUMN_NAME_ROOM)
    @NamedAttribute(ATTRIBUTE_NAME_ROOM)
    private String room;

    @Column(name = COLUMN_NAME_IS_EXTERNAL, nullable = false)
    @Basic(optional = false)
    @NamedAttribute(ATTRIBUTE_NAME_EXTERNAL)
    private boolean external;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = DateMillisAttributeConverter.class)
    @Column(name = COLUMN_NAME_START_DATE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_START_DATE)
    private Date startDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = DateMillisAttributeConverter.class)
    @Column(name = COLUMN_NAME_LAST_ACTIVITY, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_LAST_ACTIVITY)
    private Date lastActivity;

    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MESSAGE_COUNT, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_MESSAGE_COUNT)
    private int messageCount;
}
