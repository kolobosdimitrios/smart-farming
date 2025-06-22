package com.dkolovos.smart.farming.core.domain.repository.field;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.FieldDto;
import java.util.List;
import java.util.Optional;

public interface FieldRepository {
    Result<Void> insertField(FieldDto field);
    Result<Optional<FieldDto>> getField(String fieldId);
    Result<List<FieldDto>> getAllFields();
    Result<Void> updateField(FieldDto field);
    Result<Void> deleteField(FieldDto field);
}
