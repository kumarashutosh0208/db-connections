package cyc.dataModel;

import cyc.dtoModel.Address;
import cyc.dtoModel.BankAccDetails;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


@Document(collection = "user")
public class User {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String mobileNumber;
    private Address address;
    private String dob;
    private List<BankAccDetails> bankAcctDetails;
    private String status;
}
