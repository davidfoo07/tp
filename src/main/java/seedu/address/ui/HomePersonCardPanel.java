package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing list of persons in home page.
 */
public class HomePersonCardPanel extends UiPart<Region> {
    private static final String FXML = "HomePersonCardPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(HomePersonCardPanel.class);

    @FXML
    private ListView<Person> homePersonListView;

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code HomePersonCard}.
     */
    public HomePersonCardPanel(ObservableList<Person> homePersonList) {
        super(FXML);
        homePersonListView.setItems(homePersonList);
        homePersonListView.setCellFactory(listView -> new PersonListViewCell());
    }

    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HomePersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
