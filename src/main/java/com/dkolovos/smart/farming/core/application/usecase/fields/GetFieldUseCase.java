package com.dkolovos.smart.farming.core.application.usecase.fields;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.Field;
import com.dkolovos.smart.farming.core.domain.port.field.FieldRepository;
import java.util.Optional;

public class GetFieldUseCase {
    private final FieldRepository fieldRepository;

    public GetFieldUseCase(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Result<Optional<Field>> execute(String fieldId) {
        if (fieldId == null || fieldId.isBlank()) {
            return Result.failure(new IllegalArgumentException("Field id cannot be empty"));
        }
        return fieldRepository.getField(fieldId);
    }
}
