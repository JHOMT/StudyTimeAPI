package jhomt.com.studytimeapi.Domain.Core;

import java.util.function.Consumer;

public class EntityUpdater {

    /**
     * Actualiza el valor de un atributo si el nuevo valor no es nulo y no es una cadena vacía.
     * Si el valor es una cadena, también verificará si es una cadena que solo contiene espacios y la ignorará.
     *
     * @param newValue el nuevo valor a establecer (puede ser de cualquier tipo)
     * @param setter la función setter para aplicar el nuevo valor
     * @param <T> el tipo del valor
     */
    public static <T> void updateIfNotNull(T newValue, Consumer<T> setter) {
        if (newValue != null) {
            if (newValue instanceof String && ((String) newValue).trim().isEmpty()) {
                return;
            }
            setter.accept(newValue);
        }
    }

    /**
     * Actualiza el valor de un atributo si el nuevo valor es un arreglo de bytes no vacío.
     *
     * @param newValue el nuevo arreglo de bytes a establecer (no puede ser nulo ni vacío)
     * @param setter la función setter para aplicar el arreglo de bytes
     */
    public static void updateIfNotNull(byte[] newValue, Consumer<byte[]> setter) {
        if (newValue != null && newValue.length > 0) {
            setter.accept(newValue);
        }
    }

    /**
     * Actualiza el valor de un atributo si el nuevo valor es un enum no nulo.
     *
     * @param newValue el nuevo valor del enum a establecer (no puede ser nulo)
     * @param setter la función setter para aplicar el valor del enum
     * @param <E> el tipo del enum
     */
    public static <E extends Enum<E>> void updateIfNotNull(E newValue, Consumer<E> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }

}