package storage;

import java.util.ArrayList;
import java.util.Arrays;

public class Status {
	private static ArrayList<String> status = new ArrayList<String>(Arrays.asList("Aguardando Confirma��o", "Em envio", "Entregue"));
	
	public static String getStatus (int id) {
		return status.get(id);
 	}
	
}
