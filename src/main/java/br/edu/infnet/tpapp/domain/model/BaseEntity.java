package br.edu.infnet.tpapp.domain.model;


public abstract class BaseEntity<T>  {

    public abstract int getId();

    public abstract boolean compareTo(T other) throws Exception;

}
