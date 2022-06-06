package com.nostop.market.persistence.mapper;

import com.nostop.market.domain.PurchaseItem;
import com.nostop.market.persistence.entity.ComprasProducto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}) // even if ignored
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            // Este no va por ser igual
            // @Mapping(source = "total", target = "total")
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);

}
