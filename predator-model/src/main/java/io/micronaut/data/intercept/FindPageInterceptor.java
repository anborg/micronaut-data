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
package io.micronaut.data.intercept;

import io.micronaut.data.model.Page;

/**
 * An interceptor that handles a return type of {@link Page}.
 *
 * @author graemerocher
 * @param <T> The declaring type
 * @param <R> The return type
 * @since 1.0.0
 */
public interface FindPageInterceptor<T, R> extends PredatorInterceptor<T, R> {
}
