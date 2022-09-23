package ro.fasttrackit.vehicle.service.model;

import dto.MaterialOrderDTO;
import dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderMapper {

    OrderDTO toApi(OrderEntity order);

    OrderEntity toEntity(OrderDTO order);

    OrderEntity toEntityFromShop(MaterialOrderDTO order);

    MaterialOrderDTO fromEntityToShop(OrderEntity order);
}
