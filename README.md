# Book and Author Management System

A Spring Boot application that manages books and authors with basic CRUD operations.

## Entity Relationship Design

### Entities
help 
1. **Author**
   - Properties:
     - id (Long): Primary key
     - name (String): Author's name
     - email (String): Author's email
     - biography (String): Author's biography
     - books (List<Book>): One-to-Many relationship with Book

2. **Book**
   - Properties:
     - id (Long): Primary key
     - title (String): Book title
     - isbn (String): ISBN number
     - description (String): Book description
     - price (BigDecimal): Book price
     - publicationDate (LocalDate): Publication date
     - author (Author): Many-to-One relationship with Author

### Relationship
- One Author can have multiple Books (One-to-Many)
- Each Book must have one Author (Many-to-One)
- Relationship is bidirectional

## Implementation Details

### 1. Entity Classes
```java
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
    // ... other fields and methods
}

@Entity
@Table(name = "books")
public class Book {
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    // ... other fields and methods
}
```

### 2. Repository Layer
```java
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a JOIN FETCH a.books")
    List<Author> findAllWithBooks();
}

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllWithAuthors();
}
```

### 3. Service Layer
```java
@Service
@Transactional
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    // ... other methods
}
```

### 4. Controller Layer
```java
@Controller
@RequestMapping("/authors")
public class AuthorController {
    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthorsWithBooks());
        return "author/list";
    }
    // ... other methods
}
```

### 5. View Layer (JSP)
```jsp
<form action="${author.id == null ? '/authors' : '/authors/update/' + author.id}" method="post">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" name="name" value="${author.name}" required>
    </div>
    // ... other form fields
</form>
```

## Challenges Faced and Solutions

1. **Lombok Integration Issues**
   - Challenge: Lombok annotations were not being processed correctly during compilation
   - Solution: Removed Lombok dependency and manually implemented getters/setters for better control and transparency

2. **Bidirectional Relationship Management**
   - Challenge: Potential infinite recursion in JSON serialization due to bidirectional relationships
   - Solution: Implemented proper entity relationships with JPA annotations and careful cascade type selection

3. **Data Initialization**
   - Challenge: Needed to populate initial data for testing
   - Solution: Created a DataInitializer component using CommandLineRunner to automatically populate sample data on startup

4. **View Layer Integration**
   - Challenge: Integrating JSP views with Spring Boot
   - Solution: Added proper dependencies and configurations in pom.xml and application.properties

## Technical Stack

- Java 11
- Spring Boot 2.7.0
- Spring Data JPA
- H2 Database
- JSP & JSTL
- Bootstrap 5.1.3
- Maven

## Running the Application

1. Ensure you have Java 11 and Maven installed
2. Clone the repository
3. Run `mvn spring-boot:run`
4. Access the application at `http://localhost:8080`
5. H2 Console available at `http://localhost:8080/h2-console`

## Database Configuration

```properties
spring.datasource.url=jdbc:h2:mem:bookauthordb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
```
