package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ViewCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.ProfileContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class ViewCommandTest {

    private Model model;

    private final ViewCommandParser parser = new ViewCommandParser();

    @BeforeEach
    public void setUp() {
        AddressBook ab = new AddressBook();
        ab.setPersons(TypicalPersons.getTypicalPersons());
        Person davidLi = new PersonBuilder().withName("David Li").withNric("S0101010A").build();
        ab.addPerson(davidLi);
        model = new ModelManager(ab, new UserPrefs());
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
        ProfileContainsKeywordsPredicate firstPredicate =
                new ProfileContainsKeywordsPredicate(Collections.singletonList("first"));
        ProfileContainsKeywordsPredicate secondPredicate =
                new ProfileContainsKeywordsPredicate(Collections.singletonList("second"));

        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void execute_viewFullName_success() {
        // Prepare predicate and command
        ProfileContainsKeywordsPredicate predicate = new ProfileContainsKeywordsPredicate(List.of("David", "Li"));
        ViewCommand viewCommand = new ViewCommand(predicate);

        // Update model expected state
        model.updateFilteredPersonList(predicate);

        // Execute command and assert result
        CommandResult result = viewCommand.execute(model);
        assertEquals("Profile found: David Li", result.getFeedbackToUser());
    }

    @Test
    public void execute_viewSurname_success() {
        ProfileContainsKeywordsPredicate predicate = new ProfileContainsKeywordsPredicate(List.of("Li"));
        ViewCommand viewCommand = new ViewCommand(predicate);
        model.updateFilteredPersonList(predicate);

        CommandResult result = viewCommand.execute(model);
        assertEquals("Profile found: David Li", result.getFeedbackToUser());
    }

    @Test
    public void execute_personNotFound_returnsNoMatch() {
        // Non-matching keyword
        ProfileContainsKeywordsPredicate predicate = new ProfileContainsKeywordsPredicate(List.of("Bob"));
        ViewCommand viewCommand = new ViewCommand(predicate);

        CommandResult result = viewCommand.execute(model);

        String expectedMessage = String.format(
                "%s",
                "No matching profile found!!"
        );

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void parse_invalidSymbols_throwsParseException() {
        ParseException thrown = assertThrows(ParseException.class, () ->
                parser.parse("@lex"));
        assert(thrown.getMessage().equals("Names should only contain alphabetical characters and spaces."));
    }
}


