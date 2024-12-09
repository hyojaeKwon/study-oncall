package oncall.domain.cycle;

public class Person {
    private final String name;

    private Person(String name) {
        this.name = name;
    }

    public static Person create(String name) {
        validation(name);
        return new Person(name);
    }

    private static void validation(String name) {
        if (name.isEmpty() || name.length() > 5) {
            throw new IllegalArgumentException("이름은 5자 이하여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        Person person = (Person) obj;
        return person.getName().equals(name);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
