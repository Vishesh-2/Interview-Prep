import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

//import java.net.http.HttpResponse;
import java.util.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {
    static Scanner sc;
    public static void main(String[] args) throws IOException, UnirestException, InterruptedException {
        Reader.init(new FileInputStream("src/main/java/input.txt"));
        sc = new Scanner(new FileInputStream("src/main/java/input.txt"));

        int t = 1;
//        t = sc.nextInt();

        while(t-- > 0) {
            solve();
        }
    }

    static void solve() throws IOException, InterruptedException, UnirestException {
        HttpResponse<String> response = Unirest.get("https://imdb-top-100-movies.p.rapidapi.com/top15")
                .header("X-RapidAPI-Key", "a1d1519551msh0be91d8c02a5155p1bfb4ajsnf52f4f692d5b")
                .header("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com")
                .asString();

        System.out.println(response.getBody());
        Gson gson = new Gson();

        Obj o = gson.fromJson(response.getBody(), Obj.class);

        System.out.println(o.getRank());
    }

}

class Obj {
    private int rank;

    public int getRank() {
        return rank;
    }
}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextint() throws IOException {
        return Integer.parseInt( next() );
    }

    static long nextlong() throws IOException {
        return Long.parseLong( next() );
    }

    static double nextdouble() throws IOException {
        return Double.parseDouble( next() );
    }
}
