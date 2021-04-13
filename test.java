import PerceptronParalelo.red;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        //Rutas Easy email count vectorizer:
        String aee_ecv = "/src/SpamFilter-Dataset/CountVectorizer-EasyMailSpam/tr_ems_countvectorizer.csv";
        String ape_ecv = "/src/SpamFilter-Dataset/CountVectorizer-EasyMailSpam/te_ems_countvectorizer.csv";

        //Rutas Easy email Tfidf:
        String aee_tfidf = "/src/SpamFilter-Dataset/Tfidf-EasyMailSpam/tr_ems_tfidf.csv";
        String ape_tfidf = "/src/SpamFilter-Dataset/Tfidf-EasyMailSpam/te_ems_tfidf.csv";

        //Rutas Hard email count vectorizer:
        String aeh_evc = "/src/SpamFilter-Dataset/CountVectorizer-HardMailSpam/tr_hms_countvectorizer.csv";
        String aph_evc = "/src/SpamFilter-Dataset/CountVectorizer-HardMailSpam/te_hms_countvectorizer.csv";

        //Rutas Hard email Tfidf:
        String aeh_tfidf = "/src/SpamFilter-Dataset/Tfidf-HardMailSpam/tr_hms_tfidf.csv";
        String aph_tfidf = "/src/SpamFilter-Dataset/Tfidf-HardMailSpam/te_hms_tfidf.csv";

        //40 perceptrones para correos dificiles.
        //20 perceotrones para corres faciles.

        red R = new red(0.2,
                0.1,
                20,
                1.0,
                5,
                aee_tfidf,
                ape_tfidf);
        R.EjecutarRed();
    }
}
