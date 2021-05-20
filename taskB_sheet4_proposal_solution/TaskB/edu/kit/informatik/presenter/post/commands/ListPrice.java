/*
 * Copyright (c) 2020, IPD Koziolek. All rights reserved.
 */

package edu.kit.informatik.presenter.post.commands;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import edu.kit.informatik.core.LexicographicComparator;
import edu.kit.informatik.model.authentication.roles.Customer;
import edu.kit.informatik.model.authentication.roles.Role;
import edu.kit.informatik.model.post.PostSystem;
import edu.kit.informatik.model.post.PostSystemException;
import edu.kit.informatik.model.post.PostalService;
import edu.kit.informatik.model.resources.Messages;
import edu.kit.informatik.presenter.RestrictedCommand;
import edu.kit.informatik.view.command.Result;
import edu.kit.informatik.view.parameter.AuthenticationParameter;
import edu.kit.informatik.view.parameter.Parameter;
import edu.kit.informatik.view.parameter.ParameterBundle;

/**
 * This class describes a command to list prices of sent postal services.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
public class ListPrice extends RestrictedCommand {

    private static final String REGEX_LIST_PRICE = "list-price";
    private static final String OUTPUT_SEPARATOR = ";";

    private final Parameter<String> username = new AuthenticationParameter().setOptional(true);
    private final PostSystem postSystem;

    /**
     * Constructs a new instance.
     *
     * @param postSystem the post system
     */
    public ListPrice(final PostSystem postSystem) {
        super(postSystem);
        this.postSystem = postSystem;
    }

    @Override
    protected Result execute(final ParameterBundle bundle) throws PostSystemException {
        String username;
        if (this.postSystem.getAccessControl().getLoggedInUser() instanceof Customer) {
            username = this.postSystem.getAccessControl().getLoggedInUser().getIdentification();
        } else {
            username = bundle.get(this.username);
        }

        final Customer customer = this.postSystem.getCustomer(username);
        final Map<PostalService, Integer> mail = customer.getSentMail();

        if (mail.isEmpty()) {
            return new Result(Result.ResultType.SUCCESS, Messages.SUCCESS);
        } else {
            final SortedSet<PostalService> services = new TreeSet<>(new LexicographicComparator<>());
            services.addAll(List.of(PostalService.values()));
            final StringBuilder builder = new StringBuilder();

            for (PostalService service : services) {
                if (!mail.containsKey(service)) {
                    continue;
                }
                builder.append(service)
                    .append(OUTPUT_SEPARATOR)
                    .append(mail.get(service))
                    .append(OUTPUT_SEPARATOR)
                    .append(String.format("%.02f", mail.get(service) * service.getPrice()))
                    .append(System.lineSeparator());
            }
            return new Result(Result.ResultType.SUCCESS, builder.toString().trim());
        }
    }

    @Override
    protected List<Role> getAllowedRoles() {
        return List.of(Role.CUSTOMER, Role.AGENT, Role.MAILMAN);
    }

    @Override
    protected Map<Role, List<Parameter<?>>> parameterByRole() {
        return Map.of(
                Role.CUSTOMER, List.of(),
                Role.MAILMAN, List.of(this.username),
                Role.AGENT, List.of(this.username)
            );
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of(this.username);
    }

    @Override
    public String getRegex() {
        return REGEX_LIST_PRICE;
    }

}