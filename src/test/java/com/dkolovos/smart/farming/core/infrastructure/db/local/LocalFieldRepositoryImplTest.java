package com.dkolovos.smart.farming.core.infrastructure.db.local;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.Field;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalFieldRepositoryImplTest {

    @Test
    void insertAndRetrieveField() {
        LocalFieldRepositoryImpl repo = new LocalFieldRepositoryImpl();
        Field field = new Field("f1", "Field 1", 100.0);
        Result<Void> insert = repo.insertField(field);
        Assertions.assertTrue(insert.isSuccess());
        Result<Optional<Field>> fetched = repo.getField("f1");
        Assertions.assertTrue(fetched.isSuccess());
        Assertions.assertTrue(fetched.getData().isPresent());
        Assertions.assertEquals("Field 1", fetched.getData().get().getName());
    }
}
