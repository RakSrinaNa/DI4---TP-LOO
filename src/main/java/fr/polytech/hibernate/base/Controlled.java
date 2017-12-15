package fr.polytech.hibernate.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that this class should be added to the session factory.
 * <p>
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 18/11/2017.
 *
 * @author Thomas Couchoud
 * @since 2017-11-18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Controlled
{}
