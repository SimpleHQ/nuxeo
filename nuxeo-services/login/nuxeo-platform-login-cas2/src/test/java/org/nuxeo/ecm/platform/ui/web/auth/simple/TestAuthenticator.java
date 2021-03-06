/*
 * (C) Copyright 2006-2009 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *     Academie de Rennes - proxy CAS support
 *
 * $Id: JOOoConvertPluginImpl.java 18651 2007-05-13 20:28:53Z sfermigier $
 */

package org.nuxeo.ecm.platform.ui.web.auth.simple;

import java.security.Principal;

import javax.security.auth.login.LoginContext;

import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.ecm.platform.ui.web.auth.NXAuthConstants;

/**
 * @author Benjamin JALON
 */
public class TestAuthenticator extends AbstractAuthenticator {

    @Test
    public void testAuthentication() throws Exception {
        deployContrib("org.nuxeo.ecm.platform.login.cas2.test", "OSGI-INF/login-yes-contrib.xml");

        initRequest();
        setLoginPasswordInHeader("Administrator", "Administrator", request);

        naf.doFilter(request, response, chain);

        String loginError = (String) request.getAttribute(NXAuthConstants.LOGIN_ERROR);
        LoginContext loginContext = (LoginContext) request.getAttribute("org.nuxeo.ecm.login.context");
        assertNull(loginError);
        assertNotNull(loginContext);
        assertEquals("Administrator", ((Principal) loginContext.getSubject().getPrincipals().toArray()[0]).getName());
    }

    @Test
    public void testNoAuthentication() throws Exception {
        deployContrib("org.nuxeo.ecm.platform.login.cas2.test", "OSGI-INF/login-no-contrib.xml");

        initRequest();
        setLoginPasswordInHeader("Administrator", "Administrator", request);

        naf.doFilter(request, response, chain);

        String loginError = (String) request.getAttribute(NXAuthConstants.LOGIN_ERROR);
        LoginContext loginContext = (LoginContext) request.getAttribute("org.nuxeo.ecm.login.context");
        assertEquals("authentication.failed", loginError);
        assertNull(loginContext);
    }

}
