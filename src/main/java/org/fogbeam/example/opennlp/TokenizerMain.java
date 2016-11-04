
package org.fogbeam.example.opennlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

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
			/*
			 * note what happens with the "three depending on which model you
			 * use
			 */

			/*
			 * String[] tokens = tokenizer.tokenize (
			 * "A ranger journeying with Oglethorpe, founder of the Georgia Colony, "
			 * +
			 * " mentions \"three Mounts raised by the Indians over three of their Great Kings"
			 * + " who were killed in the Wars.\"" );
			 */
			String[] tokens = tokenizer.tokenize("hello");

			for (String token : tokens)
			{
				System.out.println(token);
			}
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
		System.out.println("\n-----\ndone");
	}

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
				nombFich[0] =args[0]+"\\"+ ficheros[x].getName();

				
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
	 * @param args, cadena de caracteres
	 * @return, retorna el string que construyó
	 */
	public static String leerDeUnFichero(String[] args)
	{
		System.err.println(args[0] + "------------------------------");
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
