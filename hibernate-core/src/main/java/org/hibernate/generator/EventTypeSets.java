/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.generator;

import java.util.EnumSet;
import java.util.Set;

import static org.hibernate.generator.EventType.INSERT;
import static org.hibernate.generator.EventType.SOFT_DELETE;
import static org.hibernate.generator.EventType.UPDATE;

/**
 * For convenience, enumerates the possible combinations of {@link EventType}.
 *
 * @author Gavin King
 * @author Yongjun Hong
 *
 * @since 6.2
 */
public class EventTypeSets {
	public static final EnumSet<EventType> NONE = EnumSet.noneOf(EventType.class);
	public static final EnumSet<EventType> INSERT_ONLY = EnumSet.of(INSERT);
	public static final EnumSet<EventType> UPDATE_ONLY = EnumSet.of(UPDATE);
	public static final EnumSet<EventType> SOFT_DELETE_ONLY = EnumSet.of(SOFT_DELETE);
	public static final EnumSet<EventType> INSERT_AND_UPDATE = EnumSet.of(INSERT, UPDATE);
	public static final EnumSet<EventType> ALL = EnumSet.allOf(EventType.class);

	public static EnumSet<EventType> fromArray(EventType[] types) {
		return EnumSet.copyOf( Set.of(types) );
	}
}
