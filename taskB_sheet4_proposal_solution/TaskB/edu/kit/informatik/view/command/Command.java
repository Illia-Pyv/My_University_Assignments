/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.view.command;

import java.util.List;

import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;

/**
 * This abstract class describes a command.
 * 
 * A command must be immutable.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public abstract class Command implements CommandSupplier {

    /**
     * Returns a list of {@link Parameter} in exactly the order in which
     * they must passed.
     *
     * @return A list of {@link Parameter}
     */
    public abstract List<Parameter<?>> getParameters();

    /**
     * A regular expression of the command name. 
     * The regex should <b>not</b> include any parameters nor contain any whitespace.
     *
     * @return a regular expression of the command name
     */
    public abstract String getRegex();

    /**
     * Executes the command. The caller must ensure that the Parameters match the type described 
     * by {@link #getParameters}. And that all mandatory parameters are contained in the {@link ParameterBundle}.
     *
     * @param bundle the parameters according to {@link #getParameters}
     *
     * @return the result of the execution
     */
    public abstract Result apply(ParameterBundle bundle);


    @Override
    public List<Command> get() {
        return List.of(this);
    }

}