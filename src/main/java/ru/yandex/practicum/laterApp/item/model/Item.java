package ru.yandex.practicum.laterApp.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.laterApp.user.model.User;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "url")
    private String url;

    @Column(name = "resolved_url")
    private String resolvedUrl;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "title")
    private String title;

    @Column(name = "has_image")
    private boolean hasImage;

    @Column(name = "has_video")
    private boolean hasVideo;

    @Column(name = "date_resolved")
    private Instant dateResolved;

    @Column(name = "unread")
    private boolean unread;

    @ElementCollection
    @CollectionTable(name="tags", joinColumns=@JoinColumn(name="item_id"))
    @Column(name="name")
    private Set<String> tags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        return id != null && id.equals(((Item) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
