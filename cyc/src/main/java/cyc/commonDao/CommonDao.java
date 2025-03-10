package cyc.commonDao;

import cyc.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public <T> void save(T entityToSave) {
        if (entityToSave != null) {
            mongoTemplate.save(entityToSave);
        }
    }
}
