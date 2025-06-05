package com.dkolovos.smart.farming.core.infrastructure.db.local;

import com.dkolovos.smart.farming.core.application.usecase.Result;
import com.dkolovos.smart.farming.core.domain.data.field.Field;
import com.dkolovos.smart.farming.core.domain.port.field.FieldRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LocalFieldRepositoryImpl implements FieldRepository {
    private final Map<String, Field> store = new ConcurrentHashMap<>();

    @Override
    public Result<Void> insertField(Field field) {
        store.put(field.getId(), field);
        return Result.success(null);
    }

    @Override
    public Result<Optional<Field>> getField(String fieldId) {
        return Result.success(Optional.ofNullable(store.get(fieldId)));
    }

    @Override
    public Result<List<Field>> getAllFields() {
        return Result.success(new ArrayList<>(store.values()));
    }

    @Override
    public Result<Void> updateField(Field field) {
        store.put(field.getId(), field);
        return Result.success(null);
    }

    @Override
    public Result<Void> deleteField(Field field) {
        store.remove(field.getId());
        return Result.success(null);
    }
}
