package com.bilichenko.geoipserver.translator;

public interface Translator<E, D> {
    D toDto(E entity);
}
