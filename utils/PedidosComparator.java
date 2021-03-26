package utils;

import java.util.Comparator;

import ecommerce.Pedido;

public class PedidosComparator implements Comparator<Pedido> {
	@Override
	public int compare(Pedido o1, Pedido o2) {
		int number1 = o1.getNumber();
		int number2 = o2.getNumber();
		
		if (number1 == number2)
            return 0;
        else if (number1 > number2)
            return 1;
        else
            return -1;
	}
}
