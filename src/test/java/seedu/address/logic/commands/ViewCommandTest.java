package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.ProfileContainsKeywordsPredicate;

public class ViewCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void toStringMethod() {
        ProfileContainsKeywordsPredicate predicate = new ProfileContainsKeywordsPredicate(Arrays.asList("keyword"));
        ViewCommand findCommand = new ViewCommand(predicate);
        String expected = ViewCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_personFound_success() {
        // Keyword matches the person's name "Alex"
        ProfileContainsKeywordsPredicate predicate = new ProfileContainsKeywordsPredicate(List.of("Alex"));
        ViewCommand viewCommand = new ViewCommand(predicate);

        // Update model expected state
        model.updateFilteredPersonList(predicate);
        // Execute command
        CommandResult result = viewCommand.execute(model);

        String expectedMessage = String.format(
                "%s's profile:",
                model.getFilteredPersonList().stream()
                        .map(person -> person.getName().fullName)
                        .reduce((name1, name2) -> name1 + ", " + name2)
                        .orElse("No matching profile found")
        );

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_personNotFound_returnsNoMatch() {
        // Non-matching keyword
        ProfileContainsKeywordsPredicate predicate = new ProfileContainsKeywordsPredicate(List.of("Bob"));
        ViewCommand viewCommand = new ViewCommand(predicate);

        CommandResult result = viewCommand.execute(model);

        String expectedMessage = String.format(
                "%s's profile:",
                "No matching profile found"
        );

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }
}
