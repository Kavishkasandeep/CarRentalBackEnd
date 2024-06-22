
package com.Gasto.CarRentalManagement.Controller;

import com.Gasto.CarRentalManagement.DTO.VehicalDto;
import com.Gasto.CarRentalManagement.Services.VehicleService;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

    @RestController
    @CrossOrigin
    @RequestMapping(path = "api/v1/VehicleManagement")
    public class VehicleController {

        private final VehicleService vehicleService;

        @Autowired
        public VehicleController(VehicleService vehicleService) {
            this.vehicleService = vehicleService;
        }

        @GetMapping("")
        public String vehicleControllerHome() {
            return "Welcome to Vehicle Management Controller";
        }

        @PostMapping(path = "/saveVehicle")
        public CommonResponse saveVehicle(@RequestBody VehicalDto vehicleDto) {
            return vehicleService.saveVehicle(vehicleDto);
        }

        @PutMapping(path = "/updateVehicle")
        public CommonResponse updateVehicle(@RequestBody VehicalDto vehicleDto) {
            return vehicleService.updateVehicle(vehicleDto);
        }

        @DeleteMapping("/deleteVehicle/{vehicleId}")
        public CommonResponse deleteVehicle(@PathVariable Long vehicleId) {
            return vehicleService.deleteVehicle(vehicleId);
        }

        @GetMapping(path = "/getAllVehicles")
        public CommonResponse getAllVehicles() {
            return vehicleService.getAllVehicles();
        }

        @GetMapping(path = "/getVehicle/{vehicleId}")
        public CommonResponse getVehicle(@PathVariable Long vehicleId) {
            return vehicleService.getVehicle(vehicleId);
        }
    }


