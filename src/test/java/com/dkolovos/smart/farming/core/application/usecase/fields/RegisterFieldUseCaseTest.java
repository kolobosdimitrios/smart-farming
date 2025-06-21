package com.dkolovos.smart.farming.core.application.usecase.fields;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.Area;
import com.dkolovos.smart.farming.core.domain.data.field.Field;
import com.dkolovos.smart.farming.core.infrastructure.db.local.LocalFieldRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterFieldUseCaseTest {

    @Test
    void registersFieldInRepository() {
        LocalFieldRepositoryImpl repo = new LocalFieldRepositoryImpl();
        RegisterFieldUseCase useCase = new RegisterFieldUseCase(repo);
        Area.RectangularArea rectangularArea = new Area.RectangularArea(new double[] {10.0, 20.0, 30.0 ,50.0}, new double[] {30.0, 50.0, 60.0 , 10.0});
        Field field = new Field("f1", "Field1", rectangularArea);
        Result<Void> result = useCase.execute(field);
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertTrue(repo.getField("f1").getData().isPresent());
    }

    @Test
    void failsWhenFieldIsNull() {
        LocalFieldRepositoryImpl repo = new LocalFieldRepositoryImpl();
        RegisterFieldUseCase useCase = new RegisterFieldUseCase(repo);
        Result<Void> result = useCase.execute(null);
        Assertions.assertTrue(result.isFailure());
    }
}
