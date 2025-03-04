package tz.maduka.companyms.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// MESSAGE FORMAT
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMessage {

    private Long id;
    private String title;
    private String description;
    private Double rating;
    private Long companyId;

}
