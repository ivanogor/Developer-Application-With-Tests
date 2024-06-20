package net.proselyte.qafordevs.util;

import net.proselyte.qafordevs.dto.DeveloperDto;
import net.proselyte.qafordevs.entity.DeveloperEntity;
import net.proselyte.qafordevs.entity.Status;

public class DataUtils {
    public static DeveloperEntity getJonhDoeTransient(){
        return DeveloperEntity.builder()
                .firstName("Jonh")
                .lastName("Doe")
                .email("john.doe@mail.com")
                .specialty("Java")
                .status(Status.ACTIVE)
                .build();
    }

    public static DeveloperEntity getMikeSmithTransient(){
        return DeveloperEntity.builder()
                .firstName("Mike")
                .lastName("Smith")
                .email("mike.smith@mail.com")
                .specialty("Java")
                .status(Status.ACTIVE)
                .build();
    }
    public static DeveloperEntity getFrankJonesTransient(){
        return DeveloperEntity.builder()
                .firstName("Frank")
                .lastName("Jones")
                .email("frank.jones@mail.com")
                .specialty("Java")
                .status(Status.DELETED)
                .build();
    }
    public static DeveloperEntity getJonhDoePersisted(){
        return DeveloperEntity.builder()
                .id(1)
                .firstName("Jonh")
                .lastName("Doe")
                .email("john.doe@mail.com")
                .specialty("Java")
                .status(Status.ACTIVE)
                .build();
    }

    public static DeveloperEntity getMikeSmithPersisted(){
        return DeveloperEntity.builder()
                .id(2)
                .firstName("Mike")
                .lastName("Smith")
                .email("mike.smith@mail.com")
                .specialty("Java")
                .status(Status.ACTIVE)
                .build();
    }
    public static DeveloperEntity getFrankJonesPersisted(){
        return DeveloperEntity.builder()
                .id(3)
                .firstName("Frank")
                .lastName("Jones")
                .email("frank.jones@mail.com")
                .specialty("Java")
                .status(Status.DELETED)
                .build();
    }

    public static DeveloperDto getJonhDoeDtoTransient(){
        return DeveloperDto.builder()
                .firstName("Jonh")
                .lastName("Doe")
                .email("john.doe@mail.com")
                .specialty("Java")
                .status(Status.ACTIVE)
                .build();
    }

    public static DeveloperDto getMikeSmithDtoTransient(){
        return DeveloperDto.builder()
                .firstName("Mike")
                .lastName("Smith")
                .email("mike.smith@mail.com")
                .specialty("Java")
                .status(Status.ACTIVE)
                .build();
    }
    public static DeveloperDto getFrankJonesDtoTransient(){
        return DeveloperDto.builder()
                .firstName("Frank")
                .lastName("Jones")
                .email("frank.jones@mail.com")
                .specialty("Java")
                .status(Status.DELETED)
                .build();
    }

    public static DeveloperDto getJonhDoeDtoPersisted(){
        return DeveloperDto.builder()
                .id(1)
                .firstName("Jonh")
                .lastName("Doe")
                .email("john.doe@mail.com")
                .specialty("Java")
                .status(Status.ACTIVE)
                .build();
    }

    public static DeveloperDto getMikeSmithDtoPersisted(){
        return DeveloperDto.builder()
                .id(2)
                .firstName("Mike")
                .lastName("Smith")
                .email("mike.smith@mail.com")
                .specialty("Java")
                .status(Status.ACTIVE)
                .build();
    }
    public static DeveloperDto getFrankJonesDtoPersisted(){
        return DeveloperDto.builder()
                .id(3)
                .firstName("Frank")
                .lastName("Jones")
                .email("frank.jones@mail.com")
                .specialty("Java")
                .status(Status.DELETED)
                .build();
    }
}
