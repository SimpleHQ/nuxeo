/*
 * (C) Copyright 2006-2007 Nuxeo SA (http://nuxeo.com/) and others.
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
 *     <a href="mailto:at@nuxeo.com">Anahide Tchertchian</a>
 *
 * $Id: LayoutRowDefinition.java 26053 2007-10-16 01:45:43Z atchertchian $
 */

package org.nuxeo.ecm.platform.forms.layout.api;

import java.io.Serializable;
import java.util.Map;

/**
 * Layout row definition interface.
 *
 * @author <a href="mailto:at@nuxeo.com">Anahide Tchertchian</a>
 */
public interface LayoutRowDefinition extends Serializable {

    /**
     * Returns the row name
     */
    String getName();

    /**
     * Returns the default name for this row, given an index.
     *
     * @since 6.0
     */
    String getDefaultName(int index);

    /**
     * Returns true if this row should be considered selected by default
     */
    boolean isSelectedByDefault();

    /**
     * Return true if this row should be considered always selected
     */
    boolean isAlwaysSelected();

    /**
     * Returns the row size
     */
    int getSize();

    /**
     * Returns the list of widget names to use at a given row.
     * <p>
     * For instance, this could describe a layout like: ['title'], or ['creationDate', '', 'modificationDate'].
     *
     * @deprecated since 5.5: use {@link #getWidgetReferences()} instead.
     */
    @Deprecated
    String[] getWidgets();

    /**
     * Returns the list of widget references to use at a given row.
     *
     * @since 5.5
     */
    WidgetReference[] getWidgetReferences();

    /**
     * Returns a map of properties to use in a given mode.
     */
    Map<String, Serializable> getProperties(String layoutMode);

    /**
     * Returns properties by mode.
     */
    Map<String, Map<String, Serializable>> getProperties();

    /**
     * Returns a clone instance of this row definition.
     * <p>
     * Useful for conversion of layout definition during export.
     *
     * @since 5.5
     */
    LayoutRowDefinition clone();

}
