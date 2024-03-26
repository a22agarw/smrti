package org.smrti.service.log.conditions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionForPull implements Condition {

    @Value(value = "${enable.controller.pull}")
    private boolean conditionForPull;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return conditionForPull;
    }
}
