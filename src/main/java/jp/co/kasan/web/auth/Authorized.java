/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.web.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 * 認可済み会計帳簿注入用アノテーション。
 * @author rued97
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Authorized {}
