package modelo.viaje;

public class SAViajeImp implements SAViaje {

	@Override
	public Integer crearViajeFuturo(TViaje tViaje) {
		// Añadir comprobaciones:
		// 1. No hay viaje con el mismo nombre (return -1)
		// 2. Las fecha de inicio del viaje no es una fecha pasada (return -2)
		// 3. La fecha de fin es futura a la fecha de inicio (return -3)
		// 4. Las fechas de este viaje no se solapan con las de otro viaje (return -4)
		//Si todo OK -> return el ID del viaje
		
		return null;
	}

	
}
