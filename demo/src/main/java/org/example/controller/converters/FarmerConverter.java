package org.example.controller.converters;

import org.example.domain.Farmer;
import org.example.persistence.entity.FarmerEntity;

public class FarmerConverter {
    private FarmerConverter() {
    }
    // Converts a FarmerEntity to a FarmerDTO
    public static Farmer entityToDto(FarmerEntity farmerEntity) {
        if (farmerEntity == null) {
            return null;
        }

        Farmer farmerDto = new Farmer();
        farmerDto.setId(farmerEntity.getId());
        farmerDto.setName(farmerEntity.getName());
        farmerDto.setLocation(farmerEntity.getLocation());
        farmerDto.setSpecialStatus(farmerEntity.isSpecialStatus());
        // Assuming articles are not directly converted here, as it might involve a more complex conversion logic

        // Additional fields can be set here

        return farmerDto;
    }

    // Converts a FarmerDTO to a FarmerEntity
    public static FarmerEntity dtoToEntity(Farmer farmerDto) {
        if (farmerDto == null) {
            return null;
        }

        FarmerEntity farmerEntity = new FarmerEntity();
        farmerEntity.setId(farmerDto.getId());
        farmerEntity.setName(farmerDto.getName());
        farmerEntity.setLocation(farmerDto.getLocation());
        farmerEntity.setSpecialStatus(farmerDto.isSpecialStatus());
        // Again, assuming articles are not directly converted here

        // Additional fields can be set here

        return farmerEntity;
    }

}
