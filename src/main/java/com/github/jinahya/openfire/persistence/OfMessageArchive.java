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
import static com.github.jinahya.openfire.persistence.Utilities.isozOf;
import java.util.Date;
import static java.util.Optional.ofNullable;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@XmlRootElement
@Entity
public class OfMessageArchive extends OfMapped {

    private static final long serialVersionUID = 3127709560770704106L;

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofMessageArchive";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_MESSAGE_ID = "messageID";

    public static final String ATTRIBUTE_NAME_MESSAGE_ID = "messageId";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_CONVERSATION_ID = "conversationID";

    public static final String ATTRIBUTE_NAME_CONVERSATION_ID
            = "conversationId";

    public static final String ATTRIBUTE_NAME_CONVERSATION
            = "conversation";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_FROM_JID = "fromJID";

    public static final String ATTRIBUTE_NAME_FROM_JID = "fromJid";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_FROM_JID_RESOURCE
            = COLUMN_NAME_FROM_JID + "Resource";

    public static final String ATTRIBUTE_NAME_FROM_JID_RESOURCE
            = ATTRIBUTE_NAME_FROM_JID + "Resource";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_TO_JID = "toJID";

    public static final String ATTRIBUTE_NAME_TO_JID = "toJid";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_TO_JID_RESOURCE
            = COLUMN_NAME_TO_JID + "Resource";

    public static final String ATTRIBUTE_NAME_TO_JID_RESOURCE
            = ATTRIBUTE_NAME_TO_JID + "Resource";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_SENT_DATE = "sentDate";

    public static final String ATTRIBUTE_NAME_SENT_DATE = "sentDate";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_STANZA = "stanza";

    public static final String ATTRIBUTE_NAME_STANZA = "stanza";

    // -------------------------------------------------------------------------
    /**
     * The name of the column to which {@value #ATTRIBUTE_NAME_BODY} attribute
     * is bound.
     */
    public static final String COLUMN_NAME_BODY = "body";

    /**
     * The name of the attribute from which {@value #COLUMN_NAME_BODY} column is
     * bound.
     */
    public static final String ATTRIBUTE_NAME_BODY = "body";

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "messageId=" + messageId
               + ",conversationId=" + getConversationConversationId()
               + ",fromJid=" + fromJid
               + ",fromJidResource=" + fromJidResource
               + ",toJid=" + toJid
               + ",toJidResource=" + toJidResource
               + ",sentDate=" + sentDate
               + ",sentDateIsoz=" + getSentDateIsoz()
               + ",stanza=" + stanza
               + ",body=" + body
               + "}";
    }

    // -------------------------------------------------------------------------
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(final Long messageId) {
        this.messageId = messageId;
    }

    public OfMessageArchive messageId(final Long messageId) {
        setMessageId(messageId);
        return this;
    }

    // ------------------------------------------------------------ conversation
    public OfConversation getConversation() {
        return conversation;
    }

    public void setConversation(final OfConversation conversation) {
        this.conversation = conversation;
    }

    public OfMessageArchive conversation(final OfConversation conversation) {
        setConversation(conversation);
        return this;
    }

    @JsonbProperty
    @XmlAttribute
    public Long getConversationConversationId() {
        return ofNullable(getConversation())
                .map(OfConversation::getConversationId)
                .orElse(null);
    }

    // ----------------------------------------------------------------- fromJid
    public String getFromJid() {
        return fromJid;
    }

    public void setFromJid(final String fromJid) {
        this.fromJid = fromJid;
    }

    public OfMessageArchive fromJid(final String fromJid) {
        setFromJid(fromJid);
        return this;
    }

    // --------------------------------------------------------- fromJidResource
    public String getFromJidResource() {
        return fromJidResource;
    }

    public void setFromJidResource(final String fromJidResource) {
        this.fromJidResource = fromJidResource;
    }

    public OfMessageArchive fromJidResource(final String fromJidResource) {
        setFromJidResource(fromJidResource);
        return this;
    }

    // ------------------------------------------------------------------- toJid
    public String getToJid() {
        return toJid;
    }

    public void setToJid(final String toJid) {
        this.toJid = toJid;
    }

    public OfMessageArchive toJid(final String toJid) {
        setToJid(toJid);
        return this;
    }

    // ----------------------------------------------------------- toJidResource
    public String getToJidResource() {
        return toJidResource;
    }

    public void setToJidResource(final String toJidResource) {
        this.toJidResource = toJidResource;
    }

    public OfMessageArchive toJidResource(final String toJidResource) {
        setToJidResource(toJidResource);
        return this;
    }

    // ---------------------------------------------------------------- sentDate
    public Date getSentDate() {
        return copyOf(sentDate);
    }

    public void setSentDate(final Date sentDate) {
        this.sentDate = copyOf(sentDate);
    }

    public OfMessageArchive sentDate(final Date sentDate) {
        setSentDate(sentDate);
        return this;
    }

    @JsonbProperty
    @XmlAttribute
    public String getSentDateIsoz() {
        return isozOf(getSentDate());
    }

    // ------------------------------------------------------------------ stanza
    public String getStanza() {
        return stanza;
    }

    public void setStanza(final String stanza) {
        this.stanza = stanza;
    }

    public OfMessageArchive stanza(final String stanza) {
        setStanza(stanza);
        return this;
    }

    // -------------------------------------------------------------------- body
    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public OfMessageArchive body(final String body) {
        setBody(body);
        return this;
    }

    // -------------------------------------------------------------------------
    @JsonbProperty()
    @XmlElement()
    // @NotNull // column is nullable
    @Id // not specified!!!
    @Column(name = COLUMN_NAME_MESSAGE_ID)
    @NamedAttribute(ATTRIBUTE_NAME_MESSAGE_ID)
    private Long messageId;

    @JsonbTransient
    @XmlTransient
    //@NotNull
    @ManyToOne()
    @JoinColumn(
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            name = COLUMN_NAME_CONVERSATION_ID,
            nullable = false,
            referencedColumnName = OfConversation.COLUMN_NAME_CONVERSATION_ID)
    @NamedAttribute(ATTRIBUTE_NAME_CONVERSATION)
    private OfConversation conversation;

    @JsonbProperty
    @XmlElement
    @NotNull
    @Column(name = COLUMN_NAME_FROM_JID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_FROM_JID)
    private String fromJid;

    @JsonbProperty(nillable = true)
    @XmlElement(nillable = true)
    @Column(name = COLUMN_NAME_FROM_JID_RESOURCE)
    @NamedAttribute(ATTRIBUTE_NAME_FROM_JID_RESOURCE)
    private String fromJidResource;

    @JsonbProperty
    @XmlElement
    @NotNull
    @Column(name = COLUMN_NAME_TO_JID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_TO_JID)
    private String toJid;

    @JsonbProperty(nillable = true)
    @XmlElement(nillable = true)
    @Column(name = COLUMN_NAME_TO_JID_RESOURCE)
    @NamedAttribute(ATTRIBUTE_NAME_TO_JID_RESOURCE)
    private String toJidResource;

    @JsonbProperty
    @XmlElement(required = true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = DateMillisAttributeConverter.class)
    @Column(name = COLUMN_NAME_SENT_DATE)
    @NamedAttribute(ATTRIBUTE_NAME_SENT_DATE)
    private Date sentDate;

    @JsonbProperty(nillable = true)
    @XmlElement(nillable = true)
    @Lob
    @Basic
    @Column(name = COLUMN_NAME_STANZA)
    @NamedAttribute(ATTRIBUTE_NAME_STANZA)
    private String stanza;

    @JsonbProperty(nillable = true)
    @XmlElement(nillable = true)
    @Lob
    @Basic
    @Column(name = COLUMN_NAME_BODY)
    @NamedAttribute(ATTRIBUTE_NAME_BODY)
    private String body;
}
