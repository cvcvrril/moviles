package com.example.unapantallainesmr.data.modelo

import com.example.unapantallainesmr.domain.modelo.Serie

fun SerieEntity.toSerie() : Serie {
    return Serie(this.id, this.titulo, this.descripcion)
}

fun Serie.toSerieEntity() : SerieEntity{
    return SerieEntity(this.id, this.titulo, this.descripcion)
}
