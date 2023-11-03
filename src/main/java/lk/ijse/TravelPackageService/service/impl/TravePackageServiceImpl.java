package lk.ijse.TravelPackageService.service.impl;
import lk.ijse.TravelPackageService.dto.TravelPackageDTO;
import lk.ijse.TravelPackageService.entity.TravelPackage;
import lk.ijse.TravelPackageService.repo.TravelPackageRepo;
import lk.ijse.TravelPackageService.service.TravelPackageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class TravePackageServiceImpl implements TravelPackageService {

    @Autowired
    TravelPackageRepo travelPackageRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void saveTravelPackage(TravelPackageDTO travelPackageDTO) {
        if(travelPackageRepo.existsById(travelPackageDTO.getTravelPackage_Id())){
            throw new RuntimeException(travelPackageDTO.getTravelPackage_Id()+"Already exists!");
        }
        TravelPackage travelPackage=modelMapper.map(travelPackageDTO,TravelPackage.class);
        travelPackageRepo.save(travelPackage);


    }

    @Override
    public void updateTravelPackage(TravelPackage travelPackageDTO) {

        Optional<TravelPackage>byID=travelPackageRepo.findById(travelPackageDTO.getTravelPackage_Id());
        if(byID.isEmpty()){
            throw new RuntimeException(travelPackageDTO.getTravelPackage_Id()+"Dosen't Exists");
        }
        TravelPackage travelPackage=modelMapper.map(travelPackageDTO,TravelPackage.class);
      travelPackageRepo.save(travelPackage);
    }

    @Override
    public List<TravelPackageDTO> getAllDetails() {
        return modelMapper.map(travelPackageRepo.findAll(),new TypeToken<ArrayList<TravelPackageDTO>>(){}.getType());
    }

    @Override
    public TravelPackage findByPackageId(String travelPackage_Id) {
        if(travelPackageRepo.existsById(travelPackage_Id)){
            throw  new RuntimeException("Travel Package ID :"+travelPackage_Id+"Not Available");
        }
        Optional<TravelPackage> byID=travelPackageRepo.findById(travelPackage_Id);
        return byID.get();
    }

    @Override
    public void deletePackage(String travelPackage_Id) {
            if(!travelPackageRepo.existsById(travelPackage_Id)){
                throw new RuntimeException("Travel Package ID :"+travelPackage_Id+"Not Available Deletd");
            }
                travelPackageRepo.deleteById(travelPackage_Id);
    }

    @Override
    public String getNextPkgId() {

      Optional<TravelPackage>isExistId=travelPackageRepo.getLastId();
      if (isExistId.isPresent()){
          String[] split=isExistId.get().getTravelPackage_Id().split("[-]");
          int lastDigit=Integer.parseInt(split[1]);
          lastDigit++;
          return String.format("PK-%08d",lastDigit);
      }else {
          return "PK-000000001";
      }
    }



    @Override
    public List<TravelPackageDTO> getDetails(String date) {
        List<TravelPackage>  list =travelPackageRepo.getPackageDetailsByDate(date);
        return modelMapper.map(list,new TypeToken<List<TravelPackageDTO>>(){}.getType());
    }
}
