package net.proselyte.qafordevs.service;

import net.proselyte.qafordevs.entity.DeveloperEntity;
import net.proselyte.qafordevs.entity.Status;
import net.proselyte.qafordevs.exception.DeveloperNotFoundException;
import net.proselyte.qafordevs.exception.DeveloperWithDuplicateEmailException;
import net.proselyte.qafordevs.repository.DeveloperRepository;
import net.proselyte.qafordevs.util.DataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeveloperServiceImplTests {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DeveloperServiceImpl serviceUnderTest;

    @Test
    @DisplayName("Test save developer functionality")
    public void givenDeveloperToSave_whenSaveDeveloper_thenRepositoryIsCalled() {
        //given
        DeveloperEntity developerToSave = DataUtils.getJonhDoeTransient();
        BDDMockito.given(developerRepository.findByEmail(anyString())).willReturn(null);
        BDDMockito.given(developerRepository.save(any(DeveloperEntity.class)))
                .willReturn(DataUtils.getJonhDoeTransient());
        //when
        DeveloperEntity savedDeveloper = serviceUnderTest.saveDeveloper(developerToSave);
        //then
        assertThat(savedDeveloper).isNotNull();
    }

    @Test
    @DisplayName("Test save developer with duplicate email functionality")
    public void givenDeveloperToSaveWithDuplicateEmail_whenSaveDeveloper_thenExceptionIsThrown(){
        //given
        DeveloperEntity developerToSave = DataUtils.getJonhDoeTransient();
        BDDMockito.given(developerRepository.findByEmail(anyString()))
                .willReturn(DataUtils.getJonhDoeTransient());
        //when
        assertThrows(
                DeveloperWithDuplicateEmailException.class, () -> serviceUnderTest.saveDeveloper(developerToSave)
        );
        //then
        verify(developerRepository, never()).save(any(DeveloperEntity.class));
    }

    @Test
    @DisplayName("Test update developer functionality")
    public void givenDeveloperToUpdate_whenUpdateDeveloper_thenRepositoryIsCalled() {
        //given
        DeveloperEntity developerToUpdate = DataUtils.getJonhDoeTransient();
        BDDMockito.given(developerRepository.existsById(developerToUpdate.getId()))
                .willReturn(true);
        BDDMockito.given(developerRepository.save(developerToUpdate))
                .willReturn(developerToUpdate);

        //when
        DeveloperEntity updatedDeveloper = serviceUnderTest.updateDeveloper(developerToUpdate);

        //then
        assertThat(updatedDeveloper).isNotNull();
        verify(developerRepository, times(1)).save(developerToUpdate);
    }

    @Test
    @DisplayName("Test update developer with incorrect id functionality")
    public void givenDeveloperToUpdateWithIncorrectId_whenUpdateDeveloper_thenExceptionIsThrown(){
        //given
        DeveloperEntity developerToUpdate = DataUtils.getJonhDoeTransient();
        BDDMockito.given(developerRepository.existsById(any())).willReturn(false);
        //when
        assertThrows(
                DeveloperNotFoundException.class, () -> serviceUnderTest.updateDeveloper(developerToUpdate)
        );
        //then
        verify(developerRepository, never()).save(any(DeveloperEntity.class));
    }

    @Test
    @DisplayName("Test get developer by id functionality")
    public void givenId_whenGetById_thenDeveloperIsReturned(){
        //given
        BDDMockito.given(developerRepository.findById(anyInt())).willReturn(Optional.of(DataUtils.getJonhDoePersisted()));
        //when
        DeveloperEntity obtainedDeveloper = serviceUnderTest.getDeveloperById(anyInt());
        //then
        assertThat(obtainedDeveloper).isNotNull();
        verify(developerRepository, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("Test get developer by incorrect id functionality")
    public void givenIncorrectId_whenGetById_thenExceptionIsThrown(){
        //given
        BDDMockito.given(developerRepository.findById(anyInt())).willThrow(DeveloperNotFoundException.class);
        //when
        assertThrows(DeveloperNotFoundException.class, () -> serviceUnderTest.getDeveloperById(anyInt()));
        //then
    }

    @Test
    @DisplayName("Test get developer by email functionality")
    public void givenEmail_whenGetByEmail_thenDeveloperIsReturned(){
        //given
        BDDMockito.given(developerRepository.findByEmail(anyString())).willReturn(DataUtils.getJonhDoePersisted());
        //when
        DeveloperEntity obtainedDeveloper = serviceUnderTest.getDeveloperByEmail(anyString());
        //then
        assertThat(obtainedDeveloper).isNotNull();
    }

    @Test
    @DisplayName("Test get developer by incorrect email functionality")
    public void givenIncorrectEmail_whenGetByEmail_thenDeveloperIsReturned(){
        //given
        BDDMockito.given(developerRepository.findByEmail(anyString())).willThrow(DeveloperNotFoundException.class);
        //when
        assertThrows(DeveloperNotFoundException.class, () -> serviceUnderTest.getDeveloperByEmail(anyString()));
        //then
    }

    @Test
    @DisplayName("Test get all developers functionality")
    public void givenThreeDevelopers_whenGetAll_thenDevelopersIsReturned(){
        //given
        List<DeveloperEntity> developers = new ArrayList<>(
                List.of(DataUtils.getJonhDoePersisted(), DataUtils.getFrankJonesPersisted(), DataUtils.getMikeSmithPersisted())
        );
        BDDMockito.given(developerRepository.findAll()).willReturn(developers);
        //when
        List<DeveloperEntity> obtainedDevelopers = serviceUnderTest.getAllDeveloper();
        //then
        assertThat(obtainedDevelopers.size()).isEqualTo(2);
        verify(developerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test get all developers functionality")
    public void givenSpecialty_whenGetAllActiveBySpecialty_thenDevelopersIsReturned(){
        //given
        String specialty = "Java";
        List<DeveloperEntity> developers = new ArrayList<>(
                List.of(DataUtils.getJonhDoePersisted(),
                        DataUtils.getFrankJonesPersisted(),
                        DataUtils.getMikeSmithPersisted()))
                .stream()
                .filter(developer -> developer.getSpecialty().equals(specialty))
                .filter(developer -> developer.getStatus().equals(Status.ACTIVE))
                .toList();

        BDDMockito.given(developerRepository.findAllActiveBySpecialty(specialty)).willReturn(developers);
        //when
        List<DeveloperEntity> obtainedDevelopers = serviceUnderTest.getAllActiveBySpecialty(specialty);
        //then
        assertThat(obtainedDevelopers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test soft delete by id functionality")
    public void givenId_whenSoftDeleteById_thenRepositorySaveMethodIsCalled(){
        //given
        BDDMockito.given(developerRepository.findById(anyInt())).willReturn(Optional.of(DataUtils.getJonhDoeTransient()));
        //when
        serviceUnderTest.softDeleteById(anyInt());
        //then
        verify(developerRepository, times(1)).save(any(DeveloperEntity.class));
        verify(developerRepository, never()).deleteById(anyInt());
    }

    @Test
    @DisplayName("Test soft delete by incorrect id functionality")
    public void givenIncorrectId_whenSoftDeleteById_thenExceptionIsThrown(){
        //given
        BDDMockito.given(developerRepository.findById(anyInt())).willReturn(Optional.empty());
        //when
        assertThrows(DeveloperNotFoundException.class, () -> serviceUnderTest.softDeleteById(anyInt()));
        //then
        verify(developerRepository, never()).save(any(DeveloperEntity.class));
    }

    @Test
    @DisplayName("Test hard delete by id functionality")
    public void givenId_whenHardDeleteById_thenRepositoryDeleteByIdMethodIsCalled(){
        //given
        BDDMockito.given(developerRepository.findById(anyInt())).willReturn(Optional.of(DataUtils.getJonhDoePersisted()));
        //when
        serviceUnderTest.hardDeleteById(anyInt());
        //then
        verify(developerRepository, times(1)).deleteById(anyInt());
    }

    @Test
    @DisplayName("Test hard delete by incorrect id functionality")
    public void givenIncorrectId_whenHardDeleteById_thenExceptionIsThrown(){
        //given
        BDDMockito.given(developerRepository.findById(anyInt())).willReturn(Optional.empty());
        //when
        assertThrows(DeveloperNotFoundException.class, () -> serviceUnderTest.hardDeleteById(anyInt()));
        //then
        verify(developerRepository, never()).deleteById(anyInt());
    }
}
