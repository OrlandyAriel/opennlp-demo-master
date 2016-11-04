
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
	public static void main( String[] args ) throws Exception
	{
		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
			Tokenizer tokenizer = new TokenizerME(model);
				/* note what happens with the "three depending on which model you use */
			
		/*	String[] tokens = tokenizer.tokenize
					(  "A ranger journeying with Oglethorpe, founder of the Georgia Colony, " 
							+ " mentions \"three Mounts raised by the Indians over three of their Great Kings" 
							+ " who were killed in the Wars.\"" );*/
			String[] tokens = tokenizer.tokenize(leerDeUnFichero(args));
			
			for( String token : tokens )
			{
				System.out.println( token );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
		}
		System.out.println( "\n-----\ndone" );
	}
	public static String leerDeUnFichero(String[] args)
	{
		String fichero = args[0];
		File a = new File(fichero);
		FileReader fileLee;
		String texto_token ="";
		try
		{
			fileLee = new FileReader(a);
			BufferedReader fileread = new BufferedReader(fileLee);
			
			while (fileread.ready())
			{
				texto_token+= fileread.readLine();
				texto_token+="\n";
			}
			fileread.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return texto_token;
	}
}
