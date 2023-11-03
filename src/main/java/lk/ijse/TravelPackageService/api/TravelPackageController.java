package lk.ijse.TravelPackageService.api;


import lk.ijse.TravelPackageService.dto.TravelPackageDTO;
import lk.ijse.TravelPackageService.entity.TravelPackage;
import lk.ijse.TravelPackageService.service.TravelPackageService;
import lk.ijse.TravelPackageService.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/travel/package")
@CrossOrigin
public class TravelPackageController {

    @Autowired
    TravelPackageService travelPackageService;

    @PostMapping
    public ResponseUtil saveTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO){
//        TravelPackageDTO travelpackage=new TravelPackageDTO();
//        travelpackage.setTravelPackage_Id(travelPackageService.getNextPkgId());

         travelPackageService.saveTravelPackage(travelPackageDTO);

    return new ResponseUtil("200","Travel Package Save Sucessfully",null);
}

    @DeleteMapping(params = "travelPackage_Id")
    public ResponseUtil deleteTravelPackage(@RequestParam String travelPackage_Id){
        travelPackageService.deletePackage(travelPackage_Id);
        return new ResponseUtil("200","Travel Package Deleted SucessFully",null);
    }

    @PutMapping
    public  ResponseUtil updateTravelPackage(@RequestBody TravelPackage travelPackageDTO){
        travelPackageService.updateTravelPackage(travelPackageDTO);
        return new  ResponseUtil("200","Updated Sucessfull Travel Package",null);
    }

    @GetMapping
    public ResponseUtil getAllPackages(){
       List<TravelPackageDTO> hotels=travelPackageService.getAllDetails();
        return new ResponseUtil("200","Show All Packages",hotels);
    }

    @GetMapping(path = "get/id")
    public ResponseUtil getNextPackageId(){
        return new ResponseUtil("200","Next Package Id",travelPackageService.getNextPkgId());
    }

    @GetMapping(path = "get/details",params = {"date"})
    public ResponseUtil getDetails(@RequestParam String date){
        return new ResponseUtil("200","Details",travelPackageService.getDetails(date));
    }
}


