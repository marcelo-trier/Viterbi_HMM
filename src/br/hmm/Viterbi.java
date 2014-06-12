package br.hmm;

public class Viterbi {
	
	enum ESTADOS {
		N,
		C,
		E
	};
	
	
	// entradas
	int N = 0;
	int M = 0;
	int T = 0;
	int O[] = null;
	float[][] A = null;
	float[][] B = null;
	float[] PI = null;

	// respostas
	int qt = 0;
	int Q[] = null;
	float P = 0;

	public void setEstados(int i) {
		N = i;
	}

	public void setCaracteristicas(int i) {
		M = i;
	}

	public void setObservacoes(int obs[]) {
		T = obs.length;
		O = obs;
		Q = new int[O.length];
	}

	public void setMatrizTransicao(float[][] matr) {
		A = matr;
	}

	public void setMatrizProbabCaract(float[][] matr) {
		B = matr;
	}

	public void setPi(float[] arr) {
		PI = arr;
	}

	public void execute() {
		float delta[][] = new float[T][N];
		int PSI[][] = new int[T][N];

		for (int i = 0; i < N; i++) {
			delta[0][i] = PI[i] * B[i][O[0]];
			PSI[0][i] = 0;
		}

		for (int t = 1; t < T; t++) { // varia o O
			for (int j = 0; j < N; j++) {
				float max = -1;
				int argMax = -1;
				for (int i = 0; i < N; i++) {
					float aij = A[ i ][ j ];
					float result = delta[ t-1 ][ i ]*aij;
					if( result > max ) {
						max = result;
						argMax = i;
					}					
				}
				float bjot = B[ j ][ O[ t ] ];
				delta[ t ][ j ] = max * bjot;
				PSI[ t ][ j ] = argMax;
				Q[ t ] = argMax;
			}
		}

		P = -1;
		int argMax = -1;
		for( int i=0; i<N; i++ ) {
			int ultimo = T - 1;
			if( delta[ ultimo ][ i ] > P ) {
				P = delta[ ultimo ][ i ];
				argMax = i;
			}
		}
		
		Q[ T - 1 ] = argMax;
		for( int t=T-2; t>=0; t-- ) {
			Q[ t ] = PSI[ t+1 ][ Q[ t+1 ] ];
		}

	}

	public float getP() {
		return P;
	}

	public int[] getQ() {
		return Q;
	}

	public String toString() {
		String msg;

		msg = "P = " + P + "\n";
		msg += "Q = { ";
		for( int i=0; i<Q.length; i++ ) {
			msg += ESTADOS.values()[ Q[ i] ] + ", ";
		}
		msg = msg.substring(0, msg.length() - 2 );
		msg += " }; ";
		
		return msg;
	}
	
}
