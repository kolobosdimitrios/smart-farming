package com.dkolovos.smart.farming.core.application.usecase.fields;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.FieldDto;
import com.dkolovos.smart.farming.core.domain.repository.field.FieldRepository;

public class RegisterFieldUseCase {
    private final FieldRepository fieldRepository;

    public RegisterFieldUseCase(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Result<Void> execute(FieldDto field) {
        if (field == null) {
            return Result.failure(new IllegalArgumentException("Field cannot be null"));
        }
        return fieldRepository.insertField(field);
    }
}
