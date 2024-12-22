/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.hibernate.generator.EventType;
import org.hibernate.generator.internal.CurrentTimestampGeneration;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hibernate.generator.EventType.INSERT;
import static org.hibernate.generator.EventType.SOFT_DELETE;
import static org.hibernate.generator.EventType.UPDATE;

/**
 * Specifies that the annotated field of property is a generated timestamp,
 * and also specifies the {@linkplain #event timing} of the timestamp
 * generation, and whether it is generated in Java or by the database:
 * <ul>
 * <li>{@link SourceType#VM source = VM} indicates that the virtual machine
 *     {@linkplain java.time.Clock#instant current instant} is used, and
 * <li>{@link SourceType#DB source = DB} indicates that the database
 *     {@code current_timestamp} function should be used.
 * </ul>
 * <p>
 * By default, the timestamp is generated by the database, which might
 * require an extra round trip to the database to fetch the generated
 * value, depending on the capabilities of the
 * {@linkplain org.hibernate.dialect.Dialect#supportsInsertReturning database} and
 * {@linkplain org.hibernate.dialect.Dialect#supportsInsertReturningGeneratedKeys driver}.
 * <p>
 * This annotation may be used in combination with the JPA-defined
 * {@link jakarta.persistence.Version} annotation.
 * <p>
 * The annotated property may be of any one of the following types:
 * {@link java.util.Date},
 * {@link java.util.Calendar},
 * {@link java.sql.Date},
 * {@link java.sql.Time},
 * {@link java.sql.Timestamp},
 * {@link java.time.Instant},
 * {@link java.time.LocalDate},
 * {@link java.time.LocalDateTime},
 * {@link java.time.LocalTime},
 * {@link java.time.MonthDay},
 * {@link java.time.OffsetDateTime},
 * {@link java.time.OffsetTime},
 * {@link java.time.Year},
 * {@link java.time.YearMonth}, or
 * {@link java.time.ZonedDateTime}.
 * <p>
 * A field annotated {@code @CurrentTimestamp} may not be directly set
 * by the application program.
 *
 * @see UpdateTimestamp
 * @see CreationTimestamp
 * @see SoftDeleteTimestamp
 * @see CurrentTimestampGeneration
 *
 * @since 6.0
 *
 * @author Steve Ebersole
 * @author Yongjun Hong
 */
@ValueGenerationType(generatedBy = CurrentTimestampGeneration.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
public @interface CurrentTimestamp {
	/**
	 * Determines when the timestamp is generated. By default, it is updated
	 * when any SQL {@code insert}, {@code update}, or soft-delete operation
	 * is executed. If it should be generated just once, on the initial SQL
	 * {@code insert}, explicitly specify {@link EventType#INSERT event = INSERT}.
	 * For soft-delete operations, {@link EventType#SOFT_DELETE} can be used.
	 */
	EventType[] event() default {INSERT, UPDATE, SOFT_DELETE};


	/**
	 * Specifies how the timestamp is generated. By default, it is generated
	 * by the database. Depending on the capabilities of the database and JDBC
	 * driver, this might require that the value be fetched using a subsequent
	 * {@code select} statement. Setting {@code source = VM} guarantees that
	 * this additional {@code select} never occurs.
	 */
	SourceType source() default SourceType.DB;
}
