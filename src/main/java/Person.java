import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected OptionalInt age;
    protected String address;


    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = OptionalInt.of(age);
    }

    public boolean hasAge() {
        return age.isPresent();
    }

    public boolean hasAddress() {
        return address != null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() throws NoSuchElementException {
        return age.getAsInt();
    }

    public void setAge(int personAge) throws IllegalStateException, IllegalArgumentException {
        if (personAge < 0) {
            throw new IllegalArgumentException("Вы пытаетесь установить неверное значение возраста. Возраст не может быть меньше 0.");
        } else if (hasAge()) {
            throw new IllegalStateException("Возраст уже установлен. Вы не можете изменить возраст");
        } else {
            age = OptionalInt.of(personAge);
        }
    }

    public String getAddress() {
        return hasAddress() ? address : "No address";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //прибавляет возраст на 1 год
    public void happyBirthday() {
        if (hasAge()) {
            age = OptionalInt.of(getAge() + 1);
        } else {
            throw new IllegalStateException("Возраст не установлен. Воспользуйтесь методом setAge()");
        }
    }

    @Override
    public String toString() {
        StringBuilder person = new StringBuilder();
        person.append(name + " " + surname);
        if (hasAge()) {
            person.append(" (" + age.getAsInt() + ")");
        }
        if (hasAddress()) {
            person.append(" (address: " + address + ")");
        }
        return person.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder personBuilder = new PersonBuilder();
        personBuilder.setSurname(getSurname());
        personBuilder.setAge(0);
        if (hasAddress()) {
            personBuilder.setAddress(getAddress());
        }
        return personBuilder;
    }
}