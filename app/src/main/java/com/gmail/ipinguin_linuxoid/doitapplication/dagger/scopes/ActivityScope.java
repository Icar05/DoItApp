package com.gmail.ipinguin_linuxoid.doitapplication.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * область видимости локальных синглтонов
 * уровня активности
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}