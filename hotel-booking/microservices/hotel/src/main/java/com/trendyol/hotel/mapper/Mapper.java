package com.trendyol.hotel.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<E,D> {

	public D toDTO(E entity);

	public E toEntity(D dto);
}
