package com.dkolovos.smart.farming.core.domain.port.field;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.Field;
import java.util.List;
import java.util.Optional;

public interface FieldRepository {
    Result<Void> insertField(Field field);
    Result<Optional<Field>> getField(String fieldId);
    Result<List<Field>> getAllFields();
    Result<Void> updateField(Field field);
    Result<Void> deleteField(Field field);
}
