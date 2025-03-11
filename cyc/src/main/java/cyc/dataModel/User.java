package cyc.dataModel;


import cyc.dtoModel.Address;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collation = "user")
public class User extends AuditEntity{

    private static final long serialVersionUID = 1L;
    @Indexed
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
