package lk.ijse.TravelPackageService.service;

import lk.ijse.TravelPackageService.dto.TravelPackageDTO;
import lk.ijse.TravelPackageService.entity.TravelPackage;

import java.util.List;

public interface TravelPackageService {

    public  void  saveTravelPackage(TravelPackageDTO travelPackageDTO);

    void  updateTravelPackage(TravelPackage travelPackageDTO);

    List <TravelPackageDTO> getAllDetails();

    TravelPackage findByPackageId(String travelPackage_Id);

    void deletePackage(String travelPackage_Id);

    String getNextPkgId();

    List <TravelPackageDTO>getDetails(String date);


}
