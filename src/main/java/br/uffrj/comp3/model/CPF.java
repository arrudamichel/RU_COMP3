package br.uffrj.comp3.model;

public class CPF {
	private String cpf;

	public CPF(String cpf) {
		super();
		if (valida(cpf))
			this.cpf = cpf;
		else
			throw new UnsupportedOperationException();
	}

	private boolean valida(String s) {
		int[] a = new int[9];
		char[] cpf = s.toCharArray();
        for (int i = 0; i < cpf.length ; i++) {
            if (Character.isDigit(cpf[i]))
                a[i] = cpf[i] - '0';
        }
     
        int b1 = 0, b2 = 0;

        for (int i = 1; i < 10; i++) {
            b1 += i * a[i - 1];
		    b2 += (10 - i) * a[i - 1];
        }

        b1 = getDig(b1);
        b2 = getDig(b2);
     
        return (b1 == a[9] && b2 == a[10]); 
	}

	private int getDig(int b) {
		return (b % 11 == 10) ? 0 : b % 11;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
