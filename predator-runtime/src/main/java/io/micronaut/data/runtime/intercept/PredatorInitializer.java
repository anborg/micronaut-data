/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.data.runtime.intercept;

import io.micronaut.context.annotation.Context;
import io.micronaut.core.convert.ConversionService;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * Factory for creating the different types of interceptors.
 *
 * @author graemerocher
 * @since 1.0
 */
@Context
class PredatorInitializer {

    /**
     * Default constructor.
     */
    PredatorInitializer() {
        ConversionService<?> conversionService = ConversionService.SHARED;
        conversionService.addConverter(OffsetDateTime.class, java.sql.Date.class, offsetDateTime ->
                new java.sql.Date(offsetDateTime.toInstant().toEpochMilli())
        );
        conversionService.addConverter(OffsetDateTime.class, Date.class, offsetDateTime ->
                new Date(offsetDateTime.toInstant().toEpochMilli())
        );
        conversionService.addConverter(OffsetDateTime.class, Instant.class, OffsetDateTime::toInstant);
        conversionService.addConverter(OffsetDateTime.class, Long.class, offsetDateTime ->
                offsetDateTime.toInstant().toEpochMilli()
        );
        conversionService.addConverter(OffsetDateTime.class, Timestamp.class, offsetDateTime ->
                new Timestamp(offsetDateTime.toInstant().toEpochMilli())
        );
    }
}
