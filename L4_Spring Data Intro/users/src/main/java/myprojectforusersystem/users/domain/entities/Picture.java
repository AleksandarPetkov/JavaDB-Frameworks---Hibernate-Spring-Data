package myprojectforusersystem.users.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String caption;

    @Column
    private String path;

    @ManyToMany(mappedBy = "pictures", targetEntity = Album.class)
    private Set<Album> albums;

    public Picture() {
        this.albums = new HashSet<>();
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
