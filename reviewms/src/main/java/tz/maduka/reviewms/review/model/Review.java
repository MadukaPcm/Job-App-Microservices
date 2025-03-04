package tz.maduka.reviewms.review.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double rating;
    private Long companyId;

    public Review() {}

    public Review(Long id, String title, String description, Double rating, Long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", companyId=" + companyId +
                '}';
    }
}
