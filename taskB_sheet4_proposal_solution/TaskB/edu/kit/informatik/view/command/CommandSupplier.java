/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.command;

import java.util.List;

/**
 * This interface describes a command supplier.
 * A command supplier bundles semantically connected commands.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public interface CommandSupplier {
    /**
     * Returns a list of commands.
     *
     * @return a list of commands
     */
    List<Command> get();
}