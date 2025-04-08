package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.ProfileContainsKeywordsPredicate;


/**
 * Changes the remark of an existing person in the address book.
 */


public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the note of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing note will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "Likes to swim.";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Person: ";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Person: ";

    private final Index index;
    private ProfileContainsKeywordsPredicate predicate;
    private final Note note;
    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param note of the person to be updated to
     */
    public NoteCommand(Index index, Note note) {
        requireAllNonNull(index, note);
        this.index = index;
        this.note = note;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getNric(),
                personToEdit.getGender(), personToEdit.getDob(),
                personToEdit.getDateOfJoining(),
                personToEdit.getNationality(), personToEdit.getAddress(),
                note, personToEdit.getTag());

        this.predicate = new ProfileContainsKeywordsPredicate(Arrays.asList(editedPerson.getName().toString()));
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(predicate);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the note is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        if (note.equals(new Note(" "))
                && !(personToEdit.getNote().value.isEmpty())) {
            return MESSAGE_DELETE_NOTE_SUCCESS + " Name: " + personToEdit.getName().toString()
                + "  Nric: " + personToEdit.getNric().toString();
        } else {
            return MESSAGE_ADD_NOTE_SUCCESS + " Name: " + personToEdit.getName().toString()
                + "  Nric: " + personToEdit.getNric().toString() + "  Note: " + note.toString();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }

        NoteCommand n = (NoteCommand) other;
        return index.equals(n.index)
                && note.equals(n.note);
    }
}
