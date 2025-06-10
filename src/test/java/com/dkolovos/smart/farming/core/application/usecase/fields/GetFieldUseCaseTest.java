package com.dkolovos.smart.farming.core.application.usecase.fields;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.Field;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalFieldRepositoryImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetFieldUseCaseTest {

    @Test
    void fetchesFieldById() {
        LocalFieldRepositoryImpl repo = new LocalFieldRepositoryImpl();
        Field field = new Field("f1", "Field1", 100.0);
        repo.insertField(field);
        GetFieldUseCase useCase = new GetFieldUseCase(repo);
        Result<Optional<Field>> result = useCase.execute("f1");
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(result.getData().isPresent());
        Assertions.assertEquals("Field1", result.getData().get().getName());
    }

    @Test
    void failsWhenIdBlank() {
        GetFieldUseCase useCase = new GetFieldUseCase(new LocalFieldRepositoryImpl());
        Result<Optional<Field>> result = useCase.execute(" ");
        Assertions.assertTrue(result.isFailure());
    }
}
