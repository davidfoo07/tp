package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person} in the Home Screen.
 */
public class HomePersonCard extends UiPart<Region> {

    private static final String FXML = "HomePersonCard.fxml";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label nric;
    @FXML
    private HBox tagBox;
    @FXML
    private FlowPane department;
    @FXML
    private FlowPane employmentType;
    @FXML
    private FlowPane jobTitle;

    /**
     * Creates a {@code HomePersonCard} with the given {@code Person} and index to display.
     */
    public HomePersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        nric.setText(person.getNric().value);

        Tag tag = person.getTag();

        if (tag != null) {
            String[] tags = tag.getValue();
            tagBox.getChildren().clear();
            department.getChildren().clear();
            department.getChildren().add(new Label("Department: " + tags[0]));
            employmentType.getChildren().clear();
            employmentType.getChildren().add(new Label("Employment Type: " + tags[1]));
            jobTitle.getChildren().clear();
            jobTitle.getChildren().add(new Label("Job Title: " + tags[2]));
            tagBox.getChildren().addAll(department, employmentType, jobTitle);
        }
    }

    public FlowPane getDepartment() {
        return department;
    }

    public FlowPane getEmploymentType() {
        return employmentType;
    }

    public FlowPane getJobTitle() {
        return jobTitle;
    }

}
