package itgo.it_secondhand.repository;

import itgo.it_secondhand.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
