/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.weld.deployment.processors;

import java.net.URL;

import org.jboss.as.ee.structure.SpecDescriptorPropertyReplacement;
import org.jboss.as.server.deployment.DeploymentUnit;
import org.jboss.as.weld.deployment.AS7BeansXmlHandler;
import org.jboss.as.weld.deployment.AS7BeansXmlParser;
import org.jboss.as.weld.deployment.ReplacingBeansXmlHandler;

/**
 * Deployment processor that finds <literal>beans.xml</literal> files and attaches the information to the deployment
 *
 * @author Stuart Douglas
 */
public class BeansXmlProcessor extends LegacyBeansXmlProcessor {

    @Override
    protected AS7BeansXmlParser getBeansXmlParser(final DeploymentUnit deploymentUnit) {
        return new AS7BeansXmlParser() {
            protected AS7BeansXmlHandler getHandler(final URL beansXml) {
                return new ReplacingBeansXmlHandler(beansXml, SpecDescriptorPropertyReplacement.propertyReplacer(deploymentUnit));
            }
        };
    }
}