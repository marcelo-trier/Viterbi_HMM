package br.hmm.test;

import br.hmm.Viterbi;

public class Test1 {

	// matriz de probabilidade de transião de estados	
	static float A[][] = {
			{ 0.5f, 0.3f, 0.2f }, // N
			{ 0.3f, 0.4f, 0.3f }, // E
			{ 0.3f, 0.2f, 0.5f }, // C
	};

	// matriz de probabilidade dos símbolos por estado
	static float B[][] = {
			{ 0.2f, 0.2f, 0.4f, 0.2f }, // N
			{ 0.5f, 0.3f, 0.1f, 0.1f }, // E
			{ 0.1f, 0.1f, 0.3f, 0.5f }, // C
	};
	
	static float PI[] = { 0.5f, 0.3f, 0.2f };

	// UMIDADE RELATIVA DO AR ==>> UA
	/*
	 * 1	--> 	UA	<=	0.25
	 * 2	-->		0.26 <= 0.50
	 * 3	--> 	0.51 <= 0.75
	 * 4	-->		0.76 <= 1.00
	 */

	enum UA {
		UM(0.25f), DOIS(0.50f), TRES(0.75f), QUATRO(1f);
		float valor;
		UA( float v ) {
			valor = v;
		}
	}
	
	static int observacoes[] = { 4, 4, 4, 3, 4, 3, 3, 3, 2, 2, 1 };
	//static int observacoes[] = { 1, 1, 1, 3, 3, 2, 1, 4, 4, 4, 1, 2, 3 };
	
	public static void main(String[] args) {
		Viterbi v = new Viterbi();
		
		for( int i=0; i<observacoes.length; i++ ) {
			observacoes[ i ] = observacoes[ i ] - 1;
		}
		
		v.setEstados( 3 ); // N
		v.setCaracteristicas( 4 ); // M
		v.setObservacoes( observacoes );
//		v.setTamanhoObservacao( observacoes.length );
		v.setMatrizTransicao( A );
		v.setMatrizProbabCaract( B );
		v.setPi( PI );
		v.execute();
		
		System.out.println( v.toString() );
		
/*
		float P = v.getP();
		int[] umQ = v.getQ();

		System.out.println( "P = " + P );
		System.out.print( "Q = { " );
		for( int i=0; i<umQ.length; i++ ) {
			System.out.print( "" + umQ[ i] + ", " );
		}
		System.out.println( " }; " );
*/
		
	}
}
