package com.salesianostriana.dam.damkeepapi.services

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.data.jpa.repository.JpaRepository


abstract class BaseService<T, ID, R : JpaRepository<T, ID>?> {
    @Autowired
    protected var repositorio: R? = null

    /**
     * Almacenamos una nueva entidad a través del repositorio
     *
     * @param t
     * @return
     */
    fun save(t: T): T {
        return repositorio!!.save(t)
    }

    /**
     * Localizamos una entidad en base a su Id
     *
     * @param id
     * @return
     */
    fun findById(id: ID): T {
        return repositorio!!.findById(id).orElse(null)
    }

    /**
     * Obtenemos todas las entidades de un tipo de entidad
     *
     * @return
     */
    fun findAll(): List<T> {
        return repositorio!!.findAll()
    }

    /**
     * Editamos una instancia de una entidad (si no tiene Id, la insertamos).
     *
     * @param t
     * @return
     */
    fun edit(t: T): T {
        return repositorio!!.save(t)
    }

    /**
     * Eliminamos una instancia de una entidad
     *
     * @param t
     */
    fun delete(t: T) {
        repositorio!!.delete(t)
    }

    /**
     * Eliminamos una instancia en base a su ID
     *
     * @param id
     */
    fun deleteById(id: ID) {
        repositorio!!.deleteById(id)
    }
}