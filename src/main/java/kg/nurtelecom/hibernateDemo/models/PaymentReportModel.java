package kg.nurtelecom.hibernateDemo.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentReportModel {
    Long id;
    Long amount;
    String clientName;
}
