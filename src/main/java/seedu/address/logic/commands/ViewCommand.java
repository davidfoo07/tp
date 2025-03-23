package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ProfileContainsKeywordsPredicate;

/**
 * View the person's profile
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": views the profile "
            + "by the name "
            + "Existing profile will be shown.\n"
            + "Parameters: name  "
            + "r/ [VIEW]\n"
            + "Example: " + COMMAND_WORD + "Alex";

    private final ProfileContainsKeywordsPredicate predicate;

    public ViewCommand(ProfileContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROFILE_FOUND, model.getFilteredPersonList().stream()
                        .map(person -> person.getName().fullName)
                        .reduce((name1, name2) -> name1 + ", " + name2)
                        .orElse("No matching profile found")));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return predicate.equals(otherViewCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
