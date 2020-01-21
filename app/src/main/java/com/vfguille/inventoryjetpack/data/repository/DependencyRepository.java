package com.vfguille.inventoryjetpack.data.repository;

import com.vfguille.inventoryjetpack.data.model.Dependency;

import java.util.ArrayList;
import java.util.Iterator;

public class DependencyRepository {
    private static DependencyRepository dependencyRepository;
    private ArrayList<Dependency> list;

    // Constructor privado porque sólo existe un objeto Repository.
    private DependencyRepository() {
        list = new ArrayList<>();
        initialize();
    }

    public static DependencyRepository getInstance() {
        if (dependencyRepository == null)
            dependencyRepository = new DependencyRepository();
        return dependencyRepository;
    }

    private void initialize() {
        list.add(new Dependency("2º Ciclo Formativo Grado Superior", "2CFGS", "Aula informática", "", "https://images.squarespace-cdn.com/content/v1/549f45d6e4b037c1971053fd/1429331411211-EJKOTLKYFMKLFNDCX8IE/ke17ZwdGBToddI8pDm48kAt72yGFwHZjoxtmj75n0VMUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dvp1wM0jvciobd5mvjBb-PkjbbxSYDSdt-BIyUswy_5eG6v6ULRah83RgHXAWD5lbQ/image-asset.jpeg?format=750w"));
        /*list.add(new Dependency("1º Ciclo Formativo Grado Superior", "1CFGS", "Aula informática", "", "https://images.squarespace-cdn.com/content/v1/549f45d6e4b037c1971053fd/1429331411211-EJKOTLKYFMKLFNDCX8IE/ke17ZwdGBToddI8pDm48kAt72yGFwHZjoxtmj75n0VMUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dvp1wM0jvciobd5mvjBb-PkjbbxSYDSdt-BIyUswy_5eG6v6ULRah83RgHXAWD5lbQ/image-asset.jpeg?format=750w"));
        list.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGM", "Aula informática", "", "https://images.squarespace-cdn.com/content/v1/549f45d6e4b037c1971053fd/1429331411211-EJKOTLKYFMKLFNDCX8IE/ke17ZwdGBToddI8pDm48kAt72yGFwHZjoxtmj75n0VMUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dvp1wM0jvciobd5mvjBb-PkjbbxSYDSdt-BIyUswy_5eG6v6ULRah83RgHXAWD5lbQ/image-asset.jpeg?format=750w"));
        list.add(new Dependency("2º Ciclo Formativo Grado Medio", "2CFGM", "Aula informática", "", "https://images.squarespace-cdn.com/content/v1/549f45d6e4b037c1971053fd/1429331411211-EJKOTLKYFMKLFNDCX8IE/ke17ZwdGBToddI8pDm48kAt72yGFwHZjoxtmj75n0VMUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dvp1wM0jvciobd5mvjBb-PkjbbxSYDSdt-BIyUswy_5eG6v6ULRah83RgHXAWD5lbQ/image-asset.jpeg?format=750w"));
        list.add(new Dependency("4º ESO", "4ESO", "Aula informática", "", "https://images.squarespace-cdn.com/content/v1/549f45d6e4b037c1971053fd/1429331411211-EJKOTLKYFMKLFNDCX8IE/ke17ZwdGBToddI8pDm48kAt72yGFwHZjoxtmj75n0VMUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dvp1wM0jvciobd5mvjBb-PkjbbxSYDSdt-BIyUswy_5eG6v6ULRah83RgHXAWD5lbQ/image-asset.jpeg?format=750w"));
        list.add(new Dependency("3º ESO", "3ESO", "Aula informática", "", "https://images.squarespace-cdn.com/content/v1/549f45d6e4b037c1971053fd/1429331411211-EJKOTLKYFMKLFNDCX8IE/ke17ZwdGBToddI8pDm48kAt72yGFwHZjoxtmj75n0VMUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dvp1wM0jvciobd5mvjBb-PkjbbxSYDSdt-BIyUswy_5eG6v6ULRah83RgHXAWD5lbQ/image-asset.jpeg?format=750w"));
        list.add(new Dependency("2º ESO", "2ESO", "Aula informática", "", "https://images.squarespace-cdn.com/content/v1/549f45d6e4b037c1971053fd/1429331411211-EJKOTLKYFMKLFNDCX8IE/ke17ZwdGBToddI8pDm48kAt72yGFwHZjoxtmj75n0VMUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dvp1wM0jvciobd5mvjBb-PkjbbxSYDSdt-BIyUswy_5eG6v6ULRah83RgHXAWD5lbQ/image-asset.jpeg?format=750w"));
        */
        list.add(new Dependency("1º ESO", "1ESO", "Aula informática", "", "https://images.squarespace-cdn.com/content/v1/549f45d6e4b037c1971053fd/1429331411211-EJKOTLKYFMKLFNDCX8IE/ke17ZwdGBToddI8pDm48kAt72yGFwHZjoxtmj75n0VMUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2dvp1wM0jvciobd5mvjBb-PkjbbxSYDSdt-BIyUswy_5eG6v6ULRah83RgHXAWD5lbQ/image-asset.jpeg?format=750w"));
    }

    public ArrayList<Dependency> getList() {
        return this.list;
    }

    public boolean add(Dependency dependency) {
        if (!list.contains(dependency)){
            list.add(dependency);
            return true;
        }else
            return false;
    }

    public boolean edit(Dependency dependency) {
        try {
            for (Dependency dependencyIt : list) {
                if (dependencyIt.getShortName().equals(dependency.getShortName())) {
                    dependencyIt.setName(dependency.getName());
                    dependencyIt.setDescription(dependency.getDescription());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Dependency dependency){
        Iterator<Dependency> dependencyIterator = list.iterator();
        while (dependencyIterator.hasNext()){
            if (dependencyIterator.next().equals(dependency)) {
                dependencyIterator.remove();
                return true;
            }
        }
        return false;
    }
}