package itgo.it_secondhand.repository;

import itgo.it_secondhand.domain.DeviceInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceInfoRepository extends MongoRepository<DeviceInfo, String> {
}
