/*
 * Copyright 2004-2005 The Apache Software Foundation or its licensors,
 *                     as applicable.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.core.virtual;

import org.apache.jackrabbit.core.ItemId;
import org.apache.jackrabbit.core.NodeId;
import org.apache.jackrabbit.core.PropertyId;
import org.apache.jackrabbit.core.QName;
import org.apache.jackrabbit.core.state.ItemStateException;
import org.apache.jackrabbit.core.state.ItemStateListener;
import org.apache.jackrabbit.core.state.ItemStateManager;
import org.apache.jackrabbit.core.state.NoSuchItemStateException;
import org.apache.jackrabbit.core.state.NodeReferences;

import javax.jcr.RepositoryException;

/**
 * This Interface defines a virtual item state provider.
 */
public interface VirtualItemStateProvider extends ItemStateManager, ItemStateListener {

    /**
     * Checks if the id refers to the root of a virtual tree.
     *
     * @param id
     * @return
     */
    public boolean isVirtualRoot(ItemId id);

    /**
     * Returns the id of the root node of the virtual tree.
     *
     * @return
     */
    public NodeId getVirtualRootId();

    /**
     * Checks if the node with the given id exists in this item state provider.
     *
     * @param id
     * @return
     */
    public boolean hasNodeState(NodeId id);

    /**
     * Checks if the property with the given id exists in this item state provider.
     *
     * @param id
     * @return
     */
    public boolean hasPropertyState(PropertyId id);

    /**
     * Returns the node state for the given node id
     *
     * @param id
     * @return
     * @throws ItemStateException
     * @throws NoSuchItemStateException
     */
    public VirtualNodeState getNodeState(NodeId id)
            throws ItemStateException, NoSuchItemStateException;

    /**
     * Returns the property state for the give property id
     *
     * @param id
     * @return
     * @throws ItemStateException
     * @throws NoSuchItemStateException
     */
    public VirtualPropertyState getPropertyState(PropertyId id)
            throws ItemStateException, NoSuchItemStateException;

    /**
     * Creats a new virtual property state
     *
     * @param parent
     * @param name
     * @param type
     * @param multiValued
     * @return
     * @throws RepositoryException
     */
    public VirtualPropertyState createPropertyState(VirtualNodeState parent,
                                                    QName name, int type,
                                                    boolean multiValued)
            throws RepositoryException;

    /**
     * Creates a new virtual node state
     *
     * @param parent
     * @param name
     * @param uuid
     * @param nodeTypeName
     * @return
     * @throws RepositoryException
     */
    public VirtualNodeState createNodeState(VirtualNodeState parent, QName name,
                                            String uuid, QName nodeTypeName)
            throws RepositoryException;

    /**
     * Informs this provider that the node references to one of its states has
     * changed.
     *
     * @param refs
     * @return <code>true</code> if the reference target is one of its items.
     */
    public boolean setNodeReferences(NodeReferences refs);
}
