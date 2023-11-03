package lk.ijse.TravelPackageService.repo;

import lk.ijse.TravelPackageService.dto.TravelPackageDTO;
import lk.ijse.TravelPackageService.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TravelPackageRepo extends JpaRepository<TravelPackage,String> {

    @Query(value = "SELECT t FROM TravelPackage t WHERE t.travelPackage_Id =:travelPackage_Id")
    Optional<TravelPackageDTO>findByPackageID(String travelPackage_Id);

    @Query(value = "select * from TravelPackage order by travelPackage_Id desc limit 1", nativeQuery = true)
    Optional<TravelPackage>getLastId();

    @Query(value = "select t from TravelPackage t where t.travelPackage_PacedDate like %?1%")
    List<TravelPackage> getPackageDetailsByDate(String date);
}
