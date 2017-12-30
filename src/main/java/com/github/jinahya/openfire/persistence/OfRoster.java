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

import static java.lang.invoke.MethodHandles.lookup;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 * @see <a href="https://goo.gl/YMSN8t">Table ofRoster - meaning of
 * sub/ask/recv</a>
 */
// https://goo.gl/YMSN8t Table ofRoster - meaning of sub/ask/recv
@Entity
public class OfRoster extends OfMapped {

    private static final long serialVersionUID = -3400376057340953555L;

    // -------------------------------------------------------------------------
    private static final Logger logger
            = getLogger(lookup().lookupClass().getName());

    // -------------------------------------------------------------------------
    /**
     * The name of the target table of this entity. The value is
     * {@value #TABLE_NAME}.
     */
    public static final String TABLE_NAME = "ofRoster";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ROSTER_ID = "rosterID";

    public static final String ATTRIBUTE_NAME_ROSTER_ID = "rosterId";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME
            = OfUser.COLUMN_NAME_USERNAME;

    public static final String ATTRIBUTE_NAME_USER = "user";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_JID = "jid";

    public static final String ATTRIBUTE_NAME_JID = "jid";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_SUB = "sub";

    public static final String ATTRIBUTE_NAME_SUB = "sub";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_ASK = "ask";

    public static final String ATTRIBUTE_NAME_ASK = "ask";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_RECV = "recv";

    public static final String ATTRIBUTE_NAME_RECV = "recv";

    // -------------------------------------------------------------------------
    public static final String COLUMN_NAME_NICK = "nick";

    public static final String ATTRIBUTE_NAME_NICK = "nick";

    // -------------------------------------------------------------------------
    public Long getRosterId() {
        return rosterId;
    }

    public void setRosterId(final Long rosterId) {
        this.rosterId = rosterId;
    }

    public OfRoster rosterId(final Long rosterId) {
        setRosterId(rosterId);
        return this;
    }

    // -------------------------------------------------------------------- user
    public OfUser getUser() {
        return user;
    }

    public void setUser(final OfUser user) {
        this.user = user;
    }

    public OfRoster user(final OfUser user) {
        setUser(user);
        return this;
    }

    // --------------------------------------------------------------------- jid
    public String getJid() {
        return jid;
    }

    public void setJid(final String jid) {
        this.jid = jid;
    }

    public OfRoster jid(final String jid) {
        setJid(jid);
        return this;
    }

    // --------------------------------------------------------------------- sub
    public int getSub() {
        return sub;
    }

    public void setSub(final int sub) {
        this.sub = sub;
    }

    public OfRoster sub(final int sub) {
        setSub(sub);
        return this;
    }

    // --------------------------------------------------------------------- ask
    public int getAsk() {
        return ask;
    }

    public void setAsk(final int ask) {
        this.ask = ask;
    }

    public OfRoster ask(final int ask) {
        setAsk(ask);
        return this;
    }

    // -------------------------------------------------------------------- recv
    public int getRecv() {
        return recv;
    }

    public void setRecv(final int recv) {
        this.recv = recv;
    }

    public OfRoster recv(final int recv) {
        setRecv(recv);
        return this;
    }

    // -------------------------------------------------------------------- nick
    public String getNick() {
        return nick;
    }

    public void setNick(final String nick) {
        this.nick = nick;
    }

    public OfRoster nick(final String nick) {
        setNick(nick);
        return this;
    }

    // -------------------------------------------------------------------------
    @Id
    @Column(name = COLUMN_NAME_ROSTER_ID, nullable = false, unique = true,
            updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ROSTER_ID)
    private Long rosterId;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
                name = COLUMN_NAME_USERNAME,
                nullable = false,
                referencedColumnName = OfUser.COLUMN_NAME_USERNAME
    )
    @NamedAttribute(ATTRIBUTE_NAME_USER)
    private OfUser user;

    @NotNull
    @Column(name = COLUMN_NAME_JID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_JID)
    private String jid;

    @Column(name = COLUMN_NAME_SUB, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_SUB)
    private int sub;

    @Column(name = COLUMN_NAME_ASK, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ASK)
    private int ask;

    @Column(name = COLUMN_NAME_RECV, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_RECV)
    private int recv;

    @Column(name = COLUMN_NAME_NICK)
    @NamedAttribute(ATTRIBUTE_NAME_NICK)
    private String nick;
}
