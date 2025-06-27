package com.dkolovos.smart.farming.core.infrastructure.db.local;

import com.dkolovos.smart.farming.core.infastracture.local_db.LocalFieldRepositoryImpl;
import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.AreaAbstrct;
import com.dkolovos.smart.farming.core.domain.data.field.FieldDto;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocalFieldRepositoryImplTest {

    @Test
    void insertAndRetrieveField() {
        LocalFieldRepositoryImpl repo = new LocalFieldRepositoryImpl();
        AreaAbstrct.RectangularArea rectangularArea = new AreaAbstrct.RectangularArea(new double[] {10.0, 20.0, 30.0 ,50.0}, new double[] {30.0, 50.0, 60.0 , 10.0});
        FieldDto field = new FieldDto("f1", "Field 1", rectangularArea);
        Result<Void> insert = repo.insertField(field);
        Assertions.assertTrue(insert.isSuccess());
        Result<Optional<FieldDto>> fetched = repo.getField("f1");
        Assertions.assertTrue(fetched.isSuccess());
        Assertions.assertTrue(fetched.getData().isPresent());
        Assertions.assertEquals("Field 1", fetched.getData().get().getName());
    }
}
