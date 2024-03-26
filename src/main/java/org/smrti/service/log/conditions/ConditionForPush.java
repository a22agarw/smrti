package org.smrti.service.log.conditions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionForPush implements Condition {

    @Value(value = "${enable.controller.push}")
    private boolean conditionForPush;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return conditionForPush;
    }
}
