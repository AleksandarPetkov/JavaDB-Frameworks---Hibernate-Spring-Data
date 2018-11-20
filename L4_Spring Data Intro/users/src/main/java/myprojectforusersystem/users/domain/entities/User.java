package myprojectforusersystem.users.domain.entities;

import myprojectforusersystem.users.domain.annotations.Password;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Length(min = 4, max = 30)
    private String username;

    @Column(name = "password", nullable = false)
    @Password(
            minLength = 5,
            maxLength = 40,
            containsDigit = true,
            containsUpperCase = true
    )
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9]+@[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9](\\.[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9]+)+$"
            , message = "Please provide a valid email address")
    private String email;

    @Column(name = "profile_picture", columnDefinition = "LONGBLOB")
    private String profilePicture;

    @Column(name = "registered_on", columnDefinition = "DATETIME")
    private LocalDate registeredOn;

    @Column(name = "last_time_logged_in", columnDefinition = "DATETIME")
    private LocalDate lastTimeLogged;

    @Column
    @Length(min = 1, max = 120)
    private Integer age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "town_of_birth_id", referencedColumnName = "id")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "town_of_residence_id", referencedColumnName = "id")
    private Town currentlyLivingTown;

    @OneToMany(mappedBy = "user", targetEntity = Album.class)
    private Set<Album> albums;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    public User() {
        this.friends = new HashSet<>();
        this.albums = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilPicture() {
        return profilePicture;
    }

    public void setProfilPicture(String profilPicture) {
        this.profilePicture = profilPicture;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDate getLastTimeLogged() {
        return lastTimeLogged;
    }

    public void setLastTimeLogged(LocalDate lastTimeLogged) {
        this.lastTimeLogged = lastTimeLogged;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentlyLivingTown() {
        return currentlyLivingTown;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public void setCurrentlyLivingTown(Town currentlyLivingTown) {
        this.currentlyLivingTown = currentlyLivingTown;
    }
}
