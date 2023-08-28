package org.example;

import org.example.entities.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Demo1 {
    public static void main(String[] args) {
        // creation d'un register pour charger la config à partir de notre fichier de config

        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();

        // création de la session
        Session session = sessionFactory.openSession();

        // dés l'ouverture de la session , et en fonction de la propriéte hibernate.hbm2ddl.auto.hibernate va agir sur
         session.getTransaction().begin();
        Personne pe = new Personne();
        pe.setNom("toto");
        pe.setPrenom("tata");
        session.save(pe);
        System.out.println("ID personne "+ pe.getId());
        session.getTransaction().commit();

        //Fermeture de la session et la sessionfactory
        session.close();
        sessionFactory.close();

        // Récuperer une personne

        session.getTransaction().begin();
        Personne p = session.load(Personne.class,1L);
        System.out.println(p.getNom());

        // Modification

        p.setPrenom("titi");
        session.update(p);

        // supression :

        session.delete(p);

        session.getTransaction().commit();

        // fermuture de la sessionFactory

        session.close();
        sessionFactory.close();



    }
}
