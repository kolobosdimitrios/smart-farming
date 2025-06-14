package com.dkolovos.smart.farming.core.infrastructure.db.local;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.Soil;
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
        Soil soil = new Soil("id1");
        Result<Void> insertResult = repository.insertSoil(soil);
        assertTrue(insertResult.isSuccess(), "Insert should succeed");

        Result<Soil> getResult = repository.getSoil("id1");
        assertTrue(getResult.isSuccess(), "Retrieve should succeed");
        assertEquals("id1", getResult.getData().getId(), "IDs should match");
    }

    @Test
    void testInsertDuplicateSoil() {
        Soil soil = new Soil("id1");
        repository.insertSoil(soil);
        Result<Void> dupResult = repository.insertSoil(soil);
        assertTrue(dupResult.isFailure(), "Duplicate insert should fail");
    }

    @Test
    void testGetAllSoils() {
        Soil s1 = new Soil("id1");
        Soil s2 = new Soil("id2");
        repository.insertSoil(s1);
        repository.insertSoil(s2);

        Result<List<Soil>> allResult = repository.getAllSoils();
        assertTrue(allResult.isSuccess(), "getAllSoils should succeed");
        List<Soil> soils = allResult.getData();
        assertEquals(2, soils.size(), "Should return two soil records");
    }

    @Test
    void testDeleteSoilById() {
        Soil soil = new Soil("id1");
        repository.insertSoil(soil);

        Result<Void> deleteResult = repository.deleteSoilById("id1");
        assertTrue(deleteResult.isSuccess(), "Delete by ID should succeed");

        Result<Soil> getResult = repository.getSoil("id1");
        assertTrue(getResult.isFailure(), "After delete, retrieval should fail");
    }

    @Test
    void testUpdateSoil() {
        Soil original = new Soil("id1");
        repository.insertSoil(original);

        Soil updated = new Soil("id1");  // populate with new values as needed
        Result<Soil> updateResult = repository.updateSoil(updated);
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
        Result<Soil> getResult = repository.getSoil("xxx");
        assertTrue(getResult.isFailure(), "Retrieval of non-existent soil should fail");
    }
}
