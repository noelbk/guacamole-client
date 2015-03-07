/*
 * Copyright (C) 2015 Glyptodon LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.glyptodon.guacamole.auth.jdbc.base;

import org.glyptodon.guacamole.auth.jdbc.connectiongroup.RootConnectionGroup;
import org.glyptodon.guacamole.auth.jdbc.user.AuthenticatedUser;
import org.glyptodon.guacamole.net.auth.Groupable;

/**
 * Common base class for objects that will ultimately be made available through
 * the Directory class. All such objects will need the same base set of queries
 * to fulfill the needs of the Directory class.
 *
 * @author Michael Jumper
 * @param <ModelType>
 *     The type of model object that corresponds to this object.
 */
public abstract class GroupedDirectoryObject<ModelType extends GroupedObjectModel>
    extends DirectoryObject<ModelType> implements Groupable {

    /**
     * The associated parent identifier at the time this object was initialized
     * via init(). If there was no parent, this will be null.
     */
    private String initialParentIdentifier = null;

    @Override
    public void init(AuthenticatedUser currentUser, ModelType model) {

        super.init(currentUser, model);

        // Remember original parent, if defined
        if (model != null) {
            initialParentIdentifier = model.getParentIdentifier();
            if (initialParentIdentifier == null)
                initialParentIdentifier = RootConnectionGroup.IDENTIFIER;
        }

    }

    /**
     * Returns the parent identifier of this object at the time of
     * initialization. If there was no associated parent, this will be null.
     *
     * @return
     *     The parent identifier associated with the this connection group at
     *     the time of initialization, if any.
     */
    public String getInitialParentIdentifier() {
        return initialParentIdentifier;
    }

    @Override
    public String getParentIdentifier() {

        // Translate null parent to proper identifier
        String parentIdentifier = getModel().getParentIdentifier();
        if (parentIdentifier == null)
            return RootConnectionGroup.IDENTIFIER;

        return parentIdentifier;
        
    }

    @Override
    public void setParentIdentifier(String parentIdentifier) {

        // Translate root identifier back into null
        if (parentIdentifier != null
                && parentIdentifier.equals(RootConnectionGroup.IDENTIFIER))
            parentIdentifier = null;

        getModel().setParentIdentifier(parentIdentifier);

    }

}
