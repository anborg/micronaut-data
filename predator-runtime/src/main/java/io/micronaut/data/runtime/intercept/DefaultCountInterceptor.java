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

import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.intercept.CountInterceptor;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.backend.Datastore;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.model.PreparedQuery;

import java.util.Iterator;

/**
 * Default implementation of {@link CountInterceptor}.
 * @param <T> The declaring type
 * @author graemerocher
 * @since 1.0.0
 */
public class DefaultCountInterceptor<T> extends AbstractQueryInterceptor<T, Number> implements CountInterceptor<T> {

    /**
     * Default constructor.
     * @param datastore The datastore
     */
    protected DefaultCountInterceptor(@NonNull Datastore datastore) {
        super(datastore);
    }

    @Override
    public Number intercept(MethodInvocationContext<T, Number> context) {
        long result;
        if (context.hasAnnotation(Query.class)) {
            PreparedQuery<?, Long> preparedQuery = prepareQuery(context, Long.class);
            Iterable<Long> iterable = datastore.findAll(preparedQuery);
            Iterator<Long> i = iterable.iterator();
            result = i.hasNext() ? i.next() : 0;
        } else {
            Class<?> rootEntity = getRequiredRootEntity(context);
            Pageable pageable = getPageable(context);

            if (pageable != null) {
                result = datastore.count(rootEntity, pageable);
            } else {
                result = datastore.count(rootEntity);
            }
        }

        return ConversionService.SHARED.convert(
                result,
                context.getReturnType().asArgument()
        ).orElseThrow(() -> new IllegalStateException("Unsupported number type: " + context.getReturnType().getType()));
    }
}
