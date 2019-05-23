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
import io.micronaut.core.util.ArrayUtils;
import io.micronaut.data.intercept.SaveAllInterceptor;
import io.micronaut.data.backend.Datastore;

/**
 * Default implementation of {@link SaveAllInterceptor}.
 * @param <T> The declaring type
 * @param <R> The return type
 * @author graemerocher
 * @since 1.0.0
 */
public class DefaultSaveAllInterceptor<T, R> implements SaveAllInterceptor<T, R> {

    private final Datastore datastore;

    /**
     * Default constructor.
     * @param datastore The datastore
     */
    protected DefaultSaveAllInterceptor(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Iterable<R> intercept(MethodInvocationContext<T, Iterable<R>> context) {
        Object[] parameterValues = context.getParameterValues();
        if (ArrayUtils.isNotEmpty(parameterValues) && parameterValues[0] instanceof Iterable) {
            //noinspection unchecked
            return datastore.persistAll((Iterable<R>) parameterValues[0]);
        } else {
            throw new IllegalArgumentException("First argument should be an iterable");
        }
    }
}
