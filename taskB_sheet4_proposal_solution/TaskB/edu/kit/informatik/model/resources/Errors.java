/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.model.resources;

/**
 * This class provides resources.
 * In big projects ResourceBundles would be used instead.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public final class Errors {
    /** Error-Message if a parsing operation fails. Format with actual and expected type. */
    public static final String PARSING_FAILED = "'%s' cannot be parsed as %s.";
    /** Error-Message if parameter parsing fails. Format with parameter index and detailed message */
    public static final String PARAMETER_PARSING = "failed to parse parameter on index %d: %s";
    /** Error-Message if parameter parsing fails. Format with parameter description, parameter index
     * and detailed message */
    public static final String PARAMETER_PARSING_DESC = "failed to parse parameter (%s) on index %d: %s";
    /** Error-Message if put-Operation of a bundle fails */
    public static final String PARAM_CLASS_MATCH 
            = "class %s does not match %s. Parameter could not be put into the bundle.";
    /** Error-Message if command regex does not match. Format with supplied command */
    public static final String COMMAND_REGEX_NOT_MATCHED 
            = "the supplied command '%s' does not match the command pattern.";
    /** Error-Message if a unknown command name is detected. Format with command name */
    public static final String COMMAND_NOT_KNOWN = "the command '%s' is not known.";
    /** Error-Message if parameter count does not match. */
    public static final String PARAMETER_COUNT = "parameter count does not match.";
    /** Error-Message if parameter count does not match. Format with min and max expected count and actual count */
    public static final String PARAMETER_COUNT_BETWEEN = PARAMETER_COUNT
            + " Expected between %d and %d parameter but got %d.";
    /** Error-Message if parameter count does not match. Format with expected count and actual count */
    public static final String PARAMETER_COUNT_EXACTLY = PARAMETER_COUNT
            + " Expected exactly %d parameter but got %d.";
    /** Error-Message if something was not implemented, but should */
    public static final String NOT_IMPLEMENTED = "%s not implemented!";
    /** Error-Message if a command did not succeed but no message was supplied */
    public static final String COMMAND_ENDED_ERROR = "command did not succeed.";
    /** Error-Message if a command gets executed which is only 
     * available in a logged-out state but a user is logged in */
    public static final String COMMAND_NOT_AVAIL_LOGGED_IN = "this command is not allowed in a logged-in state";
    /** Error-Message if a command gets executed which is only 
     * available in a logged-in state but nobody is logged in */
    public static final String COMMAND_NOT_AVAIL_LOGGED_OUT = "this command is only allowed in a logged-in state";
    /** Error-Message if nobody was logged in */
    public static final String NOBODY_LOGGED_IN = "nobody was logged in.";
    /** Error-Message if the stuff number is negative */
    public static final String STUFF_NUMBER_NEG = "stuff number must be a positive integer.";
    /** Error-Message describing the length of authentication parameter. Format with min and max length */
    public static final String AUTH_PARAM_LENGTH = "parameter length must be between (inclusive) %d and %d.";    
    /** Error-Message if the stuff number is already in use. Format with stuff number */
    public static final String STAFF_NUM_IN_USE = "an user with stuff number '%d' does already exist.";
    /** Error-Message if the username is already in use. Format with username */
    public static final String USERNAME_IN_USE = "an user with username '%s' does already exist.";
    /** Error-Message if identity card number is already in use. Format with id number */
    public static final String IDENTITY_NUM_EXISTS = "a customer with identity card number "
            + "'%s' does already exist.";
    /** Error-Message if identity card number length does not match. Format with length */
    public static final String IDENTITY_NUM_LENGTH = "the identity card number must be exactly %d characters long";
    /** Error-Message if the username matches the identity card number */
    public static final String CUSTOMER_PRIVACY = "the username and the identity card number must be distinct.";
    /** Error-Message if the login fails */
    public static final String LOGIN_FAILED = "login failed. Check your username and password.";
    /** Error-Message if a command does have optional parameter before non-optional parameters */
    public static final String COMMAND_PARAMETER_INVALID = "the command '%s' does specify a non-optional "
            + "parameter after an optional parameter. This is not allowed!";
    /** Error-Message that a command is restricted to certain roles. Format with allowed roles */
    public static final String COMMAND_RESTRICTED = "this command is restricted to the role(s) '%s'.";
    /** Error-Message that the postal service parameter is invalid. */
    public static final String POSTAL_SERVICE_PARAMETER_INVALID = "only one of '%s' is allowed, but got '%s'.";
    /** Error-Message if a user does not exist. Format with username */
    public static final String USER_NOT_EXISTS = "a user with username '%s' does not exist.";
    /** Error-Message if a username is not a customer. Format with username */
    public static final String USERNAME_NOT_CUSTOMER = "the user '%s' is not a customer.";
    /** Error-Message if a parameter is missing. Format with parameter name or description */
    public static final String PARAMETER_MISSING = "the parameter '%s' is expected but missing.";
    /** Error-Message if the mailbox was empty */
    public static final String MAILBOX_EMPTY = "the mailbox was empty";

    /* private utility-class constructor */
    private Errors() { }
}
