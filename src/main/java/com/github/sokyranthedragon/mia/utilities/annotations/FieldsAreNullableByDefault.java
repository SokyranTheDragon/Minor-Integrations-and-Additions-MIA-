package com.github.sokyranthedragon.mia.utilities.annotations;

import javax.annotation.Nullable;
import javax.annotation.meta.TypeQualifierDefault;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Nullable
@TypeQualifierDefault({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsAreNullableByDefault
{
}
