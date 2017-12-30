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
 * Entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@IdClass(OfMucRoomId.class)
public class OfMucRoom extends OfMapped {

    private static final long serialVersionUID = 7178837689865627613L;

    // -------------------------------------------------------------------------
    public static final String TABLE_NAME = "ofMucRoom";

    // -------------------------------------------------------------------------
    /**
     * The name of the column to which {@value #ATTRIBUTE_NAME_SERVICE}
     * attribute is bound.
     */
    public static final String COLUMN_NAME_SERVICE_ID
            = OfMucService.COLUMN_NAME_SERVICE_ID;

    /**
     * The name of the attribute from which {@value #COLUMN_NAME_SERVICE_ID}
     * column is bound.
     */
    public static final String ATTRIBUTE_NAME_SERVICE = "service";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ROOM_ID = "roomID";

    public static final String ATTRIBUTE_NAME_ROOM_ID = "roomId";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_CREATION_DATE = "creationDate";

    public static final String ATTRIBUTE_NAME_CREATION_DATE = "creationDate";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_MODIFICATION_DATE
            = "modificationDate";

    public static final String ATTRIBUTE_NAME_MODIFICATION_DATE
            = "modificationDate";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_NAME = "name";

    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_NATURAL_NAME = "naturalName";

    public static final String ATTRIBUTE_NAME_NATURAL_NAME = "naturalName";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_DESCRIPTION = "description";

    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_LOCKED_DATE = "lockedDate";

    public static final String ATTRIBUTE_NAME_LOCKED_DATE = "lockedDate";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_EMPTY_DATE = "emptyDate";

    public static final String ATTRIBUTE_NAME_EMPTY_DATE = "emptyDate";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_CAN_CHANGE_SUBJECT
            = "canChangeSubject";

    public static final String ATTRIBUTE_NAME_CAN_CHANGE_SUBJECT
            = "canChangeSubject";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_MAX_USERS = "maxUsers";

    public static final String ATTRIBUTE_NAME_MAX_USERS = "maxUsers";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_PUBLIC_ROOM = "publicRoom";

    public static final String ATTRIBUTE_NAME_PUBLIC_ROOM = "publicRoom";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_MODERATED = "moderated";

    public static final String ATTRIBUTE_NAME_MODERATED = "moderated";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_MEMBERS_ONLY = "membersOnly";

    public static final String ATTRIBUTE_NAME_MEMBERS_ONLY = "membersOnly";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_CAN_INVITE = "canInvite";

    public static final String ATTRIBUTE_NAME_CAN_INVITE = "canInvite";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ROOM_PASSWORD = "roomPassword";

    public static final String ATTRIBUTE_NAME_ROOM_PASSWORD = "roomPassword";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_CAN_DISCOVER_JID = "canDiscoverJID";

    public static final String ATTRIBUTE_NAME_CAN_DISCOVER_JID
            = "canDiscoverJid";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_LOG_ENABLED = "logEnabled";

    public static final String ATTRIBUTE_NAME_LOG_ENABLED = "logEnabled";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_SUBJECT = "subject";

    public static final String ATTRIBUTE_NAME_SUBJECT = "subject";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_USE_RESERVED_NICK
            = "useReservedNick";

    public static final String ATTRIBUTE_NAME_USE_RESERVED_NICK
            = "useReservedNick";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ROLES_TO_BROADCAST
            = "rolesToBroadcast";

    public static final String ATTRIBUTE_NAME_ROLES_TO_BROADCAST
            = "rolesToBroadcast";

    public static final int ROLES_TO_BROADCAST_MODERATOR = 0x04;

    public static final int ROLES_TO_BROADCAST_PARTICIPANT = 0x02;

    public static final int ROLES_TO_BROADCAST_VISITOR = 0x01;

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_CAN_CHANGE_NICK = "canChangeNick";

    public static final String ATTRIBUTE_NAME_CAN_CHANGE_NICK = "canChangeNick";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_CAN_REGISTER = "canRegister";

    public static final String ATTRIBUTE_NAME_CAN_REGISTER = "canRegister";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ALLOWPM = "allowpm";

    public static final String ATTRIBUTE_NAME_ALLOWPM = "allowpm";

    public static final int ALLOWPM_NONE = 3;

    public static final int ALLOWPM_MODERATOR = 2;

    public static final int ALLOWPM_PARTICIPATN = 1;

    public static final int ALLOWPM_ANYONE = 0;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "service=" + service
               + ",roomId=" + roomId
               + ",creationDate=" + creationDate
               + ",creationDateIsoz=" + getCreationDateIsoz()
               + ",modificationDate=" + modificationDate
               + ",modificationDateIsoz=" + getModificationDateIsoz()
               + ",name=" + name
               + ",naturalName=" + naturalName
               + ",description=" + description
               + ",lockedDate=" + lockedDate
               + ",lockedDateIsoz=" + getLockedDateIsoz()
               + ",emptyDate=" + emptyDate
               + ",emptyDateIsoz=" + getEmptyDateIsoz()
               + ",canChangeSubject=" + canChangeSubject
               + ",maxUsers=" + maxUsers
               + ",publicRoom=" + publicRoom
               + ",moderated=" + moderated
               + ",membersOnly=" + membersOnly
               + ",canInvite=" + canInvite
               + ",roomPassword=" + roomPassword
               + ",canDiscoverJid=" + canDiscoverJid
               + ",logEnabled=" + logEnabled
               + ",subject=" + subject
               + ",rolesToBroadcast=" + rolesToBroadcast
               + ",useReservedNick=" + useReservedNick
               + ",canChangeNick=" + canChangeNick
               + ",canRegister=" + canRegister
               + ",allowpm=" + allowpm + '}';
    }

    // -------------------------------------------------------------- idInstance
    /**
     * Returns the id instance of this entity.
     *
     * @return the id instance of this entity.
     */
    public OfMucRoomId getIdInstance() {
        return new OfMucRoomId().service(getServiceServiceId()).name(getName());
    }

    // ----------------------------------------------------------------- service
    public OfMucService getService() {
        return service;
    }

    public void setService(final OfMucService service) {
        this.service = service;
    }

    public OfMucRoom service(final OfMucService service) {
        setService(service);
        return this;
    }

    public Long getServiceServiceId() {
        return ofNullable(getService()).map(OfMucService::getServiceId)
                .orElse(null);
    }

    // ------------------------------------------------------------------ roomId
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(final Long roomId) {
        this.roomId = roomId;
    }

    public OfMucRoom roomId(final Long roomId) {
        setRoomId(roomId);
        return this;
    }

    // ------------------------------------------------------------ creationDate
    public Date getCreationDate() {
        return copyOf(creationDate);
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = copyOf(creationDate);
    }

    public OfMucRoom creationDate(final Date creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    public String getCreationDateIsoz() {
        return isozOf(getCreationDate());
    }

    // -------------------------------------------------------- modificationDate
    public Date getModificationDate() {
        return copyOf(modificationDate);
    }

    public void setModificationDate(final Date modificationDate) {
        this.modificationDate = copyOf(modificationDate);
    }

    public OfMucRoom modificationDate(final Date modificationDate) {
        setModificationDate(modificationDate);
        return this;
    }

    public String getModificationDateIsoz() {
        return isozOf(getModificationDate());
    }

    // -------------------------------------------------------------------- name
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public OfMucRoom name(final String name) {
        setName(name);
        return this;
    }

    // ------------------------------------------------------------- naturalName
    public String getNaturalName() {
        return naturalName;
    }

    public void setNaturalName(final String naturalName) {
        this.naturalName = naturalName;
    }

    public OfMucRoom naturalName(final String naturalName) {
        setNaturalName(naturalName);
        return this;
    }

    // ------------------------------------------------------------- description
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public OfMucRoom description(final String description) {
        setDescription(description);
        return this;
    }

    // -------------------------------------------------------------- lockedDate
    public Date getLockedDate() {
        return copyOf(lockedDate);
    }

    public void setLockedDate(final Date lockedDate) {
        this.lockedDate = copyOf(lockedDate);
    }

    public OfMucRoom lockedDate(final Date lockDate) {
        setLockedDate(lockedDate);
        return this;
    }

    public String getLockedDateIsoz() {
        return isozOf(getLockedDate());
    }

    // --------------------------------------------------------------- emptyDate
    public Date getEmptyDate() {
        return copyOf(emptyDate);
    }

    public void setEmptyDate(final Date emptyDate) {
        this.emptyDate = copyOf(emptyDate);
    }

    public OfMucRoom emptyDate(final Date emptyDate) {
        setEmptyDate(emptyDate);
        return this;
    }

    public String getEmptyDateIsoz() {
        return isozOf(getEmptyDate());
    }

    // -------------------------------------------------------- canChangeSubject
    public boolean isCanChangeSubject() {
        return canChangeSubject;
    }

    public void setCanChangeSubject(final boolean canChangeSubject) {
        this.canChangeSubject = canChangeSubject;
    }

    public OfMucRoom canChangeSubject(final boolean canChangeSubject) {
        setCanChangeSubject(canChangeSubject);
        return this;
    }

    // ---------------------------------------------------------------- maxUsers
    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(final int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public OfMucRoom maxUsers(final int maxUsers) {
        setMaxUsers(maxUsers);
        return this;
    }

    // -------------------------------------------------------------- publicRoom
    public boolean isPublicRoom() {
        return publicRoom;
    }

    public void setPublicRoom(final boolean publicRoom) {
        this.publicRoom = publicRoom;
    }

    public OfMucRoom publicRoom(final boolean publicRoom) {
        setPublicRoom(publicRoom);
        return this;
    }

    // --------------------------------------------------------------- moderated
    public boolean isModerated() {
        return moderated;
    }

    public void setModerated(final boolean moderated) {
        this.moderated = moderated;
    }

    public OfMucRoom moderated(final boolean moderated) {
        setModerated(moderated);
        return this;
    }

    // ------------------------------------------------------------- membersOnly
    public boolean isMembersOnly() {
        return membersOnly;
    }

    public void setMembersOnly(final boolean membersOnly) {
        this.membersOnly = membersOnly;
    }

    public OfMucRoom membersOnly(final boolean membersOnly) {
        setMembersOnly(membersOnly);
        return this;
    }

    // --------------------------------------------------------------- canInvite
    public boolean isCanInvite() {
        return canInvite;
    }

    public void setCanInvite(final boolean canInvite) {
        this.canInvite = canInvite;
    }

    public OfMucRoom canInvite(final boolean canInvite) {
        setCanInvite(canInvite);
        return this;
    }

    // ------------------------------------------------------------ roomPassword
    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(final String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public OfMucRoom roomPassword(final String roomPassword) {
        setRoomPassword(roomPassword);
        return this;
    }

    // ---------------------------------------------------------- canDiscoverJid
    public boolean isCanDiscoverJid() {
        return canDiscoverJid;
    }

    public void setCanDiscoverJid(final boolean canDiscoverJid) {
        this.canDiscoverJid = canDiscoverJid;
    }

    public OfMucRoom canDiscoverJid(final boolean canDiscoverJid) {
        setCanDiscoverJid(canDiscoverJid);
        return this;
    }

    // -------------------------------------------------------------- logEnabled
    public boolean isLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(final boolean logEnabled) {
        this.logEnabled = logEnabled;
    }

    public OfMucRoom logEnabled(final boolean logEnabled) {
        setLogEnabled(logEnabled);
        return this;
    }

    // ----------------------------------------------------------------- subject
    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public OfMucRoom subject(final String subject) {
        setSubject(subject);
        return this;
    }

    // -------------------------------------------------------- rolesToBroadcast
    public int isRolesToBroadcast() {
        return rolesToBroadcast;
    }

    public void setRolesToBroadcast(final int rolesToBroadcast) {
        this.rolesToBroadcast = rolesToBroadcast;
    }

    public OfMucRoom rolesToBroadcast(final int rolesToBroadcast) {
        setRolesToBroadcast(rolesToBroadcast);
        return this;
    }

    // --------------------------------------------------------- useReservedNick
    public boolean isUseReservedNick() {
        return useReservedNick;
    }

    public void setUseReservedNick(final boolean useReservedNick) {
        this.useReservedNick = useReservedNick;
    }

    public OfMucRoom useReservedNick(final boolean useReservedNick) {
        setUseReservedNick(useReservedNick);
        return this;
    }

    // ----------------------------------------------------------- canChangeNick
    public boolean isCanChangeNick() {
        return canChangeNick;
    }

    public void setCanChangeNick(final boolean canChangeNick) {
        this.canChangeNick = canChangeNick;
    }

    public OfMucRoom canChangeNick(final boolean canChangeNick) {
        setCanChangeNick(canChangeNick);
        return this;
    }

    // ------------------------------------------------------------- canRegister
    public boolean isCanRegister() {
        return canRegister;
    }

    public void setCanRegister(final boolean canRegister) {
        this.canRegister = canRegister;
    }

    public OfMucRoom canRegister(final boolean canRegister) {
        setCanRegister(canRegister);
        return this;
    }

    // ----------------------------------------------------------------- allowpm
    public Integer getAllowpm() {
        return allowpm;
    }

    public void setAllowpm(final Integer allowpm) {
        this.allowpm = allowpm;
    }

    public OfMucRoom allowpm(final Integer allowpm) {
        setAllowpm(allowpm);
        return this;
    }

    // -------------------------------------------------------------------------
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
                name = COLUMN_NAME_SERVICE_ID,
                nullable = false,
                updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_SERVICE)
    private OfMucService service;

    @NotNull
    @Column(name = COLUMN_NAME_ROOM_ID, nullable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ROOM_ID)
    private Long roomId;

    @NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = Date015AttributeConverter.class)
    @Column(name = COLUMN_NAME_CREATION_DATE, nullable = false,
            updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CREATION_DATE)
    private Date creationDate;

    @NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = Date015AttributeConverter.class)
    @Column(name = COLUMN_NAME_MODIFICATION_DATE, nullable = false,
            updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_MODIFICATION_DATE)
    private Date modificationDate;

    @NotNull
    @Id
    @Column(name = COLUMN_NAME_NAME, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_NAME)
    private String name;

    @NotNull
    @Column(name = COLUMN_NAME_NATURAL_NAME, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_NATURAL_NAME)
    private String naturalName;

    @Column(name = COLUMN_NAME_DESCRIPTION)
    @NamedAttribute(ATTRIBUTE_NAME_DESCRIPTION)
    private String description;

    //@NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = Date015AttributeConverter.class)
    @Column(name = COLUMN_NAME_LOCKED_DATE, nullable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_LOCKED_DATE)
    private Date lockedDate;

    //@Temporal(TemporalType.TIMESTAMP)
    @Convert(converter = Date015AttributeConverter.class)
    @Column(name = COLUMN_NAME_EMPTY_DATE, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_EMPTY_DATE)
    private Date emptyDate;

    // Room Settings / Room Options / Allow Occupants to change Subject
    @Column(name = COLUMN_NAME_CAN_CHANGE_SUBJECT, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CAN_CHANGE_SUBJECT)
    private boolean canChangeSubject;

    // Room Settings / Maximum Room Occupants
    @Column(name = COLUMN_NAME_MAX_USERS, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_MAX_USERS)
    private int maxUsers;

    // Room Settings / Room Options / List Room in Directory
    @Column(name = COLUMN_NAME_PUBLIC_ROOM, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_PUBLIC_ROOM)
    private boolean publicRoom;

    // Room Settings / Room Options / Make Room Moderated
    @Column(name = COLUMN_NAME_MODERATED, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_MODERATED)
    private boolean moderated;

    // Room Settings / Room Options / Make Room Members-only
    @Column(name = COLUMN_NAME_MEMBERS_ONLY, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_MEMBERS_ONLY)
    private boolean membersOnly;

    // Room Settings / Room Options / Allow Occupants to invite Others
    @Column(name = COLUMN_NAME_CAN_INVITE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CAN_INVITE)
    private boolean canInvite;

    // Room Settings / Password Required to Enter:
    // Room Settings / Confirm Password:
    @Column(name = COLUMN_NAME_ROOM_PASSWORD)
    @NamedAttribute(ATTRIBUTE_NAME_ROOM_PASSWORD)
    private String roomPassword;

    // Room Settings / Show Real JIDs of Occupants to == Anyone
    @Column(name = COLUMN_NAME_CAN_DISCOVER_JID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CAN_DISCOVER_JID)
    private boolean canDiscoverJid;

    @Column(name = COLUMN_NAME_LOG_ENABLED, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_LOG_ENABLED)
    private boolean logEnabled;

    @Column(name = COLUMN_NAME_SUBJECT)
    @NamedAttribute(ATTRIBUTE_NAME_SUBJECT)
    private String subject;

    @Column(name = COLUMN_NAME_ROLES_TO_BROADCAST, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ROLES_TO_BROADCAST)
    private int rolesToBroadcast;

    @Column(name = COLUMN_NAME_USE_RESERVED_NICK, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_USE_RESERVED_NICK)
    private boolean useReservedNick;

    @Column(name = COLUMN_NAME_CAN_CHANGE_NICK, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CAN_CHANGE_NICK)
    private boolean canChangeNick;

    @Column(name = COLUMN_NAME_CAN_REGISTER, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CAN_REGISTER)
    private boolean canRegister;

    // Room Settings / Allowed to Send Private Messages
    // None:        3 
    // Moderator:   2
    // Participant: 1
    // Anyone:      0
    @Column(name = COLUMN_NAME_ALLOWPM)
    @NamedAttribute(ATTRIBUTE_NAME_ALLOWPM)
    private Integer allowpm;
}
