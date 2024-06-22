package com.Gasto.CarRentalManagement.Services;

import com.Gasto.CarRentalManagement.DTO.VehicalDto;
import com.Gasto.CarRentalManagement.DTO.VehicalDto;
import com.Gasto.CarRentalManagement.Util.CommonResponse;

    public interface VehicleService {
        CommonResponse saveVehicle(VehicalDto vehicleDto);
        CommonResponse updateVehicle(VehicalDto vehicleDto);
        CommonResponse deleteVehicle(Long vehicleId);
        CommonResponse getAllVehicles();
        CommonResponse getVehicle(Long vehicleId);
    }


