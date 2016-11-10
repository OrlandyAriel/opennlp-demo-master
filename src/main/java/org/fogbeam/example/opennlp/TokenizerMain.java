
package org.fogbeam.example.opennlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
/**
 * 
 * \author Orlandy Ariel Sánchez Acosta.
 * \brief clase estatica que dado un fichero de texto separa en token.
 *
 */
public class TokenizerMain
{
	public static void main(String[] args) throws Exception
	{
		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );
		// the model we trained
		InputStream modelIn = new FileInputStream("models/en-token.model");
		try
		{
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);
			
			leerDeUndirectorio(args,tokenizer);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (modelIn != null)
			{
				try
				{
					modelIn.close();
				} catch (IOException e)
				{
				}
			}
		}
	}
	/**
	 * \brief Método que dado un directorio es capaz de listar los ficheros que tiene 
	 * Lista todos los ficheros que contiene este directorio de manera que luego se puedan analizar 
	 * por separado y se puedan separar en token's
	 * \param args: nombre del directorio que se desea leer
	 * \param tokenizer: utilizado para separar los token's
	 * \return: retorna un array de string's
	 * \throws IOException
	 */
	public static String[] leerDeUndirectorio(String[] args, Tokenizer tokenizer) throws IOException
	{
		String[] listadoFicheros = null;
		String[] nombFich = new String[1];
		File f = new File(args[0]);
		if (f.exists())
		{
			File[] ficheros = f.listFiles();
			for (int x = 0; x < ficheros.length; x++)
			{
				nombFich[0] =args[0]+"\\"+ ficheros[x].getName();/// construye la ruta del fichero a leer

				
				String[] tokens = tokenizer.tokenize(leerDeUnFichero(nombFich));

				for (String token : tokens)
				{
					System.out.println(token);
				}
			}
		} else
			System.out.println("no existe el directorio");
		return listadoFicheros;
	}
	/**
	 * \brief  Lee un fichero y construye un string con el contenido
	 * \param args, cadena de caracteres
	 * \return, retorna el string que construyó
	 */
	public static String leerDeUnFichero(String[] args)
	{
		String fichero = args[0];
		File a = new File(fichero);
		FileReader fileLee;
		String texto_token = "";
		try
		{
			fileLee = new FileReader(a);
			BufferedReader fileread = new BufferedReader(fileLee);

			while (fileread.ready())
			{
				texto_token += fileread.readLine();
				texto_token += "\n";
			}
			fileread.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return texto_token;
	}
}
