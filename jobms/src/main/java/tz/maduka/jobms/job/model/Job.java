package tz.maduka.jobms.job.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
//@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;

    /*
    @NoArgsConstructor
    This is needed by JPA to initialize instance of an object when retrieving data
    It is recommended then after you can have more other constructors after this.
    */
    public Job(){

    }
    public Job(Long id, String title, String description, String minSalary, String maxSalary, String location, Long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", minSalary='" + minSalary + '\'' +
                ", maxSalary='" + maxSalary + '\'' +
                ", location='" + location + '\'' +
                ", companyId='" + companyId + '\'' +
                '}';
    }
}
