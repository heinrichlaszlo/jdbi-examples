package user;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            UserDao felhaszdao = handle.attach(UserDao.class);
            felhaszdao.createTable();

            User user1 = User.builder()
                    .username("James Bond")
                    .password("Bond James")
                    .name("Bond")
                    .email("james@bond.com")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("1920-11-11"))
                    .enabled(true)
                    .build();

            User user2 = User.builder()
                    .username("Harry Potter")
                    .password("Harry")
                    .name("Poti")
                    .email("harry@potter.com")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("1920-11-11"))
                    .enabled(false)
                    .build();

            User user3 = User.builder()
                    .username("Zsakos Frodo")
                    .password("Frodi")
                    .name("Zsaki")
                    .email("zsakos@poti.com")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("1920-11-11"))
                    .enabled(true)
                    .build();

            felhaszdao.insert(user1);
            felhaszdao.insert(user2);
            felhaszdao.insert(user3);

            felhaszdao.list().stream().forEach(System.out::println);

            felhaszdao.findById(2).stream().forEach(System.out::println);

           felhaszdao.findByUsername("Zsakos Frodo").stream().forEach(System.out::println);

            felhaszdao.delete(user1);
            felhaszdao.list().stream().forEach(System.out::println);
}

    }
}
