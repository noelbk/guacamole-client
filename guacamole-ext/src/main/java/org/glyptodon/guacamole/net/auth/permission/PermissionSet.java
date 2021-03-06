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

package org.glyptodon.guacamole.net.auth.permission;

import java.util.Set;
import org.glyptodon.guacamole.GuacamoleException;


/**
 * An arbitrary set of permissions.
 *
 * @author Michael Jumper
 * @param <PermissionType>
 *     The type of permission stored within this PermissionSet.
 */
public interface PermissionSet<PermissionType extends Permission> {

    /**
     * Returns a Set which contains all permissions granted within this
     * permission set.
     *
     * @return
     *     A Set containing all permissions granted within this permission set.
     *
     * @throws GuacamoleException 
     *     If an error occurs while retrieving permissions, or if permissions
     *     cannot be retrieved due to lack of permissions to do so.
     */
    Set<PermissionType> getPermissions() throws GuacamoleException;

    /**
     * Adds the specified permissions, if not already granted. If a specified
     * permission is already granted, no operation is performed regarding that
     * permission.
     *
     * @param permissions
     *     The permissions to add.
     *
     * @throws GuacamoleException
     *     If an error occurs while adding the permissions, or if permission to
     *     add permissions is denied.
     */
    void addPermissions(Set<PermissionType> permissions)
            throws GuacamoleException;

    /**
     * Removes each of the specified permissions, if granted. If a specified
     * permission is not granted, no operation is performed regarding that
     * permission.
     *
     * @param permissions
     *     The permissions to remove.
     *
     * @throws GuacamoleException
     *     If an error occurs while removing the permissions, or if permission
     *     to remove permissions is denied.
     */
    void removePermissions(Set<PermissionType> permissions)
            throws GuacamoleException;


}
