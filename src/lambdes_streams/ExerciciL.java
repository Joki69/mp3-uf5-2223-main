package lambdes_streams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExerciciL {

    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyy");
        Persona p1 = new Persona("Arya", Persona.Genere.DONA, LocalDate.parse("25/12/2002",format) );
        Persona p2 = new Persona("Tyrion", Persona.Genere.HOME, LocalDate.parse("12/10/1980",format));
        Persona p3 = new Persona("Cersei", Persona.Genere.DONA, LocalDate.parse("10/01/1984",format));
        Persona p4 = new Persona("Eddard", Persona.Genere.HOME, LocalDate.parse("24/04/1974",format));
        Persona p5 = new Persona("Sansa", Persona.Genere.DONA, LocalDate.parse("24/04/1992",format));
        Persona p6 = new Persona("Jaime", Persona.Genere.HOME, LocalDate.parse("24/04/1979",format));
        Persona p7 = new Persona("Khal", Persona.Genere.HOME, LocalDate.parse("10/08/1979",format));
        Persona p8 = new Persona("Daenerys", Persona.Genere.DONA, LocalDate.parse("12/11/1992",format));
        Persona p9 = new Persona("Davos", Persona.Genere.HOME, LocalDate.parse("12/11/1965",format));
        Persona p10 = new Persona("Jon Neu", Persona.Genere.HOME, LocalDate.parse("12/11/1986",format));
        Persona p11 = new Persona("Brienne", Persona.Genere.DONA, LocalDate.parse("12/11/1989",format));

        Persona[] lpers = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11};
        List<Persona> llista_persones;
        llista_persones = new ArrayList<>(Arrays.asList(lpers));
        Map<Integer,Integer> mapPersones = new HashMap<>();

        // 1 - Canviar a lambda
        System.out.println("\n1-2");
        /*Collections.sort(llista_persones, new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                if(o1.getNom().charAt(0) >= o2.getNom().charAt(0)) return 1;
                else return -1;
            }
        });*/
        Collections.sort(llista_persones, (Persona o1, Persona o2) -> p1.getNom().compareTo(p2.getNom()));

        // 2 - Canviar a Lambda
       /* for(Persona p: llista_persones) {
            System.out.println(p);
        }*/
        List.of(lpers).forEach(p -> System.out.println(p));

        // 3 - Canvia a classe an??nima
        System.out.println("\n3-4");
        //ordenaci?? alfab??tica inversa del nom
           // llista_persones.sort((o1,o2) -> o2.getNom().compareTo(o1.getNom()));
        Collections.sort(llista_persones, new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                return o2.getNom().compareTo(o1.getNom());
            }
        });

        // 4 - Canvia per una crida al m??tode per refer??ncia
        /*for(Persona p: llista_persones) {
            System.out.println(p);
        };*/
        llista_persones.forEach(System.out::println);


        // 5 - Omplir map. Canviar per un forEach amb lambda
        /*for(Persona per : llista_persones) {
            mapPersones.put(per.getAge(),1);
        }*/
        llista_persones.forEach((Persona p) -> mapPersones.put(p.getAge(),1));

        // 6 - Canvia per un recorregut forEach amb lambda
        System.out.println("\n5");
        /*for(Map.Entry entry : mapPersones.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }*/
        mapPersones.forEach((K,V)->{System.out.println(K+" : "+V);});


        /* 7 -
            Esbrina com s'utilitzen els m??todes de map seg??ents
                map.putIfAbsent
                map.computeIfAbsent
                map.computeIfPresent

             per tal d'afegir correctament la feq????ncia d'edat de les persones.

             Si vols fes-ho primer sense els m??todes anomenats i sense lambdes
             i despr??s amb els m??todes i amb lambdes

             Exemple de sortida:
                34 anys -> 1
                38 anys -> 1
                39 anys -> 2
                26 anys -> 2
                44 anys -> 1
                15 anys -> 1

         */


        // 8 - llistat de persones DONA amb lambda (stream)
        System.out.println("8. Lista mujeres");
        llista_persones.stream().filter(persona ->persona.getGenere().equals(Persona.Genere.DONA)).forEach(System.out::println);
        // 9 - Llistat dels dos HOMES m??s joves (stream)
        System.out.println("9. Lista hombres jovenes");
        //llista_persones.stream().filter(persona ->persona.getGenere().equals(Persona.Genere.HOME) && persona.getAge()<40).forEach(persona -> imprimir(persona));

       llista_persones.stream().sorted(Comparator.comparing(Persona::getAge)).filter(persona -> persona.getGenere().equals(Persona.Genere.HOME)).limit(2).forEach(System.out::println);
        // 10- Esborrar (no filtrar o imprimir) del llistat les persones entre 30 i 40 anys (amb lambda)
        System.out.println("10");
        llista_persones.removeIf(persona ->persona.getAge()<=40 && persona.getAge()>=30);

        // 11 - Persones que tinguin una 'a' al seu nom
        System.out.println("\n11 Amb una 'A'");
        llista_persones.stream().filter(persona -> persona.getNom().contains("a") || persona.getNom().contains("A")).forEach(System.out::println);
        //12 - Llistat de les dates de naixament + dos dies
        System.out.println("\n12 - dates amb dos dies m??s");
        llista_persones.stream().map(persona -> persona.getDataNaixament().plusDays(2)).forEach(System.out::println);


        //13 - Rejovenir dos anys a totes les persones
        System.out.println("\n13 - Rejovenir dos anys a totes les persones");
        llista_persones.forEach(System.out::println);
        System.out.println("Rejuvenecen");
        llista_persones.stream().forEach(persona -> persona.setDataNaixament(persona.getDataNaixament().plusYears(2)));
         llista_persones.forEach(System.out::println);
        //llista_persones.stream().map(persona -> persona.getAge() - 2).forEach(System.out::println);

    }





}
