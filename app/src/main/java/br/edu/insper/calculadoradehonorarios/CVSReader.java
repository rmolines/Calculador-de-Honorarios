package br.edu.insper.calculadoradehonorarios;
/**
 * Created by Rafael on 5/17/2016.
 * Comments added by Carlos on 5/22/2016.
 */
import android.content.res.AssetManager;/**Provides access 
to an application's raw asset files; see Resources for the way 
most applications will want to retrieve their resource data. 
This class presents a lower-level API that allows you to open 
and read raw files that have been bundled with the application 
as a simple stream of bytes. */

import android.util.Log; /**API for sending log output
The order in terms of verbosity, from least to most is 
ERROR, WARN, INFO, DEBUG, VERBOSE. Verbose should never be 
compiled into an application except during development. 
Debug logs are compiled in but stripped at runtime. 
Error, warning and info logs are always kept.
*/

import java.io.BufferedReader; /**Reads text from a character-input stream, 
buffering characters so as to provide for the efficient reading of characters, 
arrays, and lines.*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStream; /**This abstract class is the superclass
 of all classes representing an input stream of bytes. */

import java.io.InputStreamReader; /**An InputStreamReader is a bridge 
from byte streams to character streams: It reads bytes and decodes them 
into characters using a specified charset. The charset that it uses may 
be specified by name or may be given explicitly, or the platform's default 
charset may be accepted. */

import java.util.ArrayList;

import java.util.HashMap; /**Hash table based implementation of the Map interface.
This implementation provides all of the optional map operations, and permits null 
values and the null key. (The HashMap class is roughly equivalent to Hashtable,
except that it is unsynchronized and permits nulls.) This class makes no guarantees 
as to the order of the map; in particular, it does not guarantee that the order 
will remain constant over time. */

import java.util.Map; /**This interface takes the place of the Dictionary class, 
which was a totally abstract class rather than an interface.
The Map interface provides three collection views, which allow a map's contents 
to be viewed as a set of keys, collection of values, or set of key-value mappings. 
The order of a map is defined as the order in which the iterators on the map's 
collection views return their elements. Some map implementations, like the TreeMap 
class, make specific guarantees as to their order; others, like the HashMap class, 
do not. */



public class CVSReader {
    private ArrayList<Categoria> categorias = new ArrayList<>();

    public CVSReader (AssetManager assetManager) {
        run(assetManager);
    }

    public void run (AssetManager assetManager) {

        String csvFile = "tuss.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";

        int counter = -1;

        try {
            InputStream csvStream = assetManager.open(csvFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream, "UTF8"));
            String csvLine;
            int categoriaCounter = 0;
            int subcategoriaCounter = 0;
            int grupoCounter = 0;

            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if (row.length == 3) {
                    String nomeDaCategoria = row[2];
                    Categoria categoria = new Categoria(nomeDaCategoria, categoriaCounter);
                    Subcategoria subcategoria = new Subcategoria(nomeDaCategoria, subcategoriaCounter);
                    Grupo grupo = new Grupo(nomeDaCategoria, grupoCounter);

                    if (!categorias.isEmpty()) {
                        Categoria categoriaAtual = categorias.get(categorias.size()-1);
                        if (!categoriaAtual.retornaNomeDaCategoria().equals(categoria.retornaNomeDaCategoria())) {
                            subcategoria.criarGrupo(grupo);
                            categoria.criarSubcategoria(subcategoria);
                            categorias.add(categoria);
                        } else {
                            if (!categoriaAtual.retornaSubcategorias().isEmpty()) {
                                Subcategoria subcategoriaAtual = categoriaAtual.retornaUltimaSubcategoria();
                                if (!subcategoriaAtual.retornaNomeDaSubcategoria().equals(subcategoria.retornaNomeDaSubcategoria())) {
                                    subcategoria.criarGrupo(grupo);
                                    categoriaAtual.criarSubcategoria(subcategoria);
                                } else {
                                    subcategoriaAtual.criarGrupo(grupo);
                                }
                            }
                        }
                    } else {
                        subcategoria.criarGrupo(grupo);
                        categoria.criarSubcategoria(subcategoria);
                        categorias.add(categoria);
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error in reading CSV file: "+e);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            Log.d("ERRO", "IOB"); //Send a DEBUG log message.
        }
    }

    public ArrayList<Categoria> retornaListaDeCategorias () {
        return categorias;
    }
}
