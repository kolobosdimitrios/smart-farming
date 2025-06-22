package com.dkolovos.smart.farming.core.infastracture.local_db;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.FieldDto;
import com.dkolovos.smart.farming.core.domain.repository.field.FieldRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LocalFieldRepositoryImpl implements FieldRepository {
    private final Map<String, FieldDto> store = new ConcurrentHashMap<>();

    @Override
    public Result<Void> insertField(FieldDto field) {
        store.put(field.getId(), field);
        return Result.success(null);
    }

    @Override
    public Result<Optional<FieldDto>> getField(String fieldId) {
        return Result.success(Optional.ofNullable(store.get(fieldId)));
    }

    @Override
    public Result<List<FieldDto>> getAllFields() {
        return Result.success(new ArrayList<>(store.values()));
    }

    @Override
    public Result<Void> updateField(FieldDto field) {
        store.put(field.getId(), field);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteField(FieldDto field) {
        store.remove(field.getId());
        return Result.success(null);
    }
}
