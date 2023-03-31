import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercitiul1 {
    public static void main(String[] args) {

        String sayMyName = "Dragos";
        List<String> list = filterWords(sayMyName);

        list.forEach(System.out::println);

    }
    static List<String> filterWords(String name){
        List<String> list=new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get("C://Users//Admin//Desktop//DB_Cloud_School//Homework_29_03//Exercitiul1.txt"))) {
            list = stream
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    // Avand in vedere ca textul preluat contine si semne de punctuatie si faptul ca filtrarea de mai sus
                    // le preia cu tot cu acestea, am mai facut niste filtrari suplimentare mai jos
                    .map(word->{
                        return word.replace(",","");
                    })
                    .map(word->{
                        return word.replace(".","");
                    })
                    .map(word->{
                        return word.replace("(","");
                    })
                    .map(word->{
                        return word.replace(")","");
                    })
                    .map(word->{
                        return word.replace(".","");
                    })
                    .filter(string -> string.length() <= name.length())
                    .map(String::toLowerCase)
                    .distinct()
                    .sorted()
                    //.filter(string->string.length()== name.length())
                    .collect(Collectors.toList());
            //Dat fiind faptul ca am urmarit fiecare mapare si filtrare inainte de ultimul subpunct al cerintei, am observat ca exista mai multe
            //cuvinte care au lungimea maxima ( cea a prenumelui meu). Asa ca am decis ca in ultima filtrare sa salvez doar
            //cuvintele care au lungimea prenumelui meu
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}