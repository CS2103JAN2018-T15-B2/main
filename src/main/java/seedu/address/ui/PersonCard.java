package seedu.address.ui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.address.commons.events.ui.PersonCardDoubleClick;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    private final Integer index;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    //@@author SoilChang
    @FXML
    private Label income;
    //@@author
    @FXML
    private Label actualSpending;
    @FXML
    private Label isNewClient;
    @FXML
    private Label expectedSpending;
    @FXML
    private Label age;
    @FXML
    private Label policy;

    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        index = displayedIndex;
        registerAsAnEventHandler(this);
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        //@@author SoilChang
        income.setText("Income: " + person.getIncome().toString());
        age.setText("Age: " + person.getAge().toString() + " years old");
        email.setText(person.getEmail().value);
        actualSpending.setText("Actual Spending: " + person.getActualSpending().toString());
        expectedSpending.setText("Predicted Spending: " + person.getExpectedSpending().toString());
        isNewClient.setText("New Client");
        isNewClient.setTextFill(Color.GREEN);
        if (person.getPolicy().isPresent()) {
            policy.setText("Policy: " + person.getPolicy().get().toString());
        } else {
            policy.setText("Has not applied to any policy");
        }

        if (person.getActualSpending().value != 0.0) {
            // the client has actual income
            actualSpending.setVisible(true);
            isNewClient.setVisible(false);
        } else {
            actualSpending.setVisible(false);
            isNewClient.setVisible(true);
        }
        if (person.getExpectedSpending().value == 0.0) {
            expectedSpending.setVisible(false);
        } else {
            expectedSpending.setVisible(true);
        }


        //@@author
        person.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        setDoubleClickEvent();
    }

    //@@author jstarw
    private void setDoubleClickEvent() {
        cardPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        raise(new PersonCardDoubleClick(person, index));
                    }
                }
            }
        });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
