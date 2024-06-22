package com.Gasto.CarRentalManagement.Services.ServiceIMPL;

import com.Gasto.CarRentalManagement.Const.CommonStatus;
import com.Gasto.CarRentalManagement.DTO.VehicalDto;
import com.Gasto.CarRentalManagement.Entity.VehicalEntity;
import com.Gasto.CarRentalManagement.Repository.VehicleRepository;
import com.Gasto.CarRentalManagement.Services.VehicleService;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import com.Gasto.CarRentalManagement.Util.DtoToEntityCast;
import com.Gasto.CarRentalManagement.Util.EntityToDtoCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceIMPL implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceIMPL(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public CommonResponse saveVehicle(VehicalDto vehicleDto) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            VehicalEntity vehicleEntity = DtoToEntityCast.castVehicleDtoToVehicleEntity(vehicleDto);
            vehicleRepository.save(vehicleEntity);
            commonResponse.setPayload(Collections.singletonList(vehicleEntity));
            commonResponse.setCommonMessage("Vehicle saved successfully.");
            commonResponse.setStatus(true);
        } catch (Exception e) {
            commonResponse.setCommonMessage("Error saving vehicle.");
        }
        return commonResponse;
    }

    @Override
    public CommonResponse updateVehicle(VehicalDto vehicleDto) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            VehicalEntity vehicleEntity = DtoToEntityCast.castVehicleDtoToVehicleEntity(vehicleDto);
            vehicleRepository.save(vehicleEntity);
            commonResponse.setPayload(Collections.singletonList(vehicleEntity));
            commonResponse.setCommonMessage("Vehicle updated successfully.");
            commonResponse.setStatus(true);
        } catch (Exception e) {
            commonResponse.setCommonMessage("Error updating vehicle.");
        }
        return commonResponse;
    }

    @Override
    public CommonResponse deleteVehicle(Long vehicleId) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            Optional<VehicalEntity> optionalVehicle = vehicleRepository.findById(vehicleId);
            if (optionalVehicle.isPresent()) {
                VehicalEntity vehicle = optionalVehicle.get();
                vehicle.setCommonStatus(CommonStatus.DELETE);
                vehicleRepository.save(vehicle);
                commonResponse.setCommonMessage("Vehicle deleted successfully.");
                commonResponse.setStatus(true);
            } else {
                commonResponse.setCommonMessage("Vehicle not found.");
            }
        } catch (Exception e) {
            commonResponse.setCommonMessage("Error deleting vehicle.");
        }
        return commonResponse;
    }

    @Override
    public CommonResponse getAllVehicles() {
        CommonResponse commonResponse = new CommonResponse();
        List<VehicalDto> vehicleDtoList = new ArrayList<>();
        try {
            vehicleDtoList = vehicleRepository.findAll()
                    .stream()
                    .filter(vehicle -> vehicle.getCommonStatus() != CommonStatus.DELETE)
                    .map(EntityToDtoCast::castVehicleEntityToVehicleDto)
                    .collect(Collectors.toList());
            if (vehicleDtoList.isEmpty()) {
                commonResponse.setCommonMessage("Vehicle list is empty.");
                return commonResponse;
            }
            commonResponse.setPayload(Collections.singletonList(vehicleDtoList));
            commonResponse.setCommonMessage("Vehicle list retrieved successfully.");
            commonResponse.setStatus(true);
        } catch (Exception e) {
            commonResponse.setCommonMessage("Error retrieving vehicle list.");
        }
        return commonResponse;
    }

    @Override
    public CommonResponse getVehicle(Long vehicleId) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            Optional<VehicalEntity> optionalVehicle = vehicleRepository.findById(vehicleId);
            if (optionalVehicle.isPresent()) {
                VehicalDto vehicleDto = EntityToDtoCast.castVehicleEntityToVehicleDto(optionalVehicle.get());
                commonResponse.setPayload(Collections.singletonList(vehicleDto));
                commonResponse.setCommonMessage("Vehicle retrieved successfully.");
                commonResponse.setStatus(true);
            } else {
                commonResponse.setCommonMessage("Vehicle not found.");
            }
        } catch (Exception e) {
            commonResponse.setCommonMessage("Error retrieving vehicle.");
        }
        return commonResponse;
    }
}
