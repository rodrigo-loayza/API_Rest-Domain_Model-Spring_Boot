package com.nostop.market.persistence.mapper;

import com.nostop.market.domain.Category;
import com.nostop.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//@Mapper annotation to tell the project that this is a mapper
@Mapper(componentModel = "spring") // MapStruct inegration with Spring
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    // This annotation indicates to perform a reverse mapping to the
    // one done in "toCategory"
    @InheritInverseConfiguration
    // Since we're not mapping productos (oneToMany relation), we add
    // this as parameters:
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);

}
