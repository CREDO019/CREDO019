package com.google.common.p016io;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.When;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Nonnull(when = When.UNKNOWN)
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.google.common.io.ParametricNullness */
/* loaded from: classes3.dex */
@interface ParametricNullness {
}
