package it.dev.cleto.spring.jpa;

/**
 * Created by Domenico Grieco <domenico.grieco@gfmintegration.it> on 12/8/17.
 */
public interface WithSpecificationSupport<E> {
    E build(Object criteriaValue);
}
