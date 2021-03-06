/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.common.WebEnvironment;
import io.vertx.ext.web.handler.impl.ErrorHandlerImpl;
import io.vertx.ext.web.RoutingContext;

/**
 * A pretty error handler for rendering error pages. When working in development mode
 * exception details will be returned in the server responses, otherwise or when
 * manually specified no exception details are returned in the HTTP response.
 *
 * The reason the display of the exception details is by default dependent of the mode
 * is to follow the security best practices:
 * <a href="https://www.owasp.org/index.php/Improper_Error_Handling">https://www.owasp.org/index.php/Improper_Error_Handling</a>
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 * @author <a href="mailto:plopes@redhat.com">Paulo Lopes</a>
 */
@VertxGen
public interface ErrorHandler extends Handler<RoutingContext> {

  /**
   * The default template to use for rendering
   */
  String DEFAULT_ERROR_HANDLER_TEMPLATE = "META-INF/vertx/web/vertx-web-error.html";

  /**
   * Create an error handler using defaults
   *
   * @return the handler
   */
  static ErrorHandler create(Vertx vertx) {
    return create(vertx, DEFAULT_ERROR_HANDLER_TEMPLATE, WebEnvironment.development());
  }

  /**
   * Create an error handler
   *
   * @param errorTemplateName  the error template name - will be looked up from the classpath
   * @param displayExceptionDetails  true if exception details should be displayed
   * @return the handler
   */
  static ErrorHandler create(Vertx vertx, String errorTemplateName, boolean displayExceptionDetails) {
    return new ErrorHandlerImpl(vertx, errorTemplateName, displayExceptionDetails);
  }

  /**
   * Create an error handler
   *
   * @param displayExceptionDetails  true if exception details should be displayed
   * @return the handler
   */
  static ErrorHandler create(Vertx vertx, boolean displayExceptionDetails) {
    return create(vertx, DEFAULT_ERROR_HANDLER_TEMPLATE, displayExceptionDetails);
  }

  /**
   * Create an error handler
   *
   * @param errorTemplateName  the error template name - will be looked up from the classpath
   * @return the handler
   */
  static ErrorHandler create(Vertx vertx, String errorTemplateName) {
    return create(vertx, errorTemplateName, WebEnvironment.development());
  }

}
