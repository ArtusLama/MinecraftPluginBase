package de.artus.basePlugin.logging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogUnicodeReplacements {


    Ä("Ä", "Ae"),
    ä("ä", "ae"),

    Ö("Ö", "Oe"),
    ö("ö", "oe"),

    Ü("Ü", "Ue"),
    ü("ü", "ue");


    private String from;
    private String replacement;
}
