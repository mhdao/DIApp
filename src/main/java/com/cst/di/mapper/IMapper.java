package com.cst.di.mapper;

public interface IMapper {
    void configure();
    <T> Class<? extends T> getMapping(Class<T> type);
}
