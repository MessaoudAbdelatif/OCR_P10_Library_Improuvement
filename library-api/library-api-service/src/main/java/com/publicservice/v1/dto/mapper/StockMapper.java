package com.publicservice.v1.dto.mapper;

import com.publicservice.entities.Stock;
import com.publicservice.v1.dto.model.StockDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StockMapper {

  Stock toStock(StockDto stockDto);

  StockDto toStockDto(Stock stock);
}
