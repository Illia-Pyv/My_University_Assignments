/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter.post;

import java.util.List;

import edu.kit.informatik.model.post.PostSystem;
import edu.kit.informatik.presenter.post.commands.GetMail;
import edu.kit.informatik.presenter.post.commands.ListMail;
import edu.kit.informatik.presenter.post.commands.ListPrice;
import edu.kit.informatik.presenter.post.commands.ResetPin;
import edu.kit.informatik.presenter.post.commands.SendMail;
import edu.kit.informatik.view.command.Command;
import edu.kit.informatik.view.command.CommandSupplier;

/**
 * This class describes an command provider for a post system.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class PostCommandSupplier implements CommandSupplier {

    private final List<Command> commands;

    /**
     * Constructs a command supplier for a post system.
     * 
     * @param postSystem the post system to control
     */
    public PostCommandSupplier(PostSystem postSystem) {
        this.commands = List.of(
                new ResetPin(postSystem),
                new SendMail(postSystem),
                new GetMail(postSystem),
                new ListMail(postSystem),
                new ListPrice(postSystem)
            );
    }

    @Override
    public List<Command> get() {
        // no copy needed since List.of() creates a immutable list and commands are immutable by contract.
        return this.commands;
    }
}