package lk.ijse.TravelPackageService.Embeded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class RoomCount {

    private int full_double_Opt_1;
    private int half_double_Opt_2;
    private int full_triple_Opt_3;
    private int half_triple_Opt_4;

}
