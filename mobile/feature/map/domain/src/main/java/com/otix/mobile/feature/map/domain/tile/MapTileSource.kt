package com.otix.mobile.feature.map.domain.tile

import org.osmdroid.tileprovider.tilesource.XYTileSource

val mapTileSources = listOf(
    XYTileSource(
        "OSM Mapnik",
        0,
        19,
        256,
        ".png",
        arrayOf("https://tile.openstreetmap.org/"),
        "© OpenStreetMap contributors"
    ),
    XYTileSource(
        "HOT",
        0,
        19,
        256,
        ".png",
        arrayOf("https://a.tile.openstreetmap.fr/hot/"),
        "© OpenStreetMap contributors"
    ),
    XYTileSource(
        "OpenTopoMap",
        0,
        17,
        256,
        ".png",
        arrayOf("https://a.tile.opentopomap.org/"),
        "© OpenTopoMap contributors"
    ),
    XYTileSource(
        "Stamen Toner",
        0,
        20,
        256,
        ".png",
        arrayOf("https://stamen-tiles.a.ssl.fastly.net/toner/"),
        "© Stamen Design"
    ),
    XYTileSource(
        "Stamen Watercolor",
        0,
        20,
        256,
        ".jpg",
        arrayOf("https://stamen-tiles.a.ssl.fastly.net/watercolor/"),
        "© Stamen Design"
    ),
    XYTileSource(
        "Carto Positron",
        0,
        20,
        256,
        ".png",
        arrayOf("https://cartodb-basemaps-a.global.ssl.fastly.net/light_all/"),
        "© Carto"
    ),
    XYTileSource(
        "Carto Dark Matter",
        0,
        20,
        256,
        ".png",
        arrayOf("https://cartodb-basemaps-a.global.ssl.fastly.net/dark_all/"),
        "© Carto"
    )
)
