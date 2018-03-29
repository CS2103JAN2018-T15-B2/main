package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.LookDateCommand;
import seedu.address.model.calendar.AppointmentEntry;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Expenditure;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 * {@code ParserUtil} contains methods that take in {@code Optional} as parameters. However, it goes against Java's
 * convention (see https://stackoverflow.com/a/39005452) as {@code Optional} should only be used a return type.
 * Justification: The methods in concern receive {@code Optional} return values from other methods as parameters and
 * return {@code Optional} values based on whether the parameters were present. Therefore, it is redundant to unwrap the
 * initial {@code Optional} before passing to {@code ParserUtil} as a parameter and then re-wrap it into an
 * {@code Optional} return value inside {@code ParserUtil} methods.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INSUFFICIENT_PARTS = "Number of parts must be more than 1.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws IllegalValueException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws IllegalValueException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new IllegalValueException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String input} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    static String parseString(String input) {
        requireNonNull(input);
        String trimmedInput = input.trim();

        return trimmedInput;
    }

    /**
     * Parses a {@code Optional<String> input} into an {@code Optional<String>} if {@code input} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<String> parseString(Optional<String> input) throws IllegalValueException {
        requireNonNull(input);
        return input.isPresent() ? Optional.of(parseString(input.get())) : Optional.empty();
    }


    /**
     * Parses a {@code String input} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code name} is invalid.
     */
    static LocalDateTime parseDateTime(String input) throws  IllegalValueException {
        requireNonNull(input);
        String trimmedInput = input.trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppointmentEntry.DATE_VALIDATION);

        try {

            LocalDateTime dateTime = LocalDateTime.parse(trimmedInput, formatter);
            return dateTime;

        } catch (DateTimeParseException e) {
            throw new IllegalValueException(AppointmentEntry.MESSAGE_DATE_TIME_CONSTRAINTS);
        }



    }

    /**
     * Parses a {@code Optional<String> input} into an {@code Optional<LocalDateTime>} if {@code input} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<LocalDateTime> parseDateTime(Optional<String> input) throws IllegalValueException {
        requireNonNull(input);
        return input.isPresent() ? Optional.of(parseDateTime(input.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String input} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code name} is invalid.
     */
    static LocalDate parseDate(String input) throws  IllegalValueException {
        requireNonNull(input);
        String trimmedInput = input.trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LookDateCommand.DATE_VALIDATION);

        try {

            LocalDate date = LocalDate.parse(trimmedInput, formatter);
            return date;

        } catch (DateTimeParseException e) {
            throw new IllegalValueException(LookDateCommand.MESSAGE_DATE_CONSTRAINTS);
        }



    }

    /**
     * Parses a {@code Optional<String> input} into an {@code Optional<LocalDate>} if {@code input} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<LocalDate> parseDate(Optional<String> input) throws IllegalValueException {
        requireNonNull(input);
        return input.isPresent() ? Optional.of(parseDate(input.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String name} into an {@code Name}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws IllegalValueException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new IllegalValueException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code Optional<String> name} into an {@code Optional<Name>} if {@code name} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Name> parseName(Optional<String> name) throws IllegalValueException {
        requireNonNull(name);
        return name.isPresent() ? Optional.of(parseName(name.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws IllegalValueException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_PHONE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code Optional<String> phone} into an {@code Optional<Phone>} if {@code phone} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Phone> parsePhone(Optional<String> phone) throws IllegalValueException {
        requireNonNull(phone);
        return phone.isPresent() ? Optional.of(parsePhone(phone.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws IllegalValueException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValid(trimmedAddress)) {
            throw new IllegalValueException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code Optional<String> address} into an {@code Optional<Address>} if {@code address} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Address> parseAddress(Optional<String> address) throws IllegalValueException {
        requireNonNull(address);
        return address.isPresent() ? Optional.of(parseAddress(address.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws IllegalValueException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new IllegalValueException(Email.MESSAGE_EMAIL_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code Optional<String> email} into an {@code Optional<Email>} if {@code email} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Email> parseEmail(Optional<String> email) throws IllegalValueException {
        requireNonNull(email);
        return email.isPresent() ? Optional.of(parseEmail(email.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String value} into an {@code value}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code value} is invalid.
     */
    public static Income parseIncome(String income) throws IllegalValueException {
        requireNonNull(income);
        Double trimmedIncome = Double.parseDouble(income.trim());
        if (!Income.isValid(trimmedIncome)) {
            throw new IllegalValueException(Income.MESSAGE_INCOME_CONSTRAINTS);
        }
        return new Income(trimmedIncome);
    }

    /**
     * Parses a {@code Optional<String> value} into an {@code Optional<value>} if {@code value} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Income> parseIncome(Optional<String> income) throws IllegalValueException {
        requireNonNull(income);
        return income.isPresent() ? Optional.of(parseIncome(income.get())) : Optional.empty();
    }


    /**
     * Parses a {@code String value} into an {@code value}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code value} is invalid.
     */
    public static Expenditure parseActualSpending(String actualSpending) throws IllegalValueException {
        requireNonNull(actualSpending);
        Double trimmedActualSpending = Double.parseDouble(actualSpending.trim());
        if (!Expenditure.isValid(trimmedActualSpending)) {
            throw new IllegalValueException(Expenditure.MESSAGE_EXPENDITURE_CONSTRAINTS);
        }
        return new Expenditure(trimmedActualSpending);
    }


    /**
     * Parses a {@code Optional<String> value} into an {@code Optional<value>} if {@code value} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Expenditure> parseActualSpending(Optional<String> actualSpending)
            throws IllegalValueException {
        requireNonNull(actualSpending);
        return actualSpending.isPresent()
                ? Optional.of(parseActualSpending(actualSpending.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String value} into an {@code value}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code value} is invalid.
     */
    public static Expenditure parseExpectedSpending(String expectedSpending) throws IllegalValueException {
        requireNonNull(expectedSpending);
        Double trimmedExpectedSpending = Double.parseDouble(expectedSpending.trim());
        if (!Expenditure.isValid(trimmedExpectedSpending)) {
            throw new IllegalValueException(Expenditure.MESSAGE_EXPENDITURE_CONSTRAINTS);
        }
        return new Expenditure(trimmedExpectedSpending);
    }


    /**
     * Parses a {@code Optional<String> value} into an {@code Optional<value>} if {@code value} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Expenditure> parseExpectedSpending(Optional<String> expectedSpending)
            throws IllegalValueException {
        requireNonNull(expectedSpending);
        return expectedSpending.isPresent()
                ? Optional.of(parseExpectedSpending(expectedSpending.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String value} into an {@code value}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code value} is invalid.
     */
    public static Age parseAge(String age) throws IllegalValueException {
        requireNonNull(age);
        Integer trimmedAge = Integer.parseInt(age.trim());
        if (!Age.isValidAge(trimmedAge)) {
            throw new IllegalValueException(Age.AGE_CONSTRAINTS);
        }
        return new Age(trimmedAge);
    }

    /**
     * Parses a {@code Optional<String> value} into an {@code Optional<value>} if {@code value} is present.
     * See header comment of this class regarding the use of {@code Optional} parameters.
     */
    public static Optional<Age> parseAge(Optional<String> age) throws IllegalValueException {
        requireNonNull(age);
        return age.isPresent() ? Optional.of(parseAge(age.get())) : Optional.empty();
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalValueException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws IllegalValueException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new IllegalValueException(Tag.MESSAGE_TAG_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws IllegalValueException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
