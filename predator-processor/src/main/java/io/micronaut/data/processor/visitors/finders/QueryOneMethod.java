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
package io.micronaut.data.processor.visitors.finders;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.intercept.FindOneInterceptor;
import io.micronaut.data.processor.visitors.MatchContext;
import io.micronaut.data.processor.visitors.MethodMatchContext;
import io.micronaut.inject.ast.ClassElement;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Support for explicit queries that return a single result.
 *
 * @author graemerocher
 * @since 1.0.0
 */
public class QueryOneMethod extends QueryListMethod {
    @Override
    protected boolean isValidReturnType(@NonNull ClassElement returnType, MatchContext matchContext) {
        return returnType.hasStereotype(Introspected.class);
    }

    @Override
    protected MethodMatchInfo buildMatchInfo(@NonNull MethodMatchContext matchContext, @NonNull RawQuery query) {
        return new MethodMatchInfo(
            matchContext.getReturnType(),
            query,
            FindOneInterceptor.class
        );
    }
}
