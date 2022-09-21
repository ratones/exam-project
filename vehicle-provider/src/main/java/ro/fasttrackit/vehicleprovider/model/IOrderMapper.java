package ro.fasttrackit.vehicleprovider.model;

import dto.OrderDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface IOrderMapper {

    OrderDTO toApi(ServiceOrder order);

    ServiceOrder toEntity(OrderDTO order);
}
