import java.util.OptionalInt;

public class PersonBuilder {
    private String name;
    private String surname;
    private OptionalInt age;
    private String address;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException("Вы пытаетесь установить неверное значение возраста. Возраст не может быть меньше 0");
        } else {
            this.age = OptionalInt.of(age);
        }
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() throws IllegalStateException {
        Person person;
        if (name == null || surname == null) {
            throw new IllegalStateException("Создание Person невозможно. Обязательно установите name и surname");
        } else if (age.isPresent()) {
            person = new Person(name, surname, age.getAsInt());
        } else {
            person = new Person(name, surname);
        }
        if (address != null) {
            person.setAddress(address);
        }
        return person;
    }
}