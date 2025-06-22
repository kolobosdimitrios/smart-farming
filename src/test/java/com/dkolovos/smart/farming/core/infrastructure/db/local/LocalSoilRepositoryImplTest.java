package com.dkolovos.smart.farming.core.infrastructure.db.local;

import com.dkolovos.smart.farming.core.infastracture.local_db.LocalSoilRepositoryImpl;
import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.SoilDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocalSoilRepositoryImplTest {

    private LocalSoilRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new LocalSoilRepositoryImpl();
    }

    @Test
    void testInsertAndRetrieveSoil() {
        SoilDto soil = new SoilDto("id1");
        Result<Void> insertResult = repository.insertSoil(soil);
        assertTrue(insertResult.isSuccess(), "Insert should succeed");

        Result<SoilDto> getResult = repository.getSoil("id1");
        assertTrue(getResult.isSuccess(), "Retrieve should succeed");
        assertEquals("id1", getResult.getData().getId(), "IDs should match");
    }

    @Test
    void testInsertDuplicateSoil() {
        SoilDto soil = new SoilDto("id1");
        repository.insertSoil(soil);
        Result<Void> dupResult = repository.insertSoil(soil);
        assertTrue(dupResult.isFailure(), "Duplicate insert should fail");
    }

    @Test
    void testGetAllSoils() {
        SoilDto s1 = new SoilDto("id1");
        SoilDto s2 = new SoilDto("id2");
        repository.insertSoil(s1);
        repository.insertSoil(s2);

        Result<List<SoilDto>> allResult = repository.getAllSoils();
        assertTrue(allResult.isSuccess(), "getAllSoils should succeed");
        List<SoilDto> soils = allResult.getData();
        assertEquals(2, soils.size(), "Should return two soil records");
    }

    @Test
    void testDeleteSoilById() {
        SoilDto soil = new SoilDto("id1");
        repository.insertSoil(soil);

        Result<Void> deleteResult = repository.deleteSoilById("id1");
        assertTrue(deleteResult.isSuccess(), "Delete by ID should succeed");

        Result<SoilDto> getResult = repository.getSoil("id1");
        assertTrue(getResult.isFailure(), "After delete, retrieval should fail");
    }

    @Test
    void testUpdateSoil() {
        SoilDto original = new SoilDto("id1");
        repository.insertSoil(original);

        SoilDto updated = new SoilDto("id1");  // populate with new values as needed
        Result<SoilDto> updateResult = repository.updateSoil(updated);
        assertTrue(updateResult.isSuccess(), "Update should succeed");
        assertEquals(updated, repository.getSoil("id1").getData(), "Soil should be updated");
    }

    @Test
    void testDeleteSoilNotExists() {
        Result<Void> deleteResult = repository.deleteSoilById("missing");
        assertTrue(deleteResult.isFailure(), "Deleting non-existent soil should fail");
    }

    @Test
    void testGetSoilNotExists() {
        Result<SoilDto> getResult = repository.getSoil("xxx");
        assertTrue(getResult.isFailure(), "Retrieval of non-existent soil should fail");
    }
}
