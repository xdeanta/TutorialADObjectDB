/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorialobjectdb;


import javax.persistence.*;
import java.util.*;
/**
 *
 * @author xavier
 */
public class TutorialObjectDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
        EntityManager em = emf.createEntityManager();

        // Apartado 1
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();
        //Apartado 2
        TypedQuery<Point> q1 = em.createQuery("SELECT p from Point p",Point.class);
        List<Point> res = q1.getResultList();
        for(Point p : res){
            System.out.println("Point id:" + p.getId());
            System.out.println("Point Atributos:" + p);
        }
        //Apartado 3
        System.out.println("------");
        TypedQuery<Point> q2 = em.createQuery("SELECT p from Point P where id=10", Point.class);
        Point p1 = q2.getSingleResult();
        System.out.println("Punto con id="+p1.getId() +"\nAtributos:" + p1);
        //Apartado 4
        System.out.println("------");
        System.out.println("Update del point con id=10");
        em.getTransaction().begin();
        Query q3 = em.createQuery("UPDATE Point SET y = y+2 WHERE id=10");
        q3.executeUpdate();
        em.getTransaction().commit();
        //Apartado 5
        System.out.println("------");
        System.out.println("delete del point con id=5");
        em.getTransaction().begin();
        int ok = em.createQuery("DELETE from Point WHERE id=5").executeUpdate();
        em.getTransaction().commit();
        //Apartado 6
         System.out.println("------");
         System.out.println("actualizacion de los point con y < 6, modificando y al valor pasado por parametro");
        em.getTransaction().begin();
        Query up = em.createQuery("UPDATE Point SET y=:var WHERE y<6");
        up.setParameter("var", 1000);
        up.executeUpdate();
        em.getTransaction().commit();
        //Apartado 7
        System.out.println("------");
        System.out.println("Borrado de los point con x < al valor pasado por parametro");
        em.getTransaction().begin();
        Query up2 = em.createQuery("DELETE from Point WHERE x<:var");
        up2.setParameter("var", 3);
        up2.executeUpdate();
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        System.out.println("------");
        System.out.println("Consulta");
        emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
        em = emf.createEntityManager();
        q1 = em.createQuery("SELECT p from Point p",Point.class);
        res = q1.getResultList();
        for(Point p : res){
            System.out.println("Point id:" + p.getId());
            System.out.println("Point Atributos:" + p);
        }
        em.close();
        emf.close();
    }
}
    
