package com.vfguille.inventoryjetpack.ui.base;

/**
 * Interfaz base para todas las vistas del proyecto.
 * @param <T>
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
}