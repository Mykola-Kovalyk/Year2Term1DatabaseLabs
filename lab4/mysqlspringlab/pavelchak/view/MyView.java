package com.pavelchak.view;

import com.pavelchak.controller.BookController;
import com.pavelchak.controller.CityController;
import com.pavelchak.controller.PersonController;
import com.pavelchak.domain.Book;
import com.pavelchak.domain.City;
import com.pavelchak.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {

    @Autowired
    private BookController bookController;
    @Autowired
    private CityController cityController;
    @Autowired
    private PersonController personController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Book nullBook = new Book(null, null, null, null);
    private final Person nullPerson = new Person(null, null, null, null, null);
    private final City nullCity = new City(null);

    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: Book");
        menu.put("11", "  11 - Create Book");
        menu.put("12", "  12 - Update Book");
        menu.put("13", "  13 - Delete from Book");
        menu.put("14", "  14 - Find all Books");
        menu.put("15", "  15 - Find Book by ID");
        menu.put("16", "  16 - Find Book by book_name");
        menu.put("17", "  17 - Find Book by author");

        menu.put("2", "   2 - Table: City");
        menu.put("21", "  21 - Create City");
        menu.put("22", "  22 - Update City");
        menu.put("23", "  23 - Delete from City");
        menu.put("24", "  24 - Find all Cities");
        menu.put("25", "  25 - Find City by ID");

        menu.put("3", "   3 - Table: Person");
        menu.put("31", "  31 - Create Person");
        menu.put("32", "  32 - Update Person");
        menu.put("33", "  33 - Delete from Person");
        menu.put("34", "  34 - Find all Persons");
        menu.put("35", "  35 - Find Person by ID");
        menu.put("36", "  36 - Find all Books by Person ID");
        menu.put("37", "  37 - addBookByNameToPersonBySurname");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createBook);
        methodsMenu.put("12", this::updateBook);
        methodsMenu.put("13", this::deleteFromBook);
        methodsMenu.put("14", this::findAllBooks);
        methodsMenu.put("15", this::findBookById);
        methodsMenu.put("16", this::findBookByBookName);
        methodsMenu.put("17", this::findBookByAuthor);

        methodsMenu.put("21", this::createCity);
        methodsMenu.put("22", this::updateCity);
        methodsMenu.put("23", this::deleteFromCity);
        methodsMenu.put("24", this::findAllCities);
        methodsMenu.put("25", this::findCityById);

        methodsMenu.put("31", this::createPerson);
        methodsMenu.put("32", this::updatePerson);
        methodsMenu.put("33", this::deleteFromPerson);
        methodsMenu.put("34", this::findAllPersons);
        methodsMenu.put("35", this::findPersonById);
        methodsMenu.put("36", this::findAllBooksById);
        methodsMenu.put("37", this::addBookByNameToPersonBySurname);
    }

    private void selectAllTable() {
        findAllBooks();
        findAllCities();
        findAllPersons();
    }

    // region BOOK ---------------------------------------------------
    private void createBook() {
        System.out.println("Input 'book_name': ");
        String bookName = input.nextLine();
        System.out.println("Input 'author': ");
        String author = input.nextLine();
        System.out.println("Input 'amount': ");
        Integer amount = Integer.valueOf((input.nextLine()));
        Book book = new Book(null, bookName, author, amount);

        int count = bookController.create(book);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateBook() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'book_name': ");
        String bookName = input.nextLine();
        System.out.println("Input new 'author': ");
        String author = input.nextLine();
        System.out.println("Input new 'amount': ");
        Integer amount = Integer.valueOf((input.nextLine()));
        Book book = new Book(null, bookName, author, amount);

        int count = bookController.update(id, book);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromBook() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = bookController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllBooks() {
        System.out.println("\nTable: BOOK");
        List<Book> books = bookController.findAll();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void findBookById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Book> book = bookController.findById(id);
        System.out.println(book.orElse(nullBook));
    }

    private void findBookByBookName() {
        System.out.println("Input 'book_name': ");
        String bookName = input.nextLine();

        Optional<Book> book = bookController.findByBookName(bookName);
        System.out.println(book.orElse(nullBook));
    }

    private void findBookByAuthor() {
        System.out.println("Input 'author': ");
        String author = input.nextLine();

        Optional<Book> book = bookController.findByBookName(author);
        System.out.println(book.orElse(nullBook));
    }

    //endregion
    // region CITY ---------------------------------------------------
    private void createCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        City city = new City(cityName);

        int count = cityController.create(city);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();
        System.out.println("Input new 'city_name': ");
        String newCityName = input.nextLine();

        City city = new City(newCityName);

        int count = cityController.update(cityName, city);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCity() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        int count = cityController.delete(cityName);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCities() {
        System.out.println("\nTable: CITY");
        List<City> cities = cityController.findAll();
        for (City city : cities) {
            System.out.println(city);
        }
    }

    private void findCityById() {
        System.out.println("Input 'city_name': ");
        String cityName = input.nextLine();

        Optional<City> city = cityController.findById(cityName);
        System.out.println(city.orElse(nullCity));
    }

    // endregion
    // region PERSON -------------------------------------------------
    private void createPerson() {
        System.out.println("Input 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'city': ");
        String city = input.nextLine();
        System.out.println("Input 'email': ");
        String email = input.nextLine();

        Person person = new Person(null, surname, name, city, email);

        int count = personController.create(person);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updatePerson() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'city': ");
        String city = input.nextLine();
        System.out.println("Input new 'email': ");
        String email = input.nextLine();

        Person person = new Person(null, surname, name, city, email);

        int count = personController.update(id, person);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromPerson() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = personController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllPersons() {
        System.out.println("\nTable: PERSON");
        List<Person> people = personController.findAll();
        for (Person person : people) {
            System.out.println(person);
        }
    }

    private void findPersonById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Person> person = personController.findById(id);
        System.out.println(person.orElse(nullPerson));
    }

    private void findAllBooksById() {
        System.out.println("Input 'person id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        List<Book> books = personController.findAllBooksBy(id);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void addBookByNameToPersonBySurname() {
        System.out.println("Input 'person surname': ");
        String surname = input.nextLine();
        System.out.println("Input 'book name': ");
        String bookName = input.nextLine();

        String msg = personController.addBookByNameToPersonBySurname(bookName, surname);
        System.out.println(msg);
    }
    //endregion

    //-------------------------------------------------------------------------
    // region output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }

    //endregion
}

