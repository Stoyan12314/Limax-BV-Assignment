package org.example.controller.converters;

import org.example.domain.Farmer;
import org.example.persistence.entity.FarmerEntity;

public class FarmerConverter {
    private FarmerConverter() {
    }
    public static Farmer entityToDto(FarmerEntity farmerEntity) {
        if (farmerEntity == null) {
            return null;
        }

        Farmer farmerDto = new Farmer();
        farmerDto.setId(farmerEntity.getId());
        farmerDto.setName(farmerEntity.getName());
        farmerDto.setLocation(farmerEntity.getLocation());
        farmerDto.setSpecialStatus(farmerEntity.isSpecialStatus());
        farmerDto.setInventoryItemId(farmerDto.getInventoryItemId());


        return farmerDto;
    }

    public static FarmerEntity dtoToEntity(Farmer farmerDto) {
        if (farmerDto == null) {
            return null;
        }

        FarmerEntity farmerEntity = new FarmerEntity();
        farmerEntity.setId(farmerDto.getId());
        farmerEntity.setName(farmerDto.getName());
        farmerEntity.setLocation(farmerDto.getLocation());
        farmerEntity.setSpecialStatus(farmerDto.isSpecialStatus());
        farmerEntity.setInventoryItemId(farmerDto.getInventoryItemId());


        return farmerEntity;
    }

}
